
/*
第一章
  一、三层架构
      界面层：和用户打交道的，接受用户的请求参数，显示处理结果的。(jsp,html,servlet)
      业务逻辑层：接受了界面层传递的数据，计算逻辑，调用数据库，获取数据。
      数据访问层：就是访问数据库，执行对数据的查询、修改、删除等等的

      三层对应的包
         界面层：   controller包（servlet）
         业务逻辑层：servlet包（XXXService类）
         数据访问层：dao包（XXXDao类）

      三层种类的交互
         用户使用界面层 --> 业务逻辑层 --> 数据访问层(持久层) --> 数据库(mysql)

      三层对应的处理框架
         界面层--servlet--SpringMVC(框架)
         业务逻辑层--service类--Spring(框架)
         数据访问层--dao类--MyBatis(框架)
  二、框架
      框架是一个舞台，一个模版
      模版：
         1、规定了好一些条框，内容。
         2、加入自己的东西
      框架是一个模板
         1、框架中定义好了一些功能，这些功能是可用的。
         2、可以加入项目中自己的功能，这些功能可以利用框架中写好的功能

      框架是一个软件，半成品的软件，定义好了一些基础功能，需要加入你的功能就是完整的。
      基础功能是可重复使用的，可升级的。

      框架特点：；
         1、框架一般不是全能的，不能做所有的事情
         2、框架是针对某一个领域有效。特长在某一个方面，比如mybatis做数据库操作强，但是它不能做其他的
         3、框架是一个软件

第二章
  一、MyBatis实现步骤：
      1、新建的student表
      2、加入maven的mybatis坐标,mysql驱动的坐标
      3、创建实体类，student--保存表中一行数据的
      4、创建持久层的dao接口，定义操作数据库的方法
      5、创建一个mybatis使用的配置文件
         叫做sql映射文件：写sql语句的。一般一个表一个sql映射文件。这个文件叫做xml文件.
         1)、在接口所在的目录中
         2)、文件名称和接口保持一致
      6、创建mybatis的主配置文件：
         一个项目就一个主配置文件。
         主配置文件提供了数据库的连接信息和sql映射文件的位置信息
      7、创建使用mybatis类，
         通过mybatis访问数据库
  二、mybatis提供了哪些功能：；
      1、提供了创建Connection、Statement、ResultSet的能力，不用开发人员创建这些对象了
      2、提供了执行SQL语句的能力，不用你执行SQL
      3、提供了循环SQL，把SQL的结果转为Java对象，List集合的能力
         while(rs.next()){
             Student stu = new Student();
             stu.setId(rs.getInt("id"));
             stu.setName(rs.getString("name"));
             stu.setEmail(rs.getString("email"));
             stu.setAge(rs.getInt("age"));
             // 从数据库取出数据转为Student对象，封装到List集合
             student.add(stu);
         }
      4、提供了关闭资源的能力，不用你手动关闭Conection、Statement、ResultSet

      开发人员做的是：提供SQL语句
      最后是：开发人员提供SQL语句--mybatis处理SQL--开发人员得到List集合或Java对象(表中的数据)

      总结：
      mybatis是一个SQL映射框架，提供的数据库的操作能力。增强的JDBC,
      使用mybatis让开发人员集中精神写sql就可以了，不必关心Connection、Statement、ResultSet的创建、销毁、SQL的执行。

第三章
  一、主要类的介绍
      1、Resources:mybatis中的一个类，负责读取主配置文件
         InputStream in = Resources.getResourceAsStream(config);

      2、SqlSessionFactoryBuilder：创建SqlSessionFactory对象
         SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
         // 创建SqlSessionFactory对象
         SqlSessionFactory target = builder.build(in);

      3、SqlSessionFactory：重量级对象，程序创建一个对象耗时较长，使用资源比较多。在整个项目中有一个就足够用了
         SqlSessionFactory接口：接口实现类：DefaultSqlSessionFactory
         SqlSessionFactory作用：获取SqlSession对象。SqlSession sqlSession = target.openSession();

         openSession()方法说明：
         1)、openSession():无参数的，获取是非自动提交事务的SqlSession对象
         2)、openSession(boolean):openSession(true)   获取自动提交事务的SqlSession对象
                                  openSession(false)  非自动提交事务的SqlSession对象

      4、SqlSession:
         SqlSession接口：定义了操作数据的方法  例如select(),selectList(),insert(),update(),delete(),commit(),rollback()
         SqlSession接口的实现类DefaultSqlSession

         使用要求：SqlSession对象不是线程安全的，需要在方法内部使用，在执行SQL语句之前，使用openSession()获取SqlSession对象
                  在执行完SQL语句后，需要关闭它，执行SqlSession.close().这样能保证他的使用是线程安全的。

第四章
  一、动态代理：使用SqlSession.getMapper(dao接口.class) 获取这个dao接口的对象
  二、传入参数：从Java代码中把数据传入到mapper文件的SQL语句中。
      1、parameterType：写在mapper文件中的 一个属性。表示dao接口中方法的参数的数据类型。
         例如：StudentDao接口
         public Student selectStudentById(Integer id)

      2、一个简单类型的参数：
         简单类型：mybatis把Java的基本数据类型和String都叫简单类型。
         在mapper文件获取简单类型的第一个参数的值，使用#{任意字符}

         接口：Student selectStudents(Integer id);
         mapper:select id,name,email,age from student where id=#{StudentId}

      3、多个参数，使用@param命名参数
         接口：public List<Student> selectMultiParam(@Param("mybatis") String name,@Param("mybatis") Integer age)
         使用 @param("参数名") String name
           mapper文件：
             <select>
                 select * from student where name=#{myname} or age=#{myage}
             </select>

      4、多个参数：使用Java对象作为接口中方法的参数。
                 MyBatis 会使用反射机制来解析这个对象的属性，可以直接使用 #{} 语法来引用这些属性。
         接口：List<Student> selectMultiObject(Student student);
         mapper文件：
              <select id="selectMultiObject" resultType="com.example.pojo.Student">
                  select id,name,email,age from student where name=#{name} or age=#{age}
              </select>

      5、还有两种传参方式，这里不推荐使用，因为可读性太差
         1)、按位置传参
         2)、Map传参

      6、# 和 $
         select id,name,email,age from student where name=#{name}
         # 的结果：select id,name,email,age from student where name=?
         这种方式是使用PreparedStatement对象执行sql

         select id,name,email,age from student where name=${name}
         $ 的结果：select id,name,email,age from student where name='李四'
         相当于 String sql = "select id,name,email,age from student where name=" + "'李四'";
         这种方式是使用Statement对象执行sql，效率比PreparedStatement低，还会发生SQL注入

         $：可以替换表名或者列名，你能确定数据是安全的，可以使用$

         #和$区别：
           1)、#使用?在SQL语句中作占位的，使用PreparedStatement对象执行sql，效率高
           2)、#能够避免SQL注入，更安全
           3)、$不使用占位符，是字符串连接方式，使用Statement对象执行sql，效率低
           4)、$有SQL注入的风险，缺乏安全性
           5)、$可以替换表名或列名

  三、MyBatis的输出结果
      mybatis执行了SQL语句，得到Java对象。
      1、resultType结果类型,指SQL语句执行完毕后，数据转为的Java对象，Java类型是任意的
         resultType结果类型他的值：1)、类型的全限定名称
                                2)、类型的别名，例如 java.lang.Integer 别名是int

         处理方式：
           1)、mybatis执行SQL语句，然后mybatis调用类的无参数构造方法，创建对象
           2)、mybatis把ResultSet指定列值赋给同名的属性

      2、resultMap:结果映射，指定列名和Java对象的属性对应关系。
         1)、你自定义列值赋值给哪个属性
         2)、当你的的列名和属性名不一样时，一定使用resultMap

第五章、动态sql
  动态sql:sql的内容是变化的，可以根据条件获取到不同的sql语句
  动态sql的实现，使用的是mybatis提供的标签，<if>,<where>,<foreach>

  1、<if>是判断条件的，
     语法<if test="判断Java对象的属性值">
            部分sql语句
        </if>

  2、<where>用来包含多个<if>的，当多个if有一个成立的，<where>会自动增加一个where关键字，
     并去掉 if中多余的 and,or等。

  3、<foreach> 循环Java中的数组，list集合的。主要用在sql的in语句中.
     学生id是 1001，1002，1003的三个学生

     select * from student where id in (1001,1002,1003)
     public List<Student> selectFor(List<Integer> idlist)
     List<Integer> list = new ...
     list.add(1001);
     list.add(1002);
     list.add(1003);
     mapper.selectFor(list)

     <foreach collection="" item="" open="" close="" separator=""></foreach>
     collection: 表示遍历的是集合<List>或者数组<Array>，该属性的值应该与传递给Mapper方法的参数名称相对应。
                 当传递一个单一的集合<List>或数组<Array>作为参数给 Mapper 方法时，
                 MyBatis 会默认将这个参数视为一个名为 "list" 集合或者 "array" 数组，
                 除非使用 @Param 注解在传参上指定一个不同的名称。
     item: 自定义的，表示数组和集合成员的变量
     open: 循环开始时的字符
     separator: 集合成员之间的分隔符

  4、sql代码片段，就是复用一些语法
     步骤：
         1)、先定义 <sql id="自定义名称唯一">  sql语句，表名，字段等 </sql>
         2)、再使用，<include refid="id的值"/>

第六章：
  1、数据库的属性配置文件：把数据库连接信息放到一个单独的文件中。和mybatis主配置文件分开。
     目的是便于修改，保存，处理多个数据库的信息
     1)、在resources目录中定义一个属性配置文件，xxxx.properties,例如  jdbc.properties
         在属性配置文件中，定义数据，格式是 key=value
         key: 一般使用，做多级目录
         例如：jdbc.mysql.driver  , jdbc.driver.mydriver
              jdbc.driver=com.mysql.cj.jdbc.Driver
              jdbc.url=jdbc:mysql://localhost:3306/springdb?useSSL=false&amp;allowPublicKeyRetrieval=true
              jdbc.user=root
              jdbc.passwd=131474
     2)、在mybatis的主配置文件，使用<property>指定文件的位置
         在需要使用值的地方，${key}

  2、mapper文件，使用package指定路径：
        <mappers>
            <!--当你的sql映射文件特别的多，一百多个，一个个的写mapper文件太繁琐了-->
            <!--使用包名确定sql映射文件位置。
                name:xml文件(mapper文件) 所在的包名，这个包中的所有xml文件一次都能加载给mybatis
                使用package的要求：
                   1、mapper文件名称需要和接口名称一样，区分大小写的一样
                   2、mapper文件和dao接口需要在同一目录下
            -->
            <package name="com.example.mapper"/>
        </mappers>

第七章：pageHelper
  一、加载pageHelperjar包
       <dependency>
         <groupId>com.github.pagehelper</groupId>
         <artifactId>pagehelper</artifactId>
         <version>5.1.10</version>
       </dependency>
  二、加入plugin配置
       在<environments>之前加入
       <plugins>
           <plugin interceptor="com.github.pagehelper.PageInterceptor"/>
       </plugins>
   三、PageHelper对象
        加入PageHelper的方法，分页。
        pageNum: 第几页，从1开始
        pageSize: 一页中有多少行数据
        PageHelper.startPage(pageNum:1 , pageSize:3);

第八、面试常问的：MyBatis的二级缓存是怎么回事？
   1、一级缓存Mybatis自己是默认开启的,是不需要我们去管的,不过一级缓存是SqlSession级别的,在操作数据库的时候我们需要创建SqlSession对象,
      SqlSession自己有个结构是HashMap的缓存区,每一个SqlSession都有自己的缓存区.

      例如：在 12_MyBatis-sql 模块中 TestMyBatis 类的 testone() 方法里连续两次去查询 Id为16 的数据，
           使用同一个SqlSession进行了两次一样的查询,可以从控制台信息中看到第一次查询的时候显示发送了sql语句,
           而第二次查询则没有显示发送sql语句,说明是从缓存中获取的数据,而不是数据库

      什么时候清空一级缓存?
           MyBatis 在执行增、删、改操作时，或者当 SqlSession 关闭时，会自动清空一级缓存，以确保数据的准确性和一致性。

   2、⚠️SqlSession 会话接口
      (1)、SqlSession 是 MyBatis 工作的最顶层API会话接口，所有的数据库操作都经由它来实现。
           由于它就是一个会话，不是永久存活的，即一个 SqlSession 应该仅存活于一个业务请求中，也可以说仅存活于一次数据库会话。
      (2)、每次创建一个 SqlSession 会话，都会伴随创建一个专属 SqlSession 的连接管理对象。
           为避免导致事务问题和数据不一致问题（尤其是在并发场景下），SqlSession 不应该被共享，应该仅在方法内部使用。
           SqlSession 不被共享，也就不会存在线程安全的问题了。
      (3)、Mybatis 在不集成 Spring 的情况下，SqlSession 的生命周期由开发者手动管理。
           因此，在同一个方法中是可以从一级缓存里得到 SqlSession 的，继而可以得到缓存的数据。
      (4)、Mybatis 集成 Spring 的情况下，SqlSession 的生命周期由Spring的事务管理器管理，因此每个Mapper方法调用都需要一个新的 SqlSession。
           Ⓐ、如果不开启事务管理，Spring 会为每个 Mapper 方法创建一个新的 SqlSession，并在 Mapper 方法执行完成后关闭(SqlSession会close)。
               这样可以保证线程安全，但是会增加系统开销（频繁创建和销毁 SqlSession 可能会导致连接池耗尽）
           Ⓑ、如果启用了事务管理，Spring 会将 SqlSession 绑定到当前线程的事务中，多个 Mapper 方法共享同一个 SqlSession。
               所以如果一个请求中存在多次相同 Sql 调用，那么从第二次调用相同 Sql 开始可以从缓存中读取数据。
      (5)、Mybatis 集成 Spring 的情况下，Mybatis 一级缓存实现原理：
           Mybatis 会在一级缓存中获取 SqlSession 时候加入一个事务判断机制，为的是避免在多线程情况下造成的数据不安全。
           Mybatis 调用 SqlSessionUtils.getSqlSession() 时候执行一个 sessionHolder() 方法，该方法会判断业务请求是否事务性的。
           a、如果没有事务管理，Spring 无法将多个 Mapper 方法绑定到同一个 SqlSession 中。
           b、如果有配置添加了 @Transactional 注解，则会把 SqlSession 暂存在 ThreadLocal 中，
              则当第二次执行相同的 Mapper 方法的时候就会去 ThreadLocal 中去取有没有，
              如果没有，直接返回 SqlSession 为 null，并且在第二次执行相同的 Mapper 就会新建一个新的 SqlSession。

      总结：SqlSession 不应该是共享的：在多线程或事务环境中，必须确保每个线程或事务使用独立的 SqlSession。
           Mybatis 集成 Spring 下的生命周期管理：
               a、无事务管理时，每个 Mapper 方法使用独立的 SqlSession。
               b、启用事务管理时，多个 Mapper 方法共享同一个 SqlSession，并通过 ThreadLocal 实现线程隔离。
           一级缓存的作用：在同一个 SqlSession 或事务中，一级缓存可以提升查询性能，避免重复查询数据库。

           为了避免共享 SqlSession 导致的问题，应该确保每个线程或操作使用独立的 SqlSession。以下是两种常见的解决方案：
               方案 1：使用 SqlSessionTemplate
                      SqlSessionTemplate 是线程安全的，每次操作都会创建新的 SqlSession。
                      通过 Spring 注入 SqlSessionTemplate，确保每个操作独立。
               方案 2：使用 Spring 事务管理
                      通过 Spring 的事务管理机制，确保每个事务使用独立的 SqlSession。
                      使用 @Transactional 注解声明事务边界。

   3、二级缓存是Mapper级别的，每个Mapper都有着一个缓存区，就是说不管几个 SqlSession 只要它们获取的是同一个Mapper,
      那么Mapper缓存区中的缓存数据就是共享的，而二级缓存是需要开发者自己去手动开启的。
      并且每个命名空间（namespace）都可以有自己的二级缓存配置。

      开启步骤：①、使用mybatis二级缓存的实体类必须实现Serializable接口
                    public class Student implements Serializable {}
              ②、在 mybatis.xml 文件中开启二级缓存的全局配置项，它用于控制是否启用二级缓存的能力。
                  默认情况下该配置项的属性值为 true，所以即使未显式的指定配置，二级缓存的能力也是可用的。
                      <settings>
                          <setting name="cacheEnabled" value="true"/>  <!--开启mybatis的二级缓存-->
                      </settings>
              ③、开启命名空间（namespace）的二级缓存(选择缓存策略): 【注意：以下两种模式不可以同时存在】
                     Ⅰ、注解模式：在 Mapper 接口类上，添加@CacheNamespace注解，通过注解开启二级缓存的分开关。
                           @CacheNamespace
                           public class StudentDao {
                              @ResultType(Student.class)
                              @Select("select * from student where id=#{id}")
                              Student selectIdThree(Integer id);
                           }
                     Ⅱ、XML模式：在映射的 XML 文件中，使用<cache>标签，通过xml开启二级缓存的分开关。
                           <mapper namespace="com.example.mapper.StudentDao">
                              <cache></cache>  <!--xml中二级缓存分开关-->
                           </mapper>

      问题：为什么有全局配置项，还需要为每个映射器单独启用二级缓存？
           1)、全局配置项是用来控制是否启用二级缓存的能力。
           2)、但是，为了更好的控制缓存的粒度，需要为每个映射器单独启用二级缓存。
           3)、如果有一天不再需要二级缓存的功能了，就只能一个个从映射器中手动消除</cache>配置，效率和维护成本较高。
               因此，可以在全局配置项中直接关闭二级缓存，这样所有的映射器都不会使用二级缓存，就可以避免这个问题。

      二级缓存实现用例：
           在 08_MyBatis-sql 模块中 TestMyBatis 类的 testTwo() 方法里连续两次去查询 Id为1006 的数据，
           这次是两次相同的查询,不同的SqlSession,获取的同一个Mapper,只有第一次查询的时候日志显示向数据库发送了sql语句,
           其他两次都没有发送sql语句,说明其他两次都是从缓存中获取的数据

   4、MyBatis 执行批量插入，能返回数据库主键列表吗？(示例：07_Mybatis-demo)
       useGeneratedKeys = true
           这个表示插入数据之后返回一个自增的主键id给你对应实体类中的主键属性。
           通过这个设置可以解决在主键自增的情况下通过实体的getter方法获取主键，
           当然还需要配置 keyProperty 属性，指明数据库中返回的主键id给实体类中的哪个属性。
        keyProperty = 主键字段，这样就可以解决在主键自增的情况下获取主键。
        一般来说，加上这两个设置后。#{id}就可以删掉
*/
public class MyBatis框架概念 {

}
