package it.beije.makemake.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import it.beije.makemake.utils.Contatto;
import it.beije.makemake.utils.JpaContactUtil;



/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/databaseReader")
public class DatabaseReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String htmlStart = "<HTML><HEAD><TITLE>MakemakeWeb</TITLE></HEAD><BODY>";
	private static final String htmlEnd = "</BODY></HTML>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseReader() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("queryType");
		PrintWriter printer = response.getWriter();
		printer.append(htmlStart).append("<p>");
		
		switch(value) {
			case "select":
				List<Contatto> contList = JpaContactUtil.selectAll();
				
				for(Contatto c : contList) {
					printer.append(c.toString()+"<br>");
				}
				printer.append(htmlEnd);
				break;
			case "insert":
				break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
