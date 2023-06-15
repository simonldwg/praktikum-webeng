<%--
  Created by IntelliJ IDEA.
  User: dstru
  Date: 02.06.2023
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Danke :)</title>
        <style>h1, h4 {color: green;} </style>
</head>
<body>
    <h1>Bestellung abgeschlossen</h1>
    <h4>Vielen Dank für deinen Einkauf</h4>
    <a href="<%= response.encodeURL("/webeng05/")%>?action=articles">Zu den Artikeln</a>
</body>
</html>
