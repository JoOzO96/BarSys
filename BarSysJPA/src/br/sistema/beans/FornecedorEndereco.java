package br.sistema.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.sistema.beans.Cidade;

/**
 * Entity implementation class for Entity: FornecedorEndereco
 *
 */
@Entity

public class FornecedorEndereco implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_fornecedorendereco")
	@SequenceGenerator(name = "seq_fornecedorendereco", sequenceName = "seq_fornecedorendereco", allocationSize = 1, initialValue = 1)
	private Long codFornecedorEndereco;
	@NotEmpty(message = "Deve informar a rua do fornecedor!")
	@Length(min = 2, max = 254, message = "A rua deve ter entre {min} e {max} caracteres!")
	private String rua;
	@NotEmpty(message = "Deve informar o numero da rua do fornecedor!")
	@Length(min = 1, max = 50, message = "O numero da rua deve ter entre {min} e {max} caracteres!")
	@Column(length = 50)
	private String numero;
	private String iestadual;
	@NotEmpty(message = "Deve informar o numero da rua do fornecedor!")
	@Length(min = 1, max = 50, message = "O numero da rua deve ter entre {min} e {max} caracteres!")
	@Column(length = 50)
	private String bairro;
	@Length(min=0, max=254, message="O complemento deve ter entre {min} e {max} caracteres!")
	private String complemento;
	@Length(min=0, max=254, message="A referencia deve ter entre {min} e {max} caracteres!")
	private String referencia;
	@ManyToOne(optional = false)
	private Cidade cidade;
	@ManyToOne(optional = false)
	@NotNull(message = "O fornecedor deve ser inicializado!")
	private Fornecedor fornecedor;
	private static final long serialVersionUID = 1L;

	public FornecedorEndereco() {
		super();
	}

	public Long getCodFornecedorEndereco() {
		return this.codFornecedorEndereco;
	}

	public void setCodFornecedorEndereco(Long codFornecedorEndereco) {
		this.codFornecedorEndereco = codFornecedorEndereco;
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

	public String getIestadual() {
		return this.iestadual;
	}

	public void setIestadual(String iestadual) {
		this.iestadual = iestadual;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return this.bairro;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}
