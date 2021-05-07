package it.beije.makemake.web.Try;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.makemake.web.Try.Rubrica.RubricaManager;
import it.beije.makemake.web.Try.Rubrica.ContattoNoMap;
/**
 * Servlet implementation class ServletTry
 */
@WebServlet("/rubrica")
public class ServletTry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String htmlStart = "<HTML><HEAD><TITLE>MakemakeWeb</TITLE></HEAD><BODY>";
	private static final String htmlEnd = "</BODY></HTML>";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTry() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ContattoNoMap> contatti = new ArrayList<>();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RubricaManager.readContact();
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");	
		String email = request.getParameter("email");
		
		if(nome!= null) {
			contatti = RubricaManager.cercaContatto("nome", nome);
		}else if(cognome!= null) {
			contatti = RubricaManager.cercaContatto("cognome", cognome);
		}else if(email!=null) {
			contatti = RubricaManager.cercaContatto("email", email);
		}
		
		response.getWriter().append(htmlStart).append("<h1>Contatto</h1><br>")
		.append("")//vorrei metterci il contatto 
		.append(htmlEnd);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RubricaManager.readContact();
		//doGet(request, response);
	}
	
}
