<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function fun1() {
            // 使用表单选择器$(":type的值")
            var obj = $(":text"); // 其实就是只能用来定位有input标签的
            // 获取value属性的值，val()是jQuery中的函数，读取value属性值
            alert(obj.val())
        }

        function fun2() {
            var obj = $(":radio");
            for (var i = 0; i < obj.length; i++) {
                var dom = obj[i];
                alert(dom.value);
            }
        }

        function fun3() {
            var obj = $(":checkbox");
            for (var i = 0; i < obj.length; i++) {
                var dom = obj[i];
                alert(dom.value);
            }
        }
    </script>
</head>
<body>
      <input type="text" value="我是type=text"/><br>

      <input type="radio" value="man"/>男<br>
      <input type="radio" value="woman"/>女<br>

      <input type="checkbox" value="bike"/>骑行<br>
      <input type="checkbox" value="football"/>足球<br>
      <input type="checkbox" value="music"/>音乐<br>

      <input type="button" value="读取text的值" onclick="fun1()"/><br>
      <input type="button" value="读取radio的值" onclick="fun2()"/><br>
      <input type="button" value="读取checkbox的值" onclick="fun3()"/>
</body>
</html>
