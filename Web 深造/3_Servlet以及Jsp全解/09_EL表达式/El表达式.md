一、EL表达式
   1、命令格式：${作用域对象别名.共享数据}
   2、命令作用：
              1)、EL表达式是EL工具包提供一种特殊命令格式【表达式命令格式】
              2)、EL表达式在JSP文件上使用
              3)、负责在JSP文件上从作用域对象读取指定的共享数据并输出到响应体

二、EL表达式--作用于对象别名【运行OneServlet】
    1、JSP文件可以使用的作用域对象
       1)、ServletContext          application:全局作用域对象
       2)、HttpSession             session:会话作用域对象
       3)、HttpServletRequest      request:请求作用域对象
       4)、PageContext             pageContext:当前页作用域对象，这是JSP文件独有的作用域对象。Servlet中不存在。
                                               当前页作用域对象存放的共享数据仅能在当前JSP文件中使用，不能共享给其他Servlet或者其他JSP文件
                                               真实开发过程，主要用于JSTL标签与JSP文件之间数据共享
                                               JSTL ---> pageContext ---> JSP
    2、EL表达式提供作用域对象别名
       JSP                      EL表达式
       application              ${applicationScope,共享数据名}
       session                  ${sessionScope,共享数据名}
       request                  ${requestScope,共享数据名}
       pageContext              ${pageScope,共享数据名}

三、EL表达式将引用对象属性写入到响应体【运行TwoServlet】
   1、命令格式：${作用域对象别名.共享数据名.属性名}
   2、命令作用：从作用域对象读取指定共享数据关联的引用对象的属性值。并自动将属性的结果写入到响应体
   3、属性名：一定要去引用类型属性名完全一致(大小写)
   4、EL表达式没有提供遍历集合方法，因此无法从作用于对象读取集合内容输出

四、EL表达式简化版【运行ThreeServlet】
   1、命令格式：${共享数据名}
   2、命令作用：EL表达式允许开发人员是省略作用域对象别名
   3、工作原理：EL表达式简化版由于没有指定作用域对象，所以在执行时采用【猜】算法
              首先到【pageContext】定位共享数据，如果存在直接读取输出并结束执行
              如果在【pageContext】没有定位成功，到【request】定位共享数据，如果存在直接读取输出并结束执行。
              如果在【request】没有定位成功，到【session】定位共享数据，如果存在直接读取输出并结束执行
              如果在【session】没有定位成功，到【application】定位共享数据，如果存在直接读取输出并结束执行
              如果在【application】没有定位成功，返回null
              pageContext --> request --> session --> application
   4、存在隐患：
             容易降低程序执行速度
             容易导致数据定位错误
   5、应用场景：
            设计目的：就是简化从pageContext读取共享数据并输出难度
   6、EL表达式简化版尽管存在很多隐患，但是实际开发过程中，开发人员为了节省时间，一般都使用简化版，拒绝使用标准版

五、EL表达式-------支持运算表达式【运行FourServlet】
   1、前提：在JSP文件有时需要将共享数据进行一番运算之后，将运算结果写入到响应体
   2、运算表达式：
               1)、数学运算
               2)、关系运算：>   >=   <   <=   !=
                           gt  ge   eq   lt   !=
               3)、逻辑运算

六、EL表达式提供内置对象【参考index_05.jsp】
   1、命令格式：${param.请求参数名}
   2、命令作用：从通过请求对象读取当前请求包中请求参数内容
   3、代替以下命令：index.jsp
               发送请求：http://localhost:8080/myWeb/index_05.jsp?userName=mike&password=123
               <%
               String userName = request.getParameter("userName");
               String password = request.getParameter("password");
               %>
               <%=userName%>
               <%=password%>

       1、命令格式：${paramValues.请求参数名[下标]}
       2、命令作用：如果浏览器发送的请求参数是【一个请求参数关联多个值】
                   此时可以通过paramValues读取请求参数下指定位置的值
                   并写入响应体。
       3、代替以下命令：http://localhost:8080/myWeb/index_05.jsp?deptNo=10&deptNo=20&deptNo=30
                   此时deptNo请求参数在请求包以数组形式存在
                   deptNo:[10,20,30]
                <%
                    String array[] = request.getParameterValues("deptNo");
                %>
                <%=array[0]%>
                <%=array[1]%>