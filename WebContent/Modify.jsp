<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="rubrica.Contatto"%>
<%@page import="rubrica.HCriteria"%>
<%@page import="rubrica.GestisciRubrica"%>
<%@ page import="org.hibernate.Session" %>
<%@ page import="session.SessionManager" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Session ses1 = SessionManager.getSession();
String nome = request.getParameter("fname");
String cognome = request.getParameter("lname");
String email = request.getParameter("email");
String telefono = request.getParameter("telephone");
String scelta = request.getParameter("scelta");
String nuovo = request.getParameter("nuovo");


out.print(HCriteria.modifyContact(ses1, new Contatto(nome,cognome,telefono,email), scelta, nuovo));
SessionManager.close(ses1);

%>
</body>
</html>