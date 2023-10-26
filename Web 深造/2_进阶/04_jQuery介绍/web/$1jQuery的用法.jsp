<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/27
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在jQuery中$的用法</title>

    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function $(domo){  // 这里的$只是一个函数名，写这样的函数就是用来减少程序员多次重复写代码的繁琐性
            var dom = document.getElementById(domo).value;
            return dom;
        }
        function fun1(){
            alert($("one"));
        }
        function fun2(){
            alert($("two"));
        }
    </script>
</head>
<body>
      <p align="center">在jQuery中$的用法</p>
      <table align="center">
          <tr>
              <td><input type="text" id="one" placeholder="请随意输入"/></td>
              <%--placeholder属性是文本框中的灰色提示字,但是用户在写入value时候提示字并不会消失--%>
              <td><input type="button" value="点击按钮1" onclick="fun1()"/></td>
          </tr>
          <tr>
              <td>
                  <input type="text" id="two" value="你的提示文字"
                         onFocus = "if(value === defaultValue){value=''; this.style.color='#000'}"
                         onBlur = "if(!value){value=defaultValue; this.style.color='#999'}"
                         style = "color:#999999"/>
                  <%--这种写法的提示字，用户在输入value时提示字会消失，体验更好哦!--%>
              </td>
              <td><input type="button" value="点击按钮2" onclick="fun2()"/></td>
          </tr>
      </table>

</body>
</html>
