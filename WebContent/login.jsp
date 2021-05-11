<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
</head>
<body>

<%
String errore = (String) session.getAttribute("errore");

if (errore != null) {
	%>
	<h6 style='color:red'><%= errore %></h6>
	<%
	session.removeAttribute("errore");
}

String successfulRegistration = (String) session.getAttribute("registrationResult");

if (successfulRegistration != null) {
	%>
	<h6 style='color:green'><%= successfulRegistration %></h6>
	<%
	session.removeAttribute("registrationResult");
}
%>

<form action="login" method="post">
  <label for="username">username:</label><br>
  <input type="text" name="username"><br>
  <label for="password">password:</label><br>
  <input type="password" name="password"><br><br>
  <input type="submit" value="Submit">
</form> 
<h5>Non sei registrato? Puoi farlo <a href='register.jsp'>qui</a></h5>

</body>
</html>