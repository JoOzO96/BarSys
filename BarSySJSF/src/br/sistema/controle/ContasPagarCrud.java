package br.sistema.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.ContasPagar;
import br.sistema.beans.ContasPagarParcela;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class ContasPagarCrud {

	private List<ContasPagar> lista;
	private ContasPagar objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from ContasPagar").getResultList();
		em.close();
	}

	public void enderecoContasPagar() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from ContasPagar").getResultList();
		em.close();
	}
	
	public String incluir() {
		objeto = new ContasPagar();
		return "ContasPagarForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "ContasPagarList?faces-redirect=true";
	}

	public String cancelar() {
		return "ContasPagarList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(ContasPagar.class, id);
		em.close();
		return "ContasPagarForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(ContasPagar.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "ContasPagarList?faces-redirect=true";
	}

	public ContasPagarCrud() {
		super();
	}

	public List<ContasPagar> getLista() {
		return lista;
	}

	public void setLista(List<ContasPagar> lista) {
		this.lista = lista;
	}

	public ContasPagar getObjeto() {
		return objeto;
	}

	public void setObjeto(ContasPagar objeto) {
		this.objeto = objeto;
	}

	
	
	
	
	// --------------------------------------------------------
	// Para os itens
	// --------------------------------------------------------
	
	
	
	
	private ContasPagarParcela parcelas; // item em edição, vinculado ao formulário

	private Integer rowIndex = null; // índice do item selecionado - alterar e
										// excluir

	public void incluirParcelas() {
		rowIndex = null;
		parcelas = new ContasPagarParcela();
	}

	public void alterarParcelas(Integer rowIndex) {
		this.rowIndex = rowIndex;
		parcelas = objeto.getItensContasPagarParcela().get(rowIndex); // pega item da
															// coleção
		calculaValorPago();
	}

	public void excluirParcelas(Integer rowIndex) {
		objeto.getItensContasPagarParcela().remove(rowIndex.intValue()); // exclui item
		calculaValorPago();		
	}

	public void gravarParcelas() {
		if (this.rowIndex == null) {
			parcelas.setContasPagar(objeto);
			objeto.getItensContasPagarParcela().add(parcelas); // adiciona item na coleção
		} else {
			objeto.getItensContasPagarParcela().set(rowIndex, parcelas); // altera na
																// coleção
		}
		rowIndex = null;
		parcelas = null;
		calculaValorPago();
	}

	public void cancelarParcelas() {
		rowIndex = null;
		parcelas = null;
	}
	
	public void calculaValorPago() {
		Float valorPago = 0.0F;
		for (ContasPagarParcela it : objeto.getItensContasPagarParcela())
			valorPago += it.getValor();
		System.out.println(valorPago);
		objeto.setValorPago(valorPago);
	}

	public ContasPagarParcela getParcelas() {
		return parcelas;
	}

	public void setParcelas(ContasPagarParcela parcelas) {
		this.parcelas = parcelas;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}


}
