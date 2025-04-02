<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/13
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.controller.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>  <%--导包--%>

<!--制造数据-->
<%
    // 创建一个Student类型对象
    Student stu1 = new Student(10,"mike");
    Student stu2 = new Student(20,"alien");
    Student stu3 = new Student(30,"smith");
    List<Student> list = new ArrayList();
    list.add(stu1);
    list.add(stu2);
    list.add(stu3);
%>

<!--数据输出-->
<table border="2" align="center">
    <tr>
        <td>学员编号:</td>
        <td>学员姓名:</td>
    </tr>

    <%
        for(Student stu:list){
    %>
        <tr>
            <td><%=stu.getSid()%></td>
            <td><%=stu.getSname()%></td>
        </tr>
    <%
        }
    %>
</table>
