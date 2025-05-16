<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/19
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!--http://localhost:8080/myWeb/index_05.jsp?userName=mike&password=123-->
来访者姓名：${param.userName}<br>
来访者密码：${param.password}


<!--http://localhost:8080/myWeb/index_05.jsp?deptNo=10&deptNo=20&deptNo=30-->
第一个部门编号：${paramValues.deptNo[0]}<br>
第二个部门编号：${paramValues.deptNo[1]}<br>
第三个部门编号：${paramValues.deptNo[2]}<br>
