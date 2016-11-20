package br.sistema.beans;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: ContasPagar
 *
 */
@Entity

public class ContasPagar implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_contaspagar", strategy = SEQUENCE)
	@SequenceGenerator(name = "seq_contaspagar", sequenceName = "seq_contaspagar", allocationSize = 1, initialValue = 1)
	private Long codContasPagar;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "Deve informar o vencimento da conta!")
	@Future(message="Não é possivel adicionar uma conta a pagar com a data de vencimento no passado.")
	private Date vencimento;
	@NotNull(message = "Deve informar o valor total da conta a receber!")
	@Min(message = "O valor total deve ser maior que zero.", value = 0)
	private Float valorTotal;
	@NotEmpty(message = "Deve informar a descricao da conta!")
	@Length(max=254, message="A descricao nao pode ter mais que {max} caracteres" )
	private String descricao;
	@NotNull(message = "Deve informar o valor pago da conta!")
	private Float valorPago;
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "contasPagar", fetch = FetchType.EAGER)
	private List<ContasPagarParcela> itensContasPagarParcela;
	private static final long serialVersionUID = 1L;

	public ContasPagar() {
		super();
		itensContasPagarParcela = new ArrayList();
		valorPago = 0F;
		valorTotal = 0F;
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
