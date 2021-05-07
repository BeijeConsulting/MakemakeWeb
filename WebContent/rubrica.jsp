<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "java.util.List" %>
    <%@page import = " it.beije.makemake.web.Try.Rubrica.RubricaManager" %>
	 <%@page import = " it.beije.makemake.web.Try.Rubrica.ContattoNoMap" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%


List<ContattoNoMap> contatti = new ArrayList<>() ;
String nome = request.getParameter("nome");
String cognome = request.getParameter("cognome");	
String email = request.getParameter("email");

if(nome!= null) {
	contatti = RubricaManager.cercaContatto("nome", nome);
}else if(cognome!= null) {
	contatti = RubricaManager.cercaContatto("cognome", cognome);
}else if(email!=null) {
	contatti = RubricaManager.cercaContatto("email", email);
}
for(ContattoNoMap c : contatti){
	%>
	<%= c.toString() %><br>
	<%
}

%>
</body>
</html>