package it.beije.makemake.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.makemake.Utils;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		FileReader fileReader = new FileReader("/temp/rubrica.txt");
//		BufferedReader bufferedReader = new BufferedReader(fileReader);
//		while (bufferedReader.ready()) {
//			String line = bufferedReader.readLine();
//			System.out.println(line);
//			response.getWriter().append(line).append('\n');
//		}
//		fileReader.close();
		
//		String rubrica = Utils.readRubrica("/temp/rubrica.txt");
//		response.getWriter().append(rubrica);
		
		response.sendRedirect("rubrica.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
