<html>
<head>
<meta charset="ISO-8859-1">
<title>REGISTER</title>
</head>
<body>

<%
String errore = (String) session.getAttribute("result");

if (errore != null) {
	//out.print("<h6 style='color:red'>"+errore+"</h6>");
	%>
	<h6 style='color:red'><%= errore %></h6>
	<%
	session.removeAttribute("result");
}
%>

<form action="register" method="post">
 <label for="nome">Nome:</label><br>
  <input type="text" name="nome"><br>
   <label for="cognome">Cognome:</label><br>
  <input type="text" name="cognome"><br>
  <label for="username">Username:</label><br>
  <input type="text" name="username"><br>
  <label for="password">Password:</label><br>
  <input type="password" name="password"><br><br>
  <input type="submit" value="Submit">
</form> 

</body>
</html>