<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/27
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用json格式的数据</title>

    <script type="text/javascript">
        function doSearch() {
            // 1、创建异步对象
            var xmlHttp = new XMLHttpRequest();
            // 2、绑定事件
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                    var data = xmlHttp.responseText;
                    // eval是执行括号中的代码，把json字符串转为json对象
                    var jsonObj = eval("(" + data + ")");  // 这条语句可以不做了解，后面学的jQuery会使用另种方法
                    // 更新dom对象
                    document.getElementById("proname").value = jsonObj.name;
                    document.getElementById("projiancheng").value = jsonObj.jiancheng;
                    document.getElementById("proshenghui").value = jsonObj.shenghui;
                }
            }
            // 3、初始异步对象
            var proid = document.getElementById("proid").value;
            // true:异步处理请求。使用异步对象发起请求后，不用等待数据处理完毕，就可以执行其他的操作(就相当于不必等上一个程序得到返回值后再执行一条语句，而是直接执行下一条语句)
            // false:同步处理请求。异步对象必须处理完成请求，从服务端获取数据后，才能执行下一步的程序。任意时刻只能执行一个异步请求。
            xmlHttp.open("get", "queryjson?proid=" + proid, true);
            // 4、发送请求
            xmlHttp.send();
        }
    </script>
</head>
<body>
    <p>Ajax请求使用json格式的数据</p>
    <table>
        <tr>
            <td>省份编号:</td>
            <td>
                <input type="text" id="proid"/>
                <input type="button" value="搜索" onclick="doSearch()"/>
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
