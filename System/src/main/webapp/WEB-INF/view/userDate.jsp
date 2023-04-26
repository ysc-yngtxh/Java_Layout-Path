<%--
  Created by IntelliJ IDEA.
  User: cym
  Date: 2022/9/16
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>userDate</title>
    <style>
        div{
            width:450px;
            height: 220px;
            margin:160px 380px ;
            border-color: white;
            border-width: 2px;
            border-style: solid;
            background-color: #d0e9ff;
        }
        h2{
            padding-left: 60px;
            margin-bottom: 0px;
        }
    </style>
    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){
            var id = ${sessionScope.id};
            $.ajax({
                type:"post",
                url:"selectUserBase",
                data:{"id":id},
                dataType:"json",
                success:function(data) {
                    console.log(data);
                    document.getElementById("username").innerText = data.username;
                    document.getElementById("identity").innerText = data.tenant;
                    document.getElementById("registryDate").innerText = ChangeDateFormat(data.dateTime);
                    document.getElementById("updateDate").innerText = ChangeDateFormat(data.updateTime);
                }
            })
        })

        function ChangeDateFormat(d) {
            //将时间戳转为int类型，构造Date类型
            var date = new Date(parseInt(d, 10));
            //月份得+1，且只有个位数时在前面+0
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            //日期为个位数时在前面+0
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            var hh = date.getUTCHours();
            var mm = date.getMinutes();
            var ss = date.getSeconds();
            //getFullYear得到4位数的年份 ，返回一串字符串
            return date.getFullYear() + "-" + month + "-" + currentDate+ " " + hh + ":" + mm + ":" + ss;
        }
    </script>
</head>
<body>
<div>
        <table align="center">
            <tr>
                <h2 >用户基本信息</h2>
            </tr>
            <tr>
                <td><span style="font-size: 18px;">用户名：</span></td>
                <td>
                    <span id="username" style="font-size: 18px;"></span>
                </td>
            </tr>
            <tr>
                <td><span style="font-size: 18px;">用户身份：</span></td>
                <td>
                    <span id="identity" style="font-size: 18px;"></span>
                </td>
            </tr>
            <tr>
                <td><span style="font-size: 18px;">注册日期：</span></td>
                <td>
                    <span id="registryDate" style="font-size: 18px;"></span>

                </td>
            </tr>
            <tr>
                <td><span style="font-size: 18px;">信息更新日期：</span></td>
                <td>
                    <span id="updateDate" style="font-size: 18px;"></span>
                </td>
            </tr>
            <br>

        </table>
</div>


</body>
</html>
