package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class,args);
    }
}
/**
 * 启动Config Client服务，你会发现你的配置文件中没有书写port端口，但是系统还是将你配置仓库中相应的port端口下
 * 怎么样？神不神奇？意不意外？啊哈哈哈~~~~
 *
 * 但是有一个问题，虽然我们可以将所有的服务配置文件都放在Gitee仓库中进行统一管理，但是每一次修改都需要进行重启服务
 * 假设有一万台服务器进行修改配置，我们手动去重启，可能吗？现实吗？
 * 所以这个时候就需要用到一个叫热刷新的功能组件
 *
 * 完成热刷新配置后重启，去输入 http://localhost:9001/actuator 查看自己热刷新是否启动成功
 *     info 和 health 自带的，如果有我们配置的 refresh 就表示热刷新配置成功
 * 路由Controller的api接口：http://localhost:9000/test 可以发现能获取远程仓库里配置文件信息
 * 并且修改远程仓库里的i值或者str值后，使用post方式 http://localhost:9001/actuator/refresh 刷新后
 * 路由Controller的api接口：http://localhost:9000/test 中的数据发生了改变，并且没有重启服务，完美！！！
 */