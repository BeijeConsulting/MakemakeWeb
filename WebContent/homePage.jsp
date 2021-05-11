<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.makemake.web.ecommerce.User"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="it.beije.makemake.web.ecommerce.User" scope="session"/>

<TABLE WIDTH="75%" ALIGN="center">
  <TR>
<TD>
<DIV ALIGN="center">
<h1>BENVENUTO</h1></DIV>
<DIV ALIGN="justify">
<a>
<%
User user = (User) session.getAttribute("loggedUser");
%>
<%
if (loggedUser == null) {
	response.sendRedirect("loginEcommerce.jsp");
}
%>
Ciao <%= user.getName() %> <%= user.getSurname() %> , benvenuto nel nostro ecommerce!!!
</a>

<h2>Seleziona l' operazione da effettuare</h2>
	
	<form action="menuEcommerce" method="POST" >
 	 	
 	 	<input type="radio" id="visualizza" name="operazione" value="visualizza">
 		<label for="visualizza">Visualizza Prodotti</label><br>
  		<input type="radio" id="ricerca" name="operazione" value="ricerca">
  		<label for="ricerca">Ricerca un prodotto</label><br>
  		<input type="radio" id="aggiungi" name="operazione" value="aggiungi">
  		<label for="aggiungi">Visualizza un ordine</label><br>
	
		<input type="submit" value ="Submit">
	</form>
	
	<form action="logout" method="POST" >
	 <input type="submit" value="logout">
	 </form>
	<a href="loginEcommerce.jsp"> torna alla login</a>
</DIV>
</TD>
</TR>
</TABLE>
</body>
</html>