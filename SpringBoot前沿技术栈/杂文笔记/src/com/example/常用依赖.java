package com.example;

public class 常用依赖 {
}
/**
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-web</artifactId>
 *         </dependency>
 *
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-devtools</artifactId>
 *             <scope>runtime</scope>
 *             <optional>true</optional>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.projectlombok</groupId>
 *             <artifactId>lombok</artifactId>
 *             <optional>true</optional>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-test</artifactId>
 *             <scope>test</scope>
 *         </dependency>
 *
 *         <dependency>
 *             <groupId>mysql</groupId>
 *             <artifactId>mysql-connector-java</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>druid</artifactId>
 *             <version>1.1.12</version>
 *         </dependency>
 *
 *         <!--RabbitMq依赖-->
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-amqp</artifactId>
 *         </dependency>
 *
 *         <!--Spring Retry 重试机制依赖-->
 *         <dependency>
 *             <groupId>org.springframework.retry</groupId>
 *             <artifactId>spring-retry</artifactId>
 *         </dependency>
 *         <!--配合重试机制-->
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-aop</artifactId>
 *         </dependency>
 *
 *         <!--springboot集成redis的起步依赖-->
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-data-redis</artifactId>
 *         </dependency>
 *
 *         <!--主要用于StringUtils、CollectionUtils方法-->
 *         <dependency>
 *             <groupId>org.apache.commons</groupId>
 *             <artifactId>commons-lang3</artifactId>
 *         </dependency>
 *         <!--转JSON格式-->
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>fastjson</artifactId>
 *             <version>1.2.79</version>
 *         </dependency>
 *
 *         <!--Servlet中用于转为json的该工具-->
 *         <dependency>
 *             <groupId>com.fasterxml.jackson.core</groupId>
 *             <artifactId>jackson-core</artifactId>
 *             <version>2.13.2</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.fasterxml.jackson.core</groupId>
 *             <artifactId>jackson-annotations</artifactId>
 *             <version>2.13.2</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.fasterxml.jackson.core</groupId>
 *             <artifactId>jackson-databind</artifactId>
 *             <version>2.13.2</version>
 *         </dependency>
 *
 *         <!--jwt依赖包-->
 *         <dependency>
 *             <groupId>io.jsonwebtoken</groupId>
 *             <artifactId>jjwt</artifactId>
 *             <version>0.9.1</version>
 *         </dependency>
 *
 *         <!--如果jdk超过1.8版本，就需要添加以下依赖避免jwt依赖失效-->
 *         <dependency>
 *             <groupId>javax.xml.bind</groupId>
 *             <artifactId>jaxb-api</artifactId>
 *             <version>2.4.0-b180830.0359</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.sun.xml.bind</groupId>
 *             <artifactId>jaxb-impl</artifactId>
 *             <version>3.0.1</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.sun.xml.bind</groupId>
 *             <artifactId>jaxb-core</artifactId>
 *             <version>3.0.1</version>
 *         </dependency>
 *
 *         <dependency>
 *             <groupId>org.projectlombok</groupId>
 *             <artifactId>lombok</artifactId>
 *         </dependency>
 *
 *         <!--通用mapper-->
 * 		   <dependency>
 * 		   	   <groupId>tk.mybatis</groupId>
 * 		   	   <artifactId>mapper-spring-boot-starter</artifactId>
 * 		   	   <version>2.1.5</version>
 * 		   	   <!--我们使用通用mapper后就可以删掉数据库连接池和mybatis依赖，因为我们可以在Maven中看到有连接池-->
 * 		   </dependency>
 *
 * 		   <!--springboot内嵌tomcat jsp解析依赖-->
 * 		   <dependency>
 *             <groupId></groupId>
 *             <artifactId>tomcat-embed-jasper</artifactId>
 *         </dependency>
 *
 *         <!--mybatis分页依赖-->
 *   	   <dependency>
 *             <groupId></groupId>
 *             <artifactId>pagehelper-sprinigg-boot-starter</artifactId>
 *         </dependency>
 */
