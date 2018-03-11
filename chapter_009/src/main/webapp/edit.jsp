<%@ page import="ru.job4j.sj.models.User" %>
<%@ page import="ru.job4j.sj.store.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info Storage</title>
</head>
<body>
<div id="main" align="center">
  <div id="header">
    <h1>Add User Info</h1>
  </div>
  <div class="content">
    <form action="<%=request.getContextPath()%>/edit" method="post">
      <% { User user = UserStore.getInstance().get(request.getParameter("login")); %>
      <table>
        <tr>
          <td>Name</td>
          <td>
            <input type="text" name="name" value="<%=user.getName()%>" disabled>
            <input type="text" name="name" value="<%=user.getName()%>" hidden>
          </td>
        </tr>
        <tr>
          <td>Login</td>
          <td>
            <input type="text" name="login" value="<%=user.getLogin()%>" disabled>
            <input type="text" name="login" value="<%=user.getLogin()%>" hidden>
          </td>
        </tr>
        <tr>
          <td>Email</td>
          <td><input type="text" name="email" value="<%=user.getEmail()%>"></td>
        </tr>
        <tr>
          <td>Created</td>
          <td>
            <input type="text" name="create" value="<%=user.getCreateDate().toString()%>" disabled>
            <input type="text" name="create" value="<%=user.getCreateDate().toString()%>" hidden>
          </td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" name="Add user"></td>
        </tr>
      </table>
      <% } %>
    </form>
  </div>
</div>
</body>
</html>
