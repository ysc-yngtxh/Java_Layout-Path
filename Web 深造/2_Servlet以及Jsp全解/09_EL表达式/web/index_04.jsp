<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/19
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--传统Java命令方式实现关系运算输出-->
<%
    String num1 = (String) session.getAttribute("key1");
    Integer num2 = (Integer) session.getAttribute("key2");
    int sum = Integer.valueOf(num1) + num2;  //全部转换成Integer包装类，然后自动拆箱成int类型
%>
传统的Java命令计算后的结果：<%=sum%>

<%
    String age = (String) session.getAttribute("age");
    if (Integer.valueOf(age) >= 18) {
%>
       欢迎光临<br/>
<%
    } else {
%>
       谢绝入内<br/>
<%
    }
%>
<!--以上JSP程序可能会出现类型转换异常，但现在我们不需要过分深入，只需了解其原理即可-->

<!--EL表达式输出关系运算-->
EL表达式计算后的结果：${key1 + key2}
EL表达式输出关系运算：${age >= 18?"欢迎光临":"谢绝入内"}
