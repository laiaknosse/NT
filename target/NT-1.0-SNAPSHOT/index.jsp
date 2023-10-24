<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="com.example.nt.logic.Model" %>
<html>
<head>
    <title>Домашняя страница по работе с пользователем</title>
</head>
<body>
<h1>Домашняя страница по работе с пользователем</h1>
Введите id пользователя (0 - для вывода всего списка пользователей)
<br/>
Доступно: <%
    Model model = Model.getInstance();
    out.print(model.getFromList().size());
%>
<form method="get" action="get">
    <label>ID:
        <input type="text" name="id"><br/>
    </label>
    <button type="submit">Поиск</button>
</form>

<a href="addUser.html">Создать нового пользователя</a>
</body>
</html>