<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Menu</title>
	</head>
	<body>
		<header>
			<h1>Amazerific</h1>
		</header>
		<main>
				<jsp:useBean id="logged" class="it.beije.makemake.ecommerce.User" scope="session"/>
				<% 
				if(logged.getName() == null){
					response.sendRedirect("welcome.jsp");
					return;
				}
			
				%>
				<p>Scegli l'operazione che vuoi andare a fare</p>
				<ul>
					<li><a href="showproducts">Visualizza prodotti disponibili</a></li>
					<li><a href="chooseproduct">Fai un ordine</a></li>
					<li><a href="cart">Vai al carrello</a>
					<li><a href="*">Dettaglio oridini</a></li>
					<li><a href="Logout.jsp">Fai logout</a></li>
				</ul>
		</main>
		<footer>
		</footer>
	</body>
</html>