## SpringCloud-Netflix微服务
	  1、SpringCloud Netflix是SpringCloud的一个子项目，主要是对Netflix开源组件的整合。
	  2、Netflix开源组件有：Eureka、Ribbon、Hystrix、Feign、Zuul、Config、Bus等
      但目前 Spring Cloud Netflix 多个核心组件（Eureka、Ribbon、Hystrix、Feign、Zuul）已被官方标记为进入维护模式或弃用状态

## 一、Eureka注册中心
      负载均衡ribbon：
                   就是指将负载（工作任务）进行平衡、分摊到多个操作单元上进行运行，
                   例如HTTP服务器、Web服务器、企业核心应用服务器和其它主要任务服务器等，从而协同完成工作任务。
                   比如说我同时有四个provider-service开启服务，但是他们的port值不一样。
                   我在consumer-service开启后接连发送两个请求，然后通过负载规则去给你分配到对应的服务提供者

## 二、雪崩问题：
       比如在一个Tomcat容器中有很多a,b,c,d,e,f,g......的组件服务。
       假如此时一个用户请求了a,c,d。但是其中某个组件服务例如a出现异常无法访问。
       就会导致整个请求阻塞，用户不会得到响应，则Tomcat这个线程不会释放。
       这个时候越来越多的用户请求到来，就会有越来越多的线程会阻塞。
       服务器支持的线程和并发数有限，请求一直阻塞，就会导致服务器资源耗尽，从而导致所有其他服务都不可用，形成雪崩效应

## 三、Hystrix
      Hystrix，英文翻译是豪猪，全身是刺，是一种保护机制
      Hystrix是Netflix公司的一款组件
        作用：用于隔离访问远程服务，第三方库，防止出现级联失败
        场景：通过hystrix可以解决雪崩效应问题，它提供了资源隔离、降级机制、融断、缓存等功能。
   
      Hystrix解决雪崩问题的手段有两个：
          1、线程隔离，服务降级
              ①、线程隔离：比如说我在Tomcat服务器里每个服务都建立一个线程池，然后每个线程池里分配随意个线程连接。
                 但是当我这个服务的线程池被占满，就会出现异常无法被访问。这个时候不会去占用其他服务的线程，影响其他服务的进行。
              ②、服务降级：但是仅仅线程隔离是不够的，总不能我这个异常服务一直不处理吧。所以不会一直阻塞，我会返回一个友好的响应，
                 用以表示这个资源暂时不能访问并且释放线程。这样就可以避免线程由于不能访问造成的阻塞。
          2、服务熔断
              原理：如果说Tomcat容器中有一个服务的超时时长过长，有个3-5秒时间。这个时候就会影响系统的并发量和并发性能。
                   如果一定时间内请求时长过长的百分比超过了断路器设置的一个阈值，断路器就会认为这个服务存在隐患，
                   所有的请求都进行一个降级处理返回一个友好的响应，去结束线程。
      
              断路器有三个状态：
                   Closed：关闭状态  (所有的请求都正常访问)
                   Open：打开状态    (所有的请求都被降级)
                   Half Open:半开状态(Closed状态不是永久的，关闭后有一个休眠时间[默认是5秒]。随后断路器会自动进入半开状态
                                    此时会释放部分请求通过，若这些请求都是健康的，则会完全打开断路器，否则继续保持关闭)
      
          小结：通常情况下是不会去设置断路器的数值参数，采用的都还是默认的。
               但是那个超时时长是需要重新设置的，因为默认超时时长是1秒，但由于网络波动等一系列因素会造成超时延长，需要重新设置。

## 四、openFeign：
      Feign是不需要单独列一个服务去注册的，它是随调随用。你要是用就在你的application启动类上加上@EnableFeignClients
      然后再在你的逻辑层上标注@FeignClient("服务名称")

      主要是有利于我们程序员去减少类似
      String url = "http://provice-service/user/" +id;
      String user = restTemplate.getForObject(url,String.class);代码的书写
      而且也不用再在启动类上创建RestTemplate对象了。

      只需要写一个接口类标注上@FeignClient注解，关于是否给熔断做降级处理，就去实现这个接口类

      相当于Feign组件融合了我们的ribbon，hystrix

## 五、Zuul网关
      原理：类似于路由器原理。路由器是通过光猫获得网络，再由路由器去分散网络给用户。
           而zuul网关也是一样，用户请求发过来，要通过zuul进行拦截，不会让用户直接去访问微服务。
           再由zuul去拉取Eureka的服务列表，获取微服务信息，然后请求转发给对应的微服务。
           如果服务器有多台，还会触发zuul的负载均衡(ribbon)
   
            zuul依赖集成了ribbon和hystrix
      功能：
          路由功能：相当于在请求过程中加上了一个坝
          过滤器功能：进行过滤请求
      zuul的高可用：
          我们想一下，zuul作为用户访问微服务的入口，如果zuul挂了呢？是不是很恐怖？所以我们会形成一个zuul集群。
          但是这个时候我们用户请求就犯了难，因为zuul是个集群，用户发送请求到底是发送到哪个zuul上呢？
          因此在zuul上还有一个nginx，用户请求通过nginx发送给响应zuul


## 六、Config  
       1、概念：  
              微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务。
          由于每个服务都需要必要的配置信息才能运行，所以一套集中式的，动态的配置管理设施是必不可少的。
          Spring Cloud 提供了 ConfigServer 来解决这个问题
          SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。  
       
       2、理解：  
              SpringCloud Config分为服务端和客户端两部分。服务端也称为分布式配置中心，它是一个独立的微服务应用，
          用来连接配置服务器并为客户端提供获取配置信息，加密/解密信息等访问接口。客户端则是通过指定的配置中心来管理应用资源，
          以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息配置服务器默认采用git来存储配置信息，
          这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。  

       3、作用（集中管理配置文件）  
             不同环境不同配置，动态化的配置更新，分环境部署比如dev/test/prod/beta/release
          运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
          当配置发生变动时，服务不需要重启即可感知到配置的变化并应用新的配置
          将配置信息以REST接口的形式暴露  

       4、使用    
             ①、在官方仓库中新建开源仓库，并建config_application.yml文件 和 config_application-dev.yml文件
                 然后在两个文件中分别添加以下配置信息。
                    server:                                  server:
                      port: 8899                               port: 9999
                    spring:                                  spring:
                      application:                             application:
                        name: ConfigClient-Server1               name: ConfigClient-Server2
                    my:                                      my:
                      args:                                    args:
                        int: 20                                  int: 30
                        str: 小曹                                 str: 小游
             ②、在 Config-Server服务 中设置官方仓库中的仓库路径
             ③、依次启动 Eureka-Server、Config-Server、Config-Client 等服务
             ④、然后在启动Config Client服务时，发现配置文件中并没有书写port端口，但是系统启动服务后分配的端口的是配置仓库文件中port端口
             ⑤、输入 http://localhost:8899(9999)/actuator(注意：只能是GET请求) 查看自己服务是否启动成功

## 七、热刷新  
      1、概念：不重启的前提下，刷新应用。Spring Cloud Config中的热刷新，是不重启Spring应用，刷新Spring容器。 
              Spring Cloud Config组件是支持热刷新的。
  
      2、做法：①、完成热刷新配置后重启，去输入 http://localhost:9999/actuator(注意：只能是GET请求)
                 查看自己热刷新是否启动成功：info 和 health 自带的，如果有我们配置的 refresh 就表示热刷新配置成功
              ②、每次在gitee上修改配置文件内容完后，都需要http://localhost:xxxx/actuator/refresh访问下去刷新(注意：只能POST访问)
              ③、刷新完后再去访问服务地址：http://localhost:xxxx/test。可以发现没有重启服务就能获取到修改后的值  

      2、作用：不间断服务的前提下，修改服务具体逻辑。如：类软编码配置、实现、对象之间的依赖关系等  

      3、局限性：如果分布式环境中，是多服务器集群，热刷新就不够友好。如：10万台电脑，依次热刷新port端口，
               http://localhost:xxxx/actuator/refresh 岂不是要点击十万次
               这个时候 Spring Cloud bus 闪亮登场！！！  

## 八、热刷新 + Bus（依靠 Spring Cloud Config + Spring Cloud Bus 实现全局热刷新）
         依赖  spring-cloud-Starter-bus-amqp 是基于RabbitMq实现的一个消息流资源依赖。可以基于事件，实现消息的自动收发。
      当 服务/actuator/bus-refresh 被调用的时候，自动发消息到RabbitMq上。所有客户端自动处理此消息，热刷新应用。
      使用topic交换器，配合#路由键，做的广播消息处理。
      每个客户端启动的时候，在MQ上，自动创建一个队列并监听。绑定在同一个交换器，且路由键是#

      BUS热刷新：
         开启BUS热刷新后，可以基于MQ实现全局和局部热刷新。
         使用方式：
             全局：
                POST请求 - http://ip:port/actuator/bus-refresh
             局部：
                POST请求 - http://ip:port/actuator/bus-refresh/目的地
                目的地语法 - 服务命名：端口号。  服务命名和端口号支持通配符 '*'
                             如：service*:808*;  service*:8080;  service:808*;
         注意：只有yml文件进行了以下配置的服务，Bus热刷新才会生效。没有配置的项目启动后Bus热刷新并不会生效。
                 management:
                   endpoints:
                     web:
                       exposure:
                         include:
                           - bus-refresh
