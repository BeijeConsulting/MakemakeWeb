<%@page import="it.andrea.makemake.web.controller.EcommerceController"%>
<%@page import="it.andrea.makemake.web.controller.SiteController"%>
<%@page import="it.andrea.makemake.web.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MODIFY</title>
</head>
<body>
	<jsp:useBean id="loggedUser" class="it.andrea.makemake.web.entity.User"
		scope="session" />
	<%
	User user = (User) session.getAttribute("loggedUser");
	if (SiteController.isLogged(user, "modifyAccount.jsp", session, response)) {
		String error = (String) session.getAttribute("registrationResult");
		if (error != null) {
			%>
			<h6 style='color:red'><%= error %></h6>
			<%
			session.removeAttribute("registrationResult");
		}
	}
%>

<form action="modifyAccount" method="post">
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