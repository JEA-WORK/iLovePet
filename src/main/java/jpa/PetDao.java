package jpa;
   
  import java.util.List;
   
  import javax.persistence.EntityManager;
  import javax.persistence.EntityManagerFactory;
  import javax.persistence.Persistence;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
   
  public class PetDao {
   
           private static PetDao instance;
           
           private EntityManagerFactory factory = Persistence.createEntityManagerFactory("pet");
           
           public static PetDao getInstance(){
                     if (instance == null){
                              instance = new PetDao();
                     }
                     
                     return instance;
           }
   
           private PetDao() {
           }
   
           public Pet getById(final int id) {
        	   EntityManager entityManager = factory.createEntityManager();
                     return entityManager.find(Pet.class, id);
           }
   
           @SuppressWarnings("unchecked")
           public List<Pet> findAll() {
        	   EntityManager entityManager = factory.createEntityManager();
                     return entityManager.createQuery("FROM " + Pet.class.getName()).getResultList();
           }
   
           public void inclui(Pet pet) {
        	   EntityManager entityManager = factory.createEntityManager();
                     try {
                    	 	  
                              entityManager.getTransaction().begin();
                              entityManager.persist(pet);
                              entityManager.getTransaction().commit();
                     } catch (Exception ex) {
                              ex.printStackTrace();
                              entityManager.getTransaction().rollback();
                              System.out.println("Não foi possível gravar no banco.");
                     }
                     entityManager.close();
           }
   
           public void alterar(Pet pet) {
        	   		EntityManager entityManager = factory.createEntityManager();
                     try {
                    	 
                              entityManager.getTransaction().begin();
                              entityManager.merge(pet);
                              entityManager.getTransaction().commit();
                     } catch (Exception ex) {
                              ex.printStackTrace();
                              entityManager.getTransaction().rollback();
                              System.out.println("Não foi possível alterar no banco.");
                     }
                     entityManager.close();
           }
   
           public void remove(Pet pet) {
        	   EntityManager entityManager = factory.createEntityManager();
                     try {
                              entityManager.getTransaction().begin();
                              pet = entityManager.find(Pet.class, pet.getCod());
                              entityManager.remove(pet);
                              entityManager.getTransaction().commit();
                     } catch (Exception ex) {
                              ex.printStackTrace();
                              entityManager.getTransaction().rollback();
                              System.out.println("Não foi possível remover do banco.");
                     }
                     entityManager.close();
           }
  
  }