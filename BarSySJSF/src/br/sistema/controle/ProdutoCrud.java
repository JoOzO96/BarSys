package br.sistema.controle;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.Produto;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class ProdutoCrud {

	private List<Produto> lista;
	private Produto objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		
		lista = em.createQuery("from Produto").getResultList();
		em.close();
	}

	public String incluir() {
		objeto = new Produto();
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
		objeto = em.find(Produto.class, id);
		em.close();
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

}
