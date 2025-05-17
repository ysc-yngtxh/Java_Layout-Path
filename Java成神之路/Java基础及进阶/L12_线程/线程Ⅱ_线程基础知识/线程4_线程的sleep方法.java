package L12_线程.线程Ⅱ_线程基础知识;

/* 关于线程的sleep方法
 *   static void sleep(long millis)
 *   1、静态方法：Thread.sleep(1000);
 *   2、参数是毫秒
 *   3、作用：让当前线程进入休眠，进入 "阻塞状态"，放弃占有CPU时间片，让给其他线程使用
 *           这行代码出现在A线程中，A线程就会进入休眠
 *           这行代码出现在B线程中，B线程就会进入休眠
 *   4、Thread.sleep()方法，可以做到这种效果：
 *          间隔特定的时间，去执行一段特定的代码，每隔多久执行一次
 */
class MyThread1 extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("支线程" + " --> " + i);
		}
	}

}

public class 线程4_线程的sleep方法 {

	public static void main(String[] args) {
		// 让当前线程进入休眠，休眠五秒
		// 当前线程是主线程
		try {
			Thread.sleep(1000 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 5秒之后执行的代码
		System.out.println("Hello World!");

		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " --> " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		System.out.println("==========================================================================================");

		// 关于Thread.sleep()方法的面试题：

		Thread t = new MyThread1();  // 多态
		t.setName("t");
		t.start();
		try {
			// 问题：t.sleep(1000 * 5);会让线程t进入休眠状态吗？
			t.sleep(1000 * 5);
			// 不会。sleep() 方法是一个静态方法，应该通过Thread类来调用，而不是通过Thread对象来调用 -- Thread.sleep(1000 * 5);
			// 因此在执行的 t.sleep(1000 * 5); 还是会转换成：Thread.sleep(1000*5);
			// 使用 sleep() 方法休眠的始终是当前线程，而不会是指定的线程。所以睡眠的不是t，而是调用 sleep() 方法时所在类的main线程。
			// 如果想要指定线程睡眠，sleep最好使用在run方法的内部，因为写在 run() 方法内部可以让该线程休眠
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		System.out.println("小游想小曹了");
	}

}
