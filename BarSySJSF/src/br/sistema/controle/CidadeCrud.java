package br.sistema.controle;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.Cidade;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class CidadeCrud {

	private List<Cidade> lista;
	private Cidade objeto;
	private String[] listaUF = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
			"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "EX" };

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();

		lista = em.createQuery("from Cidade").getResultList();
		em.close();
	}

	public String incluir() {
		objeto = new Cidade();
		return "CidadeForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "CidadeList?faces-redirect=true";
	}

	public String cancelar() {
		return "CidadeList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Cidade.class, id);
		em.close();
		return "CidadeForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Cidade.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "CidadeList?faces-redirect=true";
	}

	public CidadeCrud() {
		super();
	}

	public List<Cidade> getLista() {
		return lista;
	}

	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}

	public Cidade getObjeto() {
		return objeto;
	}

	public void setObjeto(Cidade objeto) {
		this.objeto = objeto;
	}

	public String[] getListaUF() {
		return listaUF;
	}

	public void setListaUF(String[] listaUF) {
		this.listaUF = listaUF;
	}

}
