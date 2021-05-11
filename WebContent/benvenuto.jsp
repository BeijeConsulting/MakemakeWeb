<%@page import="it.beije.makemake.ecommerce.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BENVENUTO</title>
</head>
<body>

	<%
	User user = (User) session.getAttribute("loggedUser");
	%>
	<h1>
		BENVENUTO
		<%=user.getName()%>!!!
	</h1>
	<form action = "menu" >
	<p>Scegli l'operazione :</p>
	<input type="radio" id="viewProfile" name="operazione" value="view">
	<label for="view">Visualizza profilo</label><br><br>
	
	<input type="radio" id="viewOrderItem" name="operazione" value="viewOrderItem">
	<label for="viewOrderItem">Visualizza order item</label>
	<br><br>
	
	<input type="radio" id="viewProduct" name="operazione" value="viewProduct">
	<label for="viewProduct">Visualizza prodotti</label>
	<br><br>
</form>
	
	<input type="submit" value="Submit">




</body>
</html>