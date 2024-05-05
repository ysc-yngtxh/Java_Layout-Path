
## 一、SpringBootApplication注解  
    @SpringBootApplication的核心注解，主要目的是开启自动配置。  
    它也是一个组合注解，主要组合了@SpringBootConfiguration，@EnableAutoConfiguration（核心）和@ComponentScan。  
    可以通过@SpringBootApplication(exclude={想要关闭的自动配置的类名.class})来关闭特定的自动配置，
    其中 @ComponentScan 让Spring Boot扫描到 Configuration类, 并把它加入到程序上下文。
   
     
    1. @ComponentScan：组件扫描器
        @ComponentScan注解默认会扫描当前配置类(启动类)所在的包及其子包。
        也可以指定 @ComponentScan(basePackages="com.example")
        个人理解相当于：扫描启动类所在包以及子包中带有 @Component、@Service、@Repository、@Controller 等注解的类，
                     并将这些类注册为 Spring 容器管理的 bean。
                     不用我们自己去手动配置 <context:component-scan base-package="**"/> 组件扫描器。
     
    2. @SpringBootConfiguration：配置文件  
        本质上是一个@Configuration注解，表明该类是一个配置类。
        而@Configuration又被@Component注解修饰，代表任何加了@Configuration注解的配置类，都会被注入进Spring容器中。

    3. @EnableAutoConfiguration：自动配置  
        此注解自动载入应用程序所需的所有Bean——这依赖于Spring Boot在类路径中的查找。  
        该注解组合了 @AutoConfigurationPackage、@Import 等子注解，
        ①、@AutoConfigurationPackage注解会将标注的类，即主配置类(启动类)，返回主配置类所在的包路径，
           一方面用来指定 @ComponentScan组件扫描器的扫描范围（@ComponentScan注解默认就能扫描到主配置类所在的包路径）；
           另一方面确保Spring Boot的自动配置类(spring.factories)能够扫描到用户自定义的配置类(启动类所在包以及子包下的Bean)。
           这些自动配置类可能依赖于用户定义的Bean，而这些Bean是通过@ComponentScan扫描到的。
           如果没有@AutoConfigurationPackage，那么自动配置类可能无法找到这些用户定义的Bean，从而导致自动配置失败。
           另外，@AutoConfigurationPackage还允许通过spring.factories文件注册额外的自动配置类，
           这为用户提供了更灵活的方式来扩展或覆盖默认的自动配置。
        ②、@Import注解导入了 AutoConfigurationImportSelector 类，
           它使用 SpringFactoriesLoader.loaderFactoryNames 方法来扫描具有META-INF/spring.factories文件的jar包。
           而spring.factories里声明了有哪些自动配置. 
    
    4. 具体参考：https://blog.csdn.net/qq_33591903/article/details/119843446


## 二、Spring中主要使用的设计模式：

    1、单例模式（Singleton Pattern）：
          在Spring中，bean的默认作用域就是单例的。 这意味着在整个Spring IoC容器中，一个bean定义对应一个对象实例。
          这有助于减少内存消耗并提高性能。
    
    2、工厂模式（Factory Pattern）：
          Spring通过BeanFactory和ApplicationContext接口实现了工厂模式。
          这些工厂类负责创建并管理bean的生命周期。它们通过读取配置文件或注解来创建bean的实例，并在需要时提供给调用者。
    
    3、代理模式（Proxy Pattern）：
          Spring的AOP（面向切面编程）功能就是基于代理模式实现的。AOP通过代理对象来增强目标对象的功能，而无需修改目标对象的代码。
          Spring提供了JDK动态代理和CGLIB等字节码生成技术来实现代理。
    
    4、观察者模式（Observer Pattern）：
          Spring的事件机制是基于观察者模式实现的。
          当某个事件发生时，Spring容器会通知所有注册的观察者（监听器），从而实现了事件驱动编程。
    
    5、模板方法模式（Template Method Pattern）：
          Spring提供了许多模板类，如JdbcTemplate、HibernateTemplate等，用于简化数据库操作。
          这些模板类定义了数据库操作的通用流程，而将具体实现留给子类来完成。
    
    6、适配器模式（Adapter Pattern）：
          Spring框架中使用了适配器模式来适配不同的类或接口，使得它们能够协同工作。
          例如，在MVC模块中，HandlerAdapter就充当了适配器的角色，将不同的处理器（Handler）适配到统一的调用接口上。
    
    7、策略模式（Strategy Pattern）：
          Spring中的资源访问接口Resource的设计就是一种典型的策略模式。
          不同的资源访问策略（如文件访问、数据库访问等）可以通过实现相同的Resource接口来统一处理。
    
    8、装饰器模式（Decorator Pattern）：
          Spring中的某些功能也使用了装饰器模式，比如配置DataSource时，可能需要根据不同的需求来切换不同的数据源。
          装饰器模式允许我们在不修改原有对象的情况下，动态地给对象添加一些职责。

## 三、事务注解详解
     默认遇到throw new RuntimeException(“…”); 会回滚
     需要捕获的throw new Exception(“…”);      不会回滚

     @Transactional(rollbackFor=Exception.class)  : 指定异常回滚
     @Transactional(noRollbackFor=Exception.class): 指定异常不回滚

     如果有事务,那么加入事务,没有的话新建一个(不写的情况下) -- 传播级别
     @Transactional(propagation=Propagation.REQUIRED)

