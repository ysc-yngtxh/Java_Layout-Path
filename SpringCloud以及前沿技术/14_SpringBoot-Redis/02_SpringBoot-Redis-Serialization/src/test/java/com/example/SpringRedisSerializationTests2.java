package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-10 18:50
 * @apiNote TODO
 */
@SpringBootTest
public class SpringRedisSerializationTests2 {
    private final Logger log = LoggerFactory.getLogger(SpringRedisSerializationTests2.class);

    // TODO 问题：在我们使用 RedisTemplate 进行操作内存数据时，我们在 Redis 服务器(使用命令提示符窗口，或者可视化工具) 上可以发现，
    //           不论是中文还是英文，都出现了乱码。
    //      方法二：使用配置类的方式，在项目启动时就对 RedisTemplate操作对象 初始化设置
    //             所以这时候的 RedisTemplate<Object, Object> 就是我们设置的 RedisTemplate操作对象
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("redisDemo5", "ysc");
        log.info("内存数据：{}", redisTemplate.opsForValue().get("redisDemo5"));
    }
}
