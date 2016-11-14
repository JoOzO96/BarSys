package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.postgresql.util.PSQLException;

import br.sistema.beans.MateriaPrima;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class MateriaPrimaCrud {

	private List<MateriaPrima> lista;
	private MateriaPrima objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();

		lista = em.createQuery("from MateriaPrima").getResultList();
		em.close();
	}

	public String incluir() {
		objeto = new MateriaPrima();
		return "MateriaPrimaForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "MateriaPrimaList?faces-redirect=true";
	}

	public String cancelar() {
		return "MateriaPrimaList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(MateriaPrima.class, id);
		em.close();
		return "MateriaPrimaForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		try {
			EntityManager em = FabricaConexao.getEntityManager();
			objeto = em.find(MateriaPrima.class, id);
			em.getTransaction().begin();
			em.remove(objeto);
			em.getTransaction().commit();
			em.close();
			return "MateriaPrimaList?faces-redirect=true";
		} catch (Exception e) {
			String mens = "";
			FacesMessage mensagem = new FacesMessage();
			Throwable err = e.getCause();

			while (err != null) {
				if (err instanceof PSQLException) {
					mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					PSQLException pe = (PSQLException) err;
					String erro = pe.toString();
					if (erro.contains("entradaitem")) {
						mensagem.setSummary(
								"Nao é possivel excluir a Materia Prima, pois ela esta vinculada a uma Entrada");
					} else if (erro.contains("produtocomposicao")) {
						mensagem.setSummary(
								"Nao é possivel excluir a Materia Prima, pois ela esta vinculada a uma Composição de Produtos");
					} else {
						mensagem.setSummary(
								"Nao é possivel excluir a Materia Prima, pois ela esta vinculada a uma Composição de Produtos");
					}
				}
				err = err.getCause();
			}
			// mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			// mens = e.getCause().toString();
			// if (mens.contains("entradaitem")){
			// mensagem.setSummary("Não é possivel deletar esta Materia Prima,
			// pois ela possui referencias em alguma entrada");
			// }else if (mens.contains("produtocomposicao")){
			// mensagem.setSummary ("Não é possivel delestar esta Materia Prima,
			// pois ela possui refencias na tabela Produto Composicao");
			// }else{
			// mensagem.setSummary("Erro ao deletar a Materia Prima");
			// }
			FacesContext.getCurrentInstance().addMessage("", mensagem);
		}
		return "";
	}

	public MateriaPrimaCrud() {
		super();
	}

	public List<MateriaPrima> getLista() {
		return lista;
	}

	public void setLista(List<MateriaPrima> lista) {
		this.lista = lista;
	}

	public MateriaPrima getObjeto() {
		return objeto;
	}

	public void setObjeto(MateriaPrima objeto) {
		this.objeto = objeto;
	}

}
