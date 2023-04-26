<%--
  Created by IntelliJ IDEA.
  User: cym
  Date: 2022/9/7
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>show</title>
    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <style>
        div{
            width:80%;
        }
        table{
            width:90%;
            height: 85%;
            border: 1px;
            border:solid#000;
        }
        td,tbody{
            text-align:center;
            border-left:1px solid #000;
            border-top:1px solid #000;
        }
        tr,th{
            height: 80px;
        }
        th{
            background-color: #b3dbff;
        }
    </style>
    <script type="text/javascript" >
        <%
           String login = (String) session.getAttribute("login");
           if(login.equals("no")){
        %>
        alert("账号或密码错误，请重新输入")
        window.location.href = "index.jsp"
        <%
            }
        %>

        // 通过Tid(教师的id)获取课程表信息
        // function getTimetable() {
        $(function(){
            var id = ${sessionScope.id};
            $.ajax({
                type:"get",
                url:"selectOne",
                data:{"id":id},
                dataType:"json",
                success:function(data) {
                    console.log(data)
                    //清空课程表
                    for (var i = 1; i < 8; i++) {
                        for (var j = 1; j < 8; j++) {
                            $("#table_" + j + "_" + i).html("");
                        }
                    }
                    //遍历课程表
                    for (var i = 0; i < data.length; i++) {
                        $("#table_" + data[i].week + "_" + data[i].start).html(data[i].courses.cname + "<br>" + data[i].courses.teacher + "<br>" + data[i].courses.tclass);
                        $("#table_" + data[i].week + "_" + data[i].finish).html(data[i].courses.cname + "<br>" + data[i].courses.teacher + "<br>" + data[i].courses.tclass);
                    }
                }
            })
        })
    </script>

</head>
<body>
<center>
    <div>
        <h2>课程表</h2>
        <table>
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">星期一</th>
                <th scope="col">星期二</th>
                <th scope="col">星期三</th>
                <th scope="col">星期四</th>
                <th scope="col">星期五</th>
                <th scope="col">星期六</th>
                <th scope="col">星期日</th>
            </tr>
            </thead>

            <tbody >
            <tr >
                <th scope="row">1-2节
                </th>
                <td id="table_1_1"></td>
                <td id="table_2_1"></td>
                <td id="table_3_1"></td>
                <td id="table_4_1"></td>
                <td id="table_5_1"></td>
                <td id="table_6_1"></td>
                <td id="table_7_1"></td>
            </tr>
            <tr>
                <th scope="row">3-4节
                </th>
                <td id="table_1_2"></td>
                <td id="table_2_2"></td>
                <td id="table_3_2"></td>
                <td id="table_4_2"></td>
                <td id="table_5_2"></td>
                <td id="table_6_2"></td>
                <td id="table_7_2"></td>
            <tr>
                <th scope="row">5-6节
                </th>
                <td id="table_1_3"></td>
                <td id="table_2_3"></td>
                <td id="table_3_3"></td>
                <td id="table_4_3"></td>
                <td id="table_5_3"></td>
                <td id="table_6_3"></td>
                <td id="table_7_3"></td>
            <tr>
                <th scope="row">7-8节
                </th>
                <td id="table_1_4"></td>
                <td id="table_2_4"></td>
                <td id="table_3_4"></td>
                <td id="table_4_4"></td>
                <td id="table_5_4"></td>
                <td id="table_6_4"></td>
                <td id="table_7_4"></td>
            <tr>
                <th scope="row">9-10节
                </th>
                <td id="table_1_5"></td>
                <td id="table_2_5"></td>
                <td id="table_3_5"></td>
                <td id="table_4_5"></td>
                <td id="table_5_5"></td>
                <td id="table_6_5"></td>
                <td id="table_7_5"></td>
            </tbody>
        </table>

    </div>
</center>

</body>
</html>
