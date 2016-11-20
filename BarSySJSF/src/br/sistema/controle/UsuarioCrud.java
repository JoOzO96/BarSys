package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.postgresql.util.PSQLException;

import br.sistema.beans.Usuario;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class UsuarioCrud {

	private List<Usuario> lista;
	private Usuario objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Usuario").getResultList();
		em.close();
	}

	public String incluir() {
		objeto = new Usuario();
		return "UsuarioForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "UsuarioList?faces-redirect=true";
	}

	public String cancelar() {
		return "UsuarioList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Usuario.class, id);
		em.close();
		return "UsuarioForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		try {
			EntityManager em = FabricaConexao.getEntityManager();
			objeto = em.find(Usuario.class, id);
			em.getTransaction().begin();
			em.remove(objeto);
			em.getTransaction().commit();
			em.close();
			return "UsuarioList?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			Throwable err = e.getCause();
			
			while (err != null){
				if (err instanceof PSQLException) {
					PSQLException pe = (PSQLException) err;
					String erro = pe.toString();
					if (erro.contains("fornecedorendereco")){
						mensagem.setSummary("Nao é possivel excluir a cidade, pois ela esta vinculada a um Fornecedor");
					} else if (erro.contains("clienteendereco")) {
						mensagem.setSummary("Nao é possivel excluir a cidade, pois ela esta vinculada a um Cliente");
					}
		         }
				err = err.getCause();
			}
			FacesContext.getCurrentInstance().addMessage("", mensagem);
			return "";
		}
	}

	public UsuarioCrud() {
		super();
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public Usuario getObjeto() {
		return objeto;
	}

	public void setObjeto(Usuario objeto) {
		this.objeto = objeto;
	}
}
