<%@ page import="it.beije.makemake.ecommerce.Ecommerce"%>
<%@ page import="it.beije.makemake.ecommerce.Product"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Prodotti</title>
	</head>
	<body>
		<header>
		</header>
		<main>
				<jsp:useBean id="logged" class="it.beije.makemake.ecommerce.User" scope="session"/>
				<% 
				if(logged.getName() == null){
					out.print("<h1>Benvenuto Ospite</h1>");
				}else{
					out.print("<h1>Benvenuot "+logged.getName()+"!</h1>");
				}
			
				List<Product> products = (List<Product>) session.getAttribute("products");
				session.removeAttribute("products");
				
				for(Product p : products){
				%>
					<%= p.toString()%><br>
				<% 	
				}
				%>
				
		</main>
		<footer>
			<ul>
				<li><a href="login.jsp">Fai il login</a></li>
				<li><a href="register.jsp">Registrati</a>
				<li><a href="PrepOrderView">Fai un ordine</a></li>
				<li><a href="ShowOrders">Dettaglio oridini</a></li>
				<li><a href="logout.jsp">Fai logout</a></li>
			</ul>
		</footer>	
	</body>
</html>