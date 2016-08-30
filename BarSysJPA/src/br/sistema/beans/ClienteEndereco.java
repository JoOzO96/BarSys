package br.sistema.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: ClienteEndereco
 *
 */
@Entity

public class ClienteEndereco implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "seq_clienteendereco")
	@SequenceGenerator(name = "seq_clienteendereco", allocationSize = 1, initialValue = 1)
	private Long codClienteEndereco;
	@NotEmpty(message="Deve informar a rua do cliente!")
	@Length(min=1, max=150, message="A rua deve ter entre {min} e {max} caracteres!")
	@Column(length = 150)
	private String rua;
	@NotEmpty(message="Deve informar o numero rua do cliente!")
	@Length(min=1, max=50, message="O numero da rua deve ter entre {min} e {max} caracteres!")
	@Column(length = 50)
	private String numero;
	private String iestadual;
	@NotEmpty(message="Deve informar o bairo do cliente!")
	@Length(min=1, max=70, message="O bairro deve ter entre {min} e {max} caracteres!")
	@Column(length = 70)
	private String bairro;
	private String complemento;
	private String referencia;
	private Cidade cidade;
	@ManyToOne
	private Cliente cliente;
	private static final long serialVersionUID = 1L;

	public ClienteEndereco() {
		super();
	}   
	public Long getCodClienteEndereco() {
		return this.codClienteEndereco;
	}

	public void setCodClienteEndereco(Long codClienteEndereco) {
		this.codClienteEndereco = codClienteEndereco;
	}   
	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}   
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}   
	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}   
	public String getIestadual() {
		return this.iestadual;
	}

	public void setIestadual(String iestadual) {
		this.iestadual = iestadual;
	}   
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}   
	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}   
	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}   
	
   
}
