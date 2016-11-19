package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.sistema.beans.Emitente;
import br.sistema.beans.Usuario;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class LoginControle {
	private String usuario;
	private String senha;
	/**
	 * Atributo para controle do usuário logado. É inicializado quando
	 * informados login e senha válidos. Setado para null quando o usuário sair
	 * do sistema.
	 */
	private Usuario usuarioLogado = null;
	private Emitente emitente = null;

	public LoginControle() {
	}

	/**
	 * Método responsável por valodar o login e senha do usuário. Se for válido
	 * inicializa o usuário logado com a instancia do usuário respectivo ao
	 * login e senha informados e redireciona para a tela principal da
	 * aplicação.
	 * 
	 * @throws Exception
	 */
	public String entrar() {
		EntityManager em = FabricaConexao.getEntityManager();
		Query qry = em.createQuery("from Usuario where usuario = :usuario and senha = :senha");
		qry.setParameter("usuario", usuario);
		qry.setParameter("senha", senha);
		List<Usuario> list = qry.getResultList();
		emitente = (Emitente) em.createQuery("from Emitente where codemitente = 1").getResultList().get(0);
		em.close();
		if (list.size() <= 0) {
			usuarioLogado = null;
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválida!", "");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			return "";
		} else {
			usuarioLogado = list.get(0);
			return "/Sistema/Home/Home.xhtml";
		}
	}

	/**
	 * Método responsável por desconectar o usuário e abrir a página de login
	 * 
	 * @throws Exception
	 */
	public String sair() {
		usuarioLogado = null;
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Desconectado!", "");
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		return "/Login/LoginForm.xhtml?faces-redirect=true";
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

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public Emitente getEmitente() {
		return emitente;
	}

	public void setEmitente(Emitente emitente) {
		this.emitente = emitente;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
}
