package it.beije.makemake.web;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet("/ServletRubrica")
public class ServletRubrica extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public  ServletRubrica() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET " + request.getRequestURL());
	doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST " + request.getRequestURL());
		
		JPASingleton.getInstance();
		EntityManager entityManager = JPASingleton.getEntityManager();
		
		String operazione = request.getParameter("operazione");
		System.out.println(operazione);
		
		response.getWriter().append(operazione);
		switch(operazione) {
		case "aggiungi":
			
			inserisciContatto(entityManager);
			break;
			
		case "visualizza":
			
			response.getWriter().append(mostraRubrica(entityManager));
			break;
		case "cerca":
			
			break;
		}
		//response.sendRedirect("risposta.html");
	}

	
	// MODIFICA CONTATTO ______________________________________________________________________________________________________
	public static void modificaContatto(EntityManager entityManager) {
		
		Contatto c = entityManager.find(Contatto.class, 20);
		System.out.println(c);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			c.setNome("Gianluca");
			c.setCognome("Bertolasi");
			c.setTelefono("55673495");
			c.setEmail("g.bertolasi@gmail.com");
			entityManager.persist(c);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}
		
		
	}
	// INSERIMENTO CONTATTO ______________________________________________________________________________________________________
	public static void inserisciContatto(EntityManager entityManager) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try {
			Contatto newContatto = new Contatto();
			newContatto.setCognome("campagnoli");
			newContatto.setNome("jacopo");
			newContatto.setEmail("j.campagnoli1@beije.it");
			System.out.println("contatto PRE : " + newContatto);
			entityManager.persist(newContatto);
			System.out.println("contatto POST : " + newContatto);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

	

		entityManager.close();
	}
	// CANCELLA DA RUBRICA ______________________________________________________________________________________________________
	public static void cancellaContatto(EntityManager entityManager) {
		Contatto c = entityManager.find(Contatto.class, 26);
		System.out.println(c);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.remove(c);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}
	}
	// MOSTRA RUBRICA ______________________________________________________________________________________________________
	public static String mostraRubrica(EntityManager entityManager) {
		String jpqlSelect = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contatto> contatti = query.getResultList();
		

		StringBuilder output = new StringBuilder();
		for (Contatto contatto : contatti) {
//			output.append("<br>CONTATTO=[" + contatto +"]");
			output.append("\nCONTATTO=[" + contatto +"]\n");
				
		}
		String respcontact = output.toString();
		return  respcontact;
	}



}
