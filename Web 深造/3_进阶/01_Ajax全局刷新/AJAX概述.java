
/*
  1、全局刷新和局部刷新
      全局刷新：整个浏览器被新的数据覆盖。在网络中传输大量的数据。浏览器需要加载，渲染页面。
      局部刷新：在浏览器的内部，发起请求，获取数据，改变页面中的部分内容。
               其余的页面无需加载和渲染。网络中数据传输量少，给用户的体验好

      Ajax是用来做局部刷新的。局部刷新使用的核心对象是 异步对象(XMLHttpRequest)
      这个异步对象是存在浏览器内存中的，使用JavaScript语法创建和使用XMLHttpRequest对象

  2、Ajax：Asynchronous JavaScript and XML(异步的JavaScript和XML)
          Asynchronous：异步的意思
          JavaScript：JavaScript脚本，在浏览器中执行
          and：和
          XML：是一种数据格式

          Ajax是一种做局部刷新的新方法(2003年左右)，不是一种语言。Ajax包含的技术只要有javascript,dom,css,xml等等。核心是JavaScript和xml
            javaScript:负责创建异步对象，发送请求，更新页面的dom对象。Ajax请求需要服务器端的数据
            xml:网络中传输的数据格式

  3、Ajax中使用XMLHttpRequest对象1
      1)创建异步对象 var xmlHttp = new XMLHttpRequest();
      2)给异步对象绑定事件。onreadystatechange:当异步对象发起请求，获取了数据都会触发这个事件。
        这个事件需要指定一个函数，在函数中处理状态的变化。
            btn.onclick = fun1()
            function fun1(){
               alert("按钮单击);
            }

            例如：
            xmlHttp.onreadystatechange = function(){
                 处理请求的状态变化
                 if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
                     // 可以处理服务器端的数据，更新当前页面
                     var data = xmlHttp.responseText;
                     document.getElementById("name").value=data;
                 }
            }

        异步对象的属性 readState 表示异步对象请求的状态变化
         0:创建异步对象时， new XMLHttpRequest();
         1:初始异步请求对象， xmlHttp.open();
         2:发送请求， xmlHttp.send();
         3:从服务器端获取了数据，此时3，注意3是异步对象内部使用，获取了原始的数据（只有获取到了数据才会有3）
         4:异步对象把接受的数据处理完成后。此时开发人员在4的时候处理数据。
           在4的时候，开发人员做了什么？更新当前页面

         异步对象的status属性，表示网络请求的状况的，200，404，500，需要是当status==200时，表示网络请求是成功的。
      3)初始异步请求对象
         异步的方法open()
         xmlHttp.open(请求方式get\post,"服务器端的访问地址",同步、异步请求(默认是true，异步请求))
         例如：
         xmlHttp.open("get","loginServlet?name=zs&pwd=123",true);
      4)使用异步对象发送请求
         xmlHttp.send();

         获取服务器端返回的数据，使用异步对象的属性 responseText
         使用例子：xmlHttp.responseText

         回调：当请求的状态变化时，异步对象会自动调用 onreadyStatechange事件对应的函数

  4、json介绍
       1)、ajax发起请求----servlet(返回的一个json格式的字符串{name:"河北", jiancheng:"冀", shenghui:"石家庄"})
           json分类：1>json对象，JSONObject,这种对象的格式  名称:值，也可以看作是 key:value 格式。
                    2>json数组，JSONArray，基本格式[{name:"河北",jiancheng:"冀",shenghui:"石家庄"},{name:"河北",jiancheng:"冀",shenghui:"石家庄"}]
       2)、为什么要使用json:
           1、json格式好理解
           2、json格式数据在多种语言中，比较容易处理。使用Java，JavaScript读写json格式的数据比较容易
           3、json格式数据它占用的空间下，在网络中传输快，用户的体验好
       3)、处理json的工具库：
           gson(google)
           fastjson(阿里):速度快，不是最符合json处理规范的
           jackson:性能好，规范好
           json-lib:性能差，依赖多
        4)、异步和同步
            异步：open(get,url,true),在send之后执行其他的代码，可以同时执行多个异步请求
            同步：open(get,url,false),一次只能执行一个异步请求，必须请求处理完成后，才能执行其他的请求处理
 */
public class AJAX概述 {
}
