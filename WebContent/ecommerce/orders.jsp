<%@ page import="it.beije.makemake.ecommerce.Order" %>
<%@ page import="java.time.format.FormatStyle" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: Padawan08
  Date: 11/05/2021
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Orders</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="it.beije.makemake.ecommerce.User" scope="session"/>
<h1>These are your orders:</h1>
<br>
<table>
    <tr>
        <th>Id</th>
        <th>Date</th>
        <th>Status</th>
        <th>Total (€)</th>
        <th></th>
    </tr>
<% for (Order order: loggedUser.getOrders()) {
    %>
    <tr>
        <td><%= order.getId()%></td>
        <td><%= order.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))%></td>
        <td><%= order.getStatus()%></td>
        <td><%= order.getTotal()%></td>
        <td><a href="orderdetails.jsp?orderId=<%=order.getId()%>">
            <button>View order details</button>
        </a></td>
    </tr>
    <%
}
%>
</table>

</body>
</html>
