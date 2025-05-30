package com.example;

import com.example.context.Scope;
import com.example.context.ScopeKey;
import com.example.pool.ScopeThreadPoolExecutor;
import java.util.concurrent.ExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringBootScopeApplicationTests {

	// TODO 1、在工作中，我们经常需要维护一些上下文，这样可以避免在多个方法之间调用过程中传入过多的参数，使其代码看起来不够优雅。
	//         需要查询/修改一些数据的时候，直接在当前上下文中操作就行了。
	//      2、举个具体点的例子：当web服务器收到一个请求时，需要解析当前登录态的用户，在后续的业务执行流程中都需要这个用户相关信息。
	//                        例如：个人实名信息、用户虚拟信息【等级，状态...】等等十几个实体类信息
	//                        执行每个业务方法的时候都通过方法传参的形式传递进去，这个方式就不太优雅了。
	//      3、可行的方案：通过ThreadLocal实现请求线程的上下文，只要是同一线程的执行过程，不同方法间不通过传参方式传递上下文状态变量，
	//                   而是直接操作ThreadLocal对象实现状态数据的读写。当存在多个上下文状态的话，则需要定义多个ThreadLocal
	//      4、但是当遇到业务流程中使用线程池的情况下，从Tomcat传递这些ThreadLocal到线程池中的线程里就变的比较麻烦了。
	//         基于以上考虑，下面介绍一种基于ThreadLocal实现的上下文管理组件Scope
	@Test
	public void testScopeKey() {
		ScopeKey<String> localThreadName = new ScopeKey<>();

		// 不同线程中执行时，开启独占的 Scope。相当于在每一个线程中开启上下文，并新增相同的数据状态
		Runnable r = () -> {
			// 开启 Scope
			Scope.beginScope();
			try {
				localThreadName.set("initVal");
				log.info("当前线程：{}，上下文中的变量副本为: {}"
					   , Thread.currentThread().getName()
					   , localThreadName.get()
				);
			} finally {
				// 关闭 Scope
				Scope.endScope();
			}
		};

		// 每个线程都有独立的Scope，今儿获取各自的数据状态
		new Thread(r, "thread-1").start();
		new Thread(r, "thread-2").start();
	}

	// 同上述示例效果一样，少一行set方法代码
	@Test
	public void testWithInitial() {
		ScopeKey<String> initValue = ScopeKey.withInitial(() -> "initVal");

		Runnable r = () -> {
			Scope.beginScope();
			try {
				log.info("当前线程：{}，上下文中的变量副本为: {}"
					   , Thread.currentThread().getName()
					   , initValue.get()
				);
			} finally {
				Scope.endScope();
			}
		};
		// 每个线程都有独立的Scope，今儿获取各自的数据状态
		new Thread(r, "thread-1").start();
		new Thread(r, "thread-2").start();
	}

	// 上面的测试用例中在代码中还需要手动开启和关闭Scope，不太优雅。这里进行了一些改造
	@Test
	public void testRunWithNewScope() {
		ScopeKey<String> localThreadName = ScopeKey.withInitial(() -> "initVal");

		Runnable r = () -> log.info("当前线程：{}，上下文中的变量副本为: {}"
				                  , Thread.currentThread().getName()
				                  , localThreadName.get()
		);

		// 不同线程中执行时，开启独占的 Scope，然后在各自的线程中获取Scope的数据状态
		new Thread(() -> Scope.OpenCloseScope(r), "thread-1").start();
		new Thread(() -> Scope.OpenCloseScope(r), "thread-2").start();
	}

	// ⚠️：不同于上述三个示例，这个测试用例中的每个线程共享同一个Scope
	@Test
	public void testRunWithNewScope1() {
		ScopeKey<String> localThreadName = new ScopeKey<>();
		// 开启 Scope
		Scope scope = Scope.beginScope();
		localThreadName.set("initVal");

		Runnable r = () -> {
			// 注意：不能在线程中修改 Scope。由于Scope是共享数据，所以会有线程安全问题
			log.info("当前线程：{}，上下文中的变量副本为: {}"
				   , Thread.currentThread().getName()
				   , localThreadName.get()
			);
		};

		// 不同线程中执行时，获取的是同一个主线程的Scope，并不是独占！！！
		new Thread(() -> Scope.supplyWithExistScope(scope, r), "thread-1").start();
		new Thread(() -> Scope.supplyWithExistScope(scope, r), "thread-2").start();

		log.info("当前线程：{}，上下文中的变量副本为: {}"
			   , Thread.currentThread().getName()
			   , localThreadName.get()
		);
	}


	// TODO 1、现在需要实现这样一个功能，在一个线程执行过程中开启了一个Scope，随后使用线程池执行任务，
	//         要求在线程池中也能获取当前Scope中的状态数据。
	//      2、典型的使用场景是：服务收到一个用户请求，通过Scope将登陆态数据存到当前线程的上下文中，
	//                        随后使用线程池执行一些耗时的操作，希望线程池中的线程也能拿到Scope中的登陆态数据。
	//      3、由于线程池中的线程和请求线程不是一个线程，按照目前的实现，线程池中的线程是无法拿到请求线程上下文中的数据的。
	//      4、解决方法：在提交 runnable 时，将当前的 Scope引用 存到 runnable对象中，
	//                 当获得线程执行时，将 Scope 替换到执行线程中，执行完成后，再恢复现场。
	@Test
	public void testScopeThreadPoolExecutor() {
		ScopeKey<String> localVariable = new ScopeKey<>();
		Scope.beginScope();

		try {
			localVariable.set("value out of thread pool");
			Runnable r = () -> log.info("localVariable in thread pool: {}", localVariable.get());

			// 使用线程池执行，能获取到外部Scope中的数据。实际上也是获取的主线程中的Scope，进而获取数据状态
			ExecutorService executor = ScopeThreadPoolExecutor.newFixedThreadPool(10);
			executor.execute(r);
			executor.submit(r);
		} finally {
			Scope.endScope();
		}
	}
}
