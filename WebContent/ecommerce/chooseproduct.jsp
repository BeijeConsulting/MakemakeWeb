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
<jsp:useBean id="logged" class="it.beije.makemake.ecommerce.User"
	scope="session" />
<%
if (logged.getName() == null) {
	response.sendRedirect("welcome.jsp");
	session.removeAttribute("logged");
	return;
}
%>
<body>
	<header> </header>
	<main>
		<%
		//se fallisce l'autenticazione nella LoginServlet
		String errore = (String) session.getAttribute("errore");

		if (errore != null) {
		%>
			<h6 style='color: red'><%=errore%></h6>
		<%
		//rimuovo l'attributo altrimenti ogni volta che ricarico la pagina mi viene presentato l'errore
			session.removeAttribute("errore");
		}
		%>
		<%
		List<Product> products = (List<Product>) session.getAttribute("products");
		%>

		<form action="addtocart" method="POST">
			<label for="product">scegli il prodotto:</label> <select
				name="product">
				<%
				for (Product p : products) {
				%>
				<option value="<%=p.getId()%>"><%=p.getName()%></option>
				<%
				}
				%>
			</select> <br> <label for="quantity">quantity:</label> <input type="text"
				name="quantity"><br> <input type="submit">
		</form>

	</main>
	<footer> </footer>
</body>
</html>