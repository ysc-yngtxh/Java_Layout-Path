<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

      <h1>第一个SpringMvc项目</h1></br>

      <p><a href="test/some.do">发起some.do的get请求</a></p>
      <form action="test/other.do" method="post">
          <input type="submit" value="post请求other.do"/>
      </form></br>

      <p>逐个接收参数：请求方式为Get</p>
      <form action="doSome.do" method="get">
          姓名：<input type="text" name="name"/><br>
          年龄：<input type="text" name="age"/><br>
          <input type="submit" value="提交参数"/>
      </form></br>

      <p>逐个接收参数：请求方式为Post</p>
      <form action="doOther.do" method="post">
          姓名：<input type="text" name="name"/><br>
          年龄：<input type="text" name="age"/><br>
          <input type="submit" value="提交参数"/>
      </form></br>

      <p>逐个接收参数：RequestParam注解</p>
      <form action="First.do" method="get">
          姓名：<input type="text" name="rname"/><br>
          年龄：<input type="text" name="rage"/><br>
          <input type="submit" value="提交参数"/>
      </form></br>

      <p>对象接收参数</p>
      <form action="Out.do" method="get">
          姓名：<input type="text" name="name"/><br>
          年龄：<input type="text" name="age"/><br>
          <input type="submit" value="提交参数"/>
      </form>
</body>
</html>
