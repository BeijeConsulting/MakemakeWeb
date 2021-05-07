<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.makemake.Utils"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Questa è la tua rubrica</title>
</head>
<body>

<%

String nomeFile = null;
{
	nomeFile = request.getParameter("file"); 
}
List<String> rubrica = Utils.readRubrica(nomeFile);

for (String row : rubrica) {
	%>
	# <%= row %><br>
	<%
	//out.println(row + "<br>");
}
%>


</body>
</html>
