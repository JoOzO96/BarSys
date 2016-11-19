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

	private List<Entrada> lista;
	private Emitente objeto = new Emitente();
	int var = 1;
	
	public void inicio(){
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Emitente.class, 1L);
		System.out.println(objeto);
		var =2;
		em.close();
	}

	public String gravar() {
		objeto.setCodEmitente(1L);
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		var =1;
		return "Sistema/Home/Home.xhtml";

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
		return "/Sistema/Home/Home.xhtml";
	}

	public EmitenteCrud() {
		super();
	}

	public List<Entrada> getLista() {
		return lista;
	}

	public void setLista(List<Entrada> lista) {
		this.lista = lista;
	}

	public Emitente getObjeto() {
		return objeto;
	}

	public void setObjeto(Emitente objeto) {
		this.objeto = objeto;
	}

	public int getVar() {
		return var;
	}

	public void setVar(int var) {
		this.var = var;
	}

}
