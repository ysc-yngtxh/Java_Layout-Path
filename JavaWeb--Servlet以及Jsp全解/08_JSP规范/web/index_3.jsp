<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/13
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
    JSP文件内置对象：request
              类型：HttpServletRequest
              作用：在JSP文件运行时读取请求包信息
                    与Servlet在请求转发过程中实现数据共享

    浏览器： http://localhost:8080/myWeb/request.jsp?userName=allen&password=123
-->
<%
    //在JSP文件执行时，借助于内置request对象读取请求包参数信息
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
%>
来访用户姓名：<%=userName%><br>
来访用户密码：<%=password%>


<!--
     JSP文件内置对象：session
               类型：HttpSession
               作用：在JSP文件运行时,可以通过Session指向当前用户私人储物柜，添加共享数据，或者读取共享数据
-->
<%
    //将共享数据添加到当前用户私人储物柜
    //HttpSession session = request.HttpSession();
    session.setAttribute("key1",200);
%>
<%
    //如果我这行代码写在一个新建的JSP里，即为同一个用户/浏览器提供服务。因此可以使用当前用户在服务端的私人储物柜进行数据共享
    Integer value = (Integer)session.getAttribute("key1");
%>



<!--
    JSP文件内置对象： ServletContext application;全局作用域对象
    同一个网站中Servlet与JSP，都可以通过当前网站的全局作用域对象实现数据共享
    JSP文件内置对象：application
-->
<%
    application.setAttribute("key2","hello world");
%>