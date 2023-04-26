<%--
  Created by IntelliJ IDEA.
  User: cym
  Date: 2022/9/12
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <style>
        body{
            width:100%;
            height:100%;
            background: url("imgs/plane.jpg") no-repeat center center;
            background-size:cover;
            position:absolute;
            left:0;
            top:0;
        }
        div{
            width:400px;
            height: 220px;
            margin:230px auto ;
            background-color: white;
            opacity: 0.7;
            border-color: black;
            border-width: 2px;
            border-style: dashed;
            border-radius: 10px;
        }
    </style>
    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <script type="text/javascript">

        function doInsert() {
            let username = $("#username").val();
            let password = $("#password").val();
            let checkupassword = $("#checkupassword").val();
            let tenant = $('input[name="tenant"]:checked').val();
            if(password != checkupassword){
                alert("两次密码输入不一致，请重新输入")
                document.getElementById("password").value = "";
                document.getElementById("checkupassword").value = "";
            }else {
                //提交数据
                $.post("insert", {
                        username: username,
                        password: password,
                        tenant: tenant
                    },
                    function (rdata) {
                        if (rdata == "yes") {
                            window.location.href = "index.jsp"
                            alert("注册成功")
                        } else {
                            alert(rdata);
                        }
                    });
            }
        }
    </script>
</head>
<body>
<div >
<%--    <form action="insert.do" method="post">--%>
        <table align="center">
            <tr>
                <h2 style="padding-left: 60px; margin-bottom: 0px;">欢迎注册</h2>
            </tr>
            <tr>
                <td><span style="font-size: 18px;">账&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;&nbsp;</span></td>
                <td>
                    <input type="text" id="username" name="username" placeholder="请设置账号" size="22"/>
                </td>
            </tr>
            <tr>
                <td><span style="font-size: 18px;">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;</span></td>
                <td>
                    <input type="password" id="password" placeholder="请设置登录密码" size="22"/>
                </td>
            </tr>
            <tr>
                <td><span style="font-size: 18px;">确认密码&nbsp;&nbsp;</span></td>
                <td>
                    <input type="password" id="checkupassword" placeholder="再次输入密码" size="22"/>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <input type="radio" name="tenant" value="老师" />老师
                    <input type="radio" name="tenant" value="学生" checked/>学生
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <input type="button" value="确定" onclick="doInsert()">
                    <input type="reset" value="重置">
                    <input type="button" value="返回" onclick='location.href=("index.jsp")'>
                </td>
            </tr>
        </table>
<%--    </form>--%>
</div>

</body>
</html>
