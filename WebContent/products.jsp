<%@ page import="it.beije.makemake.ecommerce.Ecommerce"%>
<%@ page import="it.beije.makemake.ecommerce.Product"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:useBean id="logged" class="it.beije.makemake.ecommerce.User" scope="session"/>
		<% 
			if(logged.getName() == null){
				out.print("<h1>Benvenuto Ospite</h1>");
			}else{
				out.print("<h1>Benvenuot "+logged.getName()+"!</h1>");
			}
		
			List<Product> products = Ecommerce.getProducts();
			for(Product p : products){
		%>
				<%= p.toString()%><br>
		<% 	}
		%>
		
	</body>
</html>