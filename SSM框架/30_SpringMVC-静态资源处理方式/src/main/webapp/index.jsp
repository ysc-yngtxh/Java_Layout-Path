<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
                      request.getServerName() + ":" +request.getServerPort() +
                      request.getContextPath() + "/";
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="static/js/jquery-3.4.1.js"></script>

    <%--<base href="http://localhost:8080/myWeb"/>--%>
    <base href="<%=basePath%>"/> <%--动态获取协议地址--%>
</head>
<body align="center" background-size="100%">
      <p>处理器方法返回String表示视图名称</p>
      <form action="user/some.do" method="post">
          姓名：<input type="text" name="name"/><br>
          年龄：<input type="text" name="age"/><br><br>
          <input type="submit" value="提交参数"/>
      </form>

      <img src="static/img/timg.jpg" alt="我是一个静态资源，不能显示" disabled="flex"/>
</body>
</html>
