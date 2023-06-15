<%@ page import="transfer.ShoppingCart" %>
<%@ page import="transfer.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 01.06.2023
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warenkorb</title>
</head>
<body>
    <h1>Warenkorb</h1>
    <%-- Link zur Artikelliste --%>
    <a href="<%= response.encodeURL("/webeng05/")%>?action=articles">Zurück zur Artikelliste</a>
    <br><br>
    <table>
        <thead>
        <tr>
            <th>Artikelname</th>
            <th>Preis</th>
            <th>Menge</th>
        </tr>
        </thead>
            <tbody>
            <%
                ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
                if (cart != null && !cart.getArticles().isEmpty()) {
                    // Warenkorb ist nicht leer
                    for (Map.Entry<Article, Integer> entry : ((HashMap<Article, Integer>)cart.getArticles()).entrySet()) {
            %>
            <tr>
                <td style="font-weight: bold"><%= entry.getKey().getName()%></td>
                <td><%= entry.getKey().getPrice() %> €</td>
                <td style="text-align: center"><%= entry.getValue().toString() %></td>
            </tr>
            <% }
             } else { %>
            <tr>
                <td colspan="3" style="font-style: italic; color: red">Der Warenkorb ist leer</td>
            </tr>
            <% } %>
        </tbody>
        </table>
        <p style="font-weight: bold">Gesamtpreis:</p> <p style="text-decoration: underline"><%= (cart==null) ? 0.0F : cart.getTotal() %>,- €</p>

        <% if(cart != null && !cart.getArticles().isEmpty()) {%>
            <%-- Button zum Checkout --%>
            <form action="<%= response.encodeURL("/webeng05/") %>" method="get">
                <input type="hidden" name="action" value="checkout">
                <input style="color: green" type="submit" value="Checkout">
            </form>
            <%-- Button zum Abbrechen des Einkaufs --%>
            <form action="<%= response.encodeURL("/webeng05/") %>?action=cancel" method="post">
                <input style="color: red" type="submit" value="Einkauf abbrechen">
            </form>
        <% } %>
    </body>
</html>
