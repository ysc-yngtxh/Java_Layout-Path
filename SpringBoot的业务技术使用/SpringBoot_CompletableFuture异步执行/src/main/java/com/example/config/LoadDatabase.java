package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    // TODO
    //  问题：SpringBoot在启动后，首次调用接口的时候是比较慢的，造成这种结果的原因是 DispatcherServlet 没有预热的原因。
    //       在SpringBoot启动的时候 DispatcherServlet 并没有进行初始化，而在第一次接口请求的时候，才会进行初始化操作
    //  解决方案：最靠谱的方案就是对接口做预加载，就是SpringBoot启动后，先调用一次接口，把 DispatcherServlet 预加载进去就可以了
    @Bean
    CommandLineRunner initController(RestTemplate restTemplate) {
        return args -> {
            log.info("REQUEST " + restTemplate.execute("http://127.0.0.1:8081/load", HttpMethod.GET, null, null));
        };
    }
}

