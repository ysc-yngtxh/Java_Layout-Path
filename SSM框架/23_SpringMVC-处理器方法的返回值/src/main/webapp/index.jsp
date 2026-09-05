<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#btn1").click(function() {
                $.ajax({
                    url: "returnVoid-ajax.do",
                    data: {
                        name: "zhangsan",
                        age: 20
                    },
                    type: "post",
                    dataType: "json",
                    success: function(resp) {
                        alert(resp.name + "  " + resp.age);
                    }
                })
            })
            $("#btn2").click(function() {
                $.ajax({
                    url: "returnStudentJson.do",
                    data: {
                        name: "zhangsan",
                        age: 20
                    },
                    type: "post",
                    dataType: "json",
                    success: function(resp) {
                        alert(resp.name + "  " + resp.age);

                    }
                })
            })
            $("#btn3").click(function() {
                $.ajax({
                    url: "returnList.do",
                    data: {
                        name: "zhangsan",
                        age: 20
                    },
                    type: "post",
                    dataType: "json",
                    success: function(resp) {
                        $.each(resp, function(i,n){
                            alert(n.name + "  " + n.age);
                        })
                    }
                })
            })
            $("#btn4").click(function(){
                $.ajax({
                    url: "returnString1.do",
                    data: {},
                    type: "post",
                    dataType: "json",
                    success: function (resp) {
                        alert(resp);
                    }
                })
            })
            $("#btn5").click(function () {
                $.ajax({
                    url: "returnString2.do",
                    data: {},
                    type: "post",
                    dataType: "text",
                    success: function (resp) {
                        alert(resp);
                    }
                })
            })
            $("#btn6").click(function () {
                $.ajax({
                    url: "returnString3.do",
                    data: {},
                    type: "post",
                    success: function (resp) {
                        alert(resp);
                    }
                })
            })
        })
    </script>
</head>
<body>
       <p>处理器方法返回String表示视图名称</p>
       <form action="returnString-view.do" method="post">
           <label>
               姓名：<input type="text" name="name"/>
           </label><br>
           <label>
               年龄：<input type="text" name="age"/>
           </label><br>
           <input type="submit" value="提交参数"/>
       </form>

       <button id="btn1">发起正常的Ajax请求流程</button><br><br>
       <button id="btn2">使用框架后发起Ajax请求</button><br><br>
       <button id="btn3">发起Ajax请求，返回的是一个数组json</button><br><br>
       <button id="btn4">有@ResponseBody注解，返回String类型的数据，但无法显示点击事件句柄</button><br><br>
       <button id="btn5">有@ResponseBody注解，返回String类型的数据，但中文乱码</button><br><br>
       <button id="btn6">有@ResponseBody注解，返回String类型的数据，正常</button>
</body>
</html>
