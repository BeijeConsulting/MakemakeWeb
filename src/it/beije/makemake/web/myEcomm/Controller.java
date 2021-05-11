package it.beije.makemake.web.myEcomm;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.beije.makemake.web.manager.singleton.*;
import it.beije.makemake.web.myEcomm.entity.User;
public class Controller {
//classe in cui chiamare tutti i metodi 
	private static JPASingleton manager = JPASingleton.getJPASingleton();
	
	
	public static User getUserByUsernameAndPassword(String username,String password) {
		EntityManager entityManager = manager.getEntityManager();
		String jpqlSelect = "SELECT u FROM User as u WHERE u.username = :username AND u.password =:password";
		Query queryUser = entityManager.createQuery(jpqlSelect).setParameter("username", username).setParameter("password",password);
		Object result = queryUser.getSingleResult();
		if(result == null) return null;
		entityManager.close();
		return (User) result ;

	}
}
