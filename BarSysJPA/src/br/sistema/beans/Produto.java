package br.sistema.beans;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Produto
 *
 */
@Entity

public class Produto implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_produto")
	@SequenceGenerator(name = "seq_produto", allocationSize = 1, initialValue = 1)
	private Long codProduto;
	@NotEmpty(message="Deve informar o nome do produto!")
	@Length(min=1, max=150, message="O nomedo produto deve ter entre {min} e {max} caracteres!")
	@Column(length = 150)
	private String nome;
	@NotEmpty(message="Deve informar a unidade do produto!")
	@Length(min=1, max=50, message="A unidade deve ter entre {min} e {max} caracteres!")
	@Column(length = 50)
	private String unidade;
	@NotNull(message="Deve informar o valor unitario do produto!")
	private Float valorUn;
	private static final long serialVersionUID = 1L;

	public Produto() {
		super();
		unidade = "UN";
		valorUn = 0F;
	}   
	public Long getCodProduto() {
		return this.codProduto;
	}

	public void setCodProduto(Long codProduto) {
		this.codProduto = codProduto;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}   
	public Float getValorUn() {
		return this.valorUn;
	}

	public void setValorUn(Float valorUn) {
		this.valorUn = valorUn;
	}
   
}
