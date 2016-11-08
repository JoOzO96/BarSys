package br.sistema.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.sistema.beans.Cliente;
import br.sistema.beans.Pedido;
import br.sistema.beans.PedidoProduto;
import br.sistema.beans.Produto;
import br.sistema.beans.Situacao;
import br.sistema.uteis.FabricaConexao;

@ManagedBean
@SessionScoped
@SuppressWarnings("unchecked")
public class PedidoCrud {

	private List<Pedido> lista;
	private Pedido objeto;

	public void inicializarLista() {
		EntityManager em = FabricaConexao.getEntityManager();
		
		lista = em.createQuery("from Pedido").getResultList();
		em.close();
	}

	public List<Cliente> completeCliente(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		 List<Cliente> results = em.createQuery(
		 "from Cliente where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		 "order by nome").getResultList();
		 em.close();
		 return results;
		}
	
	public List<Situacao> completeSituacao(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		List<Situacao> results = em.createQuery(
		 "from Situacao where upper(descricao) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		 "order by descricao").getResultList();
		 em.close();
		 return results;
		}
	public List<Produto> completeProduto(String query) {
		EntityManager em = FabricaConexao.getEntityManager();
		 List<Produto> results = em.createQuery(
		 "from Produto where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		 "order by nome").getResultList();
		 em.close();
		 return results;
		}
	
	public String incluir() {
		objeto = new Pedido();
		return "PedidoForm?faces-redirect=true";
	}

	public String gravar() {
		try {
			EntityManager em = FabricaConexao.getEntityManager();
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
			em.close();
			return "PedidoList?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (e.getCause().toString().contains("Deve ter pelo menos um item no pedido!")){
				mensagem.setSummary("O Pedido precisa conter no minimo um produto");
			}else{
				mensagem.setSummary("Erro ao cadastrar o Pedido");
			}
			FacesContext.getCurrentInstance().addMessage("", mensagem);
		}
		return "";
	}

	public String cancelar() {
		return "PedidoList";
	}

	public String alterar(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Pedido.class, id);
		//em.close();
		return "PedidoForm?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager em = FabricaConexao.getEntityManager();
		objeto = em.find(Pedido.class, id);
		 em.getTransaction().begin();
		 em.remove(objeto);
		 em.getTransaction().commit();
		em.close();
		 return "PedidoList?faces-redirect=true";
		}
	
	public PedidoCrud() {
		super();
	}

	public List<Pedido> getLista() {
		return lista;
	}

	public void setLista(List<Pedido> lista) {
		this.lista = lista;
	}

	public Pedido getObjeto() {
		return objeto;
	}

	public void setObjeto(Pedido objeto) {
		this.objeto = objeto;
	}
	
	
	
	private PedidoProduto itensPedido; // item em edição, vinculado ao formulário

	private Integer rowIndex = null; // índice do item selecionado - alterar e
										// excluir

	public void incluirItensPedido() {
		rowIndex = null;
		itensPedido = new PedidoProduto();
	}

	public void alterarItensPedido(Integer rowIndex) {
		this.rowIndex = rowIndex;
		itensPedido = objeto.getItensPedido().get(rowIndex); // pega item da
															// coleção
	}

	public void excluirItensPedido(Integer rowIndex) {
		objeto.getItensPedido().remove(rowIndex.intValue()); // exclui item
	}

	public void gravarItensPedido() {
		if (this.rowIndex == null) {
			itensPedido.setPedido(objeto);
			objeto.getItensPedido().add(itensPedido); // adiciona item na coleção
		} else {
			objeto.getItensPedido().set(rowIndex, itensPedido); // altera na
																// coleção
		}
		rowIndex = null;
		itensPedido = null;
	}

	public void cancelarItensPedido() {
		rowIndex = null;
		itensPedido = null;
	}

	public PedidoProduto getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(PedidoProduto itensPedido) {
		this.itensPedido = itensPedido;
	}
	public void retornaCusto(){
		itensPedido.setValorUn(itensPedido.getProduto().getValorUn());
	}
	public void retornaValorUn(){
		System.out.println(itensPedido.getValorUn());
		itensPedido.setValorUn(itensPedido.getValorUn().floatValue());
	}
	public void formataValorUn(Float valor){
		itensPedido.setValorUn(valor);
	}
}