<%@ page import="com.example.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/19
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--传统写法-->
<%
    Student stu = (Student) request.getAttribute("key");
%>
学员编号:<%=stu.getSid()%>
学员姓名:<%=stu.getSname()%>

<hr/>

<!--EL表达式-->
学员编号:${requestScope.key.sid}
学员姓名:${requestScope.key.sname}
