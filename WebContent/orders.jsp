<%@page import="it.andrea.makemake.web.controller.EcommerceController"%>
<%@page import="it.andrea.makemake.web.controller.SiteController"%>
<%@page import="it.andrea.makemake.web.entity.User"%>
<%@page import="it.andrea.makemake.web.entity.Order"%>
<%@page import="it.andrea.makemake.web.entity.OrderItem"%>
<%@page import="it.andrea.makemake.web.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ORDERS</title>
</head>
<body>
	<jsp:useBean id="loggedUser" class="it.andrea.makemake.web.entity.User"
		scope="session" />
	<%
	User user = (User) session.getAttribute("loggedUser");
	if (SiteController.isLogged(user, "orders.jsp", session, response)) {
		for (Order order : user.getOrders()) {
			%><h4><%=order.toString()%></h4><%
			for (OrderItem orderItem : order.getOrderItems()) {
				Product product = EcommerceController.getProductById(orderItem.getProductId());
				%><h5><%=product.getName() + " | " + product.getBrand() + " | " + "Prezzo per singolo prodotto: " + product.getPrice() + "€" + " | " + "Quantita acquistata: " + orderItem.getQuantity()%></h5><%
			}
		}
	}
	%>

</body>
</html>