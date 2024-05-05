
一、1、jQuery是js库，
      库：相当于Java的工具类，库是存放东西的。jQuery是存放js代码的地方，放的用js代码写的function
   2、使用jQuery，首先要将jQuery库引入
      <script type="text/javascript" src="scripts/jquery-3.4.1.js"></script>

二、1、$(document),$是jQuery中的函数名称，document是函数的参数
      作用是 document对象 变成 jQuery函数库可以使用的对象。
   2、ready:是jQuery中的函数，是准备的意思,当页面的dom对象加载成功后会执行ready函数的内容。
      ready相当于js中的onLoad事件。
   3、function()自定义的表示onLoad后要执行的功能。

三、$(document)ready()与$()、jQuery()、window.jQuery()是等价的
    所以$(document)ready(function(){alert("Hello jQuery");})可以写成$(function(){alert("Hello jQuery");})

四、dom对象和jQuery对象
    dom对象：使用Java script的语法创建的对象叫做dom对象，也就是js对象
            var obj = document.getElementById("txt1");  obj是dom对象，也叫做js对象
    jQuery对象：使用jQuery语法表示对象叫做jQuery对象，注意：jQuery表示的对象都是数组
               var jobj = $("#txt1");  jobj就是使用jQuery语法表示的对象，它是个数组，现在就只有一个值。

五、dom对象可以和jquery对象相互的转换
    1、dom对象可以转为jQuery，语法：$(dom对象)
    2、jQuery对象也可以转为dom对象，语法：从数组中获取第一个对象，第一个对象就是dom对象，使用[0]或者get{0}
    3、为什么要进行dom和jquery的转换：目的是要使用方法，或者对象的方法
       当你的dom对象时，可以使用dom对象的属性或者方法，如果你要想使用jQuery提供的函数，必须是jQuery对象才可以

六、选择器：就是一个字符串，用来定位dom对象的。定位了dom对象，就可以通过jQuery的函数操作dom
    常用的选择器：
    1)id选择器，语法：$("#dom对象的id值")
       通过dom对象的id定位dom对象的。通过id找对象，id在当前页面中是唯一值。
    2)class选择器，语法：$(".class样式名")
       class表示css中的样式，使用样式的名称定位dom对象的。
    3)标签选择器，语法：$("标签名称")
       使用标签名称定位dom对象的
    4)表单选择器
       使用<input>标签的type属性值，定位dom对象的方式。(其实就是只能用来定位有input标签的)
       语法:$(":type属性值")
       例如:$(":text"),选择的是所有的单行文本框，
           $(":button"),选择的是所有的按钮
       注意:1>、表单选择器不是一定要form表单存在
            2>、$(":tr")不能用，tr不是input标签

七、过滤器：在定位了dom对象后，根据一些条件筛选dom对象
       过滤器又是一个字符串，用来筛选dom对象的
       过滤器不能单独使用，必须和选择器一起使用
     1) $("选择器:first")：第一个dom对象
     2) $("选择器:last")：数组中的最后一个dom对象
     3) $("选择器:eq(数组的下标)")：获取指定下标的dom对象
     4) $("选择器:lt(下标)")：获取小于下标的所有dom对象
     5) $("选择器:gt(下标)")：获取大于下标的所有dom对象

     表单属性过滤器：根据表单中dom对象的状态情况，定位dom对象的
        启用状态，enabled
        不可用状态，disabled
        选择状态，checked，例如radio，checkbox

八、函数(最常用的函数：val(),append(),each())
    第一组：
         1)val:
               $(选择器).val()无参调用形式，读取数组中第一个dom对象的value属性值
               $(选择器).val(值)有参形式调用，对数组中所有dom对象的value属性值进行统一赋值
         2)text:
               $(选择器).text()无参数调用，读取数组中所有dom对象的文字显示内容，将得到内容拼接为一个字符串返回
               $(选择器).text(值)有参数方式，对数组中所有dom对象的文字显示内容进行统一赋值
         3)attr:
               $(选择器).attr("属性名")获取dom数组第一个对象的属性值
               $(选择器).attr("属性名","值")对数组中所有dom对象的属性设为新值
    第二组：
         1)remove:
               $(选择器).remove()将数组中所有的dom对象及其子对象一并删除
         2)empty:
               $(选择器).empty()将数组中所有dom对象的子对象删除
         3)append:
               $(选择器).append()为数组中所有dom对象添加子对象
         4)html:
               $(选择器).html()无参数调用方法，获取dom数组第一个元素的内容
               $(选择器).html(值)有参数调用，用于设置dom数组中所有元素的内容
         5)each:
               可以对数组,json,dom数组循环处理。数组,json中的每个成员都会调用一次处理函数
                 var arr = {1,2,3}
                 var json = {"name":"list","age":20}
                 var obj = ${":text"}
                 语法：$.each(循环的内容，处理函数)：表示使用jQuery的each，循环数组，每个数组成员，都会执行后面的”处理函数“一次

九、jQuery中给dom对象绑定事件
     1) $(选择器).事件名称(事件的处理函数)
           事件名称：就是js中时间去掉on的部分，例如js中的单击事件onclick(),
                   jQuery中的事件名称，就是click，都是小写
           事件的处理函数：就是一个function，当事件发生时，执行这个函数的内容
           例如给id是btn的按钮绑定单击事件
           $("#btn").click(function(){alert("btn的按钮单击了")})
     2) on 事件绑定
           $(选择器).on(事件名称，事件的处理函数)
           事件名称：就是js事件中去掉on的部分，例如js中onclick,这里就是click
           事件的处理函数：function 定义
           例如：<input type="button" id="btn">
            $("#btn").on("click",function(){处理按钮单击})

十、使用jQuery的函数，实现Ajax请求的处理
    没有jQuery之前，使用XMLHttpRequest做ajax,有4个步骤。jQuery简化了Ajax请求的处理，使用三个函数可以实现Ajax请求的处理。
     1) $.ajax():jquery中实现Ajax的核心函数
     2) $.post():使用post方式做Ajax请求
     3) $.get():使用get方式发送Ajax的请求

       $.post()和$.get()他们在内部都是调用的$.ajax()
       介绍$.ajax()函数的使用，函数的参数表示请求的url,请求的方式，参数值等信息。$.ajax()参数是一个json的结构
       例如：$.ajax( {名称：值，名称1：值1......} )
             例如：$.ajax( {async:true , contentType:"application/json" ,
                           data:{name:"lisi",age:23} , dataType:"json" ,
                           error:function(){
                              请求出现错误时，执行的函数
                           },
                           success:function(){
                              //data 就是responseText,是jQuery处理后的数据
                           },
                           url:"bmiAjax",
                           type:"get"
                           })

       json结构的参数说明：
         1)async:是一个boolean类型的值，默认是true，表示异步请求的。可以不写async这个配置项
                 xmlHttp.open(get,url,true)，第三个参数一样的意思
         2)contentType:一个字符串，表示从浏览器发送服务器的参数的类型，可以不写。
                       例如你想表示请求的参数是json格式的，可以写application/json
         3)data:可以是字符串，数组，json，表示请求的参数和参数值。常用的是json格式的数据
         4)dataType:表示期望从服务器端返回的数据格式，可选的有：xml,html,text,json
                    当我们使用$.ajax()发送请求时，会把dataType的值发送给服务器，那我们的servlet能够读取到dataType的值
                    就知道你的浏览器需要的是json或者xml的数据，那么服务器就可以返回你需要的数据格式。
         5)error:一个function ，表示当请求发生错误时，执行的函数。
                 error:function(){发生错误时执行}
         6)success:一个function，请求成功了，从服务器端返回了数据，会执行success指定函数之前使用XMLHttpRequest对象，
                   当readyState==4 && state==200的时候
         7)url:请求的地址
         8)type:请求方式，get或者post，不用区分大小写。默认是get方式
         
         主要使用的是 url,data,dataType,success