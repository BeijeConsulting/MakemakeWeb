package it.beije.makemake.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecommerce.ControlllerEcommerce;
import ecommerce.User;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User u = new User();

		u.setUsername(request.getParameter("username"));
		u.setName(request.getParameter("nome"));
		u.setPassword(request.getParameter("password"));
		u.setSurname(request.getParameter("cognome"));

		ControlllerEcommerce.inserisciUtente(u);
		
		response.sendRedirect("benvenuto.jsp");
	}

}
