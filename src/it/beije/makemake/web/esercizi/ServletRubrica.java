package it.beije.makemake.web.esercizi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.makemake.web.rubrica.Contatto;
import it.beije.makemake.web.rubrica.ContattoRubrica;
import it.beije.makemake.web.rubrica.utility.RubricaUtils;

@WebServlet("/rubrica")
public class ServletRubrica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<ContattoRubrica> contatti = new ArrayList<>();
	private static final String htmlStart = "<HTML><HEAD><TITLE>MakemakeWeb</TITLE></HEAD><BODY>";
	private static final String htmlEnd = "</BODY></HTML>";
	
	public ServletRubrica() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RubricaUtils.stampaContatti();
		List<ContattoRubrica> c;
		try {
			contatti = RubricaUtils.getContactList(RubricaUtils.dest);
		
		String name=request.getParameter("nome");
		String surname=request.getParameter("cognome");
		String phone=request.getParameter("telefono");
		String email=request.getParameter("email");
		if(name!=null) {
			System.out.println(RubricaUtils.searchBy( "nome", name));
		}else if(surname!=null) {
			c=RubricaUtils.searchBy("cognome",surname);
		}else if(phone!=null) {
			c=RubricaUtils.searchBy("telefono", phone);
		}else if(name!=null) {
			c=RubricaUtils.searchBy("email",email);
		}
		response.getWriter().append(htmlStart).append("<h1>Contatto</h1><br>")
		.append("<form action=\"test\" method=\"post\">\r\n");
		for(ContattoRubrica cont:contatti) {
			String  s1=cont.getNome();
		
		
		response.getWriter().append(s1)
		
		.append(htmlEnd);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);

	}

}
