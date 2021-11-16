package br.com.etaure.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.etaure.entities.Passagem;

public class PassagemDAO {

	private static EntityManager em;

	private static void createEntityManager() {
		if (em == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("passagens");
			em = factory.createEntityManager();
		}
	}

	// Inserir uma Passagem
	public static Passagem insert(Passagem passagem) {
		createEntityManager();

		em.getTransaction().begin();
		em.persist(passagem);
		em.getTransaction().commit();

		return passagem;
	}

	// Listar todas as Passagens
	public static List<Passagem> findAll() {
		createEntityManager();

		String jpql = "SELECT p FROM Passagem p";
		return em.createQuery(jpql, Passagem.class).getResultList();
	}

	// Retorna a Passagem pelo id
	public static Passagem findById(Integer id) {
		createEntityManager();

		return em.find(Passagem.class, id);
	}

	// Atualiza a Passagem
	public static Passagem update(Integer id, Passagem newPassagem) {
		createEntityManager();

		// Procura a passagem com este id no banco
		Passagem passagem = em.find(Passagem.class, id);

		em.getTransaction().begin();

		passagem.setPassageiro(newPassagem.getPassageiro());
		passagem.setOrigem(newPassagem.getOrigem());
		passagem.setDestino(newPassagem.getDestino());
		passagem.setValorDaPassagem(newPassagem.getValorDaPassagem());

		em.getTransaction().commit();

		return em.find(Passagem.class, id);
	}

	// Deleta a Passagem
	public static void delete(Integer id) {
		createEntityManager();

		Passagem passagem = em.find(Passagem.class, id);

		em.getTransaction().begin();
		em.remove(passagem);
		em.flush();
		em.getTransaction().commit();
	}

	// Retorna uma lista de passagens de acordo com o id do passageiro
	public static List<Passagem> findPassagensByIdPassageiro(Integer id) {
		createEntityManager();

		String jpql = "SELECT p1 FROM Passagem p1 JOIN p1.passageiro p2 on p1.passageiro.id = p2.id WHERE p2.id = :id";

		TypedQuery<Passagem> typedQuery = em.createQuery(jpql, Passagem.class);
		typedQuery.setParameter("id", id);

		return typedQuery.getResultList();
	}

}
