<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/25
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    Jsp文件在执行时，自动将文件所有内容写入到响应体，从而节省书写out.print();
    在JSP文件中直接书写jsp命令，是不能被JSP文件识别，此时只会被当作字符串写入到响应体
--%>
<html>
<head>
  <title>$Title$</title>
</head>
<body>

      <%
        //在jsp文件中，只有书写在执行标记中的内容才会被当作Java命令
        //1、声明Java变量
        int num1 = 100;
        int num2 = 200;
        //2、声明运行表达式：数学运算，关系运算，逻辑运算
        int num3 = num1+num2;
        int num4 = num2>=num1?num2:num1;
        boolean num5 = num2>=200 && num1>=100;
      %>


      <%
        if(num3>300){
      %>

          <font style="color:red;font_size:80px">谢绝入内</font>

      <%
        }else{
      %>

          <font style="color:red;font_size:80px">欢迎光临</font>

      <%
        }
      %>

</body>
</html>
