package br.sistema.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.Cidade;
import br.sistema.beans.Emitente;
import br.sistema.beans.Entrada;
import br.sistema.beans.Fornecedor;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class EmitenteCrud {

	private List<Emitente> lista;
	private Emitente objeto;
	
	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Emitente").getResultList();
		em.close();
	}

	public String gravar() {
		objeto.setCodEmitente(1L);
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "EmitenteList?faces-redirect=true";

	}
	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Emitente.class, id);
		em.close();
		return "EmitenteForm?faces-redirect=true";
	}

	public List<Cidade> completeCidade(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<Cidade> results = em.createQuery(
				"from Cidade where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;
	}

	public String cancelar() {
		return "EmitenteList?faces-redirect=true";
	}

	public EmitenteCrud() {
		super();
	}

	public List<Emitente> getLista() {
		return lista;
	}

	public void setLista(List<Emitente> lista) {
		this.lista = lista;
	}

	public Emitente getObjeto() {
		return objeto;
	}

	public void setObjeto(Emitente objeto) {
		this.objeto = objeto;
	}

}
