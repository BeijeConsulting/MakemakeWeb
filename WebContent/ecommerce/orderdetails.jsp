<%@ page import="it.beije.makemake.ecommerce.ECommerce" %>
<%@ page import="it.beije.makemake.database.JPAManager" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="it.beije.makemake.ecommerce.Order" %>
<%@ page import="it.beije.makemake.ecommerce.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page import="it.beije.makemake.ecommerce.Product" %><%--
  Created by IntelliJ IDEA.
  User: Padawan08
  Date: 11/05/2021
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Order details</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="it.beije.makemake.ecommerce.User" scope="session"/>

<%
    if (loggedUser.getName() == null) {
        response.sendRedirect("login.jsp");
    }
%>
<h1>Order details</h1>
<table>
<tr>
    <th>Product id</th>
    <th>Name</th>
    <th>Brand</th>
    <th>Price</th>
    <th>Quantity</th>
</tr>

<%
    Integer orderId = Integer.parseInt(request.getParameter("orderId"));
    Order order = ECommerce.getOrder(orderId);
    List<OrderItem> orderItemList = order.getOrderItems();
    System.out.println(orderItemList);
    for (OrderItem orderItem : orderItemList) {
        Integer productId = orderItem.getIdProduct();
        Product product = ECommerce.getProduct(productId);
        %>
        <tr>
            <td><%=productId%></td>
            <td><%=product.getName()%></td>
            <td><%=product.getBrand()%></td>
            <td><%=orderItem.getPrice()%></td>
            <td><%=orderItem.getQuantity()%></td>
        </tr>
        <%
    }
%>
</table>

</body>
</html>
