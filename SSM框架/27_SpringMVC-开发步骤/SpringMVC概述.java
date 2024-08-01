/*
一、SpringMVC:是基于spring的一个框架，实际上就是spring的一个模块，专门做web开发的。可以理解为是servlet的一个升级

          web开发底层是servlet，框架是在servlet基础上面加入一些功能，让你做web开发方便

   Ⅰ、SpringMVC就是一个spring，spring是容器，ico能够管理对象，使用<bean>,@Component,@Repository,@Servlet,@Controller
       SpringMVC能够创建对象，放入到容器中(SpringMVC容器)，springMvc容器中放的是控制器对象。

      我们要做的是 使用@Controller创建控制器对象，把对象放入到springMvc容器中，把创建的对象作为控制器使用
      这个控制器对象能接收用户的请求，显示处理结果，就当作是一个servlet使用。

      使用@Controller注解创建的是一个普通类的对象，不是servlet。springMvc赋予了控制器对象一些额外的功能

      web开发底层是servlet，springMvc中有一个对象是servlet： DispatcherServlet(中央调度器)
      DispatcherServlet:负责接收用户的所有请求，用户把请求给了DispatcherServlet，
                       之后DispatcherServlet把请求转发给我们的Controller对象，
                       最后是Controller对象处理请求。

      index.jsp------DispatcherServlet(Servlet)----转发，分配给---Controller对象(@Controller注解创建的对象)

   Ⅱ、SpringMVC实现步骤;
        1、新建web maven工程
        2、加入依赖
           spring-webmvc依赖，间接把spring的依赖都加入到项目jsp,servlet依赖
        3、重点：在web.xml中注册springMvc框架的核心对象DispatcherServlet
             1)、DispatcherServlet叫做中央调度器，是一个servlet，他的父类是继承HttpServlet
             2)、DispatcherServlet页叫做前端控制器(front controller)
             3)、DispatcherServlet负责接收用户提交的请求，调用其他的控制器对象，并把请求的处理结果显示给用户
        4、创建一个发起请求的页面 index.jsp
        5、创建控制器(处理器)类
             1)、在类的上面加入@Controller注解，创建对象，并放入到springMvc容器中
             2)、在类的的方法上面加入@RequestMapping注解
        6、创建一个作为结果的jsp,显示请求的处理结果
        7、创建SpringMvc的配置文件(Spring的配置文件一样)
             1)、声明组件扫描器，指定@Controller注解所在的包名
             2)、声明视图解析器，帮助处理视图的

   Ⅲ、springMvc的处理流程
        1、发起some.do
        2、tomcat(web.xml--url-patten知道 *.do的请求给DispatcherServlet)
        3、DispatcherServlet(根据springmvc.xml配置知道 some.do--doSome())
        4、DispatcherServlet把得到ModelAndView进行处理，转发到show.jsp
        5、框架执行doSome()把得到ModelAndView进行处理，转发到show.jsp

        上面的过程简化的方式
        some.do--DispatcherServlet--MyController

   Ⅳ、springmvc执行过程源代码分析
        1、tomcat启动，创建容器的过程
           通过load-on-start标签指定的1，创建DispatcherServlet对象，
           DispatcherServlet他的父类是继承httpServlet的，他是一个Servlet，在被创建时,会执行init()方法。
           在init()方法中
             // 创建容器，读取配置文件
             WebApplicationContext ac = new ClassPathXmlApplicationContext("springmvc.xml");
             // 把容器对象放入到ServletContext中
             getServletContext().setAttribute(key,ac);
 
           上面创建容器的作用：创建@controller注解所在的类的对象，创建MyController对象，
           这个对象放入到 SpringMvc的容器中，容器是map，类似map.put("myController",MyController对象)
 
        2、请求的处理过程
           1)执行servlet的service()
               protected void service(HttpServletRequest request,HttpServletResponse response)
               protected void doServlet(HttpServletRequest request,HttpServletResponse response)
               DispatcherServlet.doDispatch(request,response){
                        调用MyController的.doSome()方法
               }
               
   Ⅴ、接收请求的参数，使用的处理器方法的形参
        1、HttpServletRequest
        2、HttpServletResponse
        3、HttpSession
        4、用户提交的数据
      
      接收用户提交的参数
        1、逐个接收（用于数据量不多的时候）
        2、对象接收（用于数据量多的时候）

        注意：
           在提交请求参数时，get请求方式中文没有乱码。
           使用post方式提交请求，中文有乱码，需要使用过滤器处理乱码问题。
   
           过滤器可以自定义，也可使用看框架中提供的过滤器 characterEncodingFilter
        
   Ⅵ、处理器方法的返回值表示请求的处理结果
        1、ModelAndView：有数据和视图，对视图执行forward
        2、String：表示视图，可以逻辑名称，也可以是完整视图路径
        3、void：不能表示数据，也不能表示视图。
                 在处理Ajax的时候,可以使用void返回值。通过HttpServletResponse输出数据。响应Ajax请求。
                 Ajax请求服务器端返回的就是数据，和视图无关
        4、Object：例如String,Integer,Map,List,Student等等都是对象，
                   对象有属性，属性就是数据。所以返回Object表示数据，和视图无关。
                   可以使用对象表示的数据，响应Ajax请求

      现在做Ajax，主要使用json的数据格式。
      实现步骤：
         1、加入处理json的工具库的依赖，springMvc默认使用jackson
         2、在springMvc配置文件之间加入 <mvc:annotation-driven> 注解驱动。
            json = om.writeValueAsString(student);
         3、在处理器方法的上面加入@ResposeBode注解
             response.setContentType("application/json;charset=utf-8");
             PrintWriter pw = response.getWriter();
             pw.println(json);

      springMvc处理器方法返回Object,可以转为json输出到浏览器，响应Ajax的内部原理
      1、<mvc:annotation-driven> 注解驱动。
         注解驱动实现的功能是 完成Java对象到json,xml,text,二进制等数据格式的转换。
         HttpMessageConveter接口：消息转换器。
         功能：定义了java转为json,xml等数据格式的方法。这个接口有很多的实现类。
              这些实现类完成 Java对象到json，java对象到xml，java对象到二进制数据的转换

         下面的两个方法是控制器类把结果输出给浏览器时使用的：
         boolean canWrite(Class<?> var1,@Nullable MediaType var2);
         void write(T var1,@Nullable MediaType var2,HttpOutputMessage var3)

         例如处理器方法
         @RequestMapping(value="/returnString.do")
         public Student doReturnVoidAjax(HttpServletResponse response, String name, Integer age) {
                 Student student = new Student();
                 student.setName(name);
                 student.setAge(age);
                 return student;
         }
         1)、canWrite作用检查处理器方法的返回值，能不能转为var2表示的数据格式。
             检查student(list,20)能不能转为var2表示的数据格式。如果检查能转为json,canWrite返回true
             MediaType:表示数格式的，例如json,xml等等
         2)、write：把处理器方法的返回值对象，调用jackson中的ObjectMapper转为json字符串。
             json = om.writeValueAsString(student);
      2、@ResponseBody注解
         放在处理器方法的上面，通过HttpServletResponse输出数据，响应Ajax请求的
           PrintWriter pw = response.getWriter();
           pw.println(json);
           pw.flush();
           pw.close();

   Ⅶ、tomcat本身能处理静态资源的访问，像html,图片，js文件都是静态文件
       tomcat的web.xml文件有一个servlet 名称是default,在服务器启动时创建的。
           <servlet>
              <servlet-name>default</servlet-name>
              <servlet-class>org.apache.catalina.servlet.DefaultServlet</servlet-class>
              <init-param>
                <param-name>debug</param-name>
                <param-value>0</param-value>
              </init-param>
              <init-param>
                <param-name>listings</param-name>
                <param-value>false</param-value>
              </init-param>
              <load-on-startup>1</load-on-startup>
           </servlet>
         default这个servlet作用：
         1、处理静态资源
         2、处理未映射到其他servlet的请求

   Ⅷ、地址分类
         1、绝对地址，带有协议开头名称的是绝对地址，http://www.baidu.com
         2、相对地址：没有协议开头的，例如： user/some.do  ,   /user/some.do
                    相对地址不能独立使用，必须有一个参考地址。通过参考地址+相对地址本身才能指定资源。

          可以动态获取协议地址：在index.xml文件中加入下面语句
            <%
              String basePath = request.getScheme() + "://" +
                                request.getServerName() + ":" +request.getServerPort() +
                                request.getContextPath() + "/";
            %>
            <base href="<%=basePath%>"/>   <%--动态获取协议地址--%>

         ******前端地址不加斜杠，后端地址加斜杠******

二、SSM整合开发思路
    Ⅰ、概述：
       1、SSM:SpringMVC + Spring + Mybatis

          SpringMVC：视图层，界面层，负责接收请求，显示处理结果
          Spring ：业务层，管理service，dao,工具类对象的
          Mybatis：持久层，访问数据库的

          用户发起请求--SpringMVC接收--Spring中的Service对象--MyBatis处理数据

       2、SSM整合也叫做SSI(IBatis也就是mybatis的前身)，整合中有容器。
            1)、第一个容器SpringMVC容器，管理Controller控制器对象的。
            2)、第二个容器Spring容器，管理Service,dao，工具类对象的

            我们要做的把使用的对象交给合适的容器创建，管理。把Controller还有web开发的相关对象
            交给springmvc容器，这些web用的对象写在springmvc配置文件中

            service，dao对象定义在spring的配置文件中，让spring管理这些对象。

            springmvc容器和spring容器是有关系的，关系已经确定好了
            springmvc容器是spring容器的子容器，类似Java中的继承。子可以访问父的内容
            在子容器中的Controller可以访问父容器中的Service对象，就可以实现controller使用service对象

       3、实现步骤：
            1)、使用springdb的MySQL库，表使用student(id auto_increment,name,age)
            2)、新建maven web项目
            3)、加入依赖
                springmvc,spring,mybatis三个框架的依赖，Jackson依赖，mysql驱动,druid连接池
                jsp,servlet依赖
            4)、写web.xml
                [1]、注册DispatcherServlet，目的：【1】创建springmvc容器对象，才能创建Controller类对象
                                                【2】创建的是Servlet，才能接受用户的请求
                [2]、注册spring的监听器：ContextLoaderListener,目的：创建spring的容器对象，才能创建service，dao等对象。
                [3]、注册字符集过滤器，解决post请求乱码的问题
            5)、创建包，Controller包,service包,dao包,实体类包名创建好
            6)、写springmvc,spring,mybatis的配置文件
                [1]、springmvc配置文件
                [2]、spring配置文件
                [3]、mybatis配置文件
                [4]、数据库的属性配置文件
            7)、写代码，dao接口和mapper文件，service和实现类，controller，实体类
            8)、写jsp页面
            
       31_SpringMVC-整合SSM  发起命令请求流程
           index.xml--addStudent.jsp--student/addStudent.do(service的方法，调用dao的方法)--result.jsp

三、异常处理：
    Springmvc框架采用的是统一，全局的异常处理。
    把controller中的所有异常处理都集中到一个地方。采用的是aop的思想。把业务逻辑结合异常处理代码分开。解耦合。

    使用两个注解
    1、@ControllerAdvice
    2、@ExceptionHandler

    异常处理步骤：
      1、新建maven web项目
      2、加入依赖
      3、新建一个自定义异常类 MyUserException,再定义它的的子类NameException,AgeExxception
      4、在controller抛出NameException,AgeException
      5、创建一个普通类，作为全局异常处理类。
         1)、在类的上面加入@ControllerAdvice
         2)、在类中定义方法，方法的上面加入@ExceptionHandler
      6、创建处理异常的视图页面
      7、创建springmvc的配置文件
         1)组件扫描器，扫描@Controller注解
         2)组件扫描器，扫描@ControllerAdvice所在的包名
         3)声明注解驱动

四、拦截器：
    定义：
        1、拦截器是springmvc中的一种，需要实现HandlerInterceptor接口
        2、拦截器和过滤器类似，功能方向侧重点不同。过滤器是用来过滤请求参数，设置编码字符集等工作的。
           拦截器是拦截用户的请求，对请求做判断处理的
        3、拦截器是全局的，可以对多个Controller做拦截。
           一个项目中可以有0个或多个拦截器，他们在一起拦截用户的请求。
           拦截器常用在：用户登陆处理，权限检查，记录日志。

    拦截器的使用步骤：
        1、在定义类实现HandlerInterceptor接口
        2、在springmvc配置文件中，声明拦截器，让框架知道拦截器的存在

    拦截器的执行时间：
        1、在请求处理之前，也就是controller类中的方法执行之前先被拦截
        2、在控制器方法执行之后也会执行拦截器
        3、在请求处理完成后也会执行拦截器

    ==================================================================================
  拦截器和过滤器的区别
    1、过滤器是servlet中的对象，拦截器是框架中的对象
    2、过滤器是实现Filter接口的对象，拦截器是实现HandlerInterceptor
    3、过滤器是用来设置request，response的参数，属性的，侧重对数据过滤的。拦截器是用了来验证请求的，能截断请求。
    4、过滤器是在拦截器之前先执行的。
    5、过滤器是tomcat服务器创建的对象，拦截器是springmvc让容器中创建的对象
    6、过滤器是一个执行时间点，拦截器是三个执行时间点
    7、过滤器可以处理jsp,js,html等等。拦截器是侧重拦截对Controller的对象。如果你的请求不能被DispatcherServlet接收，这个请求不会执行拦截器内容。
    8、拦截器拦截普通类方法执行，过滤器过滤servlet请求响应

    =====================================================================================
  SpringMVC内部请求的处理流程：也就是springmvc接收请求，到处理完成的过程
    1、用户发起请求some.do

    2、DispatcherServlet接受请求some.do，把请求转交给处理器映射器
       处理器映射器：springmvc框架中的一种对象，框架把实现了HandlerMapping接口的类都叫做映射类(多个)
       处理器映射器作用：根据请求，从springmvc容器对象中获取处理器对象(MyController controller = ctx.getBean("some.do"))
                      框架把找到的处理器对象放到一个叫做处理器执行链(HandlerExecutionChain)的类保存。
       HandlerExecutionChain：类中保存着 1、处理器对象(MyController)  2、项目中的所有的拦截器List<HandlerInterceptor>

    3、DispatcherServlet把2中的HandlerExecutionChain中的处理器对象交给了处理器适配器对象(多个)
       处理器适配器：springmvc框架中的对象，需要实现HandlerAdapter接口
       处理器适配器作用：执行处理器方法(调用MyController.dosome()得到返回值ModelAndView)

    4、DispatcherServlet把3中获取的ModelAndView交给了视图解析器对象。
       视图解析器：springmvc中的对象，需要实现ViewResoler接口(可以有多个)
       视图解析器作用：组成视图完整路径，使用前缀，后缀。并创建View对象。
           View是一个接口,表示视图的，在框架中jsp,html不是string表示，而是使用View和他的实现类表示视图

           InternalResourceView:视图类，表示jsp文件，视图解析器会创建InternalResourceView类对象。
           这个对象的里面，有一个属性url=/WEB-INF/view/show.jsp

    5、DispatcherServlet把4步骤中创建的View对象获取到，调用View类自己的方法，把Model数据放入到request作用域。
       执行对象视图的forward。请求结束。

五、Spring的启动过程
    1、做的是javase项目有main方法的，执行代码是执行main方法的。
       在main里面创建的容器对象
       ApplicationContext ac = new ClassPathXmlAppllicationContext("applllicationContext.xml")

    2、web项目是在tomcat服务器上运行的。对于一个web应用，其部署在web容器中，web容器提供其一个全局的上下文环境，这个上下文就是ServletContext，
        其为后面的spring IoC容器提供宿主环境；web项目中容器对象只需要创建一次，把容器对象放入到全局作用域ServletContext中

        怎么实现：
           在web.xml中使用监听器 --> 当全局作用域对象被创建时 --> 创建容器 --> 存入ServletContext

          在web.xml中会提供有contextLoaderListener。在web容器启动时，会触发容器初始化事件，
       此时contextLoaderListener会监听到这个事件，其contextInitialized方法会被调用。
       在这个方法中，spring会初始化一个启动上下文，这个上下文被称为根上下文，即WebApplicationContext。
       WebApplicationContext是一个接口类，确切的说，其实际的实现类是XmlWebApplicationContext，它就是spring的IoC容器，
       其对应的Bean定义的配置由web.xml中的<context-param>标签指定。在这个IoC容器初始化完毕后，
       Spring以WebApplicationContext.ROOTWEBAPPLICATIONCONTEXTATTRIBUTE为属性Key，将其存储到ServletContext中，便于获取；

        监听器作用：
        1)创建容器对象，执行 WebAppllicationContext ac = new ClassPathXmlAppllicationContext("applllicationContext.xml")
        2)把容器对象放入到servletContext.serAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,this.context);
          监听器可以自己创建，也可以使用框架中提供好的ContextLoaderListener

        springMvc启动过程
        1、ContextLoaderListener监听器初始化完毕后，开始初始化web.xml中配置的Servlet，这个servlet可以配置多个，
           以最常见的DispatcherServlet为例，这个servlet实际上是一个标准的前端控制器，用以转发、匹配、处理每个servlet请求。
           DispatcherServlet上下文在初始化的时候会建立自己的IoC上下文，用以持有spring mvc相关的bean。特别地，
           在建立DispatcherServlet自己的IoC上下文前，会利用WebApplicationContext.ROOTWEBAPPLICATIONCONTEXTATTRIBUTE
           先从ServletContext中获取之前的根上下文(即WebApplicationContext)作为自己上下文的parent上下文。
           有了这个parent上下文之后，再初始化自己持有的上下文。这个DispatcherServlet初始化自己上下文的工作在其initStrategies方法中可以看到，
           大概的工作就是初始化处理器映射、视图解析等。这个servlet自己持有的上下文默认实现类也是XmlWebApplicationContext。
           初始化完毕后，spring以与servlet的名字相关(此处不是简单的以servlet名为Key，而是通过一些转换，具体可自行查看源码)的属性为属性Key，
           也将其存到ServletContext中，以便后续使用。这样每个servlet就持有自己的上下文，即拥有自己独立的bean空间，
           同时各个servlet共享相同的bean，即根上下文定义的那些bean。
*/

