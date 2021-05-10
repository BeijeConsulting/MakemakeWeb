<%@ page import="it.beije.makemake.addressbook.AddressBookDatabase" %>
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
int x = 5;
double y = 3.6;
//out.println(x + y);
//System.out.println(x + y);

String fname = (String) session.getAttribute("fname");
String lname = (String) session.getAttribute("lname");

%>

<%= "somma = " + (x + y)  %>
<br>CIAO MAKEMAKE!!!
<br><br>
fname + lname: <%= fname + lname %><br>

</body>
</html>