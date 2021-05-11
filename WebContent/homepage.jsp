<%@page import="it.andrea.makemake.web.controller.SiteController"%>
<%@page import="it.andrea.makemake.web.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<jsp:useBean id="loggedUser" class="it.andrea.makemake.web.entity.User"
		scope="session" />
	<%
	User user = (User) session.getAttribute("loggedUser");
		SiteController.isLogged(user, "homepage.jsp", session, response);
	%>
	<h3>Benvenuto, <%= user.getName() == null ? user.getUsername() : user.getName() %></h3>
	<a href=''>Visualizza i prodotti</a><br>
	<a href=''>Il mio carrello</a><br>
	<a href='orders.jsp'>I miei ordini</a><br>
	<a href='modifyAccount.jsp'>Modifica profilo</a><br>
	<a href=''>Elimina profilo</a><br><!-- actually "disabilita" il profilo -->
	<a href="logout">Esci</a><br>
</body>
</html>