package br.sistema.beans;

import br.sistema.beans.MateriaPrima;
import br.sistema.beans.Produto;
import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: ProdutoComposicao
 *
 */
@Entity

public class ProdutoComposicao implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_ProdutoComposicao")
	@SequenceGenerator(name = "seq_ProdutoComposicao", allocationSize = 1, initialValue = 1)
	private Long codProdutoComposicao;
	@ManyToOne
	@NotNull(message="A materia prima nao pode ser nula")
	private MateriaPrima materiaPrima;
	@ManyToOne(optional=false)
	@NotNull(message="O produto não pode ser nulo")
	private Produto produto;
	@NotNull(message="A quantidade não pode ser nula.")
	private Float quantidade;
	private static final long serialVersionUID = 1L;

	public ProdutoComposicao() {
		super();
		quantidade = 0F;
	}   
	public Long getCodProdutoComposicao() {
		return this.codProdutoComposicao;
	}

	public void setCodProdutoComposicao(Long codProdutoComposicao) {
		this.codProdutoComposicao = codProdutoComposicao;
	}   
	public MateriaPrima getMateriaPrima() {
		return this.materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}   
	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}   
	public Float getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
   
}
