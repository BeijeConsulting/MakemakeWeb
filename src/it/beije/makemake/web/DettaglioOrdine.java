package it.beije.makemake.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.boot.model.relational.Loggable;

import ecommerce.ControlllerEcommerce;
import ecommerce.OrderItem;
import ecommerce.User;

/**
 * Servlet implementation class DettaglioOrdine
 */
@WebServlet("/dettaglio")
public class DettaglioOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioOrdine() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User b = (User) session.getAttribute("loggedUser");
		int i = Integer.parseInt(request.getParameter("scelta"));
		System.out.println(i);
		List<OrderItem> a = b.getOrders().get(i).getOrdersItem();
		String out ="";
			for (OrderItem orderItem : a) {
				 out += orderItem.toString() + "<br>";
			}
			
			 session.setAttribute("ordine", out);
		
		response.sendRedirect("riepilogo_ordini.jsp");

		
	}

}
