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
 * Servlet implementation class EcommerceServlet
 */
@WebServlet("/ecommerce")
public class EcommerceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EcommerceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//System.out.println("username : " + username);
		//System.out.println("password : " + password);
		
		
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			session.setAttribute("errore", "INSERIRE LE CREDENZIALI");
			response.sendRedirect("myLogin.jsp");
		}else{
			User u = Controller.login(username,password);
			if(u == null) {
				session.setAttribute("errore", "CREDENZIALI ERRATE");
				response.sendRedirect("myLogin.jsp");
			}else{
				session.setAttribute("loggedUser", u);
				response.sendRedirect("Welcome.jsp");
			}
		}
		
	}

}
