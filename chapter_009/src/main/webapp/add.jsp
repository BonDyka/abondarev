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
    <form action="<%=request.getContextPath()%>/add" method="post">
      <table>
        <tr>
          <td>Name</td>
          <td><input type="text" name="name"></td>
        </tr>
        <tr>
          <td>Login</td>
          <td><input type="text" name="login"></td>
        </tr>
        <tr>
          <td>Email</td>
          <td><input type="text" name="email"></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" name="Add user"></td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
