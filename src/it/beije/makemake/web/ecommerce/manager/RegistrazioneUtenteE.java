package it.beije.makemake.web.ecommerce.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.makemake.web.ecommerce.User;

/**
 * Servlet implementation class RegistrazioneUtenteE
 */
@WebServlet("/registration")
public class RegistrazioneUtenteE extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegistrazioneUtenteE() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		User user;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		if (username == null || username.length() == 0 || password == null || password.length() == 0 ||
				name == null || name.length() == 0 || surname == null || surname.length() == 0 ) {
			session.setAttribute("errore", "credenziali non idonee");
			response.sendRedirect("registrazioneEcommerce.jsp");
			return;
		}else{
		user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setSurname(surname);
		user.setPassword(password);		
		MyEcommerceManager.insertUser(username, name, surname, password);

		session.setAttribute("loggedUser", user);


		response.sendRedirect("registration.jsp");
	}

}
}
