package it.beije.makemake.ecommerce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistratiServlet
 */
@WebServlet("/RegistratiServlet")
public class RegistratiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistratiServlet() {
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
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		User user = null;
		if (username == null || username.length() == 0 || password == null || password.length() == 0 || name == null
				|| name.length() == 0 || surname == null || surname.length() == 0) {
			if (EcommerceManager.exists(username)) {
				request.getSession().setAttribute("errore", "nome utente già esistente");
			}else {
				request.getSession().setAttribute("errore", "Compilare tutti i campi");
			}
			response.sendRedirect("registrati.jsp");
			return;
		}
		EcommerceManager.insertUser(user);

	}

}
