package it.beije.makemake.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;

import it.beije.makemake.utils.Contatto;
import it.beije.makemake.utils.FileUtil;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/reader")
public class FileReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String htmlStart = "<HTML><HEAD><TITLE>MakemakeWeb</TITLE></HEAD><BODY>";
	private static final String htmlEnd = "</BODY></HTML>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileReader() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = request.getParameter("fileName");
		
		File file = new File(filePath);
		
		if(!file.isFile()) {
			response.getWriter().append("FILE NOT FOUND");
			return;
		}
		
		
		//se il file esiste 
		try {
			PrintWriter printer = response.getWriter();
			printer.append(htmlStart).append("<p>");
			int i = filePath.lastIndexOf('.');
			
			if(i == -1) {
				response.getWriter().append("FILE NOT FOUND");
				return;
			}
			
			String exten = filePath.substring(i+1);
			List<Contatto> contactList = null;
			
			if(exten.equals("xml")) {
				Document doc = FileUtil.openXmlToRead(filePath);
				contactList = FileUtil.convertXmlToList(doc);
			}else if(exten.equals("csv")) 
			{
				contactList = FileUtil.convertCsvToList(filePath);
			}
			
			for(Contatto cont : contactList) {
				printer.append(cont.toString()+"<br>");
			}
			
			printer.append(htmlEnd);
		}catch(Exception e) {
			response.getWriter().append("ERROR WHILE READING THE FILE");
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
