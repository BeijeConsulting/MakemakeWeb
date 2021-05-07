package it.beije.makemake.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/esemp")
public class MyTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String htmlStart = "<HTML><HEAD><TITLE>MyProva</TITLE></HEAD><BODY>";
	private static final String htmlEnd = "</BODY></HTML>";

	public MyTest() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET " + request.getRequestURL());
		response.getWriter().append(htmlStart).append("<h1>Prova con le servlet</h1><p>Prova con le servlet</p><br>")
				.append("<form action=\"esemp\" method=\"post\">\r\n"
						+ "		  <label for=\"username\">username:</label><br>\r\n"
						+ "		  <input type=\"text\" name=\"username\"><br>\r\n"
						+ "		  <label for=\"password\">password:</label><br>\r\n"
						+ "		  <input type=\"password\" name=\"password\"><br><br>\r\n" + ""
						+ "</form>"
						+ "<form>\r\n"  
						+ "<p>Hai un mezzo di trasporto?</p>\r\n"
						+ "  <input type=\"checkbox\" id=\"vehicle1\" name=\"vehicle1\" value=\"Bike\">\r\n"
						+ "  <label for=\"vehicle1\"> ho una bici</label><br>\r\n"
						+ "  <input type=\"checkbox\" id=\"vehicle2\" name=\"vehicle2\" value=\"Car\">\r\n"
						+ "  <label for=\"vehicle2\"> ho una macchina</label><br>\r\n"
						+ "  <input type=\"checkbox\" id=\"vehicle3\" name=\"vehicle3\" value=\"Boat\">\r\n"
						+ "  <label for=\"vehicle3\"> ho una moto</label><br>\r\n"
						+ "  <input type=\"checkbox\" id=\"vehicle3\" name=\"vehicle3\" value=\"Boat\">\r\n"
						+ "  <label for=\"vehicle3\"> ho una aereo</label><br>\r\n" + " "
						+ " <p>Sei laureato?</p>\r\n"
						+ "  <input type=\"radio\"  >\r\n" + "  <label for=\"si\">si</label>\r\n"
						+ "  <input type=\"radio\" value=\"no\">\r\n" + "  <label for=\"no\">no</label><br>\r\n"
						+ "  <P>sicuro?</p>\r\n" + "  <input type=\"button\" value=\"si\">\r\n"
						+ "  <input type=\"button\" value=\"no\"><br><br>\r\n"
						+ "  <input type=\"submit\" value=\"Submit\">" + "</form>\r\n" + "</DIV>\r\n" + "</TD>\r\n"
						+ "</TR>\r\n" + "</TABLE>")
				.append(htmlEnd);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("test POST");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username : " + username);
		System.out.println("password : " + password);

		response.getWriter().append(htmlStart).append("username : ").append(username).append("<br>")
				.append("password : ").append(password).append(htmlEnd);
	}
}
