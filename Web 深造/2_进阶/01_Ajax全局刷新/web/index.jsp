<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/27
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>全局刷新</title>
</head>
<body>
      <p>全局刷新计算bmi</p>
      <form action="/myWeb/bmi" method="get">
        <input type="text" name="name" placeholder="姓名"/><br>
        <input type="text" name="w" placeholder="体重(公斤)"/><br>
        <input type="text" name="h" placeholder="身高(米)"/><br>
        <input type="submit" value="提交"/>
      </form>
</body>
</html>


