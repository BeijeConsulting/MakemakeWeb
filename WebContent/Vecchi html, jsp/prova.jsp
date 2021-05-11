<%-- protected void do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { --%>
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
%>

username : <%= username %><br/>
password : <%= password %><br/><br/>
<a href="prova.html">torna al form</a>
</body>
</html>
<%-- } --%>
