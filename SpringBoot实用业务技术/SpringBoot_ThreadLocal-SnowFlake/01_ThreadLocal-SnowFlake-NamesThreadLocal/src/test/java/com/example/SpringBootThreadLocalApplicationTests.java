package com.example;

import com.example.vo.User;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.NamedThreadLocal;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class SpringBootThreadLocalApplicationTests {
	private final Logger log = LoggerFactory.getLogger(SpringBootThreadLocalApplicationTests.class);

	private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();

	private static void setAndPrintData(User user) {
		THREAD_LOCAL.set(user);
		System.out.println("set数据，线程名：" + Thread.currentThread().getName());
	}

	private static void getAndPrintData() {
		// 拿到当前线程绑定的一个变量，然后做逻辑（本处只打印）
		User user = THREAD_LOCAL.get();
		System.out.println("get数据，线程名：" + Thread.currentThread().getName() + "，数据为：" + user);
	}

	@Test
	void contextLoads() throws InterruptedException {
		setAndPrintData(new User());

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				// 方法入口处，设置一个变量和当前线程绑定
				setAndPrintData(new User(i, "游家纨绔" + i));
				// 调用其它方法，其它方法内部也能获取到刚放进去的变量
				getAndPrintData();
				THREAD_LOCAL.remove();
				System.out.println("======== Finish" + i + " =========");

			}
		}, "t1");
		t1.start();
		t1.join();
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				// 方法入口处，设置一个变量和当前线程绑定
				setAndPrintData(new User(i, "曹家千金" + i));
				// 调用其它方法，其它方法内部也能获取到刚放进去的变量
				getAndPrintData();
				THREAD_LOCAL.remove();
				System.out.println("======== Finish" + i + " =========");
			}
		}, "t2");
		t2.start();
		t1.join();

		TimeUnit.SECONDS.sleep(6);
		getAndPrintData();
		THREAD_LOCAL.remove();

		// 可以发现对于User共享对象，多线程并发互不影响数据。不论线程A和线程B赋值多少次，在主线程中的user始终为空。
	}


	@Test
	void contextLoads2() {
		// NamedThreadLocal（Spring 框架提供），继承自 ThreadLocal，增加了名称标识功能，便于调试和日志记录。
		NamedThreadLocal<String> transactionId = new NamedThreadLocal<>("transactionId");
		transactionId.set("1234567890");
		log.info("当前线程：{}，上下文中的变量副本为: {}"
				, Thread.currentThread().getName()
				, transactionId.get()
		);
		transactionId.remove();
	}

}
