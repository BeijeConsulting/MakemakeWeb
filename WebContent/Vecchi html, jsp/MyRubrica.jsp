<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="it.beije.makemake.web.rubrica.utility.RubricaUtils"%>
<%@page import="it.beije.makemake.web.rubrica.ContattoRubrica"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.makemake.web.esercizi.SingletonJpa" %>
   <%@page import="javax.persistence.EntityManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rubrica</title>
</head>
<body>

<% 

EntityManager entityManager =SingletonJpa.getInstance();
final String operazione = request.getParameter("operazione"); 
		System.out.println(operazione);
		
		
		
		switch(operazione) {
		case "aggiungi":
			response.sendRedirect("InserimentoContatti.html");	
			
			break;
			
		case "visualizza":
		out.print(RubricaUtils.mostraRubrica());
			break;
			
		case "cerca":
		//	RubricaUtils.searchBy(attribute, value);
			List<ContattoRubrica> contatti = new ArrayList<>();
			String nome = request.getParameter("nome");
	System.out.print(request.getParameter("nome"));
			String cognome = request.getParameter("cognome");	
			String email = request.getParameter("email");
			
			if(nome!= null && !nome.isEmpty()) {
				contatti = RubricaUtils.searchBy("nome", nome);
			}else if(cognome!= null && !cognome.isEmpty()) {
				contatti = RubricaUtils.searchBy("cognome", cognome);
			}else if(email!=null && !email.isEmpty()) {
				contatti = RubricaUtils.searchBy("email", email);
			}
			for(ContattoRubrica c : contatti){
				%>
				<%= c.toString() %><br>
				<%
			}
			break;
		}

 %>

</body>
</html>