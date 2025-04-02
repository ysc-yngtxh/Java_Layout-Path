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
  <title>局部刷新</title>

  <script type="text/javascript">
    // 使用内存中的异步对象，代替浏览器发起请求。异步对象使用js创建和管理的
    function doAjax() {
      // 1、创建异步对象
      let xmlHttp = new XMLHttpRequest();
      // 2、绑定事件
      xmlHttp.onreadystatechange = function() {
        // 处理服务器端返回的数据，更新当前页面
        alert("readyState属性值:" + xmlHttp.readyState + "| status:" + xmlHttp.status);

        if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
          alert(xmlHttp.responseText);  // 输出的msg数据在这里得到输出
          let data = xmlHttp.responseText;
          // 更新dom对象，更新页面数据
          document.getElementById("mydata").innerText = data;
        }
      }
      // 3、初始请求数据
      // 获取dom对象的value属性值
      let name = document.getElementById("name").value;
      let h = document.getElementById("h").value;
      let w = document.getElementById("w").value;

      // 拼接路径：bmiPrint?name=李四&w=82&h=1.8
      let param = "name=" + name + "&w=" + w + "&h=" + h;
      alert("param" + param);
      xmlHttp.open("get", "bmiAjax?"+param, true);
      // 4、发起请求
      xmlHttp.send();
    }
  </script>
</head>
<body>
      <p>局部刷新ajax-计算bmi</p>
      <div>
        <input type="text" id="name" placeholder="姓名"/><br>
        <input type="text" id="w" placeholder="体重(公斤)"/><br>
        <input type="text" id="h" placeholder="身高(米)"/><br>
        <input type="button" value="计算bmi" onclick="doAjax()"/><br>
      </div>
      <br>
      <br>
      <div id="mydata">等待加载数据......</div>
</body>
</html>
