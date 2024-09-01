<%--
  Created by IntelliJ IDEA.
  User: 游诗成
  Date: 2020/8/29
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style type="text/css">
        div{
            background: gray;
        }
    </style>

    <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        // $(document).ready(函数):当页面中的dom对象加载成功后，会执行ready()。相当于是onLoad()
        $(function(){
            // 当页面dom对象加载后，给对象绑定事件，因为此时button对象已经在内存中创建好了，才能使用
            $("#btn1").click(function(){
                // 过滤器
                var obj = $("div:first");
                obj.css("background","red")
            })

            $("#btn2").click(function(){
                var obj = $("div:last");
                obj.css("background","green")
            })

            $("#btn3").click(function(){
                var obj = $("div:eq(3)");
                obj.css("background","blue")
            })

            $("#btn4").click(function(){
                var obj = $("div:gt(3)");
                obj.css("background","orange")
            })

            $("#btn5").click(function(){
                var obj = $("div:lt(3)");
                obj.css("background","yellow")
            })

            // 表单过滤器
            $("#btn6").click(function(){
                var obj = $(":text:enabled")
                obj.val("hello")
            })

            $("#btn7").click(function(){
                var obj = $(":checkbox:checked");
                for(var i=0; i<obj.length; i++){
                    // alert( obj[i].value );这里的obj为什么不是jQuery对象呢？本来的(obj)是jQuery对象，但是jQuery数组(obj[i])就转变为dom对象
                    alert( $(obj[i]).val() );
                }
            })

            $("#btn8").click(function(){
                var obj = $("select>option:selected");
                alert(obj.val());
            })
        })
    </script>
</head>
<body>
      <div id="one">我是div-0</div>
      <div id="two">我是div-1</div>
      <div>我是div-2
          <div>我是div-3</div>
          <div>我是div-4</div>
      </div>
      <div>我是div-5</div><br>
      <span>我是span标签</span><br>

      <input type="button" value="获取第一个div" id="btn1"/><br>
      <input type="button" value="获取最后一个div" id="btn2"/><br>
      <input type="button" value="获取下标为3的div" id="btn3"/><br>
      <input type="button" value="获取下标大于3的div" id="btn4"/><br>
      <input type="button" value="获取下标小于3的div" id="btn5"/><br><br><br><br>



      <span>下面是表单过滤器</span><br>
      <input type="text" value="text1"/><br>
      <input type="text" value="text2" disabled/><br> <!--disabled表示不可用状态-->

      <input type="checkbox" value="游泳"/>游泳<br>
      <input type="checkbox" value="健身"/>健身<br>
      <input type="checkbox" value="电子游戏"/>电子游戏<br>

      <select id="yuyan">
          <option value="java">java语言</option>
          <option value="go">go语言</option>
          <option value="python">python语言</option>
      </select><br><br>

      <input type="button" value="设置可用的text的value是hello" id="btn6"/><br>
      <input type="button" id="btn7" value="显示选中的复选框的值"/><br>
      <input type="button" id="btn8" value="显示下拉列表框的值"/>
</body>
</html>
