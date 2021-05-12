package it.beije.makemake.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.beije.makemake.JPASingleton;

public class EcommerceManager {
	private static JPASingleton instance=JPASingleton.getInstance();;
	private static Scanner tastiera = new Scanner(System.in);
	private static List<User> utenti;
	public void getsa(){
		
	}

	public static void getUsers() {
		String jplSelect = "SELECT u FROM User as u";
		Query query = instance.getEntityManager().createQuery(jplSelect);
		List<User> utenti = query.getResultList();
		utenti.toString();
	}

	public static void insertUser(User utente) {
		EntityTransaction transaction = instance.getEntityManager().getTransaction();
		transaction.begin();
		instance.getEntityManager().persist(utente);
		transaction.commit();
	}

	public static List<User> dbToListaUtenti() {
		utenti = new ArrayList<>();
		String jplSelect = "SELECT c FROM Contatto as c";
		Query query = instance.getEntityManager().createQuery(jplSelect);
		List<User> utenti = query.getResultList();
		System.out.println(utenti);
		return utenti;
	}

	public static void listaToDb() {
		EntityTransaction entityTransaction = instance.getEntityManager().getTransaction();
		for (User u : utenti) {
			entityTransaction.begin();
			instance.getEntityManager().persist(u);
			entityTransaction.commit();
		}
	}

	public static void getProducts() {
		String jplSelect = "SELECT p FROM Product as p";
		Query query = instance.getEntityManager().createQuery(jplSelect);
		List<Product> prodotti = query.getResultList();
		prodotti.toString();
	}

	public static void getOrderDetails() {

		String orderSelect = "SELECT o FROM Order as o ";
		String orderItemSelect = "SELECT i FROM OrderItem as i ";
		Query queryOrder = instance.getEntityManager().createQuery(orderSelect);
		Query queryOItem = instance.getEntityManager().createQuery(orderItemSelect);

		List<Order> order = queryOrder.getResultList();
		List<OrderItem> orderItem = queryOItem.getResultList();

		for (Order o : order) {
			System.out.println("Id number: " + o.getId());
			User u = instance.getEntityManager().find(User.class, o.getUserId());
			System.out.println("username : " + u.getUsername());
			for (OrderItem oi : orderItem) {
				if (o.getId().equals(oi.getOrderId())) {
					System.out.println("quantità: " + oi.getQuantity());
					Product p = instance.getEntityManager().find(Product.class, oi.getProductId());
					System.out.println("name: " + p.getName());
					System.out.println("brand: " + p.getBrand());
				}
			}
			System.out.println("-----------------------");
		}

	}

//	public static void createOrder(String username) {
//
//	}

	public static boolean exists(String username) {
		String usernameCheck = "Select u From User u WHERE u :=username";
		Query query = instance.getEntityManager().createQuery(usernameCheck);
		return query.getResultList().size() > 0;
	}

	public static User getUser(String username) {
		if (exists(username)) {
			String usernameCheck = "Select u From User u WHERE u :=username";
			TypedQuery<User> query = instance.getEntityManager().createQuery(usernameCheck, User.class);
			return query.getResultList().get(0);
		}
		return null;
	}

	public static void main(String[] args) {

		getUsers();
		getProducts();
		getOrderDetails();
	}

}
