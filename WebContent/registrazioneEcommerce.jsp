<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FORM REGISTRAZIONE</title>
</head>
<body>

<jsp:useBean id="loggedUser" class="it.beije.makemake.esercizi.ecommerce.User" scope="session"/>

<%
String errore = (String) session.getAttribute("errore");

if (errore != null) {
	//out.print("<h6 style='color:red'>"+errore+"</h6>");
	%>
	<h6 style='color:red'><%= errore %></h6>
	<%
	session.removeAttribute("errore");
}
%>
<%
if (loggedUser.getUsername() != null) {
	response.sendRedirect("utenteEcommerce.jsp");
} else {
%>
<h4>COMPLETA I TUOI DATI PER REGISTRARTI</h4>
<form action="registration" method="post">
  <label for="username">username:</label><br>
  <input type="text" name="username"><br>
  <label for="password">password:</label><br>
  <input type="text" name="password"><br><br>
  <label for="name">name:</label><br>
  <input type="text" name="name"><br><br>
  <label for="surname">surname:</label><br>
  <input type="text" name="surname"><br><br>

  <input type="submit" value="Submit">
</form> 
<%
}
%>

</body>
</html>