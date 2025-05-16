package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringRedisSerializationTests1 {

	private final Logger log = LoggerFactory.getLogger(SpringRedisSerializationTests1.class);

	// TODO 问题：在使用 RedisTemplate操作对象 使用内存数据时，可以在 Redis 服务器(使用命令提示符窗口，或者可视化工具)
	//           上可以发现，不论是中文还是英文，都出现了乱码。
	//      方法一：使用 StringRedisTemplate，这个注入的 Redis操作对象类 中的K-V部分都将会是String类型,
	//             并且解决数据序列化乱码问题。
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void test() {
		stringRedisTemplate.opsForValue().set("redisDemo3", "ysc");
		stringRedisTemplate.opsForValue().set("redisDemo4", "123456");
		log.info("内存数据1：{}", stringRedisTemplate.opsForValue().get("redisDemo3"));
		log.info("内存数据2：{}", Integer.parseInt(stringRedisTemplate.opsForValue().get("redisDemo4")));
	}


	// TODO 方法二：使用配置类的方式，在项目启动时就对 RedisTemplate操作对象 初始化设置
	//             所以这时候的 RedisTemplate<Object, Object> 就是我们设置的 RedisTemplate操作对象
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Test
	public void test2() {
		redisTemplate.opsForValue().set("redisDemo5", "小曹啊小曹！");
		log.info("内存数据：{}", redisTemplate.opsForValue().get("redisDemo5"));
	}
}
