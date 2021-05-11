<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FORM REGISTRAZIONE</title>
</head>
<body>

	<jsp:useBean id="logged" class="it.beije.makemake.ecommerce.User"
		scope="session" />

	<%
	if (logged.getUsername() != null) {
		response.sendRedirect("menu.jsp");
	} else {
	%>

	<%
	//se fallisce l'autenticazione nella LoginServlet
		String errore = (String) session.getAttribute("errore");

	if (errore != null) {
	%>
		<h6 style='color: red'><%=errore%></h6>
	<%
	//rimovo l'attributo altrimenti ogni volta che ricarico la pagina mi viene presentato l'errore
		session.removeAttribute("errore");
	}
	%>
	<h4>COMPLETA I TUOI DATI PER REGISTRARTI</h4>
	<form action="registration" method="post">
		<label for="username">username:</label>
		<input type="text" name="username"><br><br>
		<label for="password">password:</label>
		<input type="password" name="password"><br><br>
		<label for="nome">nome: &nbsp &nbsp &nbsp </label>
		<input type="text" name="nome"><br><br>
		<label for="cognome">cognome:</label>
		<input type="text" name="cognome"><br><br>
		<input type="submit" value="Submit">
	</form>
	<%
	}
	%>

</body>
</html>