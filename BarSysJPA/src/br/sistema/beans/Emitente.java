package br.sistema.beans;

import br.sistema.beans.Cidade;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Emitente
 *
 */
@Entity

public class Emitente implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_emitente")
	@SequenceGenerator(name = "seq_emitente", sequenceName = "seq_emitente", allocationSize = 1)
	private Long idEmitente;
	private String nome;
	private String rua;
	private String numero;
	private String telefone;
	private Cidade cidade;
	private String bairro;
	private String complemento;
	private static final long serialVersionUID = 1L;

	public Emitente() {
		super();
	}   
	public Long getIdEmitente() {
		return this.idEmitente;
	}

	public void setIdEmitente(Long idEmitente) {
		this.idEmitente = idEmitente;
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
