package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 游家纨绔
 */
@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
public class BatchCursorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchCursorApplication.class, args);
    }

    /** 在有的公司里，是不允许java包下出现配置文件的(.xml)。
     *     因为，Java包下文件编译时，只会编译Java类文件，但我们在pom.xml文件中的<build>标签下指定了资源编译。所以才会编译通过
     *
     *  但是，遇到不允许我们在java包下出现配置文件时候，我们可以将xml文件放到resources创建的mapper包下
     *    然后在我们的application配置文件中指定MyBatis映射文件的路径
     *    mybatis.mapper-locations=classpath:mapper/*.xml
     */

}
