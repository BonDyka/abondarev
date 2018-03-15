<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:choose>
          <c:when test="${currentUser.role.name == 'admin'}">
            <c:forEach items="${users}" var="user">
              <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.createDate}"/></td>
                <td class="actions">
                  <a href="${pageContext.servletContext.contextPath}/edit?login=${user.login}">
                    Edit
                  </a>
                </td>
                <td class="actions">
                  <a href="${pageContext.servletContext.contextPath}/delete?login=${user.login}">
                    Delete
                  </a>
                </td>
              </tr>
            </c:forEach>
          </c:when>

          <c:otherwise>
            <c:forEach items="${users}" var="user">
              <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.createDate}"/></td>
                <c:choose>
                  <c:when test="${user.login == currentUser.login}">
                    <td class="actions">
                      <a href="${pageContext.servletContext.contextPath}/edit?login=${user.login}">
                        Edit
                      </a>
                    </td>
                    <td class="actions">
                      <a href="${pageContext.servletContext.contextPath}/delete?login=${user.login}">
                        Delete
                      </a>
                    </td>
                  </c:when>

                  <c:otherwise>
                    <td class="actions">
                      Edit
                    </td>
                    <td>
                      Delete
                    </td>
                  </c:otherwise>
                </c:choose>
              </tr>
            </c:forEach>
          </c:otherwise>
        </c:choose>
        
      </table>
    </div><br/>
    <c:if test="${currentUser.role.name == 'admin'}">
      <a href="${pageContext.servletContext.contextPath}/add">Add user</a>
    </c:if>
  </div>
</div>

</body>
</html>
