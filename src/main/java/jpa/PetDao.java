package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Data Access Object.
 */
public class PetDao {

	//"pets" é o nome da unidade de persistência no "persistence.xml".
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pets");

	public static void inclui(String cod, String nome, String apelido, String raca, String descricao, String dono, String telefone) {
		// Obter "conexão".
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Pet pet = new Pet();
		pet.setCod(cod);
		pet.setNome(nome);
		pet.setApelido(apelido);
		pet.setRaca(raca);
		pet.setDono(dono);
		pet.setDescricao(descricao);
		pet.setTelefone(telefone);

		// Grava o objeto no banco de dados.
		em.persist(pet);
		em.getTransaction().commit();
		em.close();
	}

	public static void alterar(String cod, String nome) {
	}

	public static void excluir(String cod) {
	}

	public static List<Pet> listar() {
		EntityManager em = emf.createEntityManager();
		//Não é SQL! É JPQL.
		String jpql = "from Pet";
		TypedQuery<Pet> query = em.createQuery(jpql, Pet.class);
		List<Pet> result = query.getResultList();
		em.close();
		return result;
	}
}
