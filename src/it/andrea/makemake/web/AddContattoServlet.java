package it.andrea.makemake.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.andrea.makemake.web.controller.Controller;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/AddContatto")
public class AddContattoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String htmlStart = "<HTML><HEAD><TITLE>MakemakeWeb</TITLE></HEAD><BODY>";
	private static final String htmlEnd = "</BODY></HTML>";
	
	private static Controller controller = new Controller();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddContattoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET " + request.getRequestURL());

		response.getWriter().append(htmlStart).append("<h1>Aggiungi un contatto</h1><br>")
				.append("<form action=\"test\" method=\"post\">\r\n"
						+ "		  <label for=\"nome\">Nome:</label><br>\r\n"
						+ "		  <input type=\"text\" name=\"nome\"><br>\r\n"
						+ "		  <label for=\"cognome\">Cognome:</label><br>\r\n"
						+ "		  <input type=\"text\" name=\"cognome\"><br>\r\n"
						+ "		  <label for=\"telefono\">Telefono:</label><br>\r\n"
						+ "		  <input type=\"text\" name=\"telefono\"><br>\r\n"
						+ "		  <label for=\"email\">Email:</label><br>\r\n"
						+ "		  <input type=\"text\" name=\"email\"><br><br>\r\n"
						+ "		  <input type=\"submit\" value=\"Aggiungi\">\r\n" + "		</form>")
				.append(htmlEnd);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("test POST");

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		
		controller.addContatto(nome, cognome, telefono, email);

		response.getWriter().append(htmlStart).append("nome : ").append(nome).append("<br>").append("cognome : ")
				.append(cognome).append("<br>").append("telefono : ").append(telefono).append("<br>").append("email : ")
				.append(email).append(htmlEnd);
	}

}
