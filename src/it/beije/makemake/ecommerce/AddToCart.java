package it.beije.makemake.ecommerce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/ecommerce/addtocart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer productId = null;
		Integer quantity = null;
		try {
			productId = Integer.parseInt(request.getParameter("product"));
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch (NumberFormatException e) {
			session.setAttribute("errore", "Inserire valori accettabili");
			response.sendRedirect("chooseproduct.jsp");
			return;
		}
		List<Product> products = Ecommerce.getProduct(productId);
		if (products.isEmpty()) {
			session.setAttribute("errore", "Prodotto inesistente");
			response.sendRedirect("chooseproduct.jsp");
			return;
		}
		if (quantity > products.get(0).getQuantity()) {
			session.setAttribute("errore", "Quantità non disponibile");
			response.sendRedirect("chooseproduct.jsp");
			return;
		}
		List<OrderItem> cart;
		if (session.getAttribute("cart") != null) {
			cart = (List<OrderItem>) session.getAttribute("cart");
			session.removeAttribute("cart");
		} else {
			cart = new ArrayList<OrderItem>();
		}
		OrderItem item = new OrderItem();
		item.setPruductId(productId);
		item.setQuantity(quantity);
		item.setPrice(quantity * products.get(0).getPrice());
		cart.add(item);
		session.setAttribute("cart", cart);
		response.sendRedirect("menu.jsp");
	}
}
