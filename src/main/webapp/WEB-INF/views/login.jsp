<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/10/20
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
    <meta name="decorator" content="blank"/>
</head>
<body>
<form action="${ctx}/shiro/login" method="POST">
    姓名：<input type="text" name="username"/><br/>
    密码：<input type="text" name="password"/><br/>
    <%--验证：<input type="text" name="verifyCode"/>--%>
    <%--&nbsp;&nbsp;--%>
    <%--<img id="verifyCodeImage" onclick="reloadVerifyCode()" src="<%=request.getContextPath()%>/mydemo/getVerifyCodeImage"/><br/>--%>
    <input type="submit" value="确认"/>

</body>
</html>