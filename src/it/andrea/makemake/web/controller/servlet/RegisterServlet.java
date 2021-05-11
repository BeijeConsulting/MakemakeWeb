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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		String registrationResult = EcommerceController.register(user);
		session.setAttribute("registrationResult", registrationResult);
		if (registrationResult.equals(EcommerceController.MISSING_USERNAME_OR_PW)) {
			response.sendRedirect("register.jsp");
		} else if (registrationResult.equals(EcommerceController.USER_ALREADY_EXISTS)) {
			response.sendRedirect("register.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
