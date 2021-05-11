package it.beije.makemake.ecommerce;

import java.io.IOException;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.makemake.JPASingleton;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/ecommerce/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		User user= null;
		if (username == null || username.length() == 0 || password == null || password.length() == 0||!EcommerceManager.exists(username)) {
			session.setAttribute("errore", "INSERIRE LE CREDENZIALI");
			response.sendRedirect("login.jsp");
			return;
		}
		user = EcommerceManager.getUser(username);
		
		
		
		if (user.getPassword().equals(password)) {
			session.setAttribute("loggedUser", user);
			response.sendRedirect("benvenuto.jsp");

		} else {
			session.setAttribute("errore", "CREDENZIALI ERRATE");
			response.sendRedirect("login.jsp");
		}
	
	}

}
