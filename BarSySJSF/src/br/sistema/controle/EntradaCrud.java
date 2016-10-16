package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.sistema.beans.Cidade;
import br.sistema.beans.ContasPagarParcela;
import br.sistema.beans.Entrada;
import br.sistema.beans.EntradaItem;
import br.sistema.beans.Fornecedor;
import br.sistema.beans.MateriaPrima;
import br.sistema.uteis.FabricaConexao;
@ManagedBean
@SessionScoped
public class EntradaCrud {

	private List<Entrada> lista;
	private Entrada objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Entrada").getResultList();
		em.close();
	}
	
	public List<Fornecedor> completeFornecedor(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<Fornecedor> results = em.createQuery("from Fornecedor where upper(nome) like " + "'"
				+ query.trim().toUpperCase() + "%' " + "order by nome").getResultList();
		em.close();
		return results;
	}
	
	public List<MateriaPrima> completeMateriaPrima(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<MateriaPrima> results = em.createQuery("from MateriaPrima where upper(nome) like " + "'"
				+ query.trim().toUpperCase() + "%' " + "order by nome").getResultList();
		em.close();
		return results;
	}

	public void enderecoEntrada() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Entrada").getResultList();
		em.close();
	}
	
	
	public String incluir() {
		objeto = new Entrada();
		return "EntradaForm?faces-redirect=true";
	}

	public String gravar() {
		try {
			EntityManager em = FabricaConexao.getEntityManager();
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
			em.close();
			return "EntradaList?faces-redirect=true";
		} catch (Exception e) {
			String mens = "";
			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			mens = e.getCause().toString();
			if (mens.contains("1")){
				mensagem.setSummary("A entrada precisa conter no minimo uma materia prima");
			}else{
				mensagem.setSummary ("Erro ao cadastrar o fornecedor");
			}
			FacesContext.getCurrentInstance().addMessage("", mensagem);
		}
		return "";
	}

	public String cancelar() {
		return "EntradaList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Entrada.class, id);
		em.close();
		return "EntradaForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Entrada.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "EntradaList?faces-redirect=true";
	}

	public EntradaCrud() {
		super();
	}

	public List<Entrada> getLista() {
		return lista;
	}

	public void setLista(List<Entrada> lista) {
		this.lista = lista;
	}

	public Entrada getObjeto() {
		return objeto;
	}

	public void setObjeto(Entrada objeto) {
		this.objeto = objeto;
	}

	
	
	
	
	// --------------------------------------------------------
	// Para as contas a pagar
	// --------------------------------------------------------
	
	
	
	
	private EntradaItem entradaItem; // item em edição, vinculado ao formulário

	private Integer rowIndex = null; // índice do item selecionado - alterar e
										// excluir

	public void incluirItensEntrada() {
		rowIndex = null;
		entradaItem = new EntradaItem();
	}

	public void alterarItensEntrada(Integer rowIndex) {
		this.rowIndex = rowIndex;
		entradaItem = objeto.getItensEntrada().get(rowIndex); // pega item da
															// coleção
	}

	public void excluirItensEntrada(Integer rowIndex) {
		objeto.getItensEntrada().remove(rowIndex.intValue()); // exclui item
	}

	public void gravarItensEntrada() {
		if (this.rowIndex == null) {
			entradaItem.setEntrada(objeto);
			objeto.getItensEntrada().add(entradaItem); // adiciona item na coleção
		} else {
			objeto.getItensEntrada().set(rowIndex, entradaItem); // altera na
																// coleção
		}
		rowIndex = null;
		entradaItem = null;
	}

	public void cancelarItensEntrada() {
		rowIndex = null;
		entradaItem = null;
	}

	public EntradaItem getEntradaItem() {
		return entradaItem;
	}

	public void setEntradaItem(EntradaItem entradaItem) {
		this.entradaItem = entradaItem;
	}
	
	
	public void calculaValorPago() {
		Float custoTotal = 0.0F;
		for (EntradaItem it : objeto.getItensEntrada())
			custoTotal += it.getCustoTotal();
		entradaItem.setCustoTotal(custoTotal);
	}
	

}
