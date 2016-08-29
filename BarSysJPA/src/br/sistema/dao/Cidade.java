package br.sistema.dao;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import static javax.persistence.GenerationType.SEQUENCE;


/**
 * Entity implementation class for Entity: Cidade
 *
 */
@Entity

public class Cidade implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "CidadeId")
	@SequenceGenerator(name = "CidadeId", sequenceName = "CidadeId", allocationSize = 1)
	private Long codCidade;
	@NotEmpty
	@Length(min=2, max=254, message="O nome deve ter entre {min} e {max} caracteres!")
	private String nome;
	@NotEmpty
	@Length(min=2, max=2, message="O nome deve ter entre {min} e {max} caracteres!")
	@Column(length = 2)
	private String estado;
	@NotEmpty
	@Length(min=2, max=15, message="O nome deve ter entre {min} e {max} caracteres!")
	@Column(length = 15)
	private String codNacional;
	private static final long serialVersionUID = 1L;

	public Cidade() {
		super();
	}   
	public Long getCodCidade() {
		return this.codCidade;
	}

	public void setCodCidade(Long codCidade) {
		this.codCidade = codCidade;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}   
	public String getCodNacional() {
		return this.codNacional;
	}

	public void setCodNacional(String codNacional) {
		this.codNacional = codNacional;
	}
   
}
