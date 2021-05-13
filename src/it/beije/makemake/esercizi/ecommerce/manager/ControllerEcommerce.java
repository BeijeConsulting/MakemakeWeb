package it.beije.makemake.esercizi.ecommerce.manager;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import it.beije.makemake.esercizi.ecommerce.JPAManager;
import it.beije.makemake.esercizi.ecommerce.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginE")
public class ControllerEcommerce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.sendRedirect("login.jsp");
//	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			session.setAttribute("errore", "INSERIRE LE CREDENZIALI");
			response.sendRedirect("loginEcommerce.jsp");
			return;
		}
		
		//... SELECT * FROM USER WHERE USERNAME = 'XXX' AND PASSWORD = 'YYY'
		
		List<User> userList = JPAManager.getUser();
		User user;
		for (int i=0 ; i<userList.size(); i++) {
			if (userList.get(i).getUsername().equalsIgnoreCase(username) && userList.get(i).getPassword().equals(password)) {
		
			user = new User();
			user.setUsername(username);
			user.setName(userList.get(i).getName());
			user.setSurname(userList.get(i).getSurname());

			session.setAttribute("loggedUser", user);

			response.sendRedirect("homePage.jsp");
			return;
		} 
			else if( i==userList.size()-1){
			session.setAttribute("errore", "CREDENZIALI ERRATE");
			response.sendRedirect("loginEcommerce.jsp");
			return;
		}
	
	}

}
}
	

