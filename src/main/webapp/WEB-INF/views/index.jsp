<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/8/10
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
    <meta name="decorator" content="blank"/>
    <link rel="shortcut icon" type="image/x-icon" href="${ctxStatic}/assert/img/favicon.ico" media="screen" />
    <link rel="stylesheet" href="${ctxStatic}/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${ctxStatic}/assert/css/index.css?v=0.102">
    <link rel="stylesheet" href="${ctxStatic}/assert/css/style.css?v=0.102">
    <script src="${ctxStatic}/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/js/bootstrap/bootstrap.js" type="text/javascript"></script>
</head>
<body>
<div class="loginBar">
    <form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
        <input type="hidden" id="userarea" name="userarea">
        <div class="loginCon">
            <div class="name">
                <p></p>
                <input type="text" id="username" name="username" class="input-block-level required" value="" placeholder="用户名">
            </div>
            <div class="pwd">
                <p></p>
                <input type="password" id="password" name="password" class="input-block-level required" value="" placeholder="密码">
            </div>
            <input type="hidden" value="" name="clear" id="clear">
            <div class="login">
                <input id="loginBtn" type="submit" value="登录">
            </div>
        </div>
        <%--<div id="messageBox"--%>
             <%--class="alert alert-error ${empty msg ? 'hide' : ''}">&lt;%&ndash;<button data-dismiss="alert" class="close">×<tton>&ndash;%&gt;--%>
            <%--<label id="loginError" class="error">${msg}</label>--%>
        <%--</div>--%>
    </form>
</div>
<script >
    $(function () {
        $('#loginBtn').click(function () {
            $('#loginForm').submit();
        });
    });


</script>

</body>
</html>