## 四、事务不生效
    1. 访问权限问题  
       方法的访问权限被定义成了private，这样会导致事务失效，Spring 要求被代理方法必须是public的。
   
    2. 方法使用 final 或 static关键字    
       Spring框架中通过代理模式来管理事务，即在运行时为参与事务的方法创建代理对象，以便在方法调用前后添加事务相关的逻辑。
       因此 Spring 事务底层使用了 aop，也就是通过 jdk 动态代理(默认)或者 cglib，帮我们生成了代理类，在代理类中实现的事务功能。
       如果某个方法用 final 修饰了，那么在它的代理类中，就无法重写该方法，而添加事务功能。  
       如果一个方法被定义为static，意味着它属于类而不是对象。在Spring框架中，事务的处理是通过织入到对象的代理中，
       对代理对象的方法进行事务的切入。由于静态方法属于类，而不是对象，因此无法通过代理对象来对静态方法进行事务的切入，
       这同样可能导致@Transactional注解不起作用，事务无法正确地开启、提交或回滚。
   
    3. 方法内部调用  
       ```
       @Service
       public class DefaultTransactionService implement Service {
       
            public void saveUser() throws Exception {
                // do something
                doInsert();
            }
       
            @Transactional(rollbackFor = Exception.class)
            public void doInsert() throws IOException {
                User user = new User();
                UserService.insert(user);
                throw new IOException();
            }
       }
       ```
       Spring的事务管理功能是通过动态代理实现的，而Spring默认使用JDK动态代理，而JDK动态代理采用接口实现的方式，通过反射调用目标类。  
       简单理解，就是saveUser()方法中调用this.doInsert(),这里的this是被真实对象，所以会直接走doInsert的业务逻辑，而不会走切面逻辑，所以事务失败。  
       解决方案：    
              方案一：解决方法可以是直接在启动类中添加@Transactional注解saveUser()  
              方案二：@EnableAspectJAutoProxy(exposeProxy = true)在启动类中添加
   
    4. 未被 spring 管理  
       使用 spring 事务的前提是：对象要被 spring 管理，需要创建 bean 实例。  
   
    5. 多线程调用  
       spring 的事务是通过数据库连接来实现的。当前线程中保存了一个 map，key 是数据源，value 是数据库连接。  
       我们说的同一个事务，其实是指同一个数据库连接，只有拥有同一个数据库连接才能同时提交和回滚。  
       如果在不同的线程，拿到的数据库连接肯定是不一样的，所以是不同的事务。
  
    6. 表不支持事务  
       在 mysql5 之前，默认的数据库引擎是myisam。它的好处就不用多说了：索引文件和数据文件是分开存储的，对于查多写少的单表操作，性能比 innodb 更好。  
       但有个很致命的问题是：不支持事务。如果只是单表操作还好，不会出现太大的问题。但如果需要跨多张表操作，由于其不支持事务，数据极有可能会出现不完整的情况。  
       此外，myisam 还不支持行锁和外键。
 
## 五、事务不回滚
    1. 抛出检查异常
       ```
       @Slf4j
       @Service
       public class UserService {
           @Transactional
           public void add(UserModel userModel) throws Exception {
               try {
                   saveData(userModel);
                   updateData(userModel);
               } catch (Exception e) {
                   log.error(e.getMessage(), e);
                   throw new Exception(e);
               }
           }
       }
       ```
       spring事务，默认情况下只会回滚RuntimeException（运行时异常）和Error（错误），对于普通的Exception（非运行时异常），它不会回滚。  
       解决方法也很简单。配置rollbackFor属性，例如@Transactional(rollbackFor = Exception.class)。
   
    2. ### 业务方法本身捕获了异常
       Spring是否进行回滚是根据你是否抛出异常决定的，所以如果你自己try...catch...捕获了异常，Spring 也无能为力。
  

## 六、编程式事务
    基于@Transactional注解的，主要讲的是它的事务问题，我们把这种事务叫做：声明式事务。
    
    其实，spring还提供了另外一种创建事务的方式，即通过手动编写代码实现的事务，我们把这种事务叫做：编程式事务。例如：
    ```
    @Autowired
    private TransactionTemplate transactionTemplate;
    
    public void save(final User user) {
        queryData1();
        queryData2();
        transactionTemplate.execute(transactionStatus -> {
            addData1();
            updateData2();
            return Boolean.TRUE;
        });
    }
    在spring中为了支持编程式事务，专门提供了一个类：TransactionTemplate，在它的execute方法中，就实现了事务的功能。
    ```
    
    相较于@Transactional注解声明式事务，更建议大家使用，基于TransactionTemplate的编程式事务。主要原因如下：  
    ①、避免由于spring aop问题，导致事务失效的问题。
    ②、能够更小粒度的控制事务的范围，更直观。

    建议在项目中少使用@Transactional注解开启事务。但并不是说一定不能用它，如果项目中有些业务逻辑比较简单，而且不经常变动，
    使用@Transactional注解开启事务开启事务也无妨，因为它更简单，开发效率更高，但是千万要小心事务失效的问题。
