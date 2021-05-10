package it.beije.makemake.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String htmlStart = "<HTML><HEAD><TITLE>MakemakeWeb</TITLE></HEAD><BODY>";
	private static final String htmlEnd = "</BODY></HTML>";
       
  
    public TestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET " + request.getRequestURL());
		
//		response.getWriter().append(htmlStart).append("<h1>Sono un Titolo</h1><p>questa è una prova</p><br>")
//		.append("<form action=\"test\" method=\"post\">\r\n" + 
//				"		  <label for=\"fname\">First name:</label><br>\r\n" + 
//				"		  <input type=\"text\" name=\"fname\"><br>\r\n" + 
//				"		  <label for=\"lname\">Last name:</label><br>\r\n" + 
//				"		  <input type=\"text\" name=\"lname\"><br><br>\r\n" + 
//				"		  <input type=\"submit\" value=\"Submit\">\r\n" + 
//				"		</form>")
//		.append(htmlEnd);
		
		
		HttpSession session = request.getSession();
		
		String fname = (String) session.getAttribute("fname");
		String lname = (String) session.getAttribute("lname");
		
		response.getWriter().append(htmlStart)
		.append("fname : ").append(fname).append("<br>")
		.append("lname : ").append(lname)
		.append(htmlEnd);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test POST");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		System.out.println("fname : " + fname);
		System.out.println("lname : " + lname);
		
		//..... elaborazione
		// accedere al DB
		// ricerco su rubrica un contatto con  fname & lname
		
		HttpSession session = request.getSession();
		
		session.setAttribute("fname", fname);
		session.setAttribute("lname", lname);
		
		
		response.getWriter().append(htmlStart)
		.append("fname : ").append(fname).append("<br>").append("lname : ").append(lname)
		.append(htmlEnd);
	}

}
