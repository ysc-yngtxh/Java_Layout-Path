<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" +request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>功能入口</title>
    <baase href="<%=basePath%>"></baase> <%--动态获取协议地址--%>
</head>
<body>

      <p>一个拦截器</p>
      <form action="some.do" method="post">
          姓名：<input type="text" name="name"/><br>
          年龄：<input type="text" name="age"/><br>
          <input type="submit" value="提交请求"/>
      </form>
</body>
</html>
