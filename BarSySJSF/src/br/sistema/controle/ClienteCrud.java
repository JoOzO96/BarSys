package br.sistema.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.sistema.beans.Cidade;
import br.sistema.beans.Cliente;
import br.sistema.beans.ClienteEndereco;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class ClienteCrud {

	private List<Cliente> lista;
	private Cliente objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Cliente").getResultList();
		em.close();
	}

	public void enderecoCliente() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Cliente").getResultList();
		em.close();
	}
	
	public List<Cidade> completeCidade(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		 List<Cidade> results = em.createQuery(
		 "from Cidade where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		 "order by nome").getResultList();
		 em.close();
		 return results;
		}
	
	public String incluir() {
		objeto = new Cliente();
		return "ClienteForm?faces-redirect=true";
	}

	public String gravar() {
		EntityManager em = FabricaConexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "ClienteList?faces-redirect=true";
	}

	public String cancelar() {
		return "ClienteList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Cliente.class, id);
		em.close();
		return "ClienteForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Cliente.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "ClienteList?faces-redirect=true";
	}

	public ClienteCrud() {
		super();
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public Cliente getObjeto() {
		return objeto;
	}

	public void setObjeto(Cliente objeto) {
		this.objeto = objeto;
	}

	
	
	
	
	// --------------------------------------------------------
	// Para os itens
	// --------------------------------------------------------
	
	
	
	
	private ClienteEndereco enderecoCliente; // item em edição, vinculado ao formulário

	private Integer rowIndex = null; // índice do item selecionado - alterar e
										// excluir

	public void incluirEnderecoCliente() {
		rowIndex = null;
		enderecoCliente = new ClienteEndereco();
	}

	public void alterarEnderecoCliente(Integer rowIndex) {
		this.rowIndex = rowIndex;
		enderecoCliente = objeto.getEnderecoCliente().get(rowIndex); // pega item da
															// coleção
	}

	public void excluirEnderecoCliente(Integer rowIndex) {
		objeto.getEnderecoCliente().remove(rowIndex.intValue()); // exclui item
	}

	public void gravarEnderecoCliente() {
		if (this.rowIndex == null) {
			enderecoCliente.setCliente(objeto);
			objeto.getEnderecoCliente().add(enderecoCliente); // adiciona item na coleção
		} else {
			objeto.getEnderecoCliente().set(rowIndex, enderecoCliente); // altera na
																// coleção
		}
		rowIndex = null;
		enderecoCliente = null;
	}

	public void cancelarEnderecoCliente() {
		rowIndex = null;
		enderecoCliente = null;
	}

	public ClienteEndereco getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(ClienteEndereco enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

}
