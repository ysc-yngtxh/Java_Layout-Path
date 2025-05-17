<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/29
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jQuery对象转为dom对象</title>

    <script type="text/javascript" src="scripts\jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function btnClick(){
            // 使用jQuery语法，获取页面中的dom对象
            var obj= $("#txt")[0]; // 从数组中获取下标是0的dom对象
            // var obj = $("#txt")get(0)  // 效果一样

            var num = obj.value;
            obj.value = num*num;
        }
    </script>
</head>
<body>
      <input type="text"  id="txt" value="请输入整数"
             onFocus="if(value === defaultValue){value=''; this.style.color='#000'}"
             onBlur="if(!value){value=defaultValue; this.style.color='#999'}"
             style="color:#999999"/><br>
      <input type="button" value="计算平方" onclick="btnClick()"/>
</body>
</html>
