package br.sistema.dao;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;


/**
 * Entity implementation class for Entity: ContasPagar
 *
 */
@Entity

public class ContasPagar implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_contaspagar", strategy = SEQUENCE)
	@SequenceGenerator(name = "seq_contaspagar", allocationSize = 1, initialValue = 1)
	private Long codContasPagar;
	@NotEmpty(message="Deve informar o vencimento da conta!")
	private Date vencimento;
	@NotEmpty(message="Deve informar o valor total da conta a receber!")
	private Float valorTotal;
	@NotEmpty(message="Deve informar a descricao da conta!")
	private String descricao;
	@NotEmpty(message="Deve informar o valor pago da conta!")
	private Float valorPago;
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "contasPagar", fetch=FetchType.EAGER)
	private List<ContasPagarParcela> itensContasPagarParcela;
	private static final long serialVersionUID = 1L;

	public ContasPagar() {
		super();
		itensContasPagarParcela = new ArrayList();
		valorPago = 0F;
	}   
	public Long getCodContasPagar() {
		return this.codContasPagar;
	}

	public void setCodContasPagar(Long codContasPagar) {
		this.codContasPagar = codContasPagar;
	}   
	public Date getVencimento() {
		return this.vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}   
	public Float getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}   
	public String getDescricao() {
		return this.descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public void setValorPago(Float valorPago) {
		this.valorPago = valorPago;
	}
	public Float getValorPago() {
		return valorPago;
	}
	public List<ContasPagarParcela> getItensContasPagarParcela() {
		return itensContasPagarParcela;
	}
	public void setItensContasPagarParcela(List<ContasPagarParcela> itensContasPagarParcela) {
		this.itensContasPagarParcela = itensContasPagarParcela;
	}
   
}
