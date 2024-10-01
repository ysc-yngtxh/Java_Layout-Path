package com.example.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("context.properties")
public class ContextProperties {

    /**
     * 标题
     **/
    private String title = "";

    /**
     * 描述
     **/
    private String description = "";

    /**
     * 版本
     **/
    private String version = "v1";

    /**
     * 作者
     **/
    private String author = "游家纨绔";

    /**
     * 扫描的路径
     **/
    private List<String> basePaths = List.of(new String[]{"com.example.service", "com.example.controller"});
}