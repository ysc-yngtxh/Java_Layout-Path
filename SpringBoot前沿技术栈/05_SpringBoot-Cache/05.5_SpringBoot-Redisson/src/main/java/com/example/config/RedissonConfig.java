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


	// TODO
	//  Redisson看门狗机制
	//      Redisson的看门狗机制主要是用来避免Redis中的锁在超时后业务逻辑还未执行完毕，锁却被自动释放的情况。
	//      它是通过定时任务定期刷新锁的过期时间来实现自动续期，锁的默认最大持有时间（单位：毫秒），其默认值为30,000毫秒（30秒）
	//  主要原理：
	//      定时刷新：如果当前分布式锁未设置过期时间，Redisson基于Netty时间轮启动一个定时任务，
	//              定期向Redis发送命令更新锁的过期时间，默认每10s发送一次请求，每次续期30s。
	//      释放锁： 当客户端主动释放锁时，Redisson会取消看门狗刷新操作。
	//              如果客户端宕机了，定时任务自然也就无法执行了，此时等超时时间到了，锁也会自动释放。
	//  补充：
	//      看门狗续期的时间间隔为锁的最大持有时间的1/3（即10秒），也就是每隔10秒自动续期一次。
	//      看门狗续期的时间间隔可以通过 setLockWatchdogTimeout(long timeout) 方法进行设置。
	//      续期行为：
	//             T=0s   获取锁，过期时间设为10s（实际过期时间：T+10s）
	//             T=10s  看门狗检查，重置过期时间为T+10s（即实际过期时间变为20s）
	//             T=20s  看门狗检查，重置过期时间为T+10s（即实际过期时间变为30s）
	//             ...（以此类推）


	// 定义 RedissonClient Bean，方法一
	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		config.useSingleServer()
		      .setAddress("redis://" + redisHost + ":" + port);
		config.setCodec(new JsonJacksonCodec());
		// 这里设置为 10秒，看门狗会每隔 timeout/3（即 3.33 秒）自动续期，将锁的过期时间重置为 10 秒。
		config.setLockWatchdogTimeout(10_000);
		System.out.println("Redisson 已启动");
		return Redisson.create(config);
	}


	// 定义 RedissonClient Bean，方法二
	// @Autowired
	// private RedissonProperties redissonProperties;
	//
	// @Bean
	// public RedissonClient redissonClient2() throws IOException {
	// 	Config config = Config.fromYAML(redissonProperties.getConfig());
	// 	config.setCodec(new JsonJacksonCodec());
	// 	// 这里设置为 10秒，看门狗会每隔 timeout/3（即 3.33 秒）自动续期，将锁的过期时间重置为 10 秒。
	// 	config.setLockWatchdogTimeout(10_000);
	// 	System.out.println("Redisson 已启动");
	// 	return Redisson.create(config);
	// }
}
