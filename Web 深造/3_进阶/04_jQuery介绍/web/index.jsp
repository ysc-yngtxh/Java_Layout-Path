<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/27
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <%--指定jQuery的库文件位置，使用相对路径，当前项目的js目录下的指定文件--%>
    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>

    <script type="text/javascript">
      /*
        1、$(document),$是jQuery中的函数名称，document是函数的参数
           作用是 document对象 变成 jQuery函数库可以使用的对象。
        2、ready:是jQuery中的函数，是准备的意思,当页面的dom对象加载成功后会执行ready函数的内容。
           ready相当于js中的onLoad事件。
        3、function()自定义的表示onLoad后要执行的功能。
      */
      $(document).ready(function(){
        alert("Hello jQuery");
      }) // 标准写法

      $(function(){
           alert("Hello jQuery");
         }
      ) // 简写，效果一样
    </script>
  </head>
  <body>
  $END$
  </body>
</html>
