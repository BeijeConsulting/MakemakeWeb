<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<jsp:useBean id="loggedUser" class="it.andrea.makemake.web.entity.User"	scope="session" />
	<h3>Benvenuto, <%= loggedUser.getName() %></h3>
</body>
</html>