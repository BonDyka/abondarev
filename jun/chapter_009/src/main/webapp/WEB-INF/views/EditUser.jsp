<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
      <table>
        <tr>
          <td>Name</td>
          <td>
            <input type="text" name="name" value="${user.name}" disabled>
            <input type="text" name="name" value="${user.name}" hidden>
          </td>
        </tr>
        <tr>
          <td>Login</td>
          <td>
            <input type="text" name="login" value="${user.login}" disabled>
            <input type="text" name="login" value="${user.login}" hidden>
          </td>
        </tr>
        <tr>
          <td>Password</td>
          <td>
            <input type="password" name="password" value="${user.password}">
          </td>
        </tr>
        <tr>
          <td>Email</td>
          <td><input type="text" name="email" value="${user.email}"></td>
        </tr>
        <tr>
          <td>Created</td>
          <td>
            <input type="text" name="create" value="${user.createDate}" disabled>
            <input type="text" name="create" value="${user.createDate}" hidden>
          </td>
        </tr>
        <c:choose>
          <c:when test="${currentUser.role.name == 'admin'}">
            <tr>
              <td>Role</td>
              <td>
                <select name="role_id">
                  <c:forEach items="${roles}" var="role">
                    <option value="${role.id}"><c:out value="${role.name}"/></option>
                  </c:forEach>
                </select>
              </td>
            </tr>
          </c:when>

          <c:otherwise>
            <tr>
              <td>Role</td>
              <td>
                <input type="text" name="role_id" value="${user.role.name}" disabled>
                <input type="text" name="role_id" value="${user.role.id}" hidden>
              </td>
            </tr>
          </c:otherwise>
        </c:choose>
        <tr>
          <td colspan="2"><input type="submit" name="Add user"></td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
