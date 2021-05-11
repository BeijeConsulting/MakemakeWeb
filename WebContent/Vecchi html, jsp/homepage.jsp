<%@page import="it.beije.makemake.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="loggedUser" class="it.beije.makemake.User" scope="session"/>

<%
/*
User loggedUser = (User) session.getAttribute("loggedUser");
if (loggedUser == null) {
	loggedUser = new User();
	session.setAttribute("loggedUser", loggedUser);
}
*/

String name = loggedUser.getName() != null ? loggedUser.getName() : "OSPITE";
%>

<h1>BENVENUTO <%= name %>!!!</h1>
<h2> questa è l homepage</h2>
</body>
</html>