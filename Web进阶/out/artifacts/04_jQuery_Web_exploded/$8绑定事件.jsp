<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/30
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="scripts\jquery-3.4.1.js"></script>
    <script type="text/javascript" src="scripts\md5.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#loginBut").click(function(){
                var v_md5password = $.md5($("#password").val());
                alert(v_md5password);
                $("#md5password").val(v_md5password);
            })
            /*$("#btn").click(function(){
                $("span").append("<input type='button' value='我是一个新增按钮' id='newbtn'/>");
                $("#newbtn").on("click",function(){
                    alert("我是新增按钮的绑定事件");
                })
            })*/
        })
    </script>
</head>
<body>
      <span>我是一个span标签</span><br><br>
      <input type="button" value="on绑定事件" id="btn"/>
      <form action="login" method="post">
          账号<input type="text" name="username"><br>
          密码<input type="text" id="password"><br>
          <input type="hidden" name="password" id="md5password">
          <input type="submit" value="登录" id="loginBut">
      </form>
</body>
</html>
