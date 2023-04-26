<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/27
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>ajax根据省分id获取名称</title>

  <script type="text/javascript">
    function search() {
      //发起Ajax请求，传递参数给服务器，服务器返回数据
      //1、创建异步对象
      var xmlHttp = new XMLHttpRequest();
      //2、绑定事件
      xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState==4 && xmlHttp.status==200){

          //这一步是拿到数据
          alert(xmlHttp.responseText);
          //更新页面，就是更新dom对象
          document.getElementById("proname").value = xmlHttp.responseText;
        }
      }
      //3、初始异步对象
      var proid = document.getElementById("proid").value;
      xmlHttp.open("get","querypravice?proid="+proid,true);
      //4、发送请求
      xmlHttp.send();
    }
  </script>
</head>
<body>
      <p>ajax根据省分id获取名称</p>
      <table>
        <tr>
          <td>省份编号:</td>
          <td>
            <input type="text" id="proid"/>
            <input type="button" value="搜索" onclick="search()"/>
          </td>
        </tr>
        <tr>
          <td>省份名称:</td>
          <td><input type="text" id="proname"/></td>
        </tr>
      </table>
</body>
</html>

