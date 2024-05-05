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
<body>

      <p>处理异常的，全局异常处理</p>
      <form action="some.do" method="post">
          姓名：<input type="text" name="name"/><br>
          年龄：<input type="text" name="age"/><br>
          <input type="submit" value="提交请求"/>
      </form>
</body>
</html>
