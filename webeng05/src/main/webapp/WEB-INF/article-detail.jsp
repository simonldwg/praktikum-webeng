<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 01.06.2023
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="article" beanName="article" type="transfer.Article" scope="request"></jsp:useBean>
<html>
    <head>
        <title>Artikeldetail</title>
    </head>
    <body>

        <h1>Artikeldetail</h1>
        <h2><jsp:getProperty name="article" property="name"/></h2>
        <p>Preis: <jsp:getProperty name="article" property="price"/> €</p>
        <p>Lagerbestand: <jsp:getProperty name="article" property="quantity"/></p>

        <form action="<%= response.encodeURL("/webeng05/") %>?action=addToCart" method="post">
            <input type="number" id="<jsp:getProperty name="article" property="quantity"/>" name="quantity" min="1" max="100" required>
            <input type="hidden" name="id" value="<jsp:getProperty name="article" property="id"/>">
            <input type="submit" value="Zum Warenkorb hinzufügen">
        </form>
    </body>
</html>
