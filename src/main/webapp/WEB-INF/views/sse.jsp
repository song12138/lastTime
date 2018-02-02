<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/12/27
  Time: 16:38
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
<div id="msgPush"></div>
</body>

<script>
    if (!!Window.EventSource) {
            var source=new EventSource('push');
            s="";

            source.addEventListener('message',function (e) {
                s = e.data + "<br/>";
                $('#msgPush').html(s)
            })

            source.addEventListener('open',function (e) {
                console.log("连接打开");
            },false)

            source.addEventListener("error",function (e) {
                if (e.readyState==EventSource.CLOSED) {
                    console.log("连接关闭");
                }else {
                    console.log(e.readyState);
                }
            })
    }else {
        console.log("你的浏览器不支持SSE")
    }
</script>
</html>
