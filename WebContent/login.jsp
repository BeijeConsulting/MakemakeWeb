<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<CENTER><H1>Ecommerce </H1>

<form action="login" method="post">
  <label for="username">username:</label><br>
  <input type="text" name="username"><br>
  <label for="password">password:</label><br>
  <input type="password" name="password"><br><br>
  
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
  <input type="submit" value="Accedi">
</form> 
<form action="registrati.jsp" method="post">
  <input type="submit" value="Registrati">
</form> </CENTER>

</body>
</html>