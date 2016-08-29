package br.sistema.dao;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: ProdutoComposicao
 *
 */
@Entity

public class ProdutoComposicao implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_produtocomposicao")
	@SequenceGenerator(name = "seq_produtocomposicao", allocationSize = 1, initialValue = 1)
	private Long codProdutoComposicao;
	@NotEmpty(message="Deve quantidade na composicao!")
	private Float quantidade;
	@NotEmpty(message="Deve informar o produto na composicao!")
	private Produto produto;
	@NotEmpty(message="Deve a materia prima!")
	/*@OneToMany(cascade =ALL, orphanRemoval = true, mappedBy = "fkProdutoComposicao", fetch=FetchType.EAGER)
	private List<MateriaPrima> fkMateriaPrima;*/
	@ManyToOne
	private MateriaPrima materiaPrima;
	private static final long serialVersionUID = 1L;

	public ProdutoComposicao() {
		super();
	}   
	public Long getCodProdutoComposicao() {
		return this.codProdutoComposicao;
	}

	public void setCodProdutoComposicao(Long codProdutoComposicao) {
		this.codProdutoComposicao = codProdutoComposicao;
	}   
	public Float getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}
	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	} 
}
