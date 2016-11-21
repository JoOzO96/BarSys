package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.sistema.beans.Situacao;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class SituacaoCrud {

	private List<Situacao> lista;
	private Situacao objeto;
	private Boolean libera = false;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();

		lista = em.createQuery("from Situacao").getResultList();
		em.close();
	}

	public String incluir() {
		objeto = new Situacao();
		return "SituacaoForm?faces-redirect=true";
	}

	public String gravar() throws InterruptedException {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		if (objeto.getCodSituacao() != null) {
			if (objeto.getCodSituacao() > 5) {
				em.merge(objeto);
				em.getTransaction().commit();
				em.close();
				return "SituacaoList?faces-redirect=true";
			} else {
				FacesMessage mensagem = new FacesMessage();
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				mensagem.setSummary("Não é possivel editar essa situacao pois ela e padrao do sistema.");
				FacesContext.getCurrentInstance().addMessage("", mensagem);
				return "";
			}
		} else {
			em.merge(objeto);
			em.getTransaction().commit();
			em.close();
			return "SituacaoList?faces-redirect=true";
		}
	}

	public String cancelar() {
		return "SituacaoList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Situacao.class, id);
		em.close();
		return "SituacaoForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		if (id > 5) {
			objeto = em.find(Situacao.class, id);
			em.getTransaction().begin();
			em.remove(objeto);
			em.getTransaction().commit();
			em.close();
			return "SituacaoList?faces-redirect=true";
		} else {
			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensagem.setSummary("Nao é possivel excluir a Situacao, pois ela é padrao do sistema");
			FacesContext.getCurrentInstance().addMessage("", mensagem);
			return "";
		}

	}

	public SituacaoCrud() {
		super();
	}

	public List<Situacao> getLista() {
		return lista;
	}

	public void setLista(List<Situacao> lista) {
		this.lista = lista;
	}

	public Situacao getObjeto() {
		return objeto;
	}

	public void setObjeto(Situacao objeto) {
		this.objeto = objeto;
	}

}
