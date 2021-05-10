<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
	</head>
	<body>

		<%
		//se fallisce l'autenticazione nella LoginServlet
		String errore = (String) session.getAttribute("errore");
		
		if (errore != null) {
			%>
			<h6 style='color:red'><%= errore %></h6>
			<%
			//rimovo l'attributo altrimenti ogni volta che ricarico la pagina mi viene presentato l'errore
			session.removeAttribute("errore");
		}
		%>
	
		<form action="login" method="post">
		  <label for="username">username:</label><br>
		  <input type="text" name="username"><br>
		  <label for="password">password:</label><br>
		  <input type="password" name="password"><br><br>
		  <input type="submit" value="Submit">
		</form> 

	</body>
</html>