<%--
  Created by IntelliJ IDEA.
  User: 游家纨绔
  Date: 2020/8/20
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
      <div style="text-align: center;">
          <%
              String result = (String)request.getAttribute("info");
          %>
          <span style="color:red;font-size:45px">
              <%=result%>
          </span>
      </div>
</body>
</html>
