package com.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-43 22:30
 * @apiNote TODO
 */
@Data
@RefreshScope
@Configuration
public class NacosConfig {

    // 表示从配置文件读取属性 nacos.core.size 值：如果不存在，即使用默认值1；如果存在，值则被覆盖
    @Value("${nacos.core.size:1}")
    public String coreSize;

    // 表示从配置文件读取属性 nacos.max.size 值：如果不存在，即使用默认值2；如果存在，值则被覆盖
    @Value(value = "${nacos.max.size:2}")
    public String maxSize;

    // 还有一种@NacosValue注解，不推荐在SpringCloud项目中使用
    // 这里我们本地配置有其nacos.config.username属性，但是Nacos中同样包含其属性，最终打印结果为：Nacos中属性值
    @Value(value = "${nacos.config.username}")
    public String username;

    @Value(value = "${nacos.server.port}")
    public String port;

    @Value(value = "${nacos.properties.file}")
    public String file;
}
