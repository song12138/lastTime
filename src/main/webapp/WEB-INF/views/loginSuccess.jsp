<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/10/20
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>登陆成功</p>
<input id="logout" value="退出" type="button" onclick='logout()'/>
<%--<shiro:hasPermission name="one">--%>
<a href="${ctx}/one">one</a>
<%--</shiro:hasPermission>--%>
<shiro:hasPermission name="two">
<a href="${ctx}/two">two</a>
</shiro:hasPermission>
<shiro:hasPermission name="three">
<a href="${ctx}/three">three</a>
</shiro:hasPermission>
</body>
</html>
<script type="text/javascript">
    function logout () {
        window.location.href="${ctx}/logout";
    }
</script>

