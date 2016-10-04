package br.sistema.testes;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import br.sistema.beans.Cliente;
import br.sistema.beans.Pedido;
import br.sistema.uteis.FabricaConexao;

public class CriarEstruturasBD {

	@Test
	public void test() {
		//fail("Not yet implemented");
		Persistence.createEntityManagerFactory("BarSysJPA");
	}
	
}
