package com.example.application.applicationRunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-07 18:00
 * @apiNote TODO
 */
@Component
public class ConcurrentApplicationRunner implements ApplicationRunner {

	/* 这个接口也只有一个方法：run(ApplicationArguments args)，触发时机为整个项目启动完毕后，自动执行。
	 * 使用场景：用户扩展此接口，进行启动项目之后一些业务的预处理。
	 */
	@Override
	public void run(ApplicationArguments args) {
		System.out.println("项目启动完毕后，自动执行ApplicationRunner");
	}
}
