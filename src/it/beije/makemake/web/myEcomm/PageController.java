package it.beije.makemake.web.myEcomm;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.makemake.web.myEcomm.entity.User;

public class PageController {
	public static boolean checkLogin(User user, HttpSession session , HttpServletResponse response,String entryPoint) throws IOException{
		if(user.getUsername()== null){
			session.setAttribute("entryPoint", entryPoint);
			session.setAttribute("errore", "Devi prima accedere per continuare");
			response.sendRedirect("myLogin.jsp");
			return false;
		}
		return true;
	}
}
