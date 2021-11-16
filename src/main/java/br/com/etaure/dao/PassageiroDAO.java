package br.com.etaure.dao;

import java.util.List;

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

	// Listar todss os Passageiros
	public static List<Passageiro> findAll() {
		createEntityManager();

		String jpql = "SELECT p FROM Passageiro p";
		return em.createQuery(jpql, Passageiro.class).getResultList();
	}
	
	// Retorna o passageiro pelo id
	public static Passageiro findById(Integer id) {
		createEntityManager();
		
		return em.find(Passageiro.class, id);
	}
	
	//Atualiza a Passagem
	public static Passageiro update(Integer id ,Passageiro newPassageiro) {
		createEntityManager();
		
		// Procura o passageiro com este id no banco
		Passageiro passageiro = em.find(Passageiro.class, id);
		
		em.getTransaction().begin();
		
		passageiro.setNome(newPassageiro.getNome());
		passageiro.setNacionalidade(newPassageiro.getNacionalidade());
		
		em.getTransaction().commit();
		
		return em.find(Passageiro.class, id);
	}
	
	//Deleta um Passageiro
	public static void delete(Integer id) {
		createEntityManager();
		
		Passageiro passageiro = em.find(Passageiro.class, id);
		
		em.getTransaction().begin();
		em.remove(passageiro);
		em.flush();
		em.getTransaction().commit();
	}

}
