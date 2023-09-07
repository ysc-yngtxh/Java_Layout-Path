package com.example.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-16 13:09
 * @apiNote TODO 忽略路径配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class MyAuthorizationProperties {

    private Integer saveLoginTime;

    private Integer tokenExpireTime;

    private Integer loginTimeLimit;

    private Integer loginAfterTime;

    private List<String> ignoreUrls;
}
