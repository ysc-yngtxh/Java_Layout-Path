<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/9/27
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" +request.getServerPort()
            + request.getContextPath() + "/";
%>
<html>
<head>
    <title>注册学生</title>
    <base href="<%=basePath%>"/> <%--动态获取协议地址--%>
</head>
<body>
      <div align="center">
          <form action="student/addStudent.do" method="post">
              <table>
                  <tr>
                      <td>姓名：</td>
                      <td><input type="text" name="name"/></td>
                  </tr>
                  <tr>
                      <td>年龄：</td>
                      <td><input type="text" name="age"/></td>
                  </tr>
                  <tr align="center">
                      <td colspan="2">
                          <input type="submit" value="注册"/>
                      </td>
                  </tr>
              </table>
          </form>
      </div>
</body>
</html>
