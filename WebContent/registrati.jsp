<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FORM REGISTRAZIONE</title>
</head>
<body>

<jsp:useBean id="loggedUser" class="ecommerce.User" scope="session"/>

<%
if (loggedUser.getUsername() != null) {
	response.sendRedirect("dettaglio_utente.jsp");
} else {
%>
<h4>COMPLETA I TUOI DATI PER REGISTRARTI</h4>
<form action="registrazione" method="post">
  <label for="username">username:</label><br>
  <input type="text" name="username"><br>
  <label for="password">password:</label><br>
  <input type="text" name="password"><br><br>
  <label for="nome">nome:</label><br>
  <input type="text" name="nome"><br><br>
  <label for="cognome">cognome:</label><br>
  <input type="text" name="cognome"><br><br>

  <input type="submit" value="Submit">
</form> 
<%
}
%>

</body>
</html>