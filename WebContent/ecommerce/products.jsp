<%@ page import="it.beije.makemake.ecommerce.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="it.beije.makemake.ecommerce.ECommerce" %><%--
  Created by IntelliJ IDEA.
  User: Padawan08
  Date: 11/05/2021
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Product list</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="it.beije.makemake.ecommerce.User" scope="session"/>
<%
    if (loggedUser.getName() == null) {
        response.sendRedirect("login.jsp");
    }
%>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Brand</th>
        <th>Description</th>
        <th>Price</th>
        <th></th>
    </tr>

<%
    List<Product> products = ECommerce.getProductList();
    for (Product product :
            products) {
        %>
        <tr>
            <td><%=product.getId()%></td>
            <td><%=product.getName()%></td>
            <td><%=product.getBrand()%></td>
            <td><%=product.getDesc()%></td>
            <td><%=product.getPrice()%></td>
            <td>
                <form action="${pageContext.request.contextPath}/cartservlet" method="post">
                <input type="number" name="amount" step="1">
                    <input type="hidden" name="productId" value=<%=product.getId()%>>
                    <input type="submit" value="Add to order">
                </form>
            </td>
        </tr>
        <%
}
%>
</table>
<form action="${pageContext.request.contextPath}/orderservlet" method="post">
    <input type="submit" value="Send order">
</form>

</body>
</html>
