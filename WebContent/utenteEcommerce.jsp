<%@page import="it.beije.makemake.esercizi.ecommerce.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DETTAGLIO</title>
</head>
<body>

<jsp:useBean id="loggedUser" class="it.beije.makemake.esercizi.ecommerce.User" scope="session"/>

<%
//User loggedUser = (User) session.getAttribute("loggedUser");
if (loggedUser == null) {
	response.sendRedirect("loginEcommerce.jsp");
}
%>

il tuo dettaglio...<br><br>


username : <jsp:getProperty name="loggedUser" property="username"/><br>
password : <jsp:getProperty name="loggedUser" property="password"/><br>
name : <jsp:getProperty name="loggedUser" property="name"/><br>
surname : <jsp:getProperty name="loggedUser" property="surname"/><br>
</body>
</html>