package br.sistema.controle;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.Cliente;
import br.sistema.beans.MateriaPrima;
import br.sistema.beans.Pedido;
import br.sistema.beans.PedidoProduto;
import br.sistema.beans.Produto;
import br.sistema.beans.ProdutoComposicao;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class PedidoCrud {

	private List<Pedido> lista;
	private Pedido objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		
		lista = em.createQuery("from Pedido").getResultList();
		em.close();
	}

	public List<Cliente> completeCliente(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		 List<Cliente> results = em.createQuery(
		 "from Cliente where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		 "order by nome").getResultList();
		 em.close();
		 return results;
		}
	
	public String incluir() {
		objeto = new Pedido();
		return "ProdutoForm?faces-redirect=true";
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
		objeto = em.find(Pedido.class, id);
		em.close();
		return "ProdutoForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Pedido.class, id);
		 em.getTransaction().begin();
		 em.remove(objeto);
		 em.getTransaction().commit();
		em.close();
		 return "ProdutoList?faces-redirect=true";
		}
	
	public PedidoCrud() {
		super();
	}

	public List<Pedido> getLista() {
		return lista;
	}

	public void setLista(List<Pedido> lista) {
		this.lista = lista;
	}

	public Pedido getObjeto() {
		return objeto;
	}

	public void setObjeto(Pedido objeto) {
		this.objeto = objeto;
	}
	
	
	
	private PedidoProduto itensPedido; // item em edição, vinculado ao formulário

	private Integer rowIndex = null; // índice do item selecionado - alterar e
										// excluir

	public void incluirProdutoComposicao() {
		rowIndex = null;
		itensPedido = new PedidoProduto();
	}

	public void alterarProdutoComposicao(Integer rowIndex) {
		this.rowIndex = rowIndex;
		itensPedido = objeto.getItensPedido().get(rowIndex); // pega item da
															// coleção
	}

	public void excluirProdutoComposicao(Integer rowIndex) {
		objeto.getItensPedido().remove(rowIndex.intValue()); // exclui item
	}

	public void gravarProdutoComposicao() {
		if (this.rowIndex == null) {
			itensPedido.setPedido(objeto);
			objeto.getItensPedido().add(itensPedido); // adiciona item na coleção
		} else {
			objeto.getItensPedido().set(rowIndex, itensPedido); // altera na
																// coleção
		}
		rowIndex = null;
		itensPedido = null;
	}

	public void cancelarProdutoComposicao() {
		rowIndex = null;
		itensPedido = null;
	}

}
