<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
    <script src="layui/layui.js"></script>
    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <script type="text/javascript">

        <%
           String login = (String) session.getAttribute("login");
           if(login.equals("no")){
        %>
        alert("账号或密码错误，请重新输入")
        window.location.href = "index.jsp"
        <%
            }
        %>

        //使用layui-tree组件
        layui.use(['tree'], function () {
            var tree = layui.tree;
            //模拟数据1
            var data1 = [{
                title: '用户信息管理'
                , id: 1
                , children: [{
                    title: '<a href="userDate.jsp" target="content">用户信息查询</a>'
                    , id: 1000
                }, {
                    title: '<a href="show.jsp" target="content">用户课表查询</a>'
                    , id: 1001
                }, {
                    title: '<a href="" target="content">修改密码</a>'
                    , id: 1002
                }]
            }, {
                title: '课程信息管理'
                , id: 2
                , children: [{
                    title: '添加选课'
                    , id: 2000
                }, {
                    title: '<a href="coursesDate" target="content">查看已选课程</a>'
                    , id: 2001
                }]
            }, {
                title: '门诊'
                , id: 3
                , children: [{
                    title: '挂号'
                    , id: 3000
                }, {
                    title: '划价'
                    , id: 3001
                }]
            }, {title: '其他'}];
            //常规用法
            tree.render({
                elem: '#myTree' //使用标签的id，表示将菜单的数据显示在指定的标签中
                , data: data1
            });

        });

        function logout(){
            //浏览器显示一个询问框(确定，取消)
            //根据点击的按钮返回true、false
            var f = confirm('是否确认退出');
            if(f){
                location.href = 'http://localhost:8080/myWeb/logOut';
                // location.replace("http://localhost:8080/myWeb/index.jsp")
                // window.top.localhost = window.self.location
            }

        }
    </script>

    <style>
        .education {
            position: absolute;
            left: 0;
            top: 0;
            width: 200px;
            height: 100%;
            line-height: 60px;
            text-align: center;
            color: #fafafa;
            font-size: 16px
        }
    </style>
<head/>
<%--    <frameset rows="15%,85%">--%>
<%--        <frame name="top"/>--%>
<%--        <frameset cols="20%,80%">--%>
<%--            <frame name="left" src="./right.jsp"/>--%>
<%--            <frame name="right"/>--%>
<%--        </frameset>--%>
<%--    </frameset>--%>
<body class="layui-layout-body ">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header layui-bg-blue">
        <div class="education">
            教务管理系统
        </div>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:logout()">退出</a>
            </li>
        </ul>
    </div>

    <div class="layui-side" style="background-color: #d6eaff">
        <div class="layui-side-scroll">

            <!-- 展示tree结构 标签 -->
            <div id="myTree" class="demo-tree demo-tree-box"></div>

        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <!-- 内嵌子窗口 (不用了) -->
            <iframe name="content" width="100%" height="100%" frameborder="0"/>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
</body>


</html>