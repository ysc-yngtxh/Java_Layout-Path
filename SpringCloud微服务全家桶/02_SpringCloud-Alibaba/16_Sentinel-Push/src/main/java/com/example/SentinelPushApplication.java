package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinelPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelPushApplication.class, args);
    }

    // Push模式:
    //   规则中心统一推送，客户端通过注册监听器的方式时刻监听变化，比如使用 Nacos、Zookeeper 等配置中心。
    //   这种方式有更好的实时性和一致性保证。生产环境下一般采用 push 模式的数据源。
    //   优点：规则持久化；一致性；快速
    //   缺点：需要引入第三方依赖
    //          <dependency>
    //            <groupId>com.alibaba.csp</groupId>
    //            <artifactId>sentinel-datasource-nacos</artifactId>
    //          </dependency>
    //        无法实现Sentinel Dashboard与Nacos的双向一致

}
