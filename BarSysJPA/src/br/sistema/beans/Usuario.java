package br.sistema.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_usuario")
	@SequenceGenerator(name = "seq_usuario",sequenceName="seq_usuario", allocationSize = 1, initialValue = 1)
	private Long codUsuario;
	private String usuario;
	private String senha;
	private Long grauAcesso;
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}   
	public Long getCodUsuario() {
		return this.codUsuario;
	}

	public void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
	}   
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}   
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}   
	public Long getGrauAcesso() {
		return this.grauAcesso;
	}

	public void setGrauAcesso(Long grauAcesso) {
		this.grauAcesso = grauAcesso;
	}
   
}
