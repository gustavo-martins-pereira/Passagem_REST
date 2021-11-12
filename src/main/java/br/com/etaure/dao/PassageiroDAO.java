package br.com.etaure.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.etaure.entities.Passageiro;

public class PassageiroDAO {

	private static EntityManager em;

	private static void createEntityManager() {
		if (em == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("passagens");
			em = factory.createEntityManager();
		}
	}
	
	// Inserir um novo Passageiro
	public static Passageiro insert(Passageiro passageiro) {
		createEntityManager();
		
		em.getTransaction().begin();
		em.persist(passageiro);
		em.getTransaction().commit();
		
		return passageiro;
	}

}
