<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/12/27
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
    <script src="${ctxStatic}/js/jquery/jquery.min.js"></script>
</head>
<body>
<input type="button" onclick="req()" value="请求">
<div id="resp"></div>
</body>
<script>
    function req() {
        $.ajax({
            url:"${ctx}/convert",
            data:"sxf-123456",
            type:"post",
            contentType:"application/x-wisely",
            success:function (data) {
                $("#resp").html(data);
            }
        })
    }
</script>
</html>
