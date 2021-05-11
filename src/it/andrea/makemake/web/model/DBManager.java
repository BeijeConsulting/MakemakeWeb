package it.andrea.makemake.web.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import it.andrea.makemake.web.entity.Order;
import it.andrea.makemake.web.entity.OrderItem;
import it.andrea.makemake.web.entity.Product;
import it.andrea.makemake.web.entity.User;

public class DBManager {
	public List<User> getAllUsers() {
		EntityManager em = SingleEntityManager.getInstance();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		List<User> result = query.getResultList();
		return result;
	}

	public List<Product> getAllProducts() {
		EntityManager em = SingleEntityManager.getInstance();
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
		List<Product> result = query.getResultList();
		return result;
	}

	public void viewOrder(int orderId) {
		Order order = getOrderById(orderId);
		User user = getUserById(order.getUserId());

		System.out.println("ID ordine: " + order.getId());
		System.out.println("Spesa totale: " + order.getTotal() + "€");
		System.out.println("Username acquirente: " + user.getUsername());
		System.out.println("Elenco prodotti:");
		for (OrderItem orderItem : order.getOrderItems()) {
			Product product = getProductById(orderItem.getProductId());

			System.out.println(product.getName());
			System.out.println(product.getBrand());
			System.out.println("Prezzo per singolo prodotto: " + product.getPrice() + "€");
			System.out.println("Quantita' acquistata: " + orderItem.getQuantity());
		}
	}

	public void createOrder(User user, List<OrderItem> orderItems) {
		EntityManager em = SingleEntityManager.getInstance();

		Order order = new Order();
		order.setDate(LocalDateTime.now());
		order.setUserId(user.getId());
		order.setStatus("Ordine effettuato");
		order.setTotal(BigDecimal.ZERO);

		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.persist(order);
		entityTransaction.commit();
//			System.out.println(order.getId());

		BigDecimal total = BigDecimal.ZERO;
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(order.getId());
			Product product = getProductById(orderItem.getProductId());
			orderItem.setPrice(product.getPrice());
			product.setQuantity(product.getQuantity() - orderItem.getQuantity());
			entityTransaction.begin();
			em.persist(orderItem);
			em.merge(product);
			entityTransaction.commit();
			total = total.add(orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
//				System.out.println(orderItem.getId());
		}

		order.setTotal(total);
		entityTransaction.begin();
		em.persist(order);
		entityTransaction.commit();
	}

//	----------------------------------------------------------------------
	public User getUserByUsernameAndPassword(String username, String password) {
		EntityManager em = SingleEntityManager.getInstance();
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
		User result;
		try {
			result = query.setParameter("username", username).setParameter("password", password).getSingleResult();
		} catch (NoResultException noRes) {
			em.close();
			return null;
		}
		em.close();
		return result;
	}

	public User getUserById(int id) {
		EntityManager em = SingleEntityManager.getInstance();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
		User result = query.setParameter("id", id).getSingleResult();
		return result;
	}

	public Product getProductById(int id) {
		EntityManager em = SingleEntityManager.getInstance();
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class);
		Product result = query.setParameter("id", id).getSingleResult();
		return result;
	}

	public Order getOrderById(int id) {
		EntityManager em = SingleEntityManager.getInstance();
		TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.id = :id", Order.class);
		Order result = query.setParameter("id", id).getSingleResult();
		return result;
	}

	public void close() {
		EntityManager em = SingleEntityManager.getInstance();
		em.close();
	}

	public void addUser(User user) throws PersistenceException {
		EntityManager em = SingleEntityManager.getInstance();

		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		try {
			em.persist(user);
		} finally {
			em.close();
		}
		entityTransaction.commit();
		em.close();
	}
}
