
/*
一、JSP规范介绍
    1)来自于JavaEE规范中一种
    2)JSP规范制定了如何开发JSP文件代替响应对象将处理结果写入到响应体的开发流程
    3)JSP规范制定了Http服务器应该如何调用管理JSP文件

二、响应对象存在弊端
    1)适合将数据量较少的处理结果写入到响应体
    2)如果处理结果数量过多，使用响应对象增加开发难度

三、JSP文件优势
    1)JSP文件在互联网通信过程，是响应对象替代品
    2)降低将处理结果写入到响应体的开发工作量降低处理结果维护难度
    3)在JSP文件开发时，可以直接将处理结果写入到JSP文件不需要手写out.print();命令,
      在Http服务器调用JSP文件时，根据JSP规范要求自动地将JSP文件书写的所有内容通过输出流写入到响应体。

四、Servlet与JSP分工
      1)Servlet:  负责处理业务并得到处理结果----------大厨

        JSP:      不负责业务处理，主要人物将Servlet中【处理结果】写入到响应体-----传菜员

      2)Servlet与JSP之间调用关系
              Servlet工作完毕后，一般通过请求转发方式向Tomcat申请调用JSP
      3)Servlet与JSP之间如何实现数据共享
              Servlet将处理结果添加到【请求作用域对象】
              JSP文件在运行时从【请求作用域】得到处理结果

五、JSP运行原理【面试题】
      1)Http服务器调用JSP文件步骤
          1>Http服务器将JSP文件内容【编辑】为一个Servlet接口实现类(.java)
          2>Http服务器将Servlet接口实现类【编辑】为一个class文件(.class)
          3>Http服务器负责创建这个class的实例对象，这个实例对象就是Servlet实例对象
          4>Http服务器通过Servlet实例对象调用_jspServlet方法，将JSP文件内容写入到响应体

*/


public class JSP规范 {
}
