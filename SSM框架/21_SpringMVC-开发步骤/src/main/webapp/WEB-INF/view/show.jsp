<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/9/22
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
      <h3>/WEB-INF/view/show.jsp从request作用域获取数据</h3><br>
      <h3>msg数据：${msg}</h3><br>
      <h3>fun数据：${fun}</h3>

      <%--之所以将jsp文件移到WEB-INF目录下，是因为避免在没有设置拦截器的情况下。恶意登录--%>
</body>
</html>
