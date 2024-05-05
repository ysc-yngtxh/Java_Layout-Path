package com.example;

/*
一、spring的第一个核心功能  ioc
    1、Ioc (Inversion of Control):控制反转，是一个理论，概念，思想。
       描述的：把对象的创建，赋值，管理工作都交给代码之外的容器实现，也就是对象的创建是有其他外部资源完成。

        控制：创建对象，对象的属性赋值，对象之间的关系管理。
        反转：把原来的开发人员管理，创建对象的权限转移给代码之外的容器实现。由容器代替开发人员管理对象。创建对象，给属性赋值
        正转：由开发人员在代码中，使用new构造方法创建对象，开发人员主动管理对象。
              public static void main(String args[]){
                  Student student = new Student();
              }
        容器：是一个服务器软件，一个框架(spring)
        为什么要使用ioc：目的就是减少对代码的改动，也能实现不同的功能。实现解耦合。

    2、Java中创建对象有哪些方式：
        1、构造方法，new Student();
        2、反射
        3、反序列化
        4、克隆
        5、ioc：容器创建对象
        6、动态代理

    3、ioc的体现：
        servlet
        1、创建类继承HttpServlet
        2、在web.xml注册servlet
        3、没有创建Servlet对象，没有MyServlet myservlet = new MyServlet();
        4、Servlet 是Tomcat服务器他给你创建的。Tomcat也称为容器。Tomcat作为容器，里面存放的有Servlet对象，Listener,Filter对象。

     4、Ioc的技术实现
        DI是ioc的技术实现，
        DI(Dependency Injection):依赖注入，只需要在程序中提供要使用的对象名称就可以，至于对象如何在容器中创建，赋值，查找都由容器内部实现。

        spring是使用的di实现了ioc的功能，spring底层创建对象，使用的是反射机制。
        spring是一个容器，管理对象，给属性赋值，底层是反射创建对象。

     5、spring实现步骤
        1)、创建maven项目
        2)、加入maven的依赖
            spring的依赖，版本5.3.30版本
            junit依赖
        3)、创建类(接口和它的实现类) ，和没有使用框架一样，就是普通的类。
        4)、创建spring需要使用的配置文件，声明类的信息，这些类由spring创建和管理
        5)、测试spring创建的

     6、di的语法分两类：
        1)、set注入(设值注入)：spring调用类的set方法，在set方法可以实现属性的赋值。80左右都是使用的set注入。
        2)、构造注入，spring调用类的有参构造方法，创建对象。在构造方法中完成赋值。

     7、设值注入
        注入：就是赋值的意思
        简单类型：spring中规定Java的基本数据类型和String都是简单类型。
        di：给属性赋值
         1)、set注入(设置注入)：spring调用另类的set方法，你可以在set方法中完成属性赋值
            [1]、简单类型的set注入
                <bean id="XX" class="YYY">
                    <property name="属性名称" value="此属性的值"/>
                       一个property只能给一个属性赋值
                    <property......>
                </bean>
            [2]、引用类型的set注入：spring调用类的set方法
                <bean id="XX" class="YYY">
                    <property name="属性名称" ref="bean的id(对象的名称)"/>
                </bean>
         2)、构造注入：spring调用类有参数构造方法，在创建对象的同时，在构造方法中给属性赋值。
             构造注入使用<constructor-arg>标签
             <constructor-arg>标签：一个<constructor-arg>表示构造方法一个参数。
             <constructor-arg>标签属性：
                 name:表示构造方法的形参名
                 index:表示构造方法的参数的位置，参数从左往右位置是0，1，2的顺序
                 value:构造方法的形参类型是简单类型的，使用value
                 ref:构造方法的形参类型是引用类型的，使用ref

         3)、引用类型的自动注入：spring框架根据某些规则可以给引用类型赋值。不用你再给引用类型赋值了
              使用规则常用的是byName,byType.
              [1]、byName(按名称注入)：Java类中引用类型的属性名和spring容器中(配置文件)<bean>的id名称一样，
                                   且数据类型是一致的，这样的容器中的bean，spring能够赋值给引用类型。
                   语法：
                       <bean id="xx" class="yyy" autoName="byName">
                           简单类型属性赋值
                       </bean>
              [2]、byType(按类型注入)：Java类中引用类型的数据类型和spring容器中(配置文件)<bean>的class属性是同源关系，
                                     这样的bean能够赋值给引用类型
                   语法：
                       <bean id="xx" class="yyy" autoName="byType">
                           简单类型属性赋值
                       </bean>
         4)、多个配置优势
             [1]、每个文件的大小比一个文件要小很多。效率高
             [2]、避免多人竞争带来的冲突

             如果你的项目有多个模块(相关的功能在一起)，一个模块一个配置文件。
             学生考勤模块一个配置文件，
             学生成绩一个配置文件

             多文件的分配方式：
             [1]、按功能模块，一个模块一个配置文件
             [2]、按类的功能，数据库相关的配置文件，做事务的功能一个配置文件，做service功能的一个配置文件等

     8、基于注解的di:通过注解完成Java对象创建，属性赋值
        1)、使用注解的步骤
            [1]加入maven的依赖 spring-context ，在你加入spring-context的同时，间接加入spring-aop的依赖。使用注解必须使用spring-aop依赖
            [2]在类中加入spring的注解(多个不同功能的注解)
            [3]在spring的配置文件中，加入一个组件扫描器的标签，说明注解在你的项目中的位置
                学习的注解：
                        @Component: 创建对象的，等同于<bean>的功能
                        @Value: 简单类型的属性赋值
                        @Autowired: spring框架提供的注解，实现引用类型的赋值。
                        @Resource: 来自jdk中的注解，spring框架提供了对这个注解的功能支持，可以使用他给引用类型赋值.和@Autowired类似
                        
                        @Repository: (用在持久层的上面)：放在dao的实现类上面，表示创建dao对象，dao对象是能访问数据库的
                        @service: (用在业务层的上面)：放在service的实现类上面，创建service对象，service对象是做业务处理，可以有事务等功能的。
                        @Controller: (用在控制器的上面)：放在控制器(处理器)类的上面，创建控制器对象的，控制器对象，能够接受用户提交的参数，显示请求的处理结果
                      以上三个注解的使用语法和@Component一样的。都能创建对象，但是这三个注解还有额外的功能。
                      @Repository, @service, @Controller 是给项目的对象分层的。当你不确定类的功能时，可以使用@Component

二、aop
   1、动态代理
      实现方式：jdk动态代理，使用jdk中的Proxy,Method,InvocationHandler创建代理对象。
               jdk动态代理要求目标类必须实现接口
      cglib动态代理：第三方的工具库，创建代理对象，原理是继承。通过继承目标类，创建子类。
                   之类就是代理对象。要求目标类不能是final的，方法也不能是final的
   2、动态代理的作用：
      1)、在目标类源代码不改变的情况下，增加功能。
      2)、减少代码的重复
      3)、专注业务逻辑代码
      4)、解耦合，让你的业务功能和日志，事务非业务功能分离
   3、Aop：面向切面编程，基于动态代理的，可以使用jdk,cglib两种代理方式。
          Aop就是动态代理的规范化，把动态代理的实现步骤，方式都定义好了，让开发人员用一种统一的方式，使用动态代理。
   4、Aop(Aspect Orient Programming)面向切面编程
      Aspect:切面，给你的目标类增加的功能，就是切面。像使用的日志，事物都是切面。切面的特点：一般都是非业务方法，独立使用的。
      Orient:面向，对着
      Programming:编程

      oop:面向对象编程

      怎么理解面向切面编程？
      1)、需要在分析项目功能时，找出切面。
      2)、合理的安排切面的执行时间(在目标方法前，还是目标方法后)
      3)、合理的安全切面执行的位置，在哪个类，那个方法增加强功能

      术语：
      1)、Aspect:切面，表示增强的功能，就是一堆代码，完成某一个功能。非业务功能，常见的切面功能有日志，事务，统计信息，参数检查，权限验证。
      2)、JoinPoint:连接点，连接业务方法和切面的位置。就某类中的业务方法
      3)、Pointcut:切入点，至多个连接点方法的集合，多个方法
      4)、目标对象：给哪个类的方法增加功能，这个类就是目标对象
      5)、Advice:通知，通知表示切面功能执行的时间

      说一个切面有三个关键的要素：
      1)、切面的功能代码，切面干什么
      2)、切面的执行位置，使用Pointcut表示切面执行的位置
      3)、切面的执行时间，使用Advice表示时间，在目标方法之前，还是在目标方法之后。
   5、aop的实现
      aop是一个规范，是动态的一个规范化，一个标准
      aop的技术实现框架
      1)、spring：spring在内部实现了aop规范，能做aop的工作。
                 spring主要在事务处理时使用aop
                 我们项目开发中很少使用spring的aop实现，因为spring的aop比较笨重
      2)、aspectJ：一个开源的专门做aop框架，spring框架中集成了aspectJ框架，通过spring就能使用aspectJ的功能。
          aspectJ框架实现aop有两种方式
            [1]、使用xml的配置文件：配置全局事务
            [2]、使用注解，我们在项目中要做aop功能，一般都使用注解，aspectJ有5个注解

   6、学习aspectJ框架的使用 (自我理解：aspectJ就是封装好了的jdk动态代理)

      1)、切面的执行时间，这个执行时间在规范中叫做Advice(通知，增强)
          在aspectJ框架中使用注解表示的。也可以使用xml配置文件中的标签
          [1]、@Before
          [2]、@AfterReturning
          [3]、@Around
          [4]、@AfterThrowing
          [5]、@After

      2)、AspectJ的切入点表达式
          execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name=pattern(param-pattern) throws-pattern)
          execution(   访问权限  方法返回值  包名类名?方法声明(参数)  异常类型    )
                      注意：访问权限  和   异常类型  可以省略
          符号：
               *  任意字符
               .. 任意参数
          举例：
              execution(public * *(..))  表示任意公共方法
              execution(* set*(..))      表示一个以“set”开始的方法
              execution(* com.example.service.*.*(..))    表示在Service包下任意类中的任意方法和任意参数
              execution(* *..service..*.*(..))                表示所有包中的Service包下的所有包任意方法和任意参数

三、把mybatis框架和spring集成在一起，像一个框架一样使用
    用的技术是：ioc
    为什么ioc：能把mybatis和spring集成在一起，像一个框架，是因为ioc能创建对象。
              可以把mybatis框架中的对象交给spring统一创建，开发人员从spring中获取对象。
              开发人员就不用同时面对两个或多个框架了，就面对一个spring
              
    1、独立的连接池类的对象，使用阿里的druid(德鲁伊)连接池
    2、SqlSession
    
    Spring-集成mybatis的实现步骤：
      1、新建maven项目
      2、加入maven的依赖
         1)、spring依赖
         2)、mybatis依赖
         3)、mysql驱动
         4)、spring的事务的依赖
         5)、mybatis和spring集成的依赖：mybatis官方体用的，用来在spring项目中创建mybatis的SqlSessionFactory,dao对象的
      3、创建实体类
      4、创建dao接口和mapper文件
      5、创建mybatis主配置文件
      6、创建spring的配置文件：声明mybatis的对象交给spring创建
         1)、数据源
         2)、SqlSessionFactory
         3)、Dao对象

    ⚠️Spring整合MyBatis时，会使用 SqlSessionTemplate 作为 SqlSession 的实现类，
   默认情况下会自动创建一个 SqlSessionTemplate对象，并将其注入到 Mapper代理对象 中。
      这个SqlSessionTemplate对象会在每次执行Mapper方法时，自动获取当前线程上下文中的SqlSession对象，然后调用相应的Mapper方法。
   因此，SqlSession对象不是每次执行Mapper方法都会创建，而是在需要时从线程上下文中获取。
      这样做的好处是可以确保每个线程都使用独立的SqlSession对象，从而保证线程安全性。


四、spring的事务处理
    1、什么是事务？
         在mysql的时候,提出了事务。事务是指一组sql语句的集合，集合中有多条sql语句
         可能是insert,update,select,delete，我们希望这些多个sql语句都能成功，
         或者都失败，这些sql语句的执行是一致的，作为一个整体执行

    2、在什么时候想到使用事务？
         当我的操作，涉及十多个sql语句的insert,update,delete。
         需要保证这些语句都是成功才能完成我的功能，或者都是失败的，保证操作是符合要求的

         在Java代码中写程序，控制事务，此时事务应该放在哪里呢？
           service类的业务方法上，因为业务方法会调用多个dao方法，执行多个sql语句

    3、通常使用jdbc访问数据库，还是mybatis访问数据库怎么处理事务
         jdbc访问数据库，处理事务  Connection conn;  conn.commit();  conn.rollback();
         mybatis访问数据库，处理事务  SqlSession.commit();  SqlSession.rollback();
         hibernate访问数据库，处理事务  Session.commit();  Session.rollback();

    4、问题3中事务的处理方式，有什么不足？
         1)、不同的数据库访问技术，处理事务的对象，方法不同，需要了解不同数据库访问技术使用事务的原理
         2)、掌握多种数据库中事务的处理逻辑。什么时候提交事务，什么时候回滚事务
         3)、处理事务的多种方法
         总结：就是多种数据库的访问技术，由不同的事务处理的机制，对象，方法

    5、怎么解决不足？
         spring提供一种处理事务的统一模型，能使用统一步骤，方式完成多种不同数据库访问技术的事务处理。
         使用spring的事务处理机制，可以完成mybatis访问数据库的事务处理
         使用spring的事务处理机制，可以完成hibernate访问数据库的事务处理

    6、处理事务，需要怎么做，做什么？
         spring处理事务的模型，使用的步骤都是固定的，把事务使用的信息提供给spring就可以了
         1)、事务内部提交，回滚事务，使用的事务管理器对象，代替你完成 commit，rollback
             事务管理器是一个接口和他的众多实现类。
             接口：PlatformTransactionManager，定义了事务重要方法 commit，rollback
             实现类：spring把每一种数据库访问技术对应的事务处理类都创建好了。
                    mybatis访问数据库----spring创建的是DateSourceTransactionManager
                    hibernate访问数据库----spring创建的是hibernateTransactionManager

             怎么使用：你需要告诉spring你用的是哪种数据库的访问技术，怎么告诉spring呢？
               声明数据库访问技术对于的事务管理器实现类，在spring的配置文件中使用<bean>声明就可以了
               例如：你要是使用mybatis访问数据库，你应该在xml配置文件中
               <bean id="xx" class="...DateSourceTransactionManager"/>
         2)、你的业务方法需要什么样的事务，说明需要事务的类型。
             说明方法需要的事务：
               【1】事务的隔离级别：有4个值。(读未提交，读已提交，可重复读[幻读]，串行化)
               【2】事务的超时时间：表示一个方法最长的执行时间，如果方法执行时间超过了时间，事务就回滚。
                                 单位是秒，整数值，默认是 -1
               【3】事务的传播行为：控制业务方法是不是有事务的，是什么样的事务的。
                                 7个传播行为，表示你的业务方法调用时，事务在方法之前是如果使用的
                      PROPAGATION_REQUIRED – 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
                      PROPAGATION_SUPPORTS – 支持当前事务，如果当前没有事务，就以非事务方式执行。
                      PROPAGATION_MANDATORY – 支持当前事务，如果当前没有事务，就抛出异常。
                      PROPAGATION_REQUIRES_NEW – 新建事务，如果当前存在事务，把当前事务挂起。
                      PROPAGATION_NOT_SUPPORTED – 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
                      PROPAGATION_NEVER – 以非事务方式执行，如果当前存在事务，则抛出异常。
                      PROPAGATION_NESTED – 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。

         3)、事务提交事务，回滚事务的时机
             【1】当你的业务方法，执行成功，没有异常抛出，当方法执行完毕，spring在方法执行后提交事务。事务管理器commit
             【2】当你的业务方法抛出运行时异常，spring执行回滚，调用事务管理器的rollback
                 运行时异常的定义：RuntimeException 和他的子类都是运行时异常。例如：NullPointException,NumberFormatException
             【3】当你的业务方法抛出非运行时异常，主要是受查异常时，提交事务
             受查异常：在你写代码中，必须处理的异常。例如：IOException,SQLException

    总结spring的事务
      【1】管理事务的是 事务管理和它的实现类
      【2】spring的事务是一个统一模型
           1]、指定要使用的事务管理其实现类，使用<bean>
           2]、指定哪些类，那些方法需要加入事务的功能
           3]、指定方法需要的隔离级别，传播行为，超时

      你需要告诉spring，你的项目中类信息，方法的名称，方法的事务传播行为。

**********spring框架中提供的事务处理方案**********
   1、适合中小项目使用的，注解方案。
      spring框架自己用aop实现给业务方法增加事务的功能，使用@Transactional注解增加事务
      @Transacctional注解是spring框架自己注解，放在public方法的上面，表示当前方法具有事务。
      可以给注解的属性赋值，表示具体的隔离级别，传播行为，异常信息等

      使用@Transacctional的步骤：
        1)、需要声明事务管理器对象
            <bean id="xx" class="DateSourceTransactionManager">
        2)、开启事务注解驱动，告诉spring框架，我要使用注解的方式管理事务
            spring使用aop机制，创建@Transacctional所在的类代理对象，给方法加入事务的功能。
            spring给业务方法加入事务：
              在你的业务方法执行之前，先开启事务，在业务方法之后提交或回滚事务，使用aop的环绕通知

               @Around("你要增加的事务功能的业务方法名称")
                 Object myAround(){
                   开启事务，spring给你开启
                   try{
                       buy(1001,10);
                       spring的事务管理.commit();
                   } cactch(Exception e){
                       spring的事务管理.rollback();
                   }
               }
        3)、在你的方法上面加入@Transactional

   2、适合大型项目，有很多的类,方法，需要大量的配置事务，使用aspectJ框架功能，
      在spring配置文件中声明类，方法需要的事务。这种方式业务方法和事务配置完全分离。

      实现步骤：；都是在xml配置文件中实现。
        1)要使用的是aspectJ框架，需要加入依赖
           <dependency>
             <groupId>org.springframework</groupId>
             <artifactId>spring-aspects</artifactId>
             <version>5.2.5.RELEASE</version>
           </dependency>
        2)声明事务管理器对象
           <bean id="xx" class=""/>
        3)声明方法需要的事务类型(配置方法的事务属性【隔离级别，传播行为，超时】)
        4)配置aop:指定哪些哪类要创建代理。

        这样做是为了将代码和事务分离开来，因为大型的项目中与很多的方法，一条一条的加上注解太麻烦了，甚至可能会有遗漏。
        所以，这样直接在配置文件中配置事务选项就行。需要的时候添加，不需要就删除，或者换个事务都可。

五、Spring的启动过程
    1、做的是javase项目有main方法的，执行代码是执行main方法的。
       在main里面创建的容器对象
       ApplicationContext ac = new ClassPathXmlAppllicationContext("applicationContext.xml")

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
        1)创建容器对象，执行 WebAppllicationContext ac = new ClassPathXmlApplicationContext("applllicationContext.xml")
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
public class Spring概述 {
}
