<%@page import="it.beije.makemake.web.myEcomm.entity.User"%>
<%@page import="it.beije.makemake.web.myEcomm.PageController"%>
<%@page import="it.beije.makemake.web.myEcomm.entity.Order"%>
<%@page import="it.beije.makemake.web.myEcomm.entity.Product"%>
<%@page import="it.beije.makemake.web.myEcomm.entity.Order_item"%>
<%@page import="it.beije.makemake.web.myEcomm.Controller"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica</title>
</head>
<body><jsp:useBean id="loggedUser" class="it.beije.makemake.web.myEcomm.entity.User" scope="session"/>
<%
User user = (User) session.getAttribute("loggedUser");
if (PageController.checkLogin(user,session,response,"order.jsp")){


String errore = (String) session.getAttribute("result");

	if (errore != null) {
		//out.print("<h6 style='color:red'>"+errore+"</h6>");
		%>
		<h6 style='color:red'><%= errore %></h6>
		<%
		session.removeAttribute("result");
	}
}
%>

<form action="modificaAccount" method="post">
 <label for="nome">Nome:</label><br>
  <input type="text" name="nome"><br>
   <label for="cognome">Cognome:</label><br>
  <input type="text" name="cognome"><br>
  <label for="username">Username:</label><br>
  <input type="text" name="username"><br>
  <label for="password">Password:</label><br>
  <input type="password" name="password"><br><br>
  <input type="submit" value="Submit">
</form> 

</body>
</html>