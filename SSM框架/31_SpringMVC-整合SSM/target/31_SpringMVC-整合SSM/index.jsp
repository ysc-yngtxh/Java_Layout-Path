<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" +request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>功能入口</title>
    <base href="<%=basePath%>"/> <%--动态获取协议地址--%>
</head>
<body align="center">
      <div>
          <p>SSM整合的例子<%=basePath%></p>
          <img src="img/timg.jpg" alt="为什么不能显示图片？"/>
          <table border="1" align="center">
              <tr>
                  <td><a href="addStudent.jsp">注册学生</a></td>
              </tr>
              <tr>
                  <td><a href="listStudent.jsp">浏览学生</a></td>
              </tr>
          </table>
      </div>

      <p>Post请求方法返回ModelAndView实现forward</p>
      <form action="student/doForward.do" method="post">
          姓名：<input type="text" name="name"/><br>
          年龄：<input type="text" name="age"/><br>
          <input type="submit" value="提交请求"/>
      </form>

      <p>Post请求方法返回ModelAndView实现redirect</p>
      <form action="student/doRedirect.do" method="post">
          姓名：<input type="text" name="name"/><br>
          年龄：<input type="text" name="age"/><br>
          <input type="submit" value="提交请求"/>
      </form>
</body>
</html>
