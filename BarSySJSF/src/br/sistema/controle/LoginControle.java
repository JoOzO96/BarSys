package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.sistema.beans.Usuario;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class LoginControle {

	private String usuario;
	private String senha;
	private Usuario usuarioLogado = null;

	public String entrar() {
		EntityManager em = FabricaConexao.getEntityManager();
		Query qry = em.createQuery("from Usuario where usuario = :usuario and senha = :senha");
		qry.setParameter("usuario", usuario);
		qry.setParameter("senha", senha);
		List<Usuario> list = qry.getResultList();
		em.close();
		if (list.size() <= 0) {
			usuarioLogado = null;
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválida!", "");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			return "";
		} else {
			usuarioLogado = list.get(0);
			return "/faces/Sistema/Home/Home.xhtml";
		}
	}

	public String sair() {
		usuarioLogado = null;
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Desconectado!", "");
		FacesContext.getCurrentInstance().addMessage("", mensagem);
		return "/faces/Login/LoginForm.xhtml";
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public LoginControle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
