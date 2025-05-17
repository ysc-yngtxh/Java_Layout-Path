<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/29
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jquery中的选择器</title>
    <style type="text/css">
        div {
            background: gray;
            width: 200px;
            height: 100px;
        }
        #one {
            font-size: 35px;
        }

        .two {
            background: yellow;
        }
    </style>

    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function fun1() {
            // id选择器
            var obj = $("#one");
            obj.css("background", "red");
        }
        function fun2() {
            // 样式选择器
            var obj = $(".two");
            obj.css("background", "green");
        }
        function fun3() {
            // 标签选择器
            var obj = $("div"); // 数组有3个对象
            obj.css("background", "blue");
        }
        function fun4() {
            // 所有选择器
            var obj = $("*");
            obj.css("background", "pink");
        }
        function fun5() {
            // 组合选择器
            var obj = $("#one,.two,span");
            obj.css("background", "orange");
        }
    </script>

</head>
<body>
      <div id="one">我是one的div</div><br>
      <div class="two">我是样式是two的div</div><br>
      <div>我是没有id，class的div</div><br>
      <span>我是span标签</span><br>
      <input type="button" value="获取id是one的dom对象" onclick="fun1()"/><br>
      <input type="button" value="使用class样式获取dom对象" onclick="fun2()"/><br>
      <input type="button" value="使用标签选择器" onclick="fun3()"/><br>
      <input type="button" value="所有选择器" onclick="fun4()"/><br>
      <input type="button" value="组合选择器" onclick="fun5()"/>
</body>
</html>
