<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/10/24
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>three</p>
<form id="addUser" action="${ctx}/three/add" method="post" >
    姓名：<input type="text" name="username" value=""/><br/>
    密码：<input type="text" name="password" value=""/><br/>
    <input type="submit" value="确认"/>
    <p>${error}</p>
</form>
</body>
</html>
