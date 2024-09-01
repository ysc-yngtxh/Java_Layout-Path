<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/30
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style type="text/css">
        div{
            background: orange;
        }
        #image{
            height:200px;
        }
    </style>

    <script type="text/javascript" src="scripts\jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btn1").click(function(){
                // 无参调用形式，读取数组中第一个dom对象的value属性值
                var text = $(":text").val();
                alert(text)
            })
            $("#btn2").click(function(){
                // 有参形式调用，对数组中所有dom对象的value属性值进行统一赋值
                var text = $(":text").val("三国演义");
            })
            $("#btn3").click(function(){
                // 获取div，text()无参数，获取dom对象的文本值，连接成一个字符串
                alert($("div").text());
            })
            $("#btn4").click(function(){
                // 设置div的文本值
                $("div").text("新的div内容");
            })
            $("#btn5").click(function(){
                // 读取指定属性的值
                alert($("img").attr("src"));
            })
            $("#btn6").click(function(){
                // 设置指定属性的值
                $("img").attr("src", "img/timg2.jpg");
            })
            $("#btn7").click(function(){
                // 删除父和子对象
                $("select").remove();
            })
            $("#btn8").click(function(){
                // 删除子对象
                $("select").empty();
            })
            $("#btn9").click(function(){
                // 使用append，增加dom对象的提交按钮
                // $("#fatcher").append("<input type='button' value='我是增加的按钮'/>");
                // 使用append，增加dom对象的动态表格
                $("#fatcher").append("<table border='1' align='center'><tr><td>第一列</td><td>第二列</td></tr></tabble>");
            })
            $("#btn10").click(function(){
                // 使用html()函数，获取数组中第一个dom对象的文本值
                alert($("span").html()); // 我是MySQL<b>数据库</b>
            })
            $("#btn11").click(function(){
                // 使用html(有参数)：设置dom对象的文本值
                $("span").html("我是新的<b>数据</b>");
            })
            $("#btn12").click(function(){
                // 循环普通数组，非dom数组
                var arr = [11,12,13];
                $.each(arr, function(i,n){
                    alert("循环变量" + i + "===数组成员" + n)
                })
            })
            $("#btn13").click(function(){
                // 循环json，非dom数组
                var json = {"name":"游诗成", "age":23};
                $.each(json, function(i,n){
                    alert("i是key=" + i + ",n是值=" + n)
                })
            })
            $("#btn14").click(function(){
                // 循环dom数组
                var domArray = $(":text"); // dom数组
                $.each(domArray, function(i,n){  // n是数组中的dom对象
                    alert("i=" + i + ",n=" + n.value)
                })
            })
            $("#btn15").click(function(){
                // 循环jQuery对象，jQuery对象就是dom数组
                $(":text").each(function(i,n){
                    alert("i=" + i + ",n=" + n.value)
                })
            })
        })
    </script>
</head>
<body>
      <input type="text" value="刘备"/><br>
      <input type="text" value="张飞"/><br>
      <input type="text" value="关羽"/><br>

      <div id="fatcher">我是第一个div</div>
      <div>我是第二个div</div>
      <div>我是第三个div</div><br>

      <img src="img/timg1.jpg" id="image"/><br>

      <input type="button" value="获取第一文本框的值" id="btn1"/>
      <input type="button" value="设置所有文本框的值" id="btn2"/><br>
      <input type="button" value="获取所有div的文本值" id="btn3"/>
      <input type="button" value="设置div的文本值" id="btn4"/><br>
      <input type="button" value="读取src属性的值" id="btn5"/>
      <input type="button" value="设置指定的属性值" id="btn6"/><br><br><br>

      <select>
          <option value="老虎">老虎</option>
          <option value="狮子">狮子</option>
          <option value="河马">河马</option>
      </select>
      <select>
          <option value="亚洲">亚洲</option>
          <option value="欧洲">欧洲</option>
          <option value="拉丁美洲">拉丁美洲</option>
      </select><br><br>

      <span>我是MySQL<b>数据库</b></span><br><br>

      <input type="button" value="使用remove删除父和子对象" id="btn7"/>
      <input type="button" value="使用empty删除子对象" id="btn8"/>
      <input type="button" value="使用append增加dom对象" id="btn9"/>
      <input type="button" value="获取第一个dom的文本值" id="btn10"/>
      <input type="button" value="设置span的所有dom的文本值" id="btn11"/><br><br>
      <input type="button" value="循环普通数组" id="btn12"/>
      <input type="button" value="循环json" id="btn13"/>
      <input type="button" value="循环dom数组" id="btn14"/>
      <input type="button" value="循环jquery对象" id="btn15"/>
</body>
</html>
