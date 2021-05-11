<%@ page import="it.beije.makemake.ecommerce.User" %><%--
  Created by IntelliJ IDEA.
  User: Padawan08
  Date: 10/05/2021
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="it.beije.makemake.ecommerce.User" scope="session"/>
<%
    if (loggedUser.getName() != null) {
        response.sendRedirect("home.jsp");
    }

    String errorString = (String)session.getAttribute("error");
    if (errorString != null) {
        %>
        <%= errorString %> <br>
        <%
    }
%>
<form action="${pageContext.request.contextPath}/ServletHandleLogin" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit">
</form>

</body>
</html>
