<%@ page import="transfer.Article" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 31.05.2023
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Artikelübersicht</title>
    </head>
    <body>
        <h1>Artikelübersicht</h1>

        <table>
            <thead>
                <tr>
                    <th>Artikelname</th>
                    <th>Preis</th>
                    <th>Menge</th>
                </tr>
            </thead>
            <tbody>
                <% for(Article article : (List<Article>)request.getAttribute("allArticles")) { %>
                    <tr>
                        <td style="font-weight: bold"><%= article.getName()%></td>
                        <td><%= article.getPrice() %> €</td>
                        <td style="text-align: center"><%= article.getQuantity()%></td>
                        <td>
                            <a href="<%= response.encodeURL("/webeng05/") %>?action=detail&id=<%= article.getId() %>">Details</a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <br>
        <a href="<%= response.encodeURL("/webeng05/")%>?action=cart">Warenkorb</a>
    </body>
</html>
