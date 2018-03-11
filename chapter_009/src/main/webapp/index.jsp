<%@ page import="ru.job4j.sj.models.User" %>
<%@ page import="ru.job4j.sj.store.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info Storage</title>
  <style type="text/css">
    table {
      width: 300px; /* Ширина таблицы */
      border-collapse: collapse; /* Убираем двойные линии между ячейками */
    }
    td, th {
      padding: 3px; /* Поля вокруг содержимого таблицы */
      border: 1px solid black; /* Параметры рамки */
    }
    th {
      background: #b0e0e6; /* Цвет фона */
    }
  </style>
</head>
<body>
<div id="main" align="center">
  <div id="header">
    <h1>User Info Storage</h1>
  </div>
  <div class="content">
    <div class="users-table">
      <table>
        <tr>
          <th>Name</th>
          <th>Login</th>
          <th>Email</th>
          <th>Create date</th>
          <th colspan="2">Actions</th>
        </tr>
        <% for (User user : UserStore.getInstance().getAll()) {%>
        <tr>
          <td><%=user.getName() %></td>
          <td><%=user.getLogin() %></td>
          <td><%=user.getEmail() %></td>
          <td><%=user.getCreateDate().toString() %></td>
          <td class="actions">
            <a href="<%=request.getContextPath() %>/edit.jsp?login=<%=user.getLogin()%>">
              Edit
            </a>
          </td>
          <td class="actions">
            <a href="<%=request.getContextPath() %>/delete?login=<%=user.getLogin()%>">
              Delete
            </a>
          </td>
        </tr>
        <% } %>
      </table>
    </div><br/>
    <a href="<%=request.getContextPath()%>/add.jsp">Add user</a>
  </div>
</div>

</body>
</html>
