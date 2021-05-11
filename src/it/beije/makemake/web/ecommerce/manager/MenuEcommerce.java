package it.beije.makemake.web.ecommerce.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/menuEcommerce")
public class MenuEcommerce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MenuEcommerce() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String operazione = request.getParameter("operazione");
		System.out.println(operazione);

		switch (operazione) {
		case "visualizza":
			response.sendRedirect("visualizzazioneProdotti.jsp");
			break;

		case "ricerca":
			break;

		case "aggiungi":
			break;
		}
	}

}
