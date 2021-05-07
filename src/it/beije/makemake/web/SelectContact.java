package it.beije.makemake.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import rubrica.Contatto;
import rubrica.HCriteria;
import session.SessionManager;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/select")
public class SelectContact extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("GET " + request.getRequestURL());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Session session = SessionManager.getSession();
		StringBuilder contatti1 = new StringBuilder();
		try {
			List<Contatto> contatti = HCriteria.selectAll(session);
			for(Contatto c1: contatti)
					contatti1.append(c1.toString()+"<br/>");
		} catch (Exception e) {
			e.printStackTrace();
		}SessionManager.close(session);
		response.getWriter().append("<html>").append(contatti1).append("<form action=\"ModifyContact\" method=\"post\"><input type=\"submit\" value=\"Modifica contatti\"></form></html>");
	}

}
