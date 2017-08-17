<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--<p>${users}</p>--%>
    <table>
        <tbody>
        <c:forEach items="${users}" var="user">
            <%--<tr>${user.realname}</tr>--%>
            <tr>${user.username}</tr><br>
            <%--<tr>${user.employeeNo}</tr>--%>
            <%--<tr>${user.email}</tr>--%>
            <%--<tr>${user.locked}</tr>--%>
            <%--<tr>${user.phone}</tr>--%>
            <%--<tr>${user.salt}</tr>--%>
        </c:forEach>
        </tbody>
        <p>${msg}</p>
        <p>${age}</p>
        <p>${height}</p>


    </table>

</body>
</html>
