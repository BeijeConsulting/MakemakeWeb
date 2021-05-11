package it.beije.makemake.web.myEcomm.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.makemake.web.myEcomm.Controller;
import it.beije.makemake.web.myEcomm.entity.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterServlet() {
        super();
 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User newUser = new User();
		newUser.setName(request.getParameter("nome"));
		newUser.setSurname(request.getParameter("cognome"));
		newUser.setUsername(request.getParameter("username"));
		newUser.setPassword(request.getParameter("password"));
		String result = Controller.register(newUser);
		session.setAttribute("result", result);
		if(result.equals(Controller.MISSING_USERNAME_OR_PW)) {
			response.sendRedirect("Register.jsp");
		}else if(result.equals(Controller.USER_ALREADY_EXISTS)) {
			response.sendRedirect("Register.jsp");
		}else {
			response.sendRedirect("myLogin.jsp");
		}
			
		
	}

}
