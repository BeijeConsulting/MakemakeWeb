<%@page import="com.mysql.cj.x.protobuf.MysqlxCrud.Order"%>
<%@page import="it.beije.makemake.ecommerce.OrderItem"%>
<%@page import="java.util.HashMap"%>
<%@ page import="it.beije.makemake.ecommerce.Ecommerce"%>
<%@ page import="it.beije.makemake.ecommerce.Product"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Carrello</title>
	</head>
	<body>
		<header>
			<h1>Prodotti nel carrello</h1>
		</header>
		<main>
				<jsp:useBean id="logged" class="it.beije.makemake.ecommerce.User" scope="session"/>
				<% 
				if(logged.getName() == null){
					response.sendRedirect("login.jsp");
					return;
				}
				HashMap<OrderItem,Product> map =(HashMap<OrderItem,Product>)session.getAttribute("viewCart");
				for(OrderItem o : map.keySet()){
					Product p = map.get(o);
				%>
					<%= "[nome: "+ p.getName()+" brand: "+p.getBrand()+" desc: "+p.getDescription()+" quantità: "+o.getQuantity()+" total: "+ o.getPrice()+"]" %><br>
				<% 	
				}
				%>
				
		</main>
		<footer>
			<hr>
			<ul>
				<li><a href="login.jsp">Fai il login</a></li>
				<li><a href="registration.jsp">Registrati</a>
				<li><a href="chooseproduct">Aggiungi un prodotto nel carrello</a></li>
				<li><a href="ShowOrders">Dettaglio oridini</a></li>
				<li><a href="logout.jsp">Fai logout</a></li>
			</ul>
		</footer>	
	</body>
</html>