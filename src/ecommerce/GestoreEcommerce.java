package ecommerce;

import java.util.ArrayList;



public class GestoreEcommerce {
	
	//attributi
	private ArrayList<Order> listaOrder = new ArrayList<Order>();
	private ArrayList<OrderItem> listaOrderItem = new ArrayList<OrderItem>();	
	private ArrayList<Product > listaProduct = new ArrayList<Product>();	
	private ArrayList<User> listaUser = new ArrayList<User>();

	//getter
	public ArrayList<Order> getListaOrder() {
		return listaOrder;
	}
	public ArrayList<OrderItem> getListaOrderItem() {
		return listaOrderItem;
	}
	public ArrayList<Product> getListaProduct() {
		return listaProduct;
	}
	public ArrayList<User> getListaUser() {
		return listaUser;
	}
	
	//gestione lista Order
	public  void caricaOrder(Order obj) {
		if(!(this.orderEsistente(obj)))
			this.listaOrder.add(obj);	
	}
	public  void caricaOrder(ArrayList<Order> l) {
		for (Order order : l) {
			this.caricaOrder(order);
		}	
	}
	public boolean orderEsistente(Order o) {
		boolean esiste= false;
		for (Order order : this.getListaOrder()) {
			if(o.equals(order)) {
				esiste = true;	
			}				
		}
		return esiste;
	}
	public String stringaListaOrder() {
		String s="[Lista Ordini] \n";
		for (Order order : this.getListaOrder()) {
			s+="\t" + order.toString()+"\n";
		}
		return s;
	}
	public Order getOrderbyId(Integer parametroId) {
		for (Order order : this.getListaOrder()) {
			if(order.getId().equals(parametroId)) {
				return order;
			}
		}
		return null;
	}
	//gestione lista OrderItem
 	public void caricaOrderItem(OrderItem obj) {
		if(!(this.orderItemEsistente(obj)))
			this.listaOrderItem.add(obj);		
	}
	public void caricaOrderItem(ArrayList<OrderItem> l) {
		for (OrderItem orderItem : l) {
			this.caricaOrderItem(orderItem);
		}
		
	}
	public boolean orderItemEsistente(OrderItem o) {
		boolean esiste = false;
		for (OrderItem order : this.getListaOrderItem()) {
			if(o.equals(order)) {
				esiste = true;
			}
		}
		return esiste;
	}
	public String stringaListaOrderItem() {
		String s = "[Lista Order Item] \n";
		for (OrderItem orderItem : this.getListaOrderItem()) {
			s+="\t" + orderItem.toString()+"\n";
		}
		return s;
	}
	public OrderItem getOrderItembyId(Integer parametroId) {
		for (OrderItem orderItem : this.getListaOrderItem()) {
			if(orderItem.getId().equals(parametroId)) {
				return orderItem;
			}
		}
		return null;
	}
	//gestione lista Product

 	public void caricaProduct(Product obj) {
		if(!(this.ProductEsistente(obj)))
			this.listaProduct.add(obj);
	}
	public void caricaProduct(ArrayList<Product> l) {
		for (Product product : l) {
			this.caricaProduct(product);
		}
	}
	public boolean ProductEsistente(Product o) {
		boolean esiste = false;
		for (Product product : this.getListaProduct()) {
			if(o.equals(product)) {
				esiste = true;
			}
		}
		return esiste;
	}
	public String stringaListaProduct() {
		String s = "[Lista Product] \n";
		for (Product product : this.getListaProduct()) {
			s+="\t" + product.toString()+"\n";
		}
		return s;
	}
	public Product getProductbyId(Integer parametroId) {
		for (Product product : this.getListaProduct()) {
			if(product.getId().equals(parametroId)) {
				return product;
			}
		}
		return null;
	}
	//gestione lista User
	public void caricaUser(User obj) {
		this.listaUser.add(obj);
	}
	public void caricaUser(ArrayList<User> l) {
		for (User user : l) {
			this.caricaUser(user);
		}
	}
	public boolean userEsistente(User o) {
		boolean esiste = false;
		for (User user : this.getListaUser()) {
			if(o.equals(user)) {
				esiste = true;
			}
		}
		return esiste;
	}
	public String stringaListaUser() {
		String s = "[Lista User] \n";
		for (User user : this.getListaUser()) {
			s+="\t" + user.toString()+"\n";
		}
		return s;
	}
	public User getUserbyId(Integer parametroId) {
		for (User user : this.getListaUser()) {
			if(user.getId().equals(parametroId)) {
				return user;
			}
		}
		return null;
	}
	public User getUserByCredential(String username, String password) {
		User u = null;
		for (User user : this.getListaUser()) {
			if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				u = user;
			}
		}
		return u;
	}
	//metodi
	@Override
 	public String toString() {
		return "GestoreEcommerce [" +"\n"
				+ stringaListaOrder()+"\n" 
				+ stringaListaOrderItem()+"\n"
				+ stringaListaProduct()+"\n"  
				+ stringaListaUser() +"\n" + "]";
	}
	public String getOrderDetail(Integer parametroId) {
		
		Order ordine = this.getOrderbyId(parametroId);
		String dettagli=ordine.toString() + "\n";
		for (OrderItem order : ordine.getOrdersItem()) {
			dettagli+= order.toString() +"\n";
		}
		return dettagli;
	}
	public String getuUserDetail(Integer parametroId) {
		
		User user = this.getUserbyId(parametroId);
		String dettagli=user.toString() + "\n";
		for (Order order : user.getOrders()) {
			dettagli+= order.toString() +"\n";
		}
		return dettagli;
	}

}
