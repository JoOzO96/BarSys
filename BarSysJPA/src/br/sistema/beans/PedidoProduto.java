package br.sistema.beans;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: PedidoProduto
 *
 */
@Entity

public class PedidoProduto implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_pedidoproduto")
	@SequenceGenerator(name = "seq_pedidoproduto", allocationSize = 1, initialValue = 1)
	private Long codPedidoProduto;
	@NotEmpty(message="Deve informar o valor do produto!")
	private Double valorUn;
	@NotEmpty(message="Deve informar o valor do desconto!")
	private Double valorDesc;
	@NotEmpty(message="Deve informar a quantidade!")
	private Double quantidade;
	@ManyToOne
	private Pedido pedido;
	@ManyToOne(optional = false)
	@NotNull(message="O pedido deve ser inicializado!")
	private Produto produto;
	private static final long serialVersionUID = 1L;

	public PedidoProduto() {
		super();
		valorUn = 0.0;
		valorDesc = 0.0;
		quantidade = 0.0;
	}   
	public Long getCodPedidoProduto() {
		return this.codPedidoProduto;
	}

	public void setCodPedidoProduto(Long codPedidoProduto) {
		this.codPedidoProduto = codPedidoProduto;
	}   
	public Double getValorUn() {
		return this.valorUn;
	}

	public void setValorUn(Double valorUn) {
		this.valorUn = valorUn;
	}   
	public Double getValorDesc() {
		return this.valorDesc;
	}

	public void setValorDesc(Double valorDesc) {
		this.valorDesc = valorDesc;
	}   
	public Double getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
