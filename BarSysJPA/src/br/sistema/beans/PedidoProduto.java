package br.sistema.beans;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: PedidoProduto
 *
 */
@Entity

public class PedidoProduto implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_pedidoproduto")
	@SequenceGenerator(name = "seq_pedidoproduto", sequenceName = "seq_pedidoproduto", allocationSize = 1, initialValue = 1)
	private Long codPedidoProduto;
	@NotNull(message = "Deve informar o valor do produto!")
	private Double valorUn;
	@NotNull(message = "Deve informar o valor do desconto!")
	private Double valorDesc;
	@NotNull(message = "Deve informar a quantidade!")
	private Double quantidade;
	@ManyToOne(optional = false)
	@NotNull(message = "O pedido deve ser inicializado!")
	private Pedido pedido;
	@ManyToOne(optional = false)
	@NotNull(message = "O produto deve ser inicializado!")
	private Produto produto;


	private static final long serialVersionUID = 1L;

	public PedidoProduto() {
		super();
		valorUn = 0D;
		valorDesc = 0D;
		quantidade = 1D;
	}

	public Long getCodPedidoProduto() {
		return codPedidoProduto;
	}

	public void setCodPedidoProduto(Long codPedidoProduto) {
		this.codPedidoProduto = codPedidoProduto;
	}

	public Double getValorUn() {
		return valorUn;
	}

	public void setValorUn(Double valorUn) {
		this.valorUn = valorUn;
	}

	public Double getValorDesc() {
		return valorDesc;
	}

	public void setValorDesc(Double valorDesc) {
		this.valorDesc = valorDesc;
	}

	public Double getQuantidade() {
		return quantidade;
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
