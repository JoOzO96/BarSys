package br.sistema.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
	private Float valorUn;
	@NotNull(message = "Deve informar o valor do desconto!")
	private Float valorDesc;
	@NotNull(message = "Deve informar a quantidade!")
	private Float quantidade;
	@ManyToOne(optional = false)
	@NotNull(message = "O pedido deve ser inicializado!")
	private Pedido pedido;
	@ManyToOne(optional = false)
	@NotNull(message = "O produto deve ser inicializado!")
	private Produto produto;
	private Boolean itementregue;
	private Boolean finalizado;
	private Boolean baixa;

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "PedidoProduto [codPedidoProduto=" + codPedidoProduto + ", valorUn=" + valorUn + ", valorDesc="
				+ valorDesc + ", quantidade=" + quantidade + ", pedido=" + pedido + ", produto=" + produto
				+ ", itementregue=" + itementregue + ", finalizado=" + finalizado + "]";
	}

	public PedidoProduto() {
		super();
		valorUn = 0F;
		valorDesc = 0F;
		quantidade = 1F;
		itementregue = false;
		finalizado = false;
		baixa = false;
	}

	public Long getCodPedidoProduto() {
		return codPedidoProduto;
	}

	public void setCodPedidoProduto(Long codPedidoProduto) {
		this.codPedidoProduto = codPedidoProduto;
	}

	public Float getValorUn() {
		return valorUn;
	}

	public void setValorUn(Float valorUn) {
		this.valorUn = valorUn;
	}

	public Float getValorDesc() {
		return valorDesc;
	}

	public void setValorDesc(Float valorDesc) {
		this.valorDesc = valorDesc;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
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

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getItementregue() {
		return itementregue;
	}

	public void setItementregue(Boolean itementregue) {
		this.itementregue = itementregue;
	}

	public Boolean getBaixa() {
		return baixa;
	}

	public void setBaixa(Boolean baixa) {
		this.baixa = baixa;
	}
	
}
