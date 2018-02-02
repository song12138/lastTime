<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/12/15
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href='${ctxPark}/css/bootstrap.css'>
    <script src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<form id="leaveManagement" action="${ctx}/leave/apply" method="post">
    <input name="userName" value="" placeholder="姓名" /><br>
    <input name="userOffice" value="" placeholder="部门" /><br>
    <input name="startTime"  placeholder="开始时间"  style="width:120px;" type="text" maxlength="20" class="input-medium Wdate"
           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
           value=''
    /><br>
    <input name="days" value="" placeholder="天数" /><br>
    <input name="applyTime"  placeholder="申请时间"  style="width:120px;" type="text" maxlength="20" class="input-medium Wdate"
           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
           value=''
    /><br>
    <input name="leaveType" value="" placeholder="请假类型" /><br>
    <input name="leaveReason" value="" placeholder="事由" /><br>
    <input type="submit" value="提交"/>
</form>


<br>

<c:forEach var="task" items="${tasks}">
    <input value="${task.name}"><br>
    <input value="${task.assignee}"><br>
    <input value="${task.description}"><br>
    <input value="${task.createTime}"><br>
</c:forEach>
</body>
</html>
