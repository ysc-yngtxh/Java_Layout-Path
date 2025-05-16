package com.example;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.MapStruct映射实体类.MapStructExample;
import com.example.vo.ModelView;
import com.example.vo.Models;
import com.example.vo.User;
import com.example.vo.Users;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringBootJavaBasicApplicationTests {
	public static final Logger log = LoggerFactory.getLogger(SpringBootJavaBasicApplicationTests.class);

	static {
		// 默认情况下，SLF4J 的日志级别是 INFO。强制设置日志级别为 DEBUG（适用于 Logback）
		((ch.qos.logback.classic.Logger) log).setLevel(ch.qos.logback.classic.Level.DEBUG);
	}

	@Autowired
	private MapStructExample mapStructExample;

	// 测试 Mapstruct 功能
	@Test
	public void mapStructs() {
		// 方式一：通过Mapper实例对象的方式
		User user = User.builder().id(123L).name("小娘皮曹家千金").email("google@163.com").date(new Date()).build();
		Users users = MapStructExample.mapStr.toUsers(user);
		log.error(users.toString());

		User user1 = User.builder().id(123L).name("小娘皮曹家千金").email("google@163.com").build();
		ModelView modelView = ModelView.builder().address("珞狮路狮城名居").build();
		Users users1 = MapStructExample.mapStr.toUsers1(user1, modelView);
		log.error(users1.toString());

		User user2 = User.builder()
				.id(123L)
				.name("小娘皮曹家千金")
				.email("google@163.com")
				.models(new Models(new ModelView("湖北武汉红花三区"), null))
				.build();
		Users users2 = MapStructExample.mapStr.toUsers2(user2);
		log.error(users2.toString());

		// 方式二：这里通过注入的依赖，调用方法
		Users users3 = mapStructExample.toUsers(user2);
		log.error(users3.toString());
	}
}
