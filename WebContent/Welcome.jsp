<%@page import="it.beije.makemake.web.myEcomm.entity.User"%>
<%@page import="it.beije.makemake.web.myEcomm.PageController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage!</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="it.beije.makemake.web.myEcomm.entity.User" scope="session"/>
<%
User user = (User) session.getAttribute("loggedUser");

PageController.checkLogin(user,session,response,"Welcome.jsp");
%>

<h1>Benvenuto <%= user.getName() == null ? user.getUsername() : user.getName() %>!!!</h1>
<a href = ' '>Visualizza i prodotti</a><br>
<a href = ' '>Il mio carrello</a><br>
<a href = 'order.jsp'>I miei ordini</a><br>
<a href = 'modificaAccount.jsp '>Modifica profilo</a><br>
<a href = ' '>Elimina profilo</a><br>
<li><a class="linkButtons" href="logout" >Logout</a></li>
</body>
</html>