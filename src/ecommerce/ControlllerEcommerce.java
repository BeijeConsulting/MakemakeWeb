package ecommerce;

import java.util.List;

public class ControlllerEcommerce {
	private static GestoreEcommerce gestoreE = new GestoreEcommerce();
	private static JpaManager jpaManager = new JpaManager();
	
	static {
		gestoreE.caricaOrder(jpaManager.selectAllOrder());
		gestoreE.caricaOrderItem(jpaManager.SelectAllOrderItem());
		gestoreE.caricaProduct(jpaManager.SelectAllProduct());
		gestoreE.caricaUser(jpaManager.SelectAllUser());
	}
	
	public static User verificaCredenziali(String username,String password) {
		return gestoreE.getUserByCredential(username, password);
	}
	public static List<OrderItem> dettagliOrdine(int idOrdine) {
		return  gestoreE.getOrderbyId(idOrdine).getOrdersItem();
	}
	public static List<Order> dettagliUtente(int idUtente) {
		return gestoreE.getUserbyId(idUtente).getOrders();
	}
	public static void inserisciUtente(User u) {
		jpaManager.insert(u);
		gestoreE.caricaUser(u);
	}
	public static String stringaOrdiniUtente(int id) {
		return gestoreE.getuUserDetail(id);
		
	}
}
