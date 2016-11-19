package br.sistema.beans;

import br.sistema.beans.Cidade;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity implementation class for Entity: Emitente
 *
 */
@Entity

public class Emitente implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_emitente")
	@SequenceGenerator(name = "seq_emitente", sequenceName = "seq_emitente", allocationSize = 1)
	private Long codEmitente;
	@NotNull(message="O nome do emitente nao pode ser em nulo.")
	@NotBlank(message="O nome do emitente nao pode ser em branco.")
	private String nome;
	@NotNull(message="A rua do emitente nao pode ser em nulo.")
	@NotBlank(message="A rua do emitente nao pode ser em branco.")
	private String rua;
	@NotNull(message="O numero do emitente nao pode ser em nulo.")
	@NotBlank(message="O numero do emitente nao pode ser em branco.")
	private String numero;
	@NotNull(message="O telefone do emitente nao pode ser em nulo.")
	@NotBlank(message="O telefone do emitente nao pode ser em branco.")
	private String telefone;
	@ManyToOne(optional = false)
	@NotNull(message="A cidade do emitente nao pode ser em nulo.")
	private Cidade cidade;
	@NotNull(message="O bairro do emitente nao pode ser em nulo.")
	@NotBlank(message="O bairro do emitente nao pode ser em branco.")
	private String bairro;
	private String complemento;
	private static final long serialVersionUID = 1L;

	public Emitente() {
		super();
	}   

	public Long getCodEmitente() {
		return codEmitente;
	}

	public void setCodEmitente(Long codEmitente) {
		this.codEmitente = codEmitente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}   
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}   
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}   
	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}   
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}   
	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
   
}
