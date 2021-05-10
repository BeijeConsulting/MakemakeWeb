package it.beije.makemake.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rubrica.Contatto;
import rubrica.JpaManager;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String htmlStart = "<HTML><HEAD><TITLE>MakemakeWeb</TITLE></HEAD><BODY>";
	private static final String htmlEnd = "</BODY></HTML>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET " + request.getRequestURL());
		
		response.getWriter().append(htmlStart).append("<h1>Sono un Titolo</h1><p>questa è una prova</p><br>")
		.append("<form action=\"test\" method=\"post\">\r\n" + 
				"		  <label for=\"nome\"> Nome: </label><br>\r\n" + 
				"		  <input type=\"text\" name=\"nome\"><br>\r\n" + 
				
				"		  <label for=\"cognome\"> Cognome: </label><br>\r\n" + 
				"		  <input type=\"text\" name=\"cognome\"><br>\r\n" + 
				
				"		  <label for=\"telefono\"> Telefono: </label><br>\r\n" + 
				"		  <input type=\"text\" name=\"telefono\"><br>\r\n" + 
				
				"		  <label for=\"mail\"> Mail: </label><br>\r\n" + 
				"		  <input type=\"text\" name=\"mail\"><br><br>\r\n" + 
				
				"		  <input type=\"submit\" value=\"Inserisci\">\r\n" + 
				"		</form>")
		.append(htmlEnd);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test POST");
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		String mail = request.getParameter("mail");
		System.out.println("Nome : " + nome);
		System.out.println("Cognome : " + cognome);
		System.out.println("Telefono :" + telefono);
		System.out.println("Mail" + mail);
		Contatto c = new Contatto(nome, cognome, telefono, mail);
		
		JpaManager.insert(c);
		
		response.getWriter().append(htmlStart)
		.append("nome : ").append(nome).append("<br>").append("cognome : ").append(cognome)
		.append(htmlEnd);
	}

}
