<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String errore = (String) session.getAttribute("errore");

if (errore != null) {
	//out.print("<h6 style='color:red'>"+errore+"</h6>");
	%>
	<h6 style='color:red'><%= errore %></h6>
	<%
	session.removeAttribute("errore");
}
%><a>Beije-Shop: inserisci le credenziali</a>
<form action="loginE" method="post">
  <label for="username">username:</label><br>
  <input type="text" name="username"><br>
  <label for="password">password:</label><br>
  <input type="password" name="password"><br><br>
  <input type="submit" value="Submit"><br><br>
  <a href="registrazioneEcommerce.jsp">se non hai un account, registrati</a>
</form> 
</body>
</html>