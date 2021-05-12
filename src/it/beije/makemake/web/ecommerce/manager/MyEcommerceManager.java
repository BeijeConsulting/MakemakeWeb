package it.beije.makemake.web.ecommerce.manager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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

	public static void insertUser(String username, String name, String surname, String password) {
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

	public static String getOrderDetails(Order order) {
        StringBuilder output = new StringBuilder();
        EntityManager entityManager = SingletonJpa.getInstance();
        Integer id = order.getId();
        //append order info
        output.append("ORDER INFO: " + "\n");
        output.append(order.toString());
        //get data about user
        output.append("\n\nUSER INFO: " + "\n");
        User user = getUser(order.getUserId());
        output.append(user.toString());
        //get data about products
        output.append("\n\nPRODUCT INFO:\n-----------------------------------------------------\n");
        String selectOrderItem = "SELECT oi from OrderItem as oi WHERE oi.orderId =: id";
        Query query = entityManager.createQuery(selectOrderItem);
        query.setParameter("id", id);
        List<Order_item> orderItems = query.getResultList();
        for (Order_item orderItem: orderItems) {
            Integer productId = orderItem.getProductId();
            Product product = getProduct(productId);
            output.append(product.toString());
            output.append("\nOrdered amount: " + orderItem.getQuantity() + "\n");
            output.append("\nPrice amount: " + orderItem.getPrice()+ "\n");
            output.append("-----------------------------------------------------\n");
        }
        return output.toString();
    }
	//___________________________________________________________________
	public static void createNewOrder(int idUser, HashMap<Integer, Integer> products) {
		EntityManager entityManager = SingletonJpa.getInstance();
		User user = entityManager.find(User.class, idUser);
		if (user == null) {
			throw new IllegalArgumentException("Id utente non valido");
		}
		BigDecimal total = new BigDecimal(0);
		LocalDateTime dateTime = LocalDateTime.now();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Order order = new Order();
		order.setUserId(idUser);
		order.setDate(dateTime);
		order.setStatus("ok");
		order.setTotal(total);
		entityManager.persist(order);
		Integer orderId = order.getId();
		for (Integer productId : products.keySet()) {
			Product product = entityManager.find(Product.class, productId);
			if (product == null) {
				throw new IllegalArgumentException("Id prodotto " + productId + " non valido");
			}
			Order_item orderItem = new Order_item();
			orderItem.setOrderId(orderId);
			orderItem.setProductId(productId);
			orderItem.setQuantity(products.get(productId));
			orderItem.setPrice(product.getPrice());
			entityManager.persist(orderItem);
			total = total.add(product.getPrice().multiply(BigDecimal.valueOf(products.get(productId))));
		}
		order.setTotal(total);
		entityManager.merge(order);
		entityTransaction.commit();
	}
	
	//___________________________________________________________________
	public static String getOrderDetails(Integer orderId) {
		Order order = getOrder(orderId);
		return getOrderDetails(order);
	}
	//___________________________________________________________________
	private static User getUser(Integer userId) {
        EntityManager entityManager = SingletonJpa.getInstance();
        User user = entityManager.find(User.class, userId);
        return user;
    }
	//___________________________________________________________________
    private static Product getProduct(Integer productId) {
        EntityManager entityManager = SingletonJpa.getInstance();
        Product product = entityManager.find(Product.class, productId);
        return product;
    }
	//__________________________________________________________________
    private static Order getOrder(Integer orderId) {
    	EntityManager entityManager = SingletonJpa.getInstance();
        Order order = entityManager.find(Order.class, orderId);
        return order;
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
				//dettaglioOrdine();
				break;
			case "5":
				//orderCreation();
				break;
			case "6":
				f = false;
				break;
			default:
			}
		} while (f == true);

	}

}
