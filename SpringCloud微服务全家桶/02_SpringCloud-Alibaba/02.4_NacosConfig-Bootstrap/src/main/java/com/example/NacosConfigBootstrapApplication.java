package com.example;

import com.example.config.NacosConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacosConfigBootstrapApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NacosConfigBootstrapApplication.class, args);
        NacosConfig nacosConfig = context.getBean("nacosConfig", NacosConfig.class);
        System.err.println("nacos.core.size: " + nacosConfig.getCoreSize() + "\n"
                         + "nacos.max.size: " + nacosConfig.getMaxSize() + "\n"
                         + "name.config.username: " + nacosConfig.getUsername() + "\n"
                         + "name.server.port: " + nacosConfig.getPort() + "\n"
                         + "name.properties.file: " + nacosConfig.getFile());
    }
}
