<%@page import="it.beije.makemake.ecommerce.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>I TUOI ORDINI</title>
</head>
<body>

<% 
User user = (User) session.getAttribute("loggedUser");
if (user != null) {
	%>
	<h4>RIEPILOGO DEGLI ORDINI EFFETTUATI</h4>
	<%
} else {
	%>
	<h4 style='color:red'>DEVI ESSERE AUTENTICATO PER VEDERE QUESTA SEZIONE!!</h4>
	<%
}
%>
</body>
</html>