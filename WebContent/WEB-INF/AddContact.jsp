<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Address Book | Add Contact</title>
</head>
<body>
	<h1 style="text-align: center; color: red; font-family: Arial;">Add Contact</h1>
	<form action="addContact" method="post">
		<label for="name">Name</label><br>
		<input type="text" name="nome" required><br>
		<label for="surname">Surname</label><br>
		<input type="text" name="cognome" required><br>
		<label for="phone">Username</label><br>
		<input type="tel" name="telefono" required><br>
		<label for="email">Email</label><br>
		<input type="email" name="email" required><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>