<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/19
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

标准版EL表达式输出session中key的值：${sessionScope.key}<br>
简化版EL表达式输出session中key的值：${key}

<!--我们运行后发现，简化版EL表达式输出session中key的值 输出的是request中的值。说明定位失败，存在隐患-->
