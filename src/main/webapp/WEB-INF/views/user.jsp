<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--<p>${users}</p>--%>
    <p>.....................................................</p>
    <c:forEach items="${users}" var="user">
        <tr>${user.realname}</tr>
        <tr>${user.username}</tr>
        <tr>${user.email}</tr>
    </c:forEach>
</body>
</html>
d