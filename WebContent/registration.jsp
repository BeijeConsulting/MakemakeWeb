<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="loggedUser" class="it.beije.makemake.web.ecommerce.User" scope="session"/>

<jsp:setProperty name="loggedUser" property="username"/>
<jsp:setProperty name="loggedUser" property="password"/>
<jsp:setProperty name="loggedUser" property="name" param="nome"/>
<jsp:setProperty name="loggedUser" property="surname" param="cognome"/>

REGISTRAZIONE COMPLETATA<br><br>

name : <%= loggedUser.getName() %><br>
surname : <%= loggedUser.getSurname() %><br>
username : <%= loggedUser.getUsername() %><br>
password : <%= loggedUser.getPassword() %><br>
<a href="loginEcommerce.jsp"> torna alla login</a>
</body>
</html>