package br.sistema.beans;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javassist.expr.NewArray;

/**
 * Entity implementation class for Entity: MateriaPrima
 *
 */
@Entity

public class MateriaPrima implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_mateiraPrima")
	@SequenceGenerator(name = "seq_mateiraPrima", sequenceName = "seq_mateiraPrima", allocationSize = 1, initialValue = 1)
	private Long codMateriaPrima;
	@NotEmpty(message = "Deve informar o nome do fornecedor!")
	@Length(min = 1, max = 50, message = "O nome do fornecedor deve ter entre {min} e {max} caracteres!")
	private String nome;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "A data de cadatro deve ser informada")
	private Date dataCadastro;
	@NotEmpty(message = "Deve informar a unidade da materia prima!")
	@Length(min = 1, max = 10, message = "A unidade da materia prima deve ter entre {min} e {max} caracteres!")
	@Column(length = 10)
	private String unidade;
	@NotNull(message = "A quantidade nao pode ser nula.")
	private Float quantidade;
	private Float valorCustoMedio;
	private Float valorUltimaCompra;
	private static final long serialVersionUID = 1L;

	public MateriaPrima() {
		super();
		quantidade = 0F;
		unidade = "UN";
		dataCadastro = new Date(System.currentTimeMillis());
		valorUltimaCompra = 0F;
		valorCustoMedio = 0F;
	}

	public Long getCodMateriaPrima() {
		return this.codMateriaPrima;
	}

	public void setCodMateriaPrima(Long codMateriaPrima) {
		this.codMateriaPrima = codMateriaPrima;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade.toUpperCase();
	}

	public Float getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public Float getValorCustoMedio() {
		return this.valorCustoMedio;
	}

	public void setValorCustoMedio(Float valorCustoMedio) {
		this.valorCustoMedio = valorCustoMedio;
	}

	public Float getValorUltimaCompra() {
		return this.valorUltimaCompra;
	}

	public void setValorUltimaCompra(Float valorUltimaCompra) {
		this.valorUltimaCompra = valorUltimaCompra;
	}

}
