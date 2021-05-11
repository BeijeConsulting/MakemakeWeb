package it.beije.makemake.web.myEcomm;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

import it.beije.makemake.web.manager.singleton.JPASingleton;
import it.beije.makemake.web.myEcomm.entity.User;

public class Manager {
private static JPASingleton manager = JPASingleton.getJPASingleton();
	
	
	public static User getUserByUsernameAndPassword(String username,String password) {
		EntityManager entityManager = manager.getEntityManager();
		String jpqlSelect = "SELECT u FROM User as u WHERE u.username = :username AND u.password =:password";
		Query queryUser = entityManager.createQuery(jpqlSelect).setParameter("username", username).setParameter("password",password);
		Object result;
		try {
			result = queryUser.getSingleResult();
		}catch ( NoResultException e){
			result = null;
		}
		
		entityManager.close();
		return (User) result ;

	}
	
	public static void addUser(User user) throws PersistenceException{
		EntityManager entityManager = manager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		try {
			entityManager.persist(user);
		}finally {
			entityManager.close();
		}
		
		entityTransaction.commit();
		entityManager.close();
	}
}
