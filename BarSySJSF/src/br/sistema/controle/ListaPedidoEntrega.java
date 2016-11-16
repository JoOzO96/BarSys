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
	private Pedido objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createNativeQuery("SELECT pedido.* FROM PEDIDO where situacao_codsituacao = 3 ", Pedido.class).getResultList();
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
	public void entregaItem(Long codpedidoproduto){
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("UPDATE PedidoProduto set entregue = true where codpedido = ?").setParameter(1, codpedidoproduto).executeUpdate();
		em.getTransaction().commit();
		em.close();
		inicializarLista();
	}
	
	public String abrirItens(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Pedido.class, id);
		System.out.println(objeto.getItensPedido().toString());
		em.close();
		return "ListaPedidoEntregaForm?faces-redirect=true";
	}
	
	public ListaPedidoEntrega() {
		super();
	}

	public List<Pedido> getLista() {
		return lista;
	}
	

	public Pedido getObjeto() {
		return objeto;
	}

	public void setObjeto(Pedido objeto) {
		this.objeto = objeto;
	}

	public void setLista(List<Pedido> lista) {
		this.lista = lista;
	}

}
