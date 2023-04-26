<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/19
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Integer sid = (Integer)application.getAttribute("sid");
    String sname = (String)session.getAttribute("sname");
    String home = (String)request.getAttribute("home");
%>
学员ID:<%=sid%><br>
学员姓名:<%=sname%><br>
学员地址:<%=home%>
<!--以上是没使用EL表达式的写法，繁琐重复，增加程序员的操作难度-->

<hr/>

学员ID:${applicationScope.sid}<br>
学员姓名:${sessionScope.sname}<br>
学员地址:${requestScope.home}
<!--这是使用EL表达式的写法，简单，易操作-->