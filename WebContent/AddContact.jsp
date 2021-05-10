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
	<div style="margin: 0 auto;">
	<form action="addContact" method="post">
		<label style="font-family: arial;" for="name">Name</label><br>
		<input style="border-color: gray;" type="text" name="nome" required><br><br>
		<label style="font-family: arial;" for="surname">Surname</label><br>
		<input style="border-color: gray;" type="text" name="cognome" required><br><br>
		<label style="font-family: arial;" for="phone">Phone</label><br>
		<input style="border-color: gray;" type="tel" name="telefono" required><br><br>
		<label style="font-family: arial;" for="email">Email</label><br>
		<input style="border-color: gray;" type="email" name="email" required><br><br>
		<input style="background-color: black; color: white;" type="submit" value="Submit">
	</form>
	</div>
</body>
</html>