package it.beije.makemake.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import it.beije.makemake.ecommerce.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			session.setAttribute("errore", "Inserire i campi per le credenziali");
			response.sendRedirect("login.jsp");
			return;
		}
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Makemake");
		EntityManager manager = factory.createEntityManager();
		
		Query query = manager.createNativeQuery("SELECT * FROM user WHERE username = :u AND password = :p", User.class);
		query.setParameter("u", username);
		query.setParameter("p", password);
		
		List<User> users = query.getResultList();
		
		if(users.isEmpty()) {
			session.setAttribute("errore", "CREDENZIALI ERRATE");
			response.sendRedirect("login.jsp");
		}else {
			session.setAttribute("logged", users.get(0));
			response.sendRedirect("menu.jsp");
		}
	}

}
