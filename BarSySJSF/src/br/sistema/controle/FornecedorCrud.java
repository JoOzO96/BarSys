package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.sistema.beans.Cidade;
import br.sistema.beans.Fornecedor;
import br.sistema.beans.FornecedorEndereco;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
public class FornecedorCrud {

	private List<Fornecedor> lista;
	private Fornecedor objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Fornecedor").getResultList();
		em.close();
	}

	public void enderecoFornecedor() {
		EntityManager em = FabricaConexao.getEntityManager();
		lista = em.createQuery("from Fornecedor").getResultList();
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
	public List<Fornecedor> completeFornecedor(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		 List<Fornecedor> results = em.createQuery(
		 "from Fornecedor where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		 "order by nome").getResultList();
		 em.close();
		 return results;
		}
	
	public String incluir() {
		objeto = new Fornecedor();
		return "FornecedorForm?faces-redirect=true";
	}

	public String gravar() {
		try{
			EntityManager em = FabricaConexao.getEntityManager();
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
			em.close();
			return "FornecedorList?faces-redirect=true";
		}catch(Exception e){
			String mens = "";
			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			mens = e.getCause().toString();
			System.out.println(mens);
			if (mens.contains("Deve ter pelo menos um endereco no fornecedor!")){
				mensagem.setSummary("O Fornecedor precisa conter no minimo um endereco");
			}else{
				mensagem.setSummary ("Erro ao cadastrar o fornecedor");
			}
			FacesContext.getCurrentInstance().addMessage("growl", mensagem);
		}
		return "";
	}

	public String cancelar() {
		return "FornecedorList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Fornecedor.class, id);
		em.close();
		return "FornecedorForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		try {
			EntityManager em = FabricaConexao.getEntityManager();
			objeto = em.find(Fornecedor.class, id);
			em.getTransaction().begin();
			em.remove(objeto);
			em.getTransaction().commit();
			em.close();
			return "FornecedorList?faces-redirect=true";
		} catch (Exception e) {
			String mens = "";
			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			mens = e.getCause().toString();
			if (mens.contains("entradaitem")){
				mensagem.setSummary("O Fornecedor nao pode conter nenhuma entrada relacionada a ele");
			}else{
				mensagem.setSummary ("Erro ao deletar o fornecedor");
			}
			FacesContext.getCurrentInstance().addMessage("", mensagem);
		}
		return "";
	}

	public FornecedorCrud() {
		super();
	}

	public List<Fornecedor> getLista() {
		return lista;
	}

	public void setLista(List<Fornecedor> lista) {
		this.lista = lista;
	}

	public Fornecedor getObjeto() {
		return objeto;
	}

	public void setObjeto(Fornecedor objeto) {
		this.objeto = objeto;
	}

	
	
	
	
	// --------------------------------------------------------
	// Para os itens
	// --------------------------------------------------------
	
	
	
	
	private FornecedorEndereco enderecoFornecedor; // item em edição, vinculado ao formulário

	private Integer rowIndex = null; // índice do item selecionado - alterar e
										// excluir

	public void incluirEnderecoFornecedor() {
		rowIndex = null;
		enderecoFornecedor = new FornecedorEndereco();
	}

	public void alterarEnderecoFornecedor(Integer rowIndex) {
		this.rowIndex = rowIndex;
		enderecoFornecedor = objeto.getEnderecoFornecedor().get(rowIndex); // pega item da
															// coleção
	}

	public void excluirEnderecoFornecedor(Integer rowIndex) {
		objeto.getEnderecoFornecedor().remove(rowIndex.intValue()); // exclui item
	}

	public void gravarEnderecoFornecedor() {
		if (this.rowIndex == null) {
			enderecoFornecedor.setFornecedor(objeto);
			objeto.getEnderecoFornecedor().add(enderecoFornecedor); // adiciona item na coleção
		} else {
			objeto.getEnderecoFornecedor().set(rowIndex, enderecoFornecedor); // altera na
																// coleção
		}
		rowIndex = null;
		enderecoFornecedor = null;
	}

	public void cancelarEnderecoFornecedor() {
		rowIndex = null;
		enderecoFornecedor = null;
	}

	public FornecedorEndereco getEnderecoFornecedor() {
		return enderecoFornecedor;
	}

	public void setEnderecoFornecedor(FornecedorEndereco enderecoFornecedor) {
		this.enderecoFornecedor = enderecoFornecedor;
	}

}
