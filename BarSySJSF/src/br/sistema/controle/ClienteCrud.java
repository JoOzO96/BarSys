package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
		lista = em.createQuery("from Carro").getResultList();
		em.close();
	}

	public List<Cidade> completeCidade(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<Cidade> results = em.createQuery(
				"from Cidade where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
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
		if (objeto.getCodCliente() == null){		
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "ClienteList?faces-redirect=true";
		}else if (objeto.getCodCliente() > 1){
			em.merge(objeto);
			em.getTransaction().commit();
			em.close();
			return "ClienteList?faces-redirect=true";
		}else{
			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensagem.setSummary("N�o � possivel editar esse cliente pois ela e padrao do sistema.");
			FacesContext.getCurrentInstance().addMessage("", mensagem);
			return "";
		}
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
//		try {
			EntityManager em = FabricaConexao.getEntityManager();
			if (id > 1) {
				objeto = em.find(Cliente.class, id);
				em.getTransaction().begin();
				em.remove(objeto);
				em.getTransaction().commit();
				em.close();
				return "ClienteList?faces-redirect=true";
			}else{
				FacesMessage mensagem = new FacesMessage();
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				mensagem.setSummary("Nao � possivel excluir a Situacao, pois ela � padrao do sistema");
				FacesContext.getCurrentInstance().addMessage("", mensagem);
				return "";
			}
//		} catch (Exception e) {
//			FacesMessage mensagem = new FacesMessage();
//			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
//			Throwable err = e.getCause();
//
//			while (err != null) {
//				if (err instanceof PSQLException) {
//					PSQLException pe = (PSQLException) err;
//					String erro = pe.toString();
//					if (erro.contains("pedido")) {
//						mensagem.setSummary("Nao � possivel excluir o cliente, pois ele esta vinculado a um Pedido");
//					} else if (erro.contains("clienteendereco")) {
//						mensagem.setSummary("Nao � possivel excluir o cliente, pois ela esta vinculada a um Cliente");
//					}else{
//						mensagem.setSummary("Nao � possivel excluir a Situacao, pois ela � padrao do sistema");
//					}
//					FacesContext.getCurrentInstance().addMessage("", mensagem);
//				}
//				err = err.getCause();
//			}
//			FacesContext.getCurrentInstance().addMessage("", mensagem);
//			return "";
//		}
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

	private ClienteEndereco enderecoCliente; // item em edi��o, vinculado ao
												// formul�rio

	private Integer rowIndex = null; // �ndice do item selecionado - alterar e
										// excluir

	public void incluirEnderecoCliente() {
		rowIndex = null;
		enderecoCliente = new ClienteEndereco();
	}

	public void alterarEnderecoCliente(Integer rowIndex) {
		this.rowIndex = rowIndex;
		enderecoCliente = objeto.getEnderecoCliente().get(rowIndex); // pega
																		// item
																		// da
		// cole��o
	}

	public void excluirEnderecoCliente(Integer rowIndex) {
		objeto.getEnderecoCliente().remove(rowIndex.intValue()); // exclui item
	}

	public void gravarEnderecoCliente() {
		if (this.rowIndex == null) {
			enderecoCliente.setCliente(objeto);
			objeto.getEnderecoCliente().add(enderecoCliente); // adiciona item
																// na cole��o
		} else {
			objeto.getEnderecoCliente().set(rowIndex, enderecoCliente); // altera
																		// na
			// cole��o
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
