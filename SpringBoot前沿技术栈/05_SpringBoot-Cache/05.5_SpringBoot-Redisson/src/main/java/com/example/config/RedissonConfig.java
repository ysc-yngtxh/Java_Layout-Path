package com.example.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private String port;

    @Value("${spring.data.redis.password}")
    private String password;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisHost + ":" + port)
                .setPassword(password);
        config.setCodec(new JsonJacksonCodec());
        // 配置分布式锁的看门狗超时时间，即锁的默认最大持有时间（单位：毫秒），其默认值为30,000 毫秒（30 秒）
        // 这里设置为 10秒，看门狗会每隔 timeout/3（即 3.33 秒）自动续期，将锁的过期时间重置为 10 秒。
        // 续期行为：
        //        T=0s   获取锁，过期时间设为10s（实际过期时间：T+10s）
        //        T=10s  看门狗检查，重置过期时间为T+10s（即实际过期时间变为20s）
        //        T=20s  看门狗检查，重置过期时间为T+10s（即实际过期时间变为30s）
        //        ...（以此类推）
        config.setLockWatchdogTimeout(10_000);
        return Redisson.create(config);
    }
}
