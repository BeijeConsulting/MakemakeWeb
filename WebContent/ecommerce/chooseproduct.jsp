<%@ page import="it.beije.makemake.ecommerce.Ecommerce"%>
<%@ page import="it.beije.makemake.ecommerce.Product"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Aggiungi al carrello</title>
	</head>
	<jsp:useBean id="logged" class="it.beije.makemake.ecommerce.User" scope="session"/>
			<%
			if(logged.getName() == null){
				response.sendRedirect("welcome.jsp");
				session.removeAttribute("logged");
				return;
			}
			%>
	<body>
		<header>
		</header>
		<main>
			<%
			List<Product> products =(List<Product>) session.getAttribute("products");
			session.removeAttribute("products");
			%>
			
			<form action="addtocart">
					<label for="product">scegli il prodotto:</label>
					<select  name="product">
						<%
						for(Product p : products){
						%>
						<option value="<%=p.getId()%>"><%=p.getName()%></option>
						<%
						}
						%>
					</select>
					<br>
					<label for="quantity">quantity:</label>
		  			<input type="text" name="quantity"><br>
				<input type="submit">
			</form>
			
		</main>
		<footer>
		</footer>
	</body>
</html>