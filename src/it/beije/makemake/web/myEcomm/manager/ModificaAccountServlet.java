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
 * Servlet implementation class ModificaAccountServlet
 */
@WebServlet("/modificaAccount")
public class ModificaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ModificaAccountServlet() {
        super();
     
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User oldUser = (User) session.getAttribute("loggedUser");
		User newUser = new User();
		newUser.setName(request.getParameter("nome"));
		newUser.setSurname(request.getParameter("cognome"));
		newUser.setUsername(request.getParameter("username"));
		newUser.setPassword(request.getParameter("password"));
		
		String result = Controller.modificaUtente(oldUser ,newUser);
		session.setAttribute("result", result);
		if(result.equals(Controller.MISSING_USERNAME_OR_PW)) {
			response.sendRedirect("modificaAccount.jsp");
		}else if(result.equals(Controller.USER_ALREADY_EXISTS)) {
			response.sendRedirect("modificaAccount.jsp");
		}else {
			session.setAttribute("loggedUser", newUser);
			response.sendRedirect("Welcome.jsp");
		}
			
		
	}
	}


