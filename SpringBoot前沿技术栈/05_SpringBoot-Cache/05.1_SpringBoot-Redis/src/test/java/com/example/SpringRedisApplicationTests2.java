package com.example;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-10 17:28
 * @apiNote TODO
 */
@SpringBootTest
public class SpringRedisApplicationTests2 {
	private final Logger log = LoggerFactory.getLogger(SpringRedisApplicationTests2.class);

	// TODO
	//  问题：Redis操作对象默认使用 RedisTemplate<Object, Object>，在注入时想使用的操作对象的泛型key部分为String类型
	//       即 RedisTemplate<String, Object>，但是实际这么操作启动程序会报错 -- 空指针。
	//  分析：@Autowired注解 是根据byType(类型)注入bean的：RedisTemplate<Object, Object>，
	//       而想使用的是 RedisTemplate<String, Object>，根据byType(类型)在Spring容器中没有找到，所以就会报错 -- 空指针；
	//       因此使用可以通过 @Resource注解 根据名称在Spring容器中寻找bean并注入，所以没有问题.
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void test() {
		redisTemplate.opsForValue().set("redisDemo2", "ysc");
		log.info("内存数据：{}", redisTemplate.opsForValue().get("redisDemo2"));
	}
}
