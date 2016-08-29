package br.sistema.dao;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: EntradaItem
 *
 */
@Entity

public class EntradaItem implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_entrada")
	@SequenceGenerator(name = "seq_entrada", allocationSize = 1, initialValue = 1)
	private Long codEntradaItem;
	@NotEmpty(message="Deve informar o custo unitario do produto!")
	private Float custoUnitario;
	@NotEmpty(message="Deve informar a quantidade do produto!")
	private Float quantidade;
	@Transient
	private Float custoTotal;
	@ManyToOne
	private Entrada entrada;
	@ManyToOne
	private MateriaPrima materiaPrima;
	private static final long serialVersionUID = 1L;

	public EntradaItem() {
		super();
	}   
	public Long getCodEntradaItem() {
		return this.codEntradaItem;
	}

	public void setCodEntradaItem(Long codEntradaItem) {
		this.codEntradaItem = codEntradaItem;
	}   
	public Float getCustoUnitario() {
		return this.custoUnitario;
	}

	public void setCustoUnitario(Float custoUnitario) {
		this.custoUnitario = custoUnitario;
	}   
	public Float getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
	public Entrada getEntrada() {
		return entrada;
	}
	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}
	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}
	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	public Float getCustoTotal() {
		return quantidade * custoUnitario;
	}
	public void setCustoTotal(Float custoTotal) {
		this.custoTotal = custoTotal;
		this.custoUnitario = custoTotal / quantidade;
	}   
	
   
}
