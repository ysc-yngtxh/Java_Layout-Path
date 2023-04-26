<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <style type="text/css">
        body{
            width:100%;
            height:100%;
            background: url("imgs/plane.jpg") no-repeat center center;
            background-size:cover;
            position:absolute;
            left:0;
            top:0;
            right: 0;
            overflow-x: hidden;
            overflow-y: hidden;
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
    <script type="text/javascript" >
        window.onload=function () {
            pushHistory();
            // window.addEventListener("popstate", function (e) {
            //     alert("监听到了浏览器的返回按钮事件");
            //     location.href = "index.jsp"
            // }, false);
            window.onpopstate = function () {
                alert("监听到了浏览器的返回按钮事件");
                location.href = "index.jsp"
            }
            function pushHistory() {
                var state = {
                    title: "title",
                    url: "index.jsp"
                };
                window.history.pushState(state, "title", "index.jsp");
            }
        };

        setTimeout(() => {
            document.getElementById("ysc").click();
        }, 1000)

        function cym(){
            alert("曹玉敏被点击了")
        }
    </script>

</head>
<body scroll="no">
    <div>
        <form action="login" method="post">
            <table align="center">
                <tr>
                    <h2 align="center">用户登录</h2>
                </tr>
                <tr>
                    <td><span style="font-size: 18px;">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;&nbsp;</span></td>
                    <td>
                        <input type="text" name="username" placeholder="请输入账号" size="22"/>
                    </td>
                </tr>
                <tr>
                    <td><span style="font-size: 18px;">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;</span></td>
                    <td>
                        <input type="password" name="password" placeholder="请输入密码" size="22"/>
                    </td>
                </tr>
                <tr align="center">
                    <td colspan="2">
                        <input type="radio" name="tenant" value="老师" />老师
                        <input type="radio" name="tenant" value="学生" checked/>学生
                    </td>
                </tr>
                <tr align="center">
                    <td colspan="3">
                        <input type="submit" value="登录">
                        <input id="regist" type="button" value="注册" onclick='location.href=("registry.jsp")'>
                        <input id="ysc" type="button" value="重置" onclick="cym()">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
