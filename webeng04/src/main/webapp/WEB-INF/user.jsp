<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 05.05.2023
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" beanName="user" type="models.User" scope="request"></jsp:useBean>
<jsp:useBean id="user2" class="models.User"></jsp:useBean>
<html>
<head>
    <title>User</title>
</head>
<body>
    <h1>User <jsp:getProperty name="user" property="firstName"/></h1>
    <p>
        Nachname: <jsp:getProperty name="user" property="lastName"/><br>
        Alter: <jsp:getProperty name="user" property="age"/>
    </p>
    <jsp:setProperty name="user2" property="firstName" value="Sarah"></jsp:setProperty>
    <jsp:setProperty name="user2" property="lastName" value="Musterfrau"></jsp:setProperty>
    <jsp:setProperty name="user2" property="age" value="40"></jsp:setProperty>
    <h1>User2 <jsp:getProperty name="user2" property="firstName"/></h1>
    <p>
        Nachname: <jsp:getProperty name="user2" property="lastName"/><br>
        Alter: <jsp:getProperty name="user2" property="age"/>
    </p>
</body>
</html>
