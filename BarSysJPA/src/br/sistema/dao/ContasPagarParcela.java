package br.sistema.dao;

import br.sistema.dao.ContasPagar;
import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: ContasPagarParcela
 *
 */
@Entity

public class ContasPagarParcela implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_codcontaspagarparcelas", strategy = SEQUENCE)
	@SequenceGenerator(name = "seq_codcontaspagarparcelas", allocationSize = 1, initialValue = 1)
	private Long codContasPagarParcela;
	@NotEmpty(message="Deve informar o valor da parcela!")
	private Float valor;
	@NotEmpty(message="Deve informar a data do pagamento!")
	private Date dataPagamento;
	@ManyToOne
	private ContasPagar contasPagar;
	private static final long serialVersionUID = 1L;

	public ContasPagarParcela() {
		super();
		valor = 0F;
	}   
	public Long getCodContasPagarParcela() {
		return this.codContasPagarParcela;
	}

	public void setCodContasPagarParcela(Long codContasPagarParcela) {
		this.codContasPagarParcela = codContasPagarParcela;
	}   
	public Float getValor() {
		return this.valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}   
	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public ContasPagar getContasPagar() {
		return contasPagar;
	}
	public void setContasPagar(ContasPagar contasPagar) {
		this.contasPagar = contasPagar;
	} 
	
}
