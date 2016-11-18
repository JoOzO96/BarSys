package br.sistema.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.Pedido;
import br.sistema.beans.PedidoProduto;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class ListaPedidoFinaliza {

	private List<Pedido> lista;
	private List<PedidoProduto> produtos;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createNativeQuery("SELECT pedido.* FROM PEDIDO where situacao_codsituacao = 4", Pedido.class).getResultList();
		em.close();
	}

	public void finaliza(Long codpedido){
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("UPDATE Pedido set situacao_codsituacao = 5 where codpedido = ?").setParameter(1, codpedido).executeUpdate();
		em.getTransaction().commit(); 
		em.close();
		inicializarLista();
	}
	
	public String abrirItens(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		produtos = (List<PedidoProduto>) em.createNativeQuery("SELECT * FROM PedidoProduto inner join pedido on pedido.codpedido = pedidoproduto.pedido_codpedido where pedido_codPedido = "+id+" and itementregue = true and pedido.situacao_codsituacao = 4",PedidoProduto.class).getResultList();
		em.close();
		return "ListaPedidoEntregaForm?faces-redirect=true";
	}
	
	public ListaPedidoFinaliza() {
		super();
	}

	public List<Pedido> getLista() {
		return lista;
	}
	public List<PedidoProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<PedidoProduto> produtos) {
		this.produtos = produtos;
	}

	public void setLista(List<Pedido> lista) {
		this.lista = lista;
	}

}
