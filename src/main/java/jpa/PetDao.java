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
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pet");

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

	public static void alterar(String cod, String nome) {}
	/*	 
		try {
			EntityManager em = emf.createEntityManager();
 
			PreparedStatement em = prepareStatement("Update pet SET nome = ?, dono = ?, telefone = ?, "
					+ "apelido = ?, raca = ?, descricao = ?"
						+ " WHERE cod = ? ");
			em.setString(1, pet.getNome());
			em.setString(2, pet.getTelefone());
			em.setLong(5, pet.getCod());
			em.execute();
			em.close();
			conexao.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		 	
*/
	

	public static void excluir(String cod) {}
		/*	
		try {
			EntityManager em = emf.createEntityManager();
			/*Fazer uma consulta para selecionar o cod a ser excluido
					.prepareStatement("Delete from	tbaluno where matricula = ? ");
					
			em.setFlushMode(null);
			em.persist(pet);
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/*
	 * 	public Aluno consultar(Aluno aluno) {
		try {
			Connection conexao = getConexao();
			PreparedStatement pstm = conexao
					.prepareStatement("Select * from tbaluno where matricula =	?");
			pstm.setLong(1, aluno.getMatricula());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				aluno.setMatricula(rs.getLong("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setEmail(rs.getString("email"));
				aluno.setDataCadastro(new java.util.Date(rs.getDate("datacadastro").getTime()));
			}
			pstm.close();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aluno;
	}
	 * */

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
