package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SentinelPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelPushApplication.class, args);
    }

    @RequestMapping("/test")
    public String test() {
        return "你好哦！！！";
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

    // 实现Spring Cloud Alibaba Sentinel 整合 nacos 进行规则持久化步骤：
    //     1、引入 sentinel数据源集成nacos 依赖
    //     2、在 application.yml 文件中配置 sentinel数据源中nacos 的地址
    //     3、在Nacos控制台页面中创建一个数据集，数据集的格式为json格式，数据集的内容为规则配置的json格式：
    //           [
    //             {
    //               "clusterConfig": {
    //                 "acquireRefuseStrategy": 0,
    //                 "clientOfflineTime": 2000,
    //                 "fallbackToLocalWhenFail": true,
    //                 "resourceTimeout": 2000,
    //                 "resourceTimeoutStrategy": 0,
    //                 "sampleCount": 10,
    //                 "strategy": 0,
    //                 "thresholdType": 0,
    //                 "windowIntervalMs": 1000
    //               },
    //               "clusterMode": false,
    //               "controlBehavior": 0,
    //               "count": 3.0,
    //               "grade": 1,
    //               "limitApp": "default",
    //               "maxQueueingTimeMs": 500,
    //               "resource": "/test",
    //               "strategy": 0,
    //               "warmUpPeriodSec": 10
    //             }
    //           ]
    //     4、配置完成后，点击发布按钮，发布到nacos配置中心
    //     5、启动项目后，可在Sentinel控制台页面的流控规则中查看到刚刚发布的规则
    //     6、在Nacos控制台页面中修改规则（如："count": 5.0,），修改完成后点击发布按钮
    //     7、在Sentinel控制台页面中查看流控规则，发现规则已经被修改为5.0
}
