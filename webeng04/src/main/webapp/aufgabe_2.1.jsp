<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 03.05.2023
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public static String cipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
%>
<html>
<head>
    <title>Praktikum 04</title>
</head>
<body>
    <h2>
        Aktuelles Datum: <%= new java.text.SimpleDateFormat().format(new java.util.Date()) %>
    </h2>
    <h2>
        3 x 5: <%= 3*5 %>
    </h2>
    <form method="post">
        <label>
            Passwort:
            <input type="text" name="password" placeholder="Passwort eingeben..." required>
        </label>
        <label>
            Offset:
            <input type="number" name="offset" value="1" required>
        </label>
        <input type="submit" value="Abschicken">
    </form>
    <strong>
        <%
            if(request.getMethod().equals("POST")) {
                final String password = request.getParameter("password");
                final int offset = Integer.parseInt(request.getParameter("offset"));


                if(password == null || password.isBlank()) {
                    out.print("Das Passwort darf nicht leer sein!");
                } else if(offset <= 0) {
                    out.print("Der Offset muss positiv sein!");
                } else {
                    out.print("Dein codiertes Passwort: " + cipher(password, offset));
                }
            }
        %>
    </strong>
    <p><small>Verwendete Methode: <%= request.getMethod() %></small></p>
    <p><small>Test-Parameter: <%= config.getInitParameter("test") %></small></p>
</body>
</html>
