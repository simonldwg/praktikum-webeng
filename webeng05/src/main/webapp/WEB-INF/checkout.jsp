<%@ page import="transfer.ShoppingCart" %>
<jsp:useBean id="cart" beanName="cart" type="transfer.ShoppingCart" scope="session"></jsp:useBean>
<%--
  Created by IntelliJ IDEA.
  User: dstru
  Date: 02.06.2023
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Checkout</title>
        <style>h1, h4 {color: green;} </style>
    </head>
    <body>
        <h1>Einkauf abschließen:</h1>
        <p style="font-weight: bold">Zu zahlender Preis:</p><p style="text-decoration: underline"><jsp:getProperty name="cart" property="total"/>,- €</p>
        <br>

        <form action="<%= response.encodeURL("/webeng05/") %>?action=checkout-finish" method="POST">
            <p style="font-weight: bold">Wählen Sie Ihre Zahlungsmethode:</p>
            <label>
            <input type="radio" name="paypal" value="paypal" checked> PayPal<br>
            <input type="radio" name="creditCard" value="creditCard"> Kreditkarte<br>
            <input type="radio" name="debitCard" value="debitCard"> EC Karte<br>
            </label>
            <br>
            <input type="submit" value="Bezahlen">
        </form>

        <h4>Vielen Dank für Ihren Einkauf!</h4>
    </body>
</html>
