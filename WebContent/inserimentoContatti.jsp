<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.makemake.web.*"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="it.beije.makemake.web.ServletRubrica"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
JPASingleton.getInstance();
		EntityManager entityManager = JPASingleton.getEntityManager();
		
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");

	
		ServletRubrica.inserisciContatto(entityManager,nome,cognome,email,telefono);
		response.sendRedirect("rubricaMain.html");%>
</body>
</html>