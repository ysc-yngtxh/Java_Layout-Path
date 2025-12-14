package com.example;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootInheritableThreadLocalApplicationTests {

	public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
	public static final ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
	private static final TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();
	private static final ExecutorService executorService = Executors.newFixedThreadPool(1);

	// ThreadLocal（线程隔离，主线程和子线程之间是隔离的）
	// 缺点：子线程无法获取父线程的值
	@Test
	public void setThreadLocal() {
		threadLocal.set("主线程1。。。");
		Thread thread = new Thread(() -> {
			System.out.println("子线程1：" + Thread.currentThread().getName() + " -- " + threadLocal.get());
		});
		thread.start();
	}

	// InheritableThreadLocal（是从父线程里面深度copy过来的，线程共享的，子线程可以获取父线程的值）
	@Test
	public void setInheritableThreadLocal() {
		inheritableThreadLocal.set("主线程1。。。");
		Thread thread = new Thread(() -> {
			System.out.println("子线程1：" + Thread.currentThread().getName() + " -- " + inheritableThreadLocal.get());
		});
		thread.start();
	}


	// InheritableThreadLocal
	// 缺点：线程池中的线程是复用的，当父线程向线程池提交任务时，子线程（线程池中的线程）可能已被多次复用。
	//      此时，子线程的 InheritableThreadLocal 值可能残留前一次任务的数据，导致当前任务读取到错误的值。
	@Test
	public void setExecutorService() throws InterruptedException {
		inheritableThreadLocal.set("主线程1。。。");
		System.out.println("线程1：" + inheritableThreadLocal.get());
		executorService.submit(() -> System.out.println("子线程：" + Thread.currentThread().getName() + " -- " + inheritableThreadLocal.get()));
		Thread.sleep(1000);
		inheritableThreadLocal.set("主线程2。。。");
		System.out.println("线程2：" + inheritableThreadLocal.get());
		executorService.submit(() -> System.out.println("子线程：" + Thread.currentThread().getName() + " -- " + inheritableThreadLocal.get()));
	}


	// InheritableThreadLocal
	// ⚠️：继承行为仅发生在 线程创建时（即子线程从父线程复制值），之后父线程对值的修改不会影响到已经创建的子线程。
	@Test
	public void setExecutorService2() throws InterruptedException {
		inheritableThreadLocal.set("主线程1。。。");
		System.out.println("线程1：" + inheritableThreadLocal.get());
		executorService.submit(() -> {
			try {
				System.out.println("子线程：" + Thread.currentThread().getName() + " -- " + inheritableThreadLocal.get());
			} finally {
				inheritableThreadLocal.remove();
			}
		});
		Thread.sleep(1000);
		inheritableThreadLocal.set("主线程2。。。");
		System.out.println("线程2：" + inheritableThreadLocal.get());
		executorService.submit(() -> {
			try {
				System.out.println("子线程：" + Thread.currentThread().getName() + " -- " + inheritableThreadLocal.get());
			} finally {
				inheritableThreadLocal.remove();
			}
		});
	}


	// TODO ThreadLocal 用于存储线程本地变量，但子线程无法继承父线程的变量。
	//      InheritableThreadLocal 支持父子线程间传递，但无法应对线程复用（如线程池），导致上下文丢失或污染。

	// TransmittableThreadLocal（TTL）专为解决线程池场景下的上下文传递问题设计，支持线程池、定时任务等复杂场景。
	// TTL 继承自 InheritableThreadLocal，天然支持父子线程传递，并通过增强机制支持线程池环境。
	// 原理：在任务提交时（如通过 TtlRunnable/TtlCallable 包装任务），TTL 会捕获当前线程的所有 TransmittableThreadLocal 变量。
	//      任务执行时，将这些变量临时注入到执行线程中，执行完毕后自动恢复原有状态，避免污染。
	@Test
	public void setTransmittableThreadLocal() throws InterruptedException {
		transmittableThreadLocal.set("主线程1。。。");
		System.out.println("线程1：" + transmittableThreadLocal.get());
		executorService.submit(TtlRunnable.get(() -> System.out.println("子线程：" + Thread.currentThread().getName() + " -- " + transmittableThreadLocal.get())));
		Thread.sleep(1000);
		transmittableThreadLocal.set("主线程2。。。");
		System.out.println("线程2：" + transmittableThreadLocal.get());
		executorService.submit(TtlRunnable.get(() -> System.out.println("子线程：" + Thread.currentThread().getName() + " -- " + transmittableThreadLocal.get())));
	}
}
