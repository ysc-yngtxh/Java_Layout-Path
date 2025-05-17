package P16_JUC并发.JUC并发Ⅰ_关于lock锁.Lock8锁;

import java.util.concurrent.TimeUnit;

/**
 * 情况八：类中两方法，一方法被static synchronized修饰，一个方法被synchronized修饰。
 *       换句话说一个是静态同步方法，一个是普通同步方法
 *       这个时候在main方法中创建两对象，并分别去执行A和B线程。先打印出来的是 "发短信" 还是 "打电话" ？
 */
public class h八 {

	public static void main(String[] args) {
		Test8 test1 = new Test8();
		Test8 test2 = new Test8();
		// 使用Test8的静态方法 Send()
		new Thread(() -> test1.Send(), "A").start();

		try {
			TimeUnit.SECONDS.sleep(1);  // 睡眠，不再使用Thread.sleep()
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 使用Test8对象正常方法 Call()
		new Thread(() -> test2.Call(), "B").start();
	}

}

class Test8 {

	public static synchronized void Send() {
		try {
			TimeUnit.SECONDS.sleep(2);  // 睡眠，不再使用Thread.sleep()
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("发短信");
	}

	public synchronized void Call() {
		System.out.println("打电话");
	}

}

/**
 * 先打印的是"打电话"。一个是静态同步方法，锁是类锁。一个是普通同步方法，锁是普通锁。
 * 不是同一把锁的情况下，还是不同的对象，所以先执行的是"打电话"
 */
