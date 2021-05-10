<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Menu</title>
	</head>
	<body>
		<jsp:useBean id="logged" class="it.beije.makemake.ecommerce.User" scope="session"/>
		<% 
			if(logged.getName() == null){
				response.sendRedirect("welcome.jsp");
				return;
			}
		
		%>
		<p>Scegli l'operazione che vuoi andare a fare</p>
		<ul>
			<li><a href="products.jsp">Visualizza prodotti disponibili</a></li>
			<li><a href="orders.jsp">Fai un ordine</a></li>
			<li><a href="detail.jsp">Dettaglio oridini</a></li>
			<li><a href="logout.jsp">Fai logout</a></li>
		</ul>
	</body>
</html>