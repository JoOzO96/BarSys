package br.sistema.beans;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Entity implementation class for Entity: Fornecedor
 *
 */
@Entity

public class Fornecedor implements Serializable {

	   
	@Id
	@GeneratedValue(generator = "seq_fornecedor")
	@SequenceGenerator(name = "seq_fornecedor", allocationSize = 1, initialValue = 1)
	private Long codFornecedor;
	@NotEmpty(message="Deve informar o CNPJ do fornecedor!")
	private String cnpj;
	@NotEmpty(message="Deve informar o nome do fornecedor!")
	private String nome;
	@NotNull(message="Os enderecos do fornecedor devem ser inicializados!")
	@Size(min=1, message="Deve ter pelo menos um endereco no fornecedor!")
	@Valid
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "fornecedor", fetch=FetchType.EAGER)
	private List<FornecedorEndereco> enderecoFornecedor;
	private static final long serialVersionUID = 1L;

	public Fornecedor() {
		super();
		enderecoFornecedor = new ArrayList();
	}   
	public Long getCodFornecedor() {
		return this.codFornecedor;
	}

	public void setCodFornecedor(Long codFornecedor) {
		this.codFornecedor = codFornecedor;
	}   
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<FornecedorEndereco> getEnderecoFornecedor() {
		return enderecoFornecedor;
	}
	public void setEnderecoFornecedor(List<FornecedorEndereco> enderecoFornecedor) {
		this.enderecoFornecedor = enderecoFornecedor;
	}
   
}
