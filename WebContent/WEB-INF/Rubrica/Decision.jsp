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
String decisione = request.getParameter("decisione");
if(decisione.equalsIgnoreCase("no")){
	//response.sendRedirect("NewContact.html");
	response.getWriter().append("Vai a cagare!");
}else  {
	
	response.sendRedirect("NewContact.html");
}
%>
</body>
</html>