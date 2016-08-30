package br.sistema.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: Situacao
 *
 */
@Entity

public class Situacao implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_situacao")
	@SequenceGenerator(name = "seq_situacao", allocationSize = 1, initialValue = 1)
	private Long codSituacao;
	@NotNull(message="Uma descricao deve ser informada")
	private String descricao;
	private static final long serialVersionUID = 1L;

	public Situacao() {
		super();
	}   
	public Long getCodSituacao() {
		return this.codSituacao;
	}

	public void setCodSituacao(Long codSituacao) {
		this.codSituacao = codSituacao;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
   
}
