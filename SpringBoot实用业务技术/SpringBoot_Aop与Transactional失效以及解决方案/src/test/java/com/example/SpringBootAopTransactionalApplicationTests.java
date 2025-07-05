package com.example;

import com.example.proxy.ProxyService;
import com.example.transactional.service.StudentService;
import com.example.transactionalInAsync.service.StudentAsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAopTransactionalApplicationTests {

	@Autowired
	private ProxyService proxyService;

	@Test
	void contextLoads1() {
		// Aop切面逻辑没有执行
		proxyService.useSigInterface();
	}

	@Test
	void contextLoads2() {
		// Aop切面逻辑生效，执行成功
		proxyService.useAllInterface();
	}


    // 测试事务失效的情况
	@Autowired
	private StudentService studentService;

	@Test
	void contextLoads3() {
		// 使用直接对象，事务不生效
		studentService.saveSigUser();
	}

	@Test
	void contextLoads4() {
		// 使用代理对象，事务生效
		studentService.saveAllUser();
	}


	@Autowired
	private StudentAsyncService studentAsyncService;
	// 测试在事务中执行异步写操作
	@Test
	void contextLoads5() {
		// 该方法中第一个写操作报错，即回滚（轮不到异步处理中的写操作执行，事务生效）
		studentAsyncService.saveAndAsync();
	}

	@Test
	void contextLoads6() {
		// 该方法中异步处理很明显会报错，但是最终方法执行会成功（事务不生效）
		studentAsyncService.saveAndAsyncException();
	}

	@Test
	void contextLoads7() {
		// 等待异步处理执行完，再进行处理
		studentAsyncService.saveAndAsyncScheme1();
	}

	@Test
	void contextLoads8() {
		// 使用事务管理器，传递事务上下文
		studentAsyncService.saveAndAsyncScheme2();
	}

	@Test
	void contextLoads9() {
		// 使用 Spring 的事件发布机制
		studentAsyncService.saveAndAsyncScheme3();
	}

}
