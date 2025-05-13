package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinelPullApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelPullApplication.class, args);
    }

    // Pull模式
    //     客户端主动向本地文件数据源定时轮询文件的变更，读取规则。
    //     Sentinel控制台通过 API 将规则推送至客户端并更新到内存中，接着注册的写数据源会将新的规则保存到本地文件数据源中
    //     这样我们既可以在应用本地直接修改文件来更新规则，也可以通过 Sentinel 控制台推送规则。
    // 优点：实现方法简单，不引入新的依赖；规则持久化；
    // 缺点：不保证一致性；实时性不保证，拉取过于频繁也可能会有性能问题。
}
