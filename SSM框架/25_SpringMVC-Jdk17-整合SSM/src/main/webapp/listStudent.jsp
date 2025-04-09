<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/9/27
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" +request.getServerPort()
            + request.getContextPath() + "/";
%>
<html>
<head>
    <title>查询学生Ajax</title>
    <base href="<%=basePath%>"/> <%--动态获取协议地址--%>
    <script type="text/javascript" src="script/jquery-3.4.1.js"></script>
    <script>
        $(function(){
            // 在当前页面dom对象加载后，执行loadStudentData();
            loadStudentData();

            $("#btnLoader").click(function(){
                loadStudentData();
            })
        })

        function loadStudentData(){
            $.ajax({
                url: "student/queryStudent.do",
                type: "get",
                dataType: "json",
                success: function(data) {
                    // 清除旧的数据
                    $("#info").html("");
                    // 增加新的数据
                    $.each(data, function(i,n) {
                        $("#info").append("<tr>")
                                  .append("<td>" + n.id + "</td>")
                                  .append("<td>" + n.name + "</td>")
                                  .append("<td>" + n.age + "</td>")
                                  .append("</tr>")
                    })
                }
            })
        }
    </script>
</head>
<body>
      <div align='center'>
          <table>
              <thead align='center'>
              <tr>
                  <td width="50px">学号</td>
                  <td width="150px">姓名</td>
                  <td>年龄</td>
              </tr>
              </thead>
              <tbody id="info" align='center'>

              </tbody>
          </table>
          <input type="button" id="btnLoader" value="查询数据"/>
      </div>
</body>
</html>
