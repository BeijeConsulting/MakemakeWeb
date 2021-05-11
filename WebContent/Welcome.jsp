<%@page import="it.beije.makemake.web.myEcomm.entity.User"%>
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
%>
<h1>Welcome <%= user.getName() %>!!!</h1>

</body>
</html>