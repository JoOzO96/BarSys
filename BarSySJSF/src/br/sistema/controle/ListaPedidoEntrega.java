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
public class ListaPedidoEntrega {

	private List<Pedido> lista;
	private List<Pedido> lista2;
	private List<PedidoProduto> produtos;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createNativeQuery("SELECT pedido.* FROM PEDIDO where situacao_codsituacao = 3 and entregue = false ", Pedido.class).getResultList();
		lista2 = em.createNativeQuery("SELECT p.* FROM PEDIDO p inner join pedidoproduto pp on pp.pedido_codpedido = p.codpedido inner join produto pr on pp.produto_codproduto = pr.codproduto where pp.itementregue = false and pr.listacozinha = false", Pedido.class).getResultList();
		lista.addAll(lista2);
		em.close();
	}

	public void entrega(Long codpedido){
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("UPDATE Pedido set entregue = true where codpedido = ?").setParameter(1, codpedido).executeUpdate();
		em.createQuery("UPDATE PedidoProduto set itementregue = true where pedido_codpedido = ?").setParameter(1, codpedido).executeUpdate();
		em.getTransaction().commit(); 
		em.close();
		inicializarLista();
	}
	public String entregaItem(Long codpedidoproduto, Long codPedido){
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("UPDATE PedidoProduto set itementregue = true where codPedidoProduto = ?").setParameter(1, codpedidoproduto).executeUpdate();
		if (em.createNativeQuery("SELECT * FROM PedidoProduto where pedido_codPedido = " + codPedido + "and itementregue = false").getResultList().size() == 0){
			em.createNativeQuery("UPDATE Pedido set situacao_codSituacao = 4, entregue = true").executeUpdate();
		}
		em.getTransaction().commit();
		em.close();
		inicializarLista();
		return "ListaPedidoEntregaForm?faces-redirect=true";
	
	}
	
	public String abrirItens(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		produtos = (List<PedidoProduto>) em.createNativeQuery("SELECT * FROM PedidoProduto inner join pedido on pedido.codpedido = pedidoproduto.pedido_codpedido where pedido_codPedido = "+id+" and itementregue = false and pedido.situacao_codsituacao = 3",PedidoProduto.class).getResultList();
		produtos.addAll(em.createNativeQuery("SELECT pp.* From Pedidoproduto pp inner join produto p on p.codproduto = pp.produto_codproduto where p.listacozinha = false and pp.pedido_codPedido = " +id+ " and pp.itementregue = false", PedidoProduto.class).getResultList());
		em.close();
		return "ListaPedidoEntregaForm?faces-redirect=true";
	}
	
	public ListaPedidoEntrega() {
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
