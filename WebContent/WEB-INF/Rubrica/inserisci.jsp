<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "java.util.List" %>
        <%@page import = " it.beije.makemake.web.Try.Rubrica.RubricaManager" %>
	 <%@page import = " it.beije.makemake.web.Try.Rubrica.ContattoNoMap" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String decisione = request.getParameter("decisione");

	
String nome = request.getParameter("nome");
String cognome = request.getParameter("cognome");	
String telefono = request.getParameter("telefono");
String email = request.getParameter("email");

RubricaManager.newContact(nome, cognome, email, telefono);
List<ContattoNoMap> contatti = RubricaManager.getContatti();

for(ContattoNoMap c : contatti){
	%>
	<%= c.toString() %><br>
	<%
}

%>
</body>
</html>