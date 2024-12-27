package com.example.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration
// 默认情况下，Spring Boot 会忽略那些不能绑定到 @ConfigurationProperties 类字段的属性
// 然而，当配置文件中有一个属性实际上没有绑定到 @ConfigurationProperties 类时，我们可能希望启动失败。
// 为了实现上述情况，我们仅需要将 ignoreUnknownFields 属性设置为 false (默认是 true)
@ConfigurationProperties(value = ContextProperties.PREFIX, ignoreUnknownFields = false)
public class ContextProperties {

    public static final String PREFIX = "metadata.properties";

    /**
     * 作者
     **/
    private String author = "游家纨绔";

    /**
     * 描述
     **/
    @Deprecated // 被该 @Deprecated 注解标注的[属性、方法、类]                                                                    已废弃、暂时可用
    private String description = "";

    /**
     * 版本
     **/
    private String version = "v1";

    /**
     * 性别
     */
    private SexEnum sexEnum;

    /**
     * 爱好
     */
    private String[] hobbies;

    /**
     * 规格内容
     */
    private Map<String, Object> contexts;

    /**
     * 扫描的路径
     **/
    private List<String> basePaths = Arrays.asList("com.example.service", "com.example.controller");

    /**
     * 学校
     **/
    // 配置类的数据结构比较复杂时，比如说一层嵌套一层，或者有List，Map这种结构的，需要使用@NestedConfigurationProperty注解完成配置。
    @NestedConfigurationProperty
    private School school;


    enum SexEnum {
        MAN, WOMAN
    }

    @Data
    public static class School {

        private String name;

        private String address;
    }
}