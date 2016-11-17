package br.sistema.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.PedidoProduto;
import br.sistema.beans.Situacao;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class ListaCrud {

	private List<PedidoProduto> lista;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createNativeQuery("SELECT p.* FROM PedidoProduto p inner join produto pr on p.produto_codproduto = pr.codproduto inner join pedido pe on pe.codPedido = p.pedido_codPedido inner join situacao s on pe.situacao_codsituacao = s.codsituacao where s.cozinha = true and p.finalizado = false and pr.listacozinha = true", PedidoProduto.class).getResultList();
		em.close();
	}

	public void finaliza(Long codpedidoproduto){
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.createQuery("UPDATE PedidoProduto set finalizado = true where codpedidoproduto = ?").setParameter(1, codpedidoproduto).executeUpdate();
		em.getTransaction().commit();
		em.close();
		inicializarLista();
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

}
