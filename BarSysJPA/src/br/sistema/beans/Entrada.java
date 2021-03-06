	package br.sistema.beans;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Entrada
 *
 */
@Entity

public class Entrada implements Serializable {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_entrada")
	@SequenceGenerator(name = "seq_entrada", sequenceName = "seq_entrada", initialValue = 1, allocationSize = 1)
	private Long codEntrada;
	@NotNull
	@Length(min = 1, max = 254, message = "O numero deve ter entre {min} e {max} caracteres!")
	private String numeroNota;
	@NotNull(message = "Deve informar o valor total da nota!")
	private Float valorTotal;
	@NotNull(message = "Deve informar o fornecedor!")
	@ManyToOne
	private Fornecedor fornecedor;
	@NotNull(message = "As composicoes de produtos devem ser inicializados!")
	@Size(min = 1, message = "A entrada deve conter pelo menos 1 item")
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "entrada", fetch = FetchType.EAGER)
	private List<EntradaItem> itensEntrada;
	private static final long serialVersionUID = 1L;

	public Entrada() {
		super();
		itensEntrada = new ArrayList();
		valorTotal = 0F;
	}

	public Long getCodEntrada() {
		return this.codEntrada;
	}

	public void setCodEntrada(Long codEntrada) {
		this.codEntrada = codEntrada;
	}

	public String getNumeroNota() {
		return this.numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public Float getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<EntradaItem> getItensEntrada() {
		return itensEntrada;
	}

	public void setItensEntrada(List<EntradaItem> itensEntrada) {
		this.itensEntrada = itensEntrada;
	}

}
