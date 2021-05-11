package it.beije.makemake.esercizi.ecommerce;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;



public class JPAManager {
	

	public static void main (String...args) {
		
		

	}

//	public static User searchContatto( int id) {
//		EntityManager entityManager = manager.getEntityManager();
//		User contatto = entityManager.find(User.class, id);
//		
//	return contatto;	
//	}

	public static List<User> getUser(){
		EntityManager entityManager = JPASingleton.getInstance();
	
		CriteriaBuilder criteria_builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<User> criteria_query = criteria_builder.createQuery(User.class);
		
		Root<User> from = criteria_query.from(User.class);
		
		CriteriaQuery<User> select = criteria_query.select(from);
		
		TypedQuery<User> user = entityManager.createQuery(select);
		
		List<User> contatti = user.getResultList();
		
		return contatti;
		
	}
	
	
	public static void insertUser(String username, String name, String surname , String password) {
		EntityManager entityManager = JPASingleton.getInstance();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
			User user = new User();
			user.setUsername(username);
			user.setName(name);
			user.setSurname(surname);
			user.setPassword(password);
			System.out.println("contatto PRE : " + user);
			entityManager.persist(user);
			System.out.println("contatto POST : " + user);
			entityTransaction.commit();
		
	}
	
	
	public static List<Product> getProduct(){
		EntityManager entityManager = JPASingleton.getInstance();
	
		CriteriaBuilder criteria_builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Product> criteria_query = criteria_builder.createQuery(Product.class);
		
		Root<Product> from = criteria_query.from(Product.class);
		
		CriteriaQuery<Product> select = criteria_query.select(from);
		
		TypedQuery<Product> product = entityManager.createQuery(select);
		
		List<Product> prodotti = product.getResultList();
		
		return prodotti;
		
	}

	public static void getOrderInfo(){
//		EntityManager entityManager = manager.getEntityManager();
//		
//		String jpqlSelect = "SELECT o FROM Order as o";
//		
//		Query queryOrdini = entityManager.createQuery(jpqlSelect);
//		List<Order> ordini = queryOrdini.getResultList();
//		
//		
//		String jpqlSelectOrder_item = "SELECT ord FROM Order_Item as ord ";
//		Query queryOrderItem =entityManager.createQuery(jpqlSelectOrder_item);
//		List<Order_Item> order_item = queryOrderItem.getResultList();
//		
//	
//		
//		
//		 for(Order o : ordini) {
//			 	System.out.println("_____Dettaglio ordine_____");
//			 	System.out.println("Id Order : " + o.getId());
//			 	System.out.println("Tot Order : "+ o.getTotal());
//				User u =entityManager.find(User.class, o.getId_User());
//				System.out.println("Username for Order " + u.getUsername());
//				for(int i=0;i<order_item.size();i++) {
//					if(o.getId().equals(order_item.get(i).getId_Order())) {
//						Product p = entityManager.find(Product.class, order_item.get(i).getId_Product());
//						System.out.println("-----Elenco prodotti-----");
//						System.out.println("Product Name : " + p.getName());
//						System.out.println("Price " + order_item.get(i).getPrice());
//						System.out.println("Quantita " +  order_item.get(i).getQuantity() );
//						System.out.println();
//						System.out.println();
//					}
//				}
//			
//			}
//					
//		
//		
//	
		EntityManager entityManager = JPASingleton.getInstance();
		String jpqlSelect = "SELECT u FROM User as u";
		TypedQuery<User> query = entityManager.createQuery(jpqlSelect , User.class);
		
//      TypedQuery: Si avvicina al tipo di classe
		
		List<User> users = query.getResultList();

		for (User user : users) {
			System.out.println("-----Utente-----");
			System.out.println(user);
			for (Order order : user.getOrders()) {
				System.out.println("-----Ordine-----");
				System.out.println(order);
				for (Order_Item items : order.getOrderItems()) {
					
//					System.out.println(items);
					Product p = entityManager.find(Product.class, items.getId_Product());
					System.out.println("-----Elenco prodotti-----");
					System.out.println("Product Name : " + p.getName());
					System.out.println("Price " + items.getPrice());
					System.out.println("Quantita " +  items.getQuantity() );
					System.out.println();
					
					
				}
			}
			System.out.println("_____________________________________________________________________");
			System.out.println();
		}
	}
		
	 
	public static User getUserByUsername(String username) {
		
		EntityManager entityManager =JPASingleton.getInstance();
		String usernameCheck = "SELECT u FROM User u  WHERE u.username = :username";
		
		TypedQuery<User> query = entityManager.createQuery(usernameCheck, User.class);
		
		query.setParameter("username", username);
		
		User user = query.getSingleResult();
		
		
		return user;
	}
	

		
	}
	
	

	
	


