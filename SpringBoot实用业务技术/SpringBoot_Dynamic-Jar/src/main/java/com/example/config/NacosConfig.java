package com.example.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-29 00:28
 * @apiNote TODO
 */
public class NacosConfig {

    @Value("${spring.cloud.nacos.server-addr}")
    private String serverAddr;

    public ConfigService configService() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", namespace);
        return NacosFactory.createConfigService(properties);
    }
}