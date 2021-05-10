<%@ page import="it.beije.makemake.addressbook.Contact" %>
<%@ page import="it.beije.makemake.addressbook.AddressBook" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Padawan08
  Date: 10/05/2021
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Risultato ricerca</title>
</head>
<body>
<%
    AddressBook addressBook = AddressBook.createFromXML("C:\\Users\\Padawan08\\IdeaProjects\\MakemakeWeb\\testfiles\\rubrica.xml");
    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String phone = request.getParameter("phone");
    String mail = request.getParameter("mail");
    Contact c = new Contact(name, surname, phone, mail);
    List<Contact> contacts = addressBook.search(c);
    for (Contact contact :
            contacts) {
        %>
        <%= contact %> <br>
        <%
}
%>

</body>
</html>
