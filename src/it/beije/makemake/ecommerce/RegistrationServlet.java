package it.beije.makemake.ecommerce;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/ecommerce/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");

		HttpSession session = request.getSession();

		if (username == null || username.length() == 0 || password == null || password.length() == 0 || nome == null
				|| nome.length() == 0 || cognome == null || cognome.length() == 0) {
			session.setAttribute("errore", "Inserire tutti i campi");
			response.sendRedirect("registration.jsp");
			return;
		}

		List<User> users = Ecommerce.getUser(username);
		if(!users.isEmpty()) {
			session.setAttribute("errore", "Username già in uso");
			response.sendRedirect("registration.jsp");
			return;
		}
		User user = new User();
		user.setName(nome);
		user.setSurname(cognome);
		user.setUsername(username);
		user.setPassword(password);
		Ecommerce.addUser(user);
		response.sendRedirect("login.jsp");
	}
}
