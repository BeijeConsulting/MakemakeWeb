package it.andrea.makemake.web.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.andrea.makemake.web.controller.EcommerceController;
import it.andrea.makemake.web.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			session.setAttribute("errore", "INSERIRE LE CREDENZIALI");
			response.sendRedirect("login.jsp");
		} else {
			User user = EcommerceController.login(username, password);
			if (user == null) {
				session.setAttribute("errore", "CREDENZIALI ERRATE");
				response.sendRedirect("login.jsp");
			} else {
				session.setAttribute("loggedUser", user);
				response.sendRedirect("homepage.jsp");
			}
		}
	}
}
