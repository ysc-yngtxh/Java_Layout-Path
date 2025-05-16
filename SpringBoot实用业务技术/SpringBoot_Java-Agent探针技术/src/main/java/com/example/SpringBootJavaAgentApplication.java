package com.example;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJavaAgentApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SpringBootJavaAgentApplication.class, args);

		// 获取代表正在运行的Java虚拟机的名称
		String name = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println(name);
		// 获取 pid
		String pid = name.split("@")[0];
		System.out.println("Pid:" + pid);

		while (true) {
			TimeUnit.MILLISECONDS.sleep(2000);
			System.out.println("Hello World");
		}
	}

	/* 一、Java Agent的概念
	 *	  Java Agent是Java语言提供的一种字节码增强技术，能够在Java应用运行时对字节码进行修改。
	 *
	 *    Java 代理 (agent) 是在你的main方法前的一个拦截器 (interceptor)，也就是在main方法执行之前，执行agent的代码。
	 *    agent 的代码与你的main方法在同一个JVM中运行，并被同一个system classloader装载，被同一的安全策略 (security policy) 和上下文 (context) 所管理。
	 *
	 *    Java Agent其实也是一个Jar包，只是启动方式和普通Jar包有所不同，对于普通的Jar包，通过指定类的main函数进行启动，
	 *    但是Java Agent并不能单独启动，必须依附在一个Java应用程序运行，有点像寄生虫的感觉。
	 *
	 * 二、Java Agent提供了一种在加载字节码时对字节码进行修改的能力，有两种执行方式：
	 *     1、在应用运行之前，通过 premain() 方法来实现「在应用启动时侵入并代理应用」，
	 *        这种方式是利用Instrumentation接口实现的；
	 *     2、在应用运行之后，通过Attach API和agentmain()方法来实现「在应用启动后的某一个运行阶段中侵入并代理应用」，
	 *        这种方式是利用Attach接口实现的。
	 *
	 * 三、基于 Instrumentation 接口和premain()方法实现Java Agent
	 *     通过JVM参数「-javaagent:*.jar」启动应用。
	 *     应用在启动时，会优先加载Java Agent，并执行premain()方法，这时部分的类都还没有被加载。
	 *     此时，可以实现对新加载的类进行字节码修改，但如果premain()方法执行失败或者抛出异常，则JVM会被终止，这太蛋疼了。
	 *
	 * 四、基于 Attach 接口和agentmain()方法实现Java Agent
	 *     通过JVM参数「-javaagent:*.jar」启动应用之后，可以通过Java JVM 的Attach接口加载Java Agent。
	 *     Attach 接口其实是JVM进程之间的沟通桥梁，底层通过Socket进行通信，JVM A可以发送指令给JVM B，JVM B在收到指令之后执行相应的逻辑。
	 *     比如，在命令行中经常使用的Jstack、Jcmd、Jps等，都是基于这种机制实现的。
	 *
	 * 五、Java Agent的使用场景
	 *     1、调试代码（通过IDEA调试代码就是基于Java Agent技术实现的）
	 *     2、热部署（热部署能够帮助开发者减少频繁编译重启应用的次数，节省碎片化的时间）
	 *     3、在线诊断（阿里开源的线上诊断工具 arthas）
	 *     4、性能分析
	 *     5、APM应用性能监控（Application Performance Management ，APM）
	 */
}
