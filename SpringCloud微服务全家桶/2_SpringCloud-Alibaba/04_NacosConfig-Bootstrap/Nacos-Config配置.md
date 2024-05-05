

### 配置文件加载的优先级（由高到低）
- ### bootstrap.properties -> bootstrap.yml -> application.properties -> application.yml
- ### bootstrap.properties是系统级的资源配置文件，是用在程序引导执行时更加早期配置信息读取；
- ### application.properties是用户级的资源配置文件，是用来后续的一些配置所需要的公共参数。

- #### 可参考：https://blog.csdn.net/yueyezhufeng/article/details/126277891     


    1、通过SpringApplication.run(...)，进入源码。
       项目启动的时候先加载配置信息，也就是当前的系统信息、JDK信息、配置文件信息等
    
    2、环境信息进行加载，可以发现 Environment 中有七个环境变量，其中第七个为我们自己配置的配置文件：config/bootstrap.properties
       这时候，项目还在启动阶段，尚未初始化完成，从这里可以看出 bootstrap.properties 被加载的优先级非常高。
    
    3、加载完环境信息，紧接着开始扫描项目以及jar包中的 META-INF/spring.factories
       org.springframework.cloud.bootstrap.BootstrapConfiguration=\
       com.alibaba.cloud.nacos.NacosConfigBootstrapConfiguration
       循环遍历获取到 NacosConfigBootstrapConfiguration，此时还没有开始将对象实例化。
    
    4、通过反射调用 NacosConfigBootstrapConfiguration 中的 nacosConfigProperties()方法。
       NacosConfigProperties实例化后，会执行 @Postconstruct 修饰的init()方法，并在此方法调用 overrideFromEnv()方法。
       在overrideFromEnv()方法中，会对nacos的serverAddr、userName、password进行初始化。

       @PostConstruct
       public void init() {
           this.overrideFromEnv();
       }
       private void overrideFromEnv() {
           if (this.environment != null) {
               if (StringUtils.isEmpty(this.getServerAddr())) {
                   String serverAddr = this.environment.resolvePlaceholders("${spring.cloud.nacos.config.server-addr:}");
                   if (StringUtils.isEmpty(serverAddr)) {
                       serverAddr = this.environment.resolvePlaceholders("${spring.cloud.nacos.server-addr:127.0.0.1:8848}");
                   }
                   this.setServerAddr(serverAddr);
               }
               if (StringUtils.isEmpty(this.getUsername())) {
                   this.setUsername(this.environment.resolvePlaceholders("${spring.cloud.nacos.username:}"));
               }
               if (StringUtils.isEmpty(this.getPassword())) {
                   this.setPassword(this.environment.resolvePlaceholders("${spring.cloud.nacos.password:}"));
               }
           }
       }

    5、属性信息从enviroment中获取：enviroment的属性信息来源于 config/bootstrap.properties，
       此时还没有完成环境的初始化，也就是还没开始加载应用的配置信息，config/application.properties 并没有被加载。
       所以，无法通过 application.properties 文件配置得到连接 Nacos 用户名及密码等信息，导致连接Nacos失败。
       因此会选择 bootstrap.yml 文件作为Nacos的配置文件.

    6、旧版的 spring-cloud-starter-alibaba-nacos-config 模块包括了 spring-cloud-starter-bootstrap 依赖
       新版 spring-cloud-starter-alibaba-nacos-config 模块剔除了 spring-cloud-starter-bootstrap 依赖，
       因此需要添加 spring-cloud-starter-bootstrap 依赖，才能正常使用 bootstrap.yml 文件进行配置Nacos。
       <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>