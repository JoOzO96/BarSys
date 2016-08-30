package br.sistema.controle;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.MateriaPrima;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class MateriaPrimaCrud {

	private List<MateriaPrima> lista;
	private MateriaPrima objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		
		lista = em.createQuery("from MateriaPrima").getResultList();
		em.close();
	}

	public String incluir() {
		objeto = new MateriaPrima();
		return "MateriaPrimaForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "MateriaPrimaList?faces-redirect=true";
	}

	public String cancelar() {
		return "MateriaPrimaList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(MateriaPrima.class, id);
		em.close();
		return "MateriaPrimaForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(MateriaPrima.class, id);
		 em.getTransaction().begin();
		 em.remove(objeto);
		 em.getTransaction().commit();
		em.close();
		 return "MateriaPrimaList?faces-redirect=true";
		}
	
	public MateriaPrimaCrud() {
		super();
	}

	public List<MateriaPrima> getLista() {
		return lista;
	}

	public void setLista(List<MateriaPrima> lista) {
		this.lista = lista;
	}

	public MateriaPrima getObjeto() {
		return objeto;
	}

	public void setObjeto(MateriaPrima objeto) {
		this.objeto = objeto;
	}

}
