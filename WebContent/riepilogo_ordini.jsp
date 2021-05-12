<%@page import="ecommerce.User"%>
<%@page import="ecommerce.ControlllerEcommerce"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>I TUOI ORDINI</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="ecommerce.User" scope="session"/>

<%
User user = (User) session.getAttribute("loggedUser");
if (user != null) {
%>
	<h4>RIEPILOGO DEGLI ORDINI EFFETTUATI</h4>
	
<form action=logout method="post">
  <input type="submit" value="Logout">
</form> 
	<%
	
} else {
	%>
	<h4 style='color:red'>DEVI ESSERE AUTENTICATO PER VEDERE QUESTA SEZIONE!!</h4>
	<%
}
%>
<form action=logout method="post">
  <input type="submit" value="Logout">
</form> 
<form action="dettaglio" method="post">
<%
for(int i=0; i < ControlllerEcommerce.dettagliUtente(loggedUser.getId()).size();i++){
%>
	<input type="radio" name="scelta"value="<%=i%>">
	<label for="scelta">

<%= loggedUser.getOrders().get(i) %>
	
	</label><br>
<% 
}if(ControlllerEcommerce.dettagliUtente(loggedUser.getId()).size()!=0){ 
%>
<input type="submit" value="Mostra dettaglio ordine">
<% }%>
<br>
<br>

 <%
String ordine = (String) session.getAttribute("ordine");

if (ordine != null) {
	
	%>
	<h6 style='color:red'><%= ordine %></h6>
	<%
	session.removeAttribute("ordine");

}
%>
</body>
</html>