<%--
  Created by IntelliJ IDEA.
  User: cym
  Date: 2022/9/16
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>table 模块快速使用</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <script src="layui/layui.js"></script>
    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>

    <script type="text/javascript">
        layui.use('table', function(){
            var table = layui.table;
            //第一个实例
            table.render({
                elem: '#courses'
                ,height: 312
                ,url: 'selectUserDate' //数据接口

                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'code', title: 'ID', width:80, sort: true, fixed: 'left'}
                    ,{field: 'username', title: '用户名', width:80}
                    ,{field: 'sex', title: '性别', width:80, sort: true}
                    ,{field: 'city', title: '城市', width:80}
                    ,{field: 'sign', title: '签名', width: 177}
                    ,{field: 'experience', title: '积分', width: 80, sort: true}
                    ,{field: 'score', title: '评分', width: 80, sort: true}
                    ,{field: 'classify', title: '职业', width: 80}
                    ,{field: 'words', title: '字数', width: 135, sort: true}
                ]]
            });

        });
    </script>
</head>
<body>

<%--<table id="courses" lay-filter="test"></table>--%>
<table class="layui-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>课程编号</th>
        <th>课程名称</th>
        <th>专业</th>
        <th>任课教师</th>
        <th>授课班级</th>
        <th>课程开课时间</th>
        <th>课程结课时间</th>
    </tr>
    </thead>
    <tbody>
    <!-- 循环组装 jstl+el-->
    <c:forEach items="${sessionScope.courses}" var="courses"  varStatus="i">
        <tr>
            <td>${i.index+1}</td>
            <td>${courses.code}</td>
            <td>${courses.cname}</td>
            <td>${courses.zyname}</td>
            <td>${courses.teacher}</td>
            <td>${courses.tclass}</td>
            <td>${courses.registry.dateTime.toLocaleString()}</td>
            <td>${courses.registry.updateTime.toLocaleString()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
