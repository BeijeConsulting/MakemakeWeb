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
 * Servlet implementation class ModifyAccountServlet
 */
@WebServlet("/modifyAccount")
public class ModifyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyAccountServlet() {
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

		User oldUser = (User) session.getAttribute("loggedUser");
		User newUser = new User();
		newUser.setName(request.getParameter("name"));
		newUser.setSurname(request.getParameter("surname"));
		newUser.setUsername(request.getParameter("username"));
		newUser.setPassword(request.getParameter("password"));
		
		String registrationResult = EcommerceController.modify(oldUser, newUser);
		session.setAttribute("registrationResult", registrationResult);
		if (registrationResult.equals(EcommerceController.MISSING_USERNAME_OR_PW)) {
			response.sendRedirect("modifyAccount.jsp");
		} else if (registrationResult.equals(EcommerceController.USER_ALREADY_EXISTS)) {
			response.sendRedirect("modifyAccount.jsp");
		} else {
			session.setAttribute("loggedUser", newUser);
			response.sendRedirect("homepage.jsp");
		}
	}

}
