<%@ page import="com.example.dao.UserDao" %>
<%@ page import="com.example.entity.Users" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/21
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

      <%
          UserDao dao = new UserDao();
          List<Users> userList = dao.findAll();
      %>
      <table border="1" align="center">
          <tr>
              <td>用户编号</td>
              <td>用户姓名</td>
              <td>用户密码</td>
              <td>用户性别</td>
              <td align="center">用户邮箱</td>
              <td>操作</td>
          </tr>
          <%
              for(Users users:userList){
          %>
          <tr>
              <td><%=users.getUserId()%></td>
              <td><%=users.getUserName()%></td>
              <td>******</td>
              <td><%=users.getSex()%></td>
              <td><%=users.getEmail()%></td>
              <td><a href="/myWeb/user/delete?userId=<%=users.getUserId()%>">删除用户</a></td>
          </tr>
          <%
              }
          %>
      </table>

</body>
</html>
