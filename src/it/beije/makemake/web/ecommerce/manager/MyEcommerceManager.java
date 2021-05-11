package it.beije.makemake.web.ecommerce.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.makemake.web.ecommerce.Order;
import it.beije.makemake.web.ecommerce.Order_item;
import it.beije.makemake.web.ecommerce.Product;
import it.beije.makemake.web.ecommerce.User;
import it.beije.makemake.web.esercizi.SingletonJpa;
import it.beije.makemake.web.rubrica.ContattoRubrica;

public class MyEcommerceManager { // metodi per gestire db ecommerce

	private static Scanner in = new Scanner(System.in);

	public static List<User> selectUtenti() {// visualizza utenti
		EntityManager entityManager = SingletonJpa.getInstance();
		String select = "SELECT c FROM User as c";
		Query query = entityManager.createQuery(select);
		List<User> user = query.getResultList();

		for (User utente : user) {
			System.out.println("id : " + utente.getId());
			System.out.println("username : " + utente.getUsername());
			System.out.println("name : " + utente.getName());
			System.out.println("surname : " + utente.getSurname());
			System.out.println("password : " + utente.getPassword());
			System.out.println("-----------------------------");

		}
		return user;
		
	}

	public static void insertUser(String username, String name, String surname , String password) {
		EntityManager entityManager = SingletonJpa.getInstance();

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

	
	
	public static List<Product> selectProdotti() {// visualizza prodotti
		EntityManager entityManager = SingletonJpa.getInstance();
		String select = "SELECT c FROM Product as c";
		Query query = entityManager.createQuery(select);
		List<Product> product = query.getResultList();

		for (Product prodotto : product) {
			System.out.println("id : " + prodotto.getId());
			System.out.println("name : " + prodotto.getName());
			System.out.println("brand : " + prodotto.getBrand());
			System.out.println("description : " + prodotto.getDescription());
			System.out.println("price : " + prodotto.getPrice());
			System.out.println("quantity : " + prodotto.getQuantity());
			System.out.println("-----------------------------");
		}
		return product;
	}

	public static Order_item createOrderItem() {// crea un prodotto
		Integer productId, quantity;

		try {
			System.out.println("inserisci l'id del prodotto");
			productId = Integer.parseInt(in.nextLine());
			System.out.println("inserisci la quantita del prodotto");
			quantity = Integer.parseInt(in.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("hai inserito quantita o product_id non validi");
			return null;
		}
		Order_item orderItem = new Order_item();
		orderItem.setId(productId);
		orderItem.setQuantity(quantity);
		return orderItem;

	}

	public static void selectOrdini() { // mostra tutti gli ordini

		EntityManager entityManager = SingletonJpa.getInstance();
		String select = "SELECT c FROM Order as c";
		Query query = entityManager.createQuery(select);
		List<Order> order = query.getResultList();

		for (Order ordini : order) {
			System.out.println("data : " + ordini.getDate());
			System.out.println("id utente : " + ordini.getUserId());
			System.out.println("stato ordine : " + ordini.getStatus());
			System.out.println("totale ordine : " + ordini.getTotal());
			System.out.println("-----------------------------");
		}
	}

	public static void dettaglioOrdine() throws NumberFormatException {
		selectOrdini();
		System.out.println("inserisi l id dell ordine per visualizzarne i dettagli");

		try {
			Integer choice = Integer.parseInt(in.nextLine());
			EntityManager entityManager = SingletonJpa.getInstance();

			// cerco l ordine richiesto
			Query query = entityManager.createNativeQuery("SELECT * FROM `order` WHERE id=?", Order.class);
			query.setParameter(1, choice);
			System.out.println(query.getResultList());

			List<Order> orderList = query.getResultList();

			if (orderList.isEmpty()) {
				System.out.println("nessun ordine trovato");
			} else {
				// se l ordine esiste cerco l utente ad esso associato
				Query queryNome = entityManager.createNativeQuery("SELECT * FROM User  WHERE id=?", User.class);
				queryNome.setParameter(1, orderList.get(0).getUserId());
				List<User> userList = queryNome.getResultList();
				System.out.println("l utent che ha fatto l orine numero  " + choice + " e' " + userList.get(0).getName()
						+ " " + userList.get(0).getSurname());

				Query itemQuery = entityManager.createNativeQuery("SELECT * FROM order_item WHERE id_order=?",
						Order_item.class);
				itemQuery.setParameter(1, orderList.get(0).getId());
				List<Order_item> orderItems = itemQuery.getResultList();

				for (Order_item o : orderItems) {
					System.out.println(o);
					Query productQuery = entityManager.createNativeQuery("SELECT * FROM product WHERE id=?",
							Product.class);
					productQuery.setParameter(1, o.getProductId());
					List<Product> productList = productQuery.getResultList();
					System.out.println("il product_id corrispnde al seguente prodotto : " + productList.get(0).getName()
							+ ", " + productList.get(0).getBrand() + ", " + productList.get(0).getPrice() + "€");
				}
			}
			entityManager.close();
		} catch (NumberFormatException e) {
			System.out.println("input non valido");
			
}

	}
//da sistemare
	public static void orderCreation() {
		EntityManager entityManager = SingletonJpa.getInstance();
		System.out.println("inserisi nome utente e password");
		System.out.println("Username: ");
		String username = in.nextLine();
		System.out.println("Password: ");
		String psw = in.nextLine();
		Query userQuery = entityManager
				.createNativeQuery("SELECT * FROM user WHERE username=:username AND password =:psw=?", User.class);
		userQuery.setParameter("username", username);
		userQuery.setParameter("psw", psw);

		// mi ritorna o una lista vuota o lo user corripondente
		List<User> userList = userQuery.getResultList();

		if (userList.isEmpty()) {
			System.out.println("hai sbagliato username o password");
			return;
		}

		Integer userId = userList.get(0).getId();

		// stampo a video i prodotti tra cui scegliere
		selectProdotti();

		System.out.println("scegli i prodotti e le quantita' da aggiungere all'ordine");
		List<Order_item> items = new ArrayList<>();
		String answer;
		do {
			Order_item temp = createOrderItem();
			if (temp != null)
				items.add(temp);
			else
				System.out.println("inserisci valori validi");

			System.out.println("vuoi continuare ad aggiungere prodotti al tu ordine? y/n");
			answer=in.nextLine();
		} while (answer.equals("y"));

		if (items.isEmpty()) {
			System.out.println("lista vuota");
			return;
		}
		Double tot = 0.0;
		for (Order_item i : items) {
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
		
		EntityTransaction trans2 = entityManager.getTransaction();
		trans.begin();
		for (Order_item i : items) {
			i.setQuantity(orderId);
			entityManager.persist(i);
		}
		trans.commit();

		entityManager.close();
	}

	public static void main(String[] args) {
		
		boolean f = true;
		String s;
		do {
			System.out.println("---------------------------------------------------");
			System.out.println("Benvenuto, scegli l operazione che vuoi effettuare?");
			System.out.println("1) visualizza tutti gli utenti");
			System.out.println("2) visualizza tutti i prodotti");
			System.out.println("3) visualizza tutti gli ordini");
			System.out.println("4) visualizza il dettaglio di un ordine");
			System.out.println("5) crea un ordine");
			System.out.println("6) esci");
			System.out.println("---------------------------------------------------");
			s = in.nextLine();
			switch (s) {

			case "1":
				selectUtenti();
				break;
			case "2":
				selectProdotti();

				break;
			case "3":
				selectOrdini();
				break;
			case "4":
				dettaglioOrdine();
				break;
			case "5":
				orderCreation();
				break;
			case "6":
				f = false;
				break;
			default:
			}
		} while (f == true);

	}

}
