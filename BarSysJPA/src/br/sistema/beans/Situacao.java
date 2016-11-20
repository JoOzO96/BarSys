package br.sistema.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: Situacao
 *
 */
@Entity

public class Situacao implements Serializable {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_situacao")
	@SequenceGenerator(name = "seq_situacao", sequenceName = "seq_situacao", allocationSize = 1, initialValue = 1)
	private Long codSituacao;
	@NotNull(message = "Uma descricao deve ser informada")
	@Length(min=0, max=254, message="A descrição deve ter entre {min} e {max} caracteres!")
	private String descricao;
	private boolean finaliza;
	private boolean cozinha;
	private static final long serialVersionUID = 1L;

	public Situacao() {
		super();
	}

	public boolean isFinaliza() {
		return finaliza;
	}

	public void setFinaliza(boolean finaliza) {
		this.finaliza = finaliza;
	}

	public boolean isCozinha() {
		return cozinha;
	}

	public void setCozinha(boolean cozinha) {
		this.cozinha = cozinha;
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
