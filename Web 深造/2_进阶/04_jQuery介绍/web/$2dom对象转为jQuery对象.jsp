<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/29
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dom对象转为jQuery</title>

    <script type="text/javascript" src="scripts\jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function btnClick() {
            // 获取dom对象
            var obj = document.getElementById("btn");
            // 使用dom的value属性，获取值
            alert("使用dom对象的属性=" + obj.value);

            // 把dom对象转jQuery，使用jQuery库中的函数
            var jobj = $(obj);
            // 调用jQuery中的函数，获取value的值
            alert(jobj.val());
        }
    </script>
</head>
<body>
      <input type="button" id="btn" value="==我是按钮==" onclick="btnClick()"/>
</body>
</html>
