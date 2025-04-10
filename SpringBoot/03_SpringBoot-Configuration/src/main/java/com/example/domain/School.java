package com.example.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "school")  // 这里是将配置文件中的key值前缀写出来
public class School {

    private String name;  // 所以这里就可以不用写前缀的
    private String webSit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebSit() {
        return webSit;
    }

    public void setWebSit(String webSit) {
        this.webSit = webSit;
    }
}
