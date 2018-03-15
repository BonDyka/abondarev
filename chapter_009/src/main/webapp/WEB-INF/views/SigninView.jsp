<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info Storage</title>
    <style>
        .alert {
            color: #ff0000;
        }
    </style>
</head>
<body>
<div id="main">
    <div align="center">
        <c:if test="${error != ''}">
            <div class="alert">
                <c:out value="${error}" />
            </div>
        </c:if>
        <h2>SignIn Form</h2>
        <form action="${pageContext.servletContext.contextPath}/signin" method="post">
            <table>
                <tr>
                    <td>Login</td>
                    <td>
                        <input id="log" type="text" name="login">
                    </td>
                </tr><tr>
                    <td>Password</td>
                    <td>
                        <input id="pass" type="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" ><input type="submit"></td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>
