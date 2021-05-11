<%@page import="it.beije.makemake.web.myEcomm.entity.User"%>
<%@page import="it.beije.makemake.web.myEcomm.PageController"%>
<%@page import="it.beije.makemake.web.myEcomm.entity.Order"%>
<%@page import="it.beije.makemake.web.myEcomm.entity.Product"%>
<%@page import="it.beije.makemake.web.myEcomm.entity.Order_item"%>
<%@page import="it.beije.makemake.web.myEcomm.Controller"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordini</title>
</head>
<body>
<jsp:useBean id="loggedUser" class="it.beije.makemake.web.myEcomm.entity.User" scope="session"/>
<%
User user = (User) session.getAttribute("loggedUser");
if (PageController.checkLogin(user,session,response,"order.jsp")){
	for(Order order : user.getOrders()){
		%>
		<h4> <%=order.toString() %></h4>
		<%
		for(Order_item orderItem : order.getOrders_item()){
		Product product = Controller.getProductbyID(orderItem.getOrderId());
		%>
		<h5> <%= product.getName() + " | " + product.getBrand() + " | " + "Prezzo per un prodotto: " + product.getPrice() + " € " + "Quantità acquistata: " + orderItem.getQuantity()%></h5>
		<%
		}
	}
	
}

%>


</body>
</html>