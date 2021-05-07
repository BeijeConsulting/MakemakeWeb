package it.beije.makemake.esercizi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rubrica.RubricaManager;
import rubrica.Contatto;

/**
 * Servlet implementation class ProvaServlet
 */
@WebServlet("/rubrica")
public class ServletRubrica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<Contatto> listaContatti = new ArrayList<>();
       
   
    public ServletRubrica() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RubricaManager.printRubrica();
		String name =  request.getParameter("name");
		String surname =  request.getParameter("surname");
		String phone =  request.getParameter("phone");
		String email =  request.getParameter("email");
		
		if(name!= null) {
			System.out.println(RubricaManager.cercaContatto("name" , name));
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
