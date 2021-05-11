<%--
  Created by IntelliJ IDEA.
  User: Padawan08
  Date: 10/05/2021
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="it.beije.makemake.ecommerce.User" scope="session"/>

<%
    if (loggedUser.getName() == null) {
        response.sendRedirect("login.jsp");
    }
%>
<h3>Welcome, <%= loggedUser.getName() %></h3>
<br>
<h4>Options: </h4>
<br>
<a href="orders.jsp">
    <button>View orders</button>
</a>
</body>
</html>
