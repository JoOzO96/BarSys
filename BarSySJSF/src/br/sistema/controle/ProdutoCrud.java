package br.sistema.controle;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.MateriaPrima;
import br.sistema.beans.Produto;
import br.sistema.beans.ProdutoComposicao;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class ProdutoCrud {

	private List<Produto> lista;
	private Produto objeto;
	private Boolean desativa = false;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Produto").getResultList();
		em.close();
	}

	public List<Produto> completeProduto(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		 List<Produto> results = em.createQuery(
		 "from Produto where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		 "order by nome").getResultList();
		 em.close();
		 return results;
		}
	
	public List<MateriaPrima> completeMateriaPrima(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		 List<MateriaPrima> results = em.createQuery(
		 "from MateriaPrima where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		 "order by nome").getResultList();
		 em.close();
		 return results;
		}
	
	public String incluir() {
		objeto = new Produto();
		desabilita();
		return "ProdutoForm?faces-redirect=true";
	}
	
	public void desabilita(){
		if (desativa){
			desativa=false;
			objeto.setListacozinha(true);
		}else{
			desativa=true;
			objeto.setListacozinha(false);
		}
		
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "ProdutoList?faces-redirect=true";
	}

	public String cancelar() {
		return "ProdutoList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Produto.class, id);
		em.close();
		desabilita();
		return "ProdutoForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Produto.class, id);
		 em.getTransaction().begin();
		 em.remove(objeto);
		 em.getTransaction().commit();
		em.close();
		 return "ProdutoList?faces-redirect=true";
		}
	
	public ProdutoCrud() {
		super();
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public Produto getObjeto() {
		return objeto;
	}

	public void setObjeto(Produto objeto) {
		this.objeto = objeto;
	}
	
	
	
	private ProdutoComposicao produtoComposicao; // item em edição, vinculado ao formulário

	private Integer rowIndex = null; // índice do item selecionado - alterar e
										// excluir

	public void incluirProdutoComposicao() {
		rowIndex = null;
		produtoComposicao = new ProdutoComposicao();
		calculaCusto();
	}

	public void alterarProdutoComposicao(Integer rowIndex) {
		this.rowIndex = rowIndex;
		produtoComposicao = objeto.getProdutoComposicao().get(rowIndex); // pega item da
															// coleção
		calculaCusto();
	}

	public void excluirProdutoComposicao(Integer rowIndex) {
		objeto.getProdutoComposicao().remove(rowIndex.intValue()); // exclui item
		calculaCusto();
	}

	public void gravarProdutoComposicao() {
		if (this.rowIndex == null) {
			produtoComposicao.setProduto(objeto);
			objeto.getProdutoComposicao().add(produtoComposicao); // adiciona item na coleção
		} else {
			objeto.getProdutoComposicao().set(rowIndex, produtoComposicao); // altera na
																// coleção
		}
		rowIndex = null;
		produtoComposicao = null;
		calculaCusto();
	}
	
	public void calculaCusto(){
		Float valorCusto = 0F;
		for (ProdutoComposicao it : objeto.getProdutoComposicao()){
			valorCusto += it.getMateriaPrima().getValorUltimaCompra() * it.getQuantidade();
		}
		objeto.setValorCusto(valorCusto);
	}

	public void cancelarProdutoComposicao() {
		rowIndex = null;
		produtoComposicao = null;
	}

	public ProdutoComposicao getProdutoComposicao() {
		return produtoComposicao;
	}

	public void seProdutoComposicao(ProdutoComposicao produtoComposicao) {
		this.produtoComposicao = produtoComposicao;
	}

	public Boolean getDesativa() {
		return desativa;
	}

	public void setDesativa(Boolean desativa) {
		this.desativa = desativa;
	}

	public void setProdutoComposicao(ProdutoComposicao produtoComposicao) {
		this.produtoComposicao = produtoComposicao;
	}
	

}
