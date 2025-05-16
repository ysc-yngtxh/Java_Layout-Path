package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootThreadLocalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThreadLocalApplication.class, args);
	}

	/**
	 * ThreadLocal和线程同步机制相比有什么优势呢？ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。
	 *     1、在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。
	 *        这时该变量是多个线程共享的，使用同步机制要求程序慎密地分析什么时候对变量进行读写，
	 *        什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大。
	 *     2、而ThreadLocal则从另一个角度来解决多线程的并发访问。
	 *        ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。
	 *        因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。
	 *        ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
	 *     3、概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。
	 *        前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。
	 *
	 * NamedThreadLocal是ThreadLocal的一个子类，且是Spring框架提供的一个工具类。
	 * 主要作用是为每个线程提供一个命名的ThreadLocal变量，方便在多线程环境中进行调试和监控。
	 *    场景一：请求上下文管理（如用户会话、权限信息）
	 *           在 Web 应用中，每个请求可能需要传递用户身份、权限等上下文信息。
	 *           可以在拦截器中使用 NamedThreadLocal 可以清晰标识存储的内容，便于日志记录和问题排查。
	 *    场景二：事务管理（事务ID传递）
	 *           在分布式事务或复杂业务链路中，需要传递事务 ID 以跟踪整个流程。
	 *           使用 NamedThreadLocal 可以明确标识事务 ID 的存储位置。
	 *    场景三：性能监控（方法耗时统计）
	 *           在性能分析时，需记录方法调用的耗时或上下文参数。使用 NamedThreadLocal 可以隔离不同监控指标的存储。
	 */
}
