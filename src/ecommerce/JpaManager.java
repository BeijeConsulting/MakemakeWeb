package ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import rubrica.Contatto;
import rubrica.SingletonJpa;

public class JpaManager {
	static Scanner in = new Scanner(System.in);
	static EntityManager entityManager = SingletonJpa.getInstance();

	public static ArrayList<Order> selectAllOrder() {

		String select = "SELECT c FROM Order as c";
		Query query = entityManager.createQuery(select);
		ArrayList<Order> ordini = (ArrayList<Order>) query.getResultList();

		return ordini;

	}

	public static ArrayList<Product> SelectAllProduct() {

		String jpqlSelect = "SELECT c FROM Product as c";
		Query query = entityManager.createQuery(jpqlSelect);
		ArrayList<Product> products = (ArrayList<Product>) query.getResultList();
		return products;
	}

	public static ArrayList<OrderItem> SelectAllOrderItem() {

		String jpqlSelect = "SELECT c FROM OrderItem as c";
		Query query = entityManager.createQuery(jpqlSelect);
		ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>) query.getResultList();

		return orderItems;
	}

	public static ArrayList<User> SelectAllUser() {

		String jpqlSelect = "SELECT c FROM User as c";
		Query query = entityManager.createQuery(jpqlSelect);
		ArrayList<User> Users = (ArrayList<User>) query.getResultList();

		return Users;
	}

	public static void insert(User u) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(u);

		entityTransaction.commit();

	}

	public static void insert(Order u) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(u);

		entityTransaction.commit();

	}

	public static void insert(OrderItem u) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(u);

		entityTransaction.commit();

	}

	public static void insert(Product u) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(u);

		entityTransaction.commit();

	}

	public static void main(String[] args) {
		GestoreEcommerce a = new GestoreEcommerce();
		a.caricaProduct(SelectAllProduct());
		a.caricaUser(SelectAllUser());

		System.out.println(a);
		System.out.println("---------------------");
		System.out.println(a.getProductbyId(4));
		System.out.println(a.getUserByCredential("filo.viscomi@gmail.com", "cammello"));
		User u = new User("paolorossi2@gmail.com", "cipolla", "Paolo", "Rossi");
		insert(u);
		a.caricaUser(SelectAllUser());
		System.out.println("---------------------");
		System.out.println(a.stringaListaUser());
	}
}