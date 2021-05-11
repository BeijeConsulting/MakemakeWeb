<%@page import="it.beije.makemake.User"%>
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

</body>
</html>