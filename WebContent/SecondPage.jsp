<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="it.beije.makemake.web.*" %>
 <%@page import="javax.persistence.EntityManager"%>
 <%@page import="it.beije.makemake.web.ServletRubrica" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%  JPASingleton.getInstance();
	
	EntityManager entityManager = JPASingleton.getEntityManager();

	final String operazione = request.getParameter("operazione");
	System.out.println(operazione);
		
		
		
		switch(operazione) {
		case "aggiungi":
			response.sendRedirect("inserimentoContatti.html");			
			break;
			
		case "visualizza":
			out.println(ServletRubrica.mostraRubrica(entityManager));
			
			break;
			
		case "cerca":
			out.println("3");
			response.getWriter().append(operazione);
			break;
		}%>
</body>
</html>