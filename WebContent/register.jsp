<%@page import="it.andrea.makemake.web.controller.EcommerceController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGISTER</title>
</head>
<body>

<%
String error = (String) session.getAttribute("registrationResult");

if (error != null) {
	%>
	<h6 style='color:red'><%= error %></h6>
	<%
	session.removeAttribute("registrationResult");
}
%>

<form action="register" method="post">
  <label for="name">Nome:</label><br>
  <input type="text" name="name"><br>
  <label for="surname">Cognome:</label><br>
  <input type="text" name="surname"><br>
  <label for="username">Username:</label><br>
  <input type="text" name="username"><br>
  <label for="password">Password:</label><br>
  <input type="password" name="password"><br><br>
  <input type="submit" value="Submit">
</form> 

</body>
</html>