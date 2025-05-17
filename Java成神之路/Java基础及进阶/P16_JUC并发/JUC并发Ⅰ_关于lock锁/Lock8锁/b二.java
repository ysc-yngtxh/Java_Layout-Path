package P16_JUC并发.JUC并发Ⅰ_关于lock锁.Lock8锁;

import java.util.concurrent.TimeUnit;

/**
 * 情况二：类中两个方法都加上了synchronized关键字，创建两对象各自进行方法调用，也就是说并不共用同一个锁。
 *        这个时候在main方法中，并分别执行A和B线程，先打印出来的是 "发短信" 还是 "打电话" ？
 */
public class b二 {

	public static void main(String[] args) {
		Test2 test1 = new Test2();
		Test2 test2 = new Test2();
		new Thread(test1::Send, "A").start();

		try {
			TimeUnit.SECONDS.sleep(1);  // 睡眠，不再使用Thread.sleep()
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(test2::Call, "B").start();
	}

}

class Test2 {

	public synchronized void Send() {
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
 * 回答：先打印的是"打电话"。因为一个对象对应一个类，这个时候锁是不一样的，所以B线程不需要排队等A线程
 */
