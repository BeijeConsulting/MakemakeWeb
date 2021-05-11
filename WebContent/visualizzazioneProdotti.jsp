<%@page import="it.beije.makemake.web.ecommerce.manager.MyEcommerceManager"%>
<%@page import="it.beije.makemake.web.ecommerce.Product"%>
<%@page import="java.util.List"%>

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
List<Product> prodotti=(MyEcommerceManager.selectProdotti());
for(Product p: prodotti){
out.println(p);
%><br>
<%
}
%>
  <a href="homePage.jsp">torna alla Home</a>
</body>
</html>