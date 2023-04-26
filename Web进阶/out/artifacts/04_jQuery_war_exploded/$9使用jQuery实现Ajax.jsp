<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/27
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用json格式的数据</title>

    <script type="text/javascript" src="scripts\jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btn").click(function(){
                //获取dom的value值
                var proid = $("#proid").val();
                //发送Ajax请求
                $.ajax({
                    url:"queryjson",
                    data:{
                        "proid":proid
                    },
                    dataType:"json",
                    success:function(resp){
                        alert(resp);
                        $("#proname").val(resp.name);
                        $("#projiancheng").val(resp.jiancheng);
                        $("#proshenghui").val(resp.shenghui);
                    }
                })
            })
        })
    </script>
</head>
<body>
      <p align="center">Ajax请求使用json格式的数据</p>
      <table align="center">
          <tr>
              <td>省份编号:</td>
              <td>
                  <input type="text" id="proid"/>
                  <input type="button" value="搜索" id="btn"/>
              </td>
          </tr>
          <tr>
              <td>省份名称:</td>
              <td><input type="text" id="proname"/></td>
          </tr>
          <tr>
              <td>省份简称:</td>
              <td>
                  <input type="text" id="projiancheng"/>
              </td>
          </tr>
          <tr>
              <td>省份名称:</td>
              <td><input type="text" id="proshenghui"/></td>
          </tr>
      </table>
</body>
</html>
