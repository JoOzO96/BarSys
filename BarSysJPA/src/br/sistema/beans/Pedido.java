package br.sistema.beans;

import java.io.Serializable;
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
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Pedido
 *
 */
@Entity

public class Pedido implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_produto")
	@SequenceGenerator(name = "seq_produto", allocationSize = 1, initialValue = 1)
	private Long codPedido;
	@NotEmpty(message="Deve informar a data do pedido!")
	private Date data;
	@NotEmpty(message="Deve informar a situacao do pedido!")
	private Situacao situacao;
	@ManyToOne
	@NotEmpty(message="Deve informaro cliente do pedido!")
	private Cliente cliente;
	@NotNull(message="Os itens do pedido devem ser inicializados!")
	@Size(min=1, message="Deve ter pelo menos um item no pedido!")
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "pedido", fetch=FetchType.EAGER)
	private List<PedidoProduto> itensPedido;
	private static final long serialVersionUID = 1L;

	public Pedido() {
		super();
	}   
	public Long getCodPedido() {
		return this.codPedido;
	}

	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}   
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}   
	public Situacao getSituacao() {
		return this.situacao;
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
   
}
