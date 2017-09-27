<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 2017/9/27
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/taglib.jsp" %>
<html>
<head>
    <title>分页</title>
    <script src="${ctxStatic}/js/jquery/jquery.min.js"></script>
    <%--<script src="${ctxStatic}/js/template/template-debug.js" ></script>--%>
    <script src="${ctxStatic}/js/jsrender/jsrender.js" ></script>
    <script src="${ctxStatic}/js/common/queryPage.js"></script>
</head>
<div class="pageDiv" id="pageDiv">
</div>

</body>
<script type="text/tmp" id="templateTr">
    <table style="margin-bottom:10px;">
    <tr>
        <th width="120px">1</th>
        <th width="120px">2</th>
        <th width="120px">3</th>
        <th width="120px">4</th>
        <th width="120px">5</th>
    </tr>
    {{for data}}
    <tr>
    <td>{{:username}}</td>
    <td>{{:realname}}</td>
    <td>{{:employeeNo}}</td>
    <td>{{:email}}</td>
    <td>{{:phone}}</td>
    </tr>
    {{/for}}
</table>
<div class="page fr">
            <button id="prev" {{if isFirst == 0}}disabled="disabled"{{{/if}} class="prevPage active" onclick="query({{:pageNo-1}})">上一页</button>
            <button id="next" {{if isLase == 0 }}disabled="disabled"{{/if}} onclick="query({{:pageNo+1}})">下一页</button>
            <span>共 {{:pageCount}} 页，到第&nbsp;
            <input id="queryPageNo" type="text" value="{{:pageNo}}"
                onkeyup=""
                > &nbsp;页</span>
            <button onclick="queryByPageNo()">确定</button>
        </div>

</script>

<script type="text/javascript">
    $(function () {
        query(1);
    });
    /**
     * 使用分页查询必须要有这个方法
     * 分页查询
     * @param page 页码
     */
    function query(pageNo) {
        $.queryPage.query('pageDiv', 'templateTr', 'restPage', pageNo, 3, {});
    }
    /**
     * 使用分页查询必须要有这个方法
     * 分页查询确定按钮调用方法
     */
    function queryByPageNo() {
        var pageNo = $('#queryPageNo').val();
        query(pageNo);
    }
</script>

</html>