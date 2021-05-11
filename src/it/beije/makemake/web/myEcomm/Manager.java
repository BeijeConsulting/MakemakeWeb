package it.beije.makemake.web.myEcomm;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;

import it.beije.makemake.web.manager.singleton.JPASingleton;
import it.beije.makemake.web.myEcomm.entity.Product;
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
	
	public Order getOrderbyId(int id) {
		EntityManager entityManager = manager.getEntityManager();
		TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.id =:id",Order.class);
		Order result = query.setParameter("id",id).getSingleResult();
		entityManager.close();
		return result;
	}
	public Product getProductbyId(int id) {
		EntityManager entityManager = manager.getEntityManager();
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.id =:id",Product.class);
		Product result = query.setParameter("id",id).getSingleResult();
		entityManager.close();
		return result;
	}

	public void editUser(User oldUser,User newUser) {
		EntityManager entityManager = manager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		User user = entityManager.find(User.class, oldUser.getId());
		user.setName(newUser.getName());
		user.setPassword(newUser.getPassword());
		user.setSurname(newUser.getSurname());
		user.setUsername(newUser.getUsername());
		try {
			entityManager.persist(user);
		}finally {
			entityManager.close();
		}
		entityTransaction.commit();
		entityManager.close();
	}
}
