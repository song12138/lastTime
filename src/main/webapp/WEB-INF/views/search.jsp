<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/11/29
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="search" action="${ctx}/search/key">
    <input id="searchKey" name="searchKey">
    <input type="submit">
</form>

<c:forEach items="${doc}" var="d">
    <input value="${d.get("filename")}">

    <%--<a href="${d.get("filepath")}"/>--%>
    <br/>
</c:forEach>

</body>
</html>
