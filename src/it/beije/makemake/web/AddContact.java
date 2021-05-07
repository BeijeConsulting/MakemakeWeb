package it.beije.makemake.web;

import java.io.IOException;
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
 * Servlet implementation class TestServlet
 */
@WebServlet("/aggiungi")
public class AddContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContact() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET " + request.getRequestURL());
		response.sendRedirect("aggiungi.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test POST");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		Contatto c = new Contatto(fname,lname,telephone,email);
		Session session = SessionManager.getSession();
		try {
			HCriteria.addContact(c,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SessionManager.close(session);

		response.sendRedirect("risposta.html");
		//response.getWriter().append(htmlStart).append("CONTATTO AGGIUNTO").append(htmlEnd);
	}

}
