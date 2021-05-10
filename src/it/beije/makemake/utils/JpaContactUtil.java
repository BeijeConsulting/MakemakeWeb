package it.beije.makemake.utils;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class JpaContactUtil {

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("Makemake");
	
	@SuppressWarnings("unchecked")
	public static List<Contatto> selectAll(){
		EntityManager manager = FACTORY.createEntityManager();
		
		Query query = manager.createNativeQuery("SELECT * FROM rubrica ", Contatto.class);
		List<Contatto> contactList = query.getResultList();
		
		manager.close();
		return contactList;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean delete(Contatto deleteCont) {
		//Session mySession = newSingleton.getSession();
		EntityManager manager = FACTORY.createEntityManager();
	
		
		//Query<Contatto> myQuery = mySession.createQuery("SELECT c FROM rubrica as c");
		Query query = manager.createNativeQuery("SELECT * FROM rubrica WHERE id = :id", Contatto.class);
		query.setParameter("id", deleteCont.getId());
		
		List<Contatto> contactList = query.getResultList();
		
		if(contactList.isEmpty()) {
			manager.close();
			return false;
		}else {
			//elimino il contatto
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(contactList.get(0));
			transaction.commit();
			manager.close();
			return true;
		}
	}
	
	public static boolean insert(Contatto contact) {
		//Session mySession = newSingleton.getSession();
		EntityManager entityManager = FACTORY.createEntityManager();
		//Transaction transaction = mySession.beginTransaction();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		//mySession.save(contatto);
		entityManager.persist(contact);
		
		//transaction.commit();
		entityTransaction.commit();
		
		//mySession.close();
		entityManager.close();
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean update(Contatto updateCont) {
		//Session mySession = newSingleton.getSession();
		EntityManager manager = FACTORY.createEntityManager();
	
		
		//Query<Contatto> myQuery = mySession.createQuery("SELECT c FROM Contatto as c");
		Query query = manager.createNativeQuery("SELECT * FROM rubrica WHERE id = :id", Contatto.class);
		query.setParameter("id", updateCont.getId());
		
		List<Contatto> contactList = query.getResultList();
		
		if(contactList.isEmpty()) {
			manager.close();
			return false;
		}else {
			//elimino il contatto
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(contactList.get(0));
			transaction.commit();
			manager.close();
			return true;
		}
	}
}
