package it.beije.makemake.web.rubrica;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.makemake.web.ecommerce.manager.MyEcommerceManager;
import it.beije.makemake.web.esercizi.SingletonJpa;
import it.beije.makemake.web.rubrica.utility.RubricaUtils;

@WebServlet("/MainRubrica")
public class MainRubrica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainRubrica() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager entityManager=SingletonJpa.getInstance();


		System.out.println("GET " + request.getRequestURL());
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		System.out.println(nome);
		System.out.println(cognome);
		System.out.println(telefono);
		System.out.println(email);

		RubricaUtils.inserisciContatto( nome, cognome, email, telefono);
		
		response.sendRedirect("ComeBack.html");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST " + request.getRequestURL());

		EntityManager entityManager=SingletonJpa.getInstance();


		final String operazione = request.getParameter("operazione");
		System.out.println(operazione);

		switch (operazione) {
		case "visalizza":
			response.sendRedirect("visualizzazioneOrdine.jsp");
			break;

		case "ricerca":
			break;

		case "aggiungi":
			break;
		}
	}

	
	
}