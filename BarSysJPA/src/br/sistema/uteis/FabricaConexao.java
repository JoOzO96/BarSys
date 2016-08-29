package br.sistema.uteis;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.jpa.HibernateEntityManager;

public class FabricaConexao {

	private static EntityManagerFactory fabrica;

	public static EntityManager getEntityManager() {
		if (fabrica == null) {
			fabrica = Persistence.createEntityManagerFactory("BarSysJPA");
		}
		return fabrica.createEntityManager();
	}
	
	
	 public static Connection getEntityManagerJDBCConnection() 
			 throws SQLException { 
	      EntityManager em = getEntityManager(); 
	      HibernateEntityManager hem = (HibernateEntityManager) em;
	      SessionImplementor sim = (SessionImplementor) hem.getSession();
	      Connection conexao = sim.connection();
	      em.close();
	      return conexao; 
	  }  	
	
	

}
