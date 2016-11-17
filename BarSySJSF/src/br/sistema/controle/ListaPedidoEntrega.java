package br.sistema.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.Cliente;
import br.sistema.beans.Pedido;
import br.sistema.beans.PedidoProduto;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class ListaPedidoEntrega {

	private List<Pedido> lista;
	private Pedido produtos;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createNativeQuery("SELECT pedido.* FROM PEDIDO where situacao_codsituacao = 3 and entregue = false ", Pedido.class).getResultList();
		em.close();
	}

	public void entrega(Long codpedido){
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("UPDATE Pedido set entregue = true where codpedido = ?").setParameter(1, codpedido).executeUpdate();
		em.createQuery("UPDATE PedidoProduto set entregue = true where pedido_codpedido = ?").setParameter(1, codpedido).executeUpdate();
		em.getTransaction().commit(); 
		em.close();
		inicializarLista();
	}
	public void entregaItem(Long codpedidoproduto, Long codPedido){
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("UPDATE PedidoProduto set entregue = true where codPedidoProduto = ?").setParameter(1, codpedidoproduto).executeUpdate();
		if (em.createNativeQuery("SELECT * FROM PedidoProduto where pedido_codPedido = " + codPedido + "and entregue = false").getResultList().size() == 0){
			em.createNativeQuery("UPDATE Pedido set situacao_codSituacao = 4").executeUpdate();
		}
		em.getTransaction().commit();
		em.close();
		inicializarLista();
	}
	
	public String abrirItens(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		produtos = (Pedido) em.createNativeQuery("SELECT p.*, pp.codPedidoProduto, pp.entregue as ppEntregue, pp.finalizado, pp.quantidade,pp.valorDesc,pp.valorUn,pp.pedido_codPedido,pp.produto_codproduto as ppEntregue FROM Pedido p inner join pedidoproduto pp on p.codPedido = pp.pedido_codPedido where p.codPedido = 7 and p.entregue = false and pp.entregue = false").getSingleResult();
		em.close();
		return "ListaPedidoEntregaForm?faces-redirect=true";
	}
	
	public ListaPedidoEntrega() {
		super();
	}

	public List<Pedido> getLista() {
		return lista;
	}

	public Pedido getProdutos() {
		return produtos;
	}

	public void setProdutos(Pedido produtos) {
		this.produtos = produtos;
	}

	public void setLista(List<Pedido> lista) {
		this.lista = lista;
	}

}
