package br.sistema.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: Pedido
 *
 */
@Entity

public class Pedido implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_pedido")
	@SequenceGenerator(name = "seq_pedido", sequenceName = "seq_pedido", allocationSize = 1, initialValue = 1)
	private Long codPedido;
	@NotNull(message = "Deve informar a data do pedido!")
	private Date data;
	@ManyToOne
	@NotNull(message = "Deve informar a situacao do pedido!")
	private Situacao situacao;
	@ManyToOne
	@NotNull(message = "Deve informaro cliente do pedido!")
	private Cliente cliente;
	@NotNull(message = "Os itens do pedido devem ser inicializados!")
	@Size(min = 1, message = "Deve ter pelo menos um item no pedido!")
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "pedido", fetch=FetchType.LAZY)
	private List<PedidoProduto> itensPedido;
	@NotNull(message = "O valor total nao pode ser nulo")
	private Float valorTotal;
	private String nrComanda;
	static final long serialVersionUID = 1L;

	public Pedido() {
		super();
		data = new Date();
		valorTotal = 0F;
		itensPedido = new ArrayList<>();
	}

	public Long getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PedidoProduto> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<PedidoProduto> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNrComanda() {
		return nrComanda;
	}

	public void setNrComanda(String nrComanda) {
		this.nrComanda = nrComanda;
	}

}
