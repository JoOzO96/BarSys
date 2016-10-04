package br.sistema.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity

public class Cliente implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_cliente", strategy = SEQUENCE)
	@SequenceGenerator(name = "seq_cliente",sequenceName="seq_cliente", allocationSize = 1, initialValue = 1)
	private Long codCliente;
	@NotEmpty(message="Deve informar o nome do cliente!")
	@Length(min=2, max=80, message="O nome deve ter entre {min} e {max} caracteres!")
	@Column(length = 80)
	private String nome;
	@CPF
	@NotEmpty(message="Deve informar o CPF do cliente!")
	@Column(length = 14)
	private String cpf;
	@Column(length = 30)
	private String rg;
	private Date dataCadastro;
	@NotNull(message="O endereco do cliente devem ser inicializados!")
	@Size(min=0, message="Deve ter pelo menos um endereco no cliente!")
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "cliente", fetch=FetchType.EAGER)
	private List<ClienteEndereco> enderecoCliente;
	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
		enderecoCliente = new ArrayList();
	}   
	public Long getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}   
	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public List<ClienteEndereco> getEnderecoCliente() {
		return enderecoCliente;
	}
	public void setEnderecoCliente(List<ClienteEndereco> enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}
   
}
