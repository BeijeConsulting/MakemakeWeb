<%@page import="ecommerce.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BENVENUTO</title>
</head>
<body>

<%
User user = (User) session.getAttribute("loggedUser");
%>
<h1>BENVENUTO <%= user.getName() %>!!!</h1>
<form action=riepilogo_ordini.jsp method="post">
  <input type="submit" value="Riepilogo ordini">
</form> 
<form action=dettaglio_utente.jsp method="post">
  <input type="submit" value="Dettaglio utente">
</form> 
<form action=logout method="post">
  <input type="submit" value="Logout">
</form> 
</body>
</html>