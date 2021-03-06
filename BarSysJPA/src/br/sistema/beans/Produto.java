package br.sistema.beans;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
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
	@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
	private Long codProduto;
	@NotEmpty(message = "Deve informar o nome do produto!")
	@Length(min = 1, max = 150, message = "O nomedo produto deve ter entre {min} e {max} caracteres!")
	@Column(length = 150)
	private String nome;
	@NotEmpty(message = "Deve informar a unidade do produto!")
	@Length(min = 1, max = 50, message = "A unidade deve ter entre {min} e {max} caracteres!")
	@Column(length = 50)
	private String unidade;
	@NotNull(message = "Deve informar o valor unitario do produto!")
	private Float valorUn;
	@NotNull(message = "As composicoes de produtos devem ser inicializados!")
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "produto", fetch = FetchType.EAGER)
	private List<ProdutoComposicao> produtoComposicao;
	private Boolean listacozinha;
	private Float valorCusto;
	private static final long serialVersionUID = 1L;

	public Produto() {
		super();
		unidade = "UN";
		valorUn = 0F;
		listacozinha = false;
		produtoComposicao = new ArrayList();
		valorCusto = 0F;
	}

	public Float getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(Float valorCusto) {
		this.valorCusto = valorCusto;
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

	public List<ProdutoComposicao> getProdutoComposicao() {
		return produtoComposicao;
	}

	public void setProdutoComposicao(List<ProdutoComposicao> produtoComposicao) {
		this.produtoComposicao = produtoComposicao;
	}

	public Boolean getListacozinha() {
		return listacozinha;
	}

	public void setListacozinha(Boolean listacozinha) {
		this.listacozinha = listacozinha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
