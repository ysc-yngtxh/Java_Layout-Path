<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/10/7
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
      <h1>hello.jsp从request作用域获取数据</h1><br>
      <h3>msg数据：${msg}</h3><br>
      <h3>fun数据：${fun}</h3><br><br>

      <h1>hello.jsp页面的处理结果</h1>
      <h3>name数据取值方式\${myName}：${myName}</h3><br>
      <h3>age数据取值方式\${myAge}：${myAge}</h3><br>
      <%--想一想，为什么，重定向处理结果没显示？取不到值呢？

            回忆下重定向的工作原理放入：是浏览器发送请求到服务器，服务器在响应包里放入二次请求的命令信息给浏览器。
                                    然后浏览器接收后又发送给服务器，服务器再次进行处理。

            也就是说在重定向工作中有至少两次的请求包和响应包，意味着有至少两个request请求域。
            而在第二次的request请求域中，我们并没有裹挟任何数据，所以导致无法取值
      --%>

      <h1>hello.jsp页面的处理结果</h1>
      <h3>name数据取值方式\${param.myName}：${param.myName}</h3><br>
      <h3>age数据取值方式\${param.myName}：${param.myAge}</h3><br>
      <%--这种写法就可以取到值。
           虽然，我们在重定向的第二次请求域中并没有裹挟请求参数，但是，第一次的请求参数还是在请求头的。
           所以，在我们的EL表达式中，加入param.可以取得到所需要的参数值
      --%>
</body>
</html>
