package br.sistema.controle;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.sistema.beans.PedidoProduto;
import br.sistema.beans.Situacao;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class ListaCrud {

	private List<PedidoProduto> lista;
	private Situacao objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		//lista = em.createNativeQuery("SELECT p.* FROM PedidoProduto p inner join pedido pe on pe.codPedido = p.pedido_codPedido where pe.situacao_codsituacao = 1").getResultList();
		lista = em.createNativeQuery("SELECT p.* FROM PedidoProduto p inner join pedido pe on pe.codPedido = p.pedido_codPedido where pe.situacao_codsituacao = 1 and p.finalizado = false;", PedidoProduto.class).getResultList();
		em.close();
	}

	public void finaliza(Long codpedidoproduto){
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("UPDATE PedidoProduto set finalizado = true where codpedidoproduto = ?").setParameter(1, codpedidoproduto).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	
	public ListaCrud() {
		super();
	}

	public List<PedidoProduto> getLista() {
		return lista;
	}

	public void setLista(List<PedidoProduto> lista) {
		this.lista = lista;
	}

	public Situacao getObjeto() {
		return objeto;
	}

	public void setObjeto(Situacao objeto) {
		this.objeto = objeto;
	}

}
