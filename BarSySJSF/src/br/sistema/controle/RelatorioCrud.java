package br.sistema.controle;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.sistema.beans.Cliente;
import br.sistema.uteis.FabricaConexao;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean
@RequestScoped
public class RelatorioCrud {

	public String[] listaRelatorio = { "Materia Prima", "Pedido Cliente", "Cidade" };
	public String caminho;
	public String selecionado = "Pedido Cliente";
	public Cliente cliente;
	public Boolean render = true;
	public Long codCli;

	public void select() {
		List<Cliente> temp = completeCliente("");
		if (selecionado.equals("Pedido Cliente")) {
			render = true;
		} else {
			render = false;
		}
	}

	public void cam(String select) {
		if (select.equals("Materia Prima")) {
			caminho = "MateriaPrima/MateriaPrima.jasper";
		}
		if (select.equals("Pedido Cliente")) {
			caminho = "PedidoCliente/PedidosCliente.jasper";
		}
		if (select.equals("Cidade")) {
			caminho = "Cidade/Cidade.jasper";
		}
	}

	public List<Cliente> completeCliente(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<Cliente> results = em
				.createNativeQuery(
						"SELECT cliente.* from Cliente inner join pedido on pedido.cliente_codcliente = cliente.codcliente where upper(nome) like "
								+ "'" + query.trim().toUpperCase() + "%'  " + "order by nome",
						Cliente.class)
				.getResultList();
		Set<Cliente> setCliente = new LinkedHashSet<Cliente>(results);
		results.clear();
		results.addAll(setCliente);
		em.close();
		return results;
	}

	public void emitir() {
		try {
			cam(selecionado);
			if (cliente != null){
				pegacod(cliente);
			}
			if (codCli != null) {
				HashMap parameters = new HashMap();
				parameters.put("codcli", codCli);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.responseComplete();
				ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
				Connection con = FabricaConexao.getEntityManagerJDBCConnection();
				System.out.println(scontext.getRealPath("/WEB-INF/Relatorios/" + caminho));
				JasperPrint jasperPrint = JasperFillManager
						.fillReport(scontext.getRealPath("/WEB-INF/Relatorios/" + caminho), parameters, con);
				byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
				HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
						.getResponse();
				res.setContentType("application/pdf");
				res.setHeader("Content-disposition", "inline;filename=arquivo.pdf"); // diretamente
																						// na
																						// página
				// res.setHeader("Content-disposition",
				// "attachment;filename=arquivo.pdf");// baixar ou salvar
				res.getOutputStream().write(b);
				res.getCharacterEncoding();
				FacesContext.getCurrentInstance().responseComplete();
			} else {
				HashMap parameters = new HashMap();
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.responseComplete();
				ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
				Connection con = FabricaConexao.getEntityManagerJDBCConnection();
				JasperPrint jasperPrint = JasperFillManager
						.fillReport(scontext.getRealPath("/WEB-INF/Relatorios/" + caminho), parameters, con);
				byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
				HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
						.getResponse();
				res.setContentType("application/pdf");
				res.setHeader("Content-disposition", "inline;filename=arquivo.pdf"); // diretamente
																						// na
																						// página
				// res.setHeader("Content-disposition",
				// "attachment;filename=arquivo.pdf");// baixar ou salvar
				res.getOutputStream().write(b);
				res.getCharacterEncoding();
				FacesContext.getCurrentInstance().responseComplete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void pegacod(Cliente cliente2) {
		codCli = cliente2.getCodCliente();
	}

	public String[] getListaRelatorio() {
		return listaRelatorio;
	}

	public void setListaRelatorio(String[] listaRelatorio) {
		this.listaRelatorio = listaRelatorio;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(String selecionado) {
		this.selecionado = selecionado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Boolean getRender() {
		return render;
	}

	public void setRender(Boolean render) {
		this.render = render;
	}

}
