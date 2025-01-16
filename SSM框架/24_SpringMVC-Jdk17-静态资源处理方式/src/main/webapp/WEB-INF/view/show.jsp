
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
      <%--之所以将jsp文件移到WEB-INF目录下，是因为避免在没有设置拦截器的情况下。恶意登录--%>

      <h3>/WEB-INF/view/show.jsp从request作用域获取数据</h3><br>

      <h3>name数据：${myname}</h3><br>
      <h3>age数据：${myage}</h3><br>
</body>
</html>
