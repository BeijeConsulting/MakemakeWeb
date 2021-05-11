package it.beije.makemake.ecommerce;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Ecommerce {

	private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("Makemake");
	private final static Scanner input = new Scanner(System.in);

	public static void main(String... args) {
		menu();
	}

	public static void menu() {
		System.out.println("Benvenuto, scegli le operazioni da fare");
		Integer choice;
		do {

			System.out.println("----------------------------------------");
			System.out.println("1:Per visualizzare utenti");
			System.out.println("2:Per visualizzare prodotti");
			System.out.println("3:Elenco oridini");
			System.out.println("4:Dettagli di un ordine particolare");
			System.out.println("5:Creazione di un ordine");
			System.out.println("6:Per uscire");
			System.out.println("----------------------------------------");
			choice = Integer.parseInt(input.nextLine());
			switch (choice) {
			case 1:
				printUsers();
				break;
			case 2:
				printProducts();
				break;
			case 3:
				printOrders();
				break;
			case 4:
				orderDetails();
				break;
			case 5:
				orderCreation();
				break;
			case 6:
				break;
			default:
				System.out.println("Inserisci un valore accettabile");
			}
		} while (choice != 6);

		input.close();
		System.out.println("Ho chiuso il programma");
	}

	public static OrderItem createOrderItem() {
		Integer productId, quantity;
		try {
			System.out.println("Inserisci l'id del prodotto");
			productId = Integer.parseInt(input.nextLine());
			System.out.println("Inserisci la quantità");
			quantity = Integer.parseInt(input.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("Hai inserito quantià o product_id non validi");
			return null;
		}
		OrderItem orderItem = new OrderItem();
		orderItem.setId(productId);
		orderItem.setQuantity(quantity);

		return orderItem;
	}

	public static void addUser(User u) {
		EntityManager entityManager = FACTORY.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(u);
		entityTransaction.commit();
		entityManager.close();
	}

	public static void printUsers() {
		List<User> userList = getUsers();

		for (User u : userList) {
			System.out.println(u);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<User> getUsers() {
		EntityManager entityManager = FACTORY.createEntityManager();

		Query query = entityManager.createQuery("SELECT u FROM User AS u");
		List<User> userList = query.getResultList();

		entityManager.close();

		return userList;
	}

	public static List<User> getUser(String username) {
		EntityManager entityManager = FACTORY.createEntityManager();

		Query userQuery = entityManager.createNativeQuery("SELECT * FROM User WHERE username = :u", User.class);
		userQuery.setParameter("u", username);
		List<User> userList = userQuery.getResultList();

		entityManager.close();

		return userList;
	}

	@SuppressWarnings("unchecked")
	public static List<User> login(String username, String password) {
		EntityManager manager = FACTORY.createEntityManager();

		Query query = manager.createNativeQuery("SELECT * FROM user WHERE username = :u AND password = :p", User.class);
		query.setParameter("u", username);
		query.setParameter("p", password);

		List<User> users = query.getResultList();

		return users;
	}

	public static void printProducts() {
		List<Product> productList = getProducts();

		for (Product p : productList) {
			System.out.println(p);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Product> getProducts() {
		EntityManager entityManager = FACTORY.createEntityManager();

		Query query = entityManager.createQuery("SELECT p FROM Product AS p");
		List<Product> productList = query.getResultList();

		entityManager.close();

		return productList;
	}

	public static List<Product> getProduct(Integer idProduct) {
		EntityManager entityManager = FACTORY.createEntityManager();

		Query productQuery = entityManager.createNativeQuery("SELECT * FROM Product WHERE id = :id", Product.class);
		productQuery.setParameter("id", idProduct);
		List<Product> productList = productQuery.getResultList();

		entityManager.close();

		return productList;
	}

	public static void printOrders() {
		List<Order> orderList = getOrders();

		for (Order o : orderList) {
			System.out.println(o);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Order> getOrders() {
		EntityManager entityManager = FACTORY.createEntityManager();

		Query query = entityManager.createQuery("SELECT o FROM Order AS o");
		List<Order> orderList = query.getResultList();

		entityManager.close();

		return orderList;
	}

	public static void orderDetails() {
		printOrders();
		System.out.println("Scegli l'id di un ordine per visualizzarne i dettagli!");
		try {
			Integer choice = Integer.parseInt(input.nextLine());
			EntityManager entityManager = FACTORY.createEntityManager();

			// cerco l'ordine richiesto
			Query idQuery = entityManager.createNativeQuery("SELECT * FROM `order` WHERE id = :id", Order.class);
			idQuery.setParameter("id", choice);
			List<Order> orderList = idQuery.getResultList();

			if (orderList.isEmpty()) {
				System.out.println("nessun ordine trovato");
			} else {
				// se l'ordine esiste cerco l'utente ad esso associato
				Query userQuery = entityManager.createNativeQuery("SELECT * FROM User WHERE id = :id", User.class);
				userQuery.setParameter("id", orderList.get(0).getUserId());
				List<User> userList = userQuery.getResultList();

				System.out
						.println("L'utente che ha fatto l'ordine numero " + choice + " è " + userList.get(0).getName());

				Query itemQuery = entityManager.createNativeQuery("SELECT * FROM order_item WHERE id_order = :id",
						OrderItem.class);
				itemQuery.setParameter("id", orderList.get(0).getId());
				List<OrderItem> orderItems = itemQuery.getResultList();
				System.out.println(
						"-------------------------------------------------------------------------------------------------");
				System.out.println(
						"-------------------------------------------------------------------------------------------------");
				for (OrderItem o : orderItems) {
					System.out.println(o);
					Query productQuery = entityManager.createNativeQuery("SELECT * FROM Product WHERE id = :id",
							Product.class);
					productQuery.setParameter("id", o.getPruductId());
					List<Product> productList = productQuery.getResultList();
					System.out.println("Il product_id corrisponde al seguente prodotto : "
							+ productList.get(0).getName() + ", " + productList.get(0).getBrand() + ", "
							+ productList.get(0).getDescription() + ", " + productList.get(0).getPrice());
				}
				System.out.println(
						"-------------------------------------------------------------------------------------------------");
				System.out.println(
						"-------------------------------------------------------------------------------------------------");
				entityManager.close();
			}

		} catch (NumberFormatException ex) {
			System.out.println("Hai inserito un carattere non valido");

		}

	}

	public static HashMap<OrderItem, Product> itemToProduct(List<OrderItem> items) {
		EntityManager entityManager = FACTORY.createEntityManager();
		HashMap<OrderItem,Product> map = new HashMap<>();
		for (OrderItem o : items) {
			Query productQuery = entityManager.createNativeQuery("SELECT * FROM Product WHERE id = :id", Product.class);
			productQuery.setParameter("id", o.getPruductId());
			List<Product> productList = productQuery.getResultList();
			map.put(o, productList.get(0));
		}
		return map;
	}

	public static void orderCreation() {
		EntityManager entityManager = FACTORY.createEntityManager();

		System.out.println("Inserisci nome utente e password:");
		System.out.println("Username: ");
		String username = input.nextLine();
		System.out.println("Password: ");
		String psw = input.nextLine();
		Query userQuery = entityManager
				.createNativeQuery("SELECT * FROM User WHERE username = :username AND password = :psw", User.class);
		userQuery.setParameter("username", username);
		userQuery.setParameter("psw", psw);
		// mi ritorna o una lista vuota o lo user corrispondente
		List<User> userList = userQuery.getResultList();

		if (userList.isEmpty()) {
			System.out.println("Hai sbagliato username o psw");
			return;
		}
		Integer userId = userList.get(0).getId();
		// stampo a video i prodotti tra cui scegliere
		printProducts();

		System.out.println("Scegli i prodotti e le quantità da aggiungere all' oridine");
		List<OrderItem> items = new ArrayList<>();

		String answer;
		do {
			OrderItem temp = createOrderItem();
			if (temp != null)
				items.add(temp);
			else
				System.out.println("Inserisci dei valori validi !!!");

			System.out.println("Voui continuare ad aggiungere prodotti al tuo ordine?y/n");
			answer = input.nextLine();

		} while (answer.equals("y"));

		if (items.isEmpty()) {
			System.out.println("Sei un idiota");
			return;
		}

		Double tot = 0.0;

		for (OrderItem i : items) {
			tot += i.getPrice() * i.getQuantity();
		}

		Order order = new Order();
		order.setDate(LocalDateTime.now());
		order.setTotal(tot);
		order.setUserId(userId);

		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(order);
		trans.commit();
		List<Order> oList = entityManager.createNativeQuery("SELECT * FROM `order`", Order.class).getResultList();
		int lastIndex = oList.size() - 1;
		Order lastOrder = oList.get(lastIndex);
		Integer orderId = lastOrder.getId();

		trans = entityManager.getTransaction();
		trans.begin();
		for (OrderItem i : items) {
			i.setQuantity(orderId);
			entityManager.persist(i);
		}
		trans.commit();

		entityManager.close();
	}

}
