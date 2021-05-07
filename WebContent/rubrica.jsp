<%@page import="org.hibernate.internal.build.AllowSysOut"%>
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
List<ContattoNoMap> rubrica = new ArrayList<>() ;

rubrica = RubricaManager.readContact();

List<ContattoNoMap> contatti = new ArrayList<>() ;
String nome = request.getParameter("nome");
String cognome = request.getParameter("cognome");	
String email = request.getParameter("email");



if(nome!= null && !nome.isEmpty()) {
	contatti = RubricaManager.cercaContatto("nome", nome);
}else if(cognome!= null && !cognome.isEmpty()) {
	contatti = RubricaManager.cercaContatto("cognome", cognome);
}else if(email!=null && !email.isEmpty()) {
	contatti = RubricaManager.cercaContatto("email", email);
}

for(ContattoNoMap c : contatti){
	%>
	<%= c.toString() %><br>
	<%
}
if(contatti.size() == 0){
	%>
	<a href = "NewFile.html">Scegli se inserire un nuovo contatto o meno!</a>;
	
	<%
}

%>
</body>
</html>