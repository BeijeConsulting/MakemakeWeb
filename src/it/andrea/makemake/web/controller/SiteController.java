package it.andrea.makemake.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.andrea.makemake.web.entity.User;

public class SiteController {
	public static boolean isLogged(User user, String entryPoint, HttpSession session, HttpServletResponse response)
			throws IOException {
		if (user.getUsername() == null) {
			session.setAttribute("entryPoint", entryPoint);
			session.setAttribute("errore", "Devi accedere per continuare");
			response.sendRedirect("login.jsp");
			return false;
		}
		return true;
	}
}
