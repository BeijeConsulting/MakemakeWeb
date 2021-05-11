<%@page import="it.beije.makemake.esercizi.ecommerce.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
</head>
<body>

<jsp:useBean id="loggedUser" class="it.beije.makemake.esercizi.ecommerce.User" scope="session"/>

<%
//User loggedUser = (User) session.getAttribute("loggedUser");
if (loggedUser == null) {
	response.sendRedirect("loginEcommerce.jsp");
}
%>


<TABLE WIDTH="75%" ALIGN="center">
  <TR>
<TD>
<DIV ALIGN="center">
<h1>Benvenuto</h1></DIV>
<DIV ALIGN="justify">
<a>
<%
User user = (User) session.getAttribute("loggedUser");
%>
Ciao <%= user.getName() %>, benvenuto nel nostro Ecommerce!!!
</a>
<h1>Seleziona l' operazione da effettuare</h1>



	
	<form action="menuEcommerce.jsp" method="POST" >
		<p>Scegli l'operazione :</p>
 	 	
 	 	<input type="radio" id="visualizza" name="operazione" value="visualizza">
 		<label for="visualizza">Visualizza Prodotti</label><br>
  		<input type="radio" id="ricerca" name="operazione" value="ricerca">
  		<label for="ricerca">Ricerca un prodotto</label><br>
  		<input type="radio" id="aggiungi" name="operazione" value="aggiungi">
  		<label for="aggiungi">Visualizza un ordine</label><br>
		
		
		
		
		<input type="submit" value ="Submit">
	</form>
</DIV>
</TD>
</TR>
</TABLE>
</body>
</html>