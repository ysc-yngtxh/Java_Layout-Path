package P16_JUC并发.JUC并发Ⅲ_常用辅助类;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch辅助类 {
    /**
     * CountDownLatch:允许一个或多个线程等待直到在其它线程中执行的一组操作完成的同步辅助(相当于一个计数器)
     *    原理：count.countDown(); // 数量-1
     *         count.await();     // 等待计数器归零，然后再向下执行
     *         每次有线程调用countDown()数量-1，假设计数器变为0，count.await()就会被唤醒，继续执行。
     *    缺陷：CountDownLatch是一次性的，计数器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，
     *         当CountDownLatch使用完毕后，它不能再次被使用
     */
    public static void main(String[] args) throws InterruptedException {
        // 创建一个值为7 的计数器。
        CountDownLatch count = new CountDownLatch(7);

		for (int i = 0; i < 7; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " -- Go Out");
				count.countDown(); // 计数器-1
			}, String.valueOf(i)).start();
		}

		count.await(); // 等待计数器归零，主线程才会向下执行。但是我只有6个线程，因此主线程会一直被阻塞
		// count.await(3, TimeUnit.SECONDS); // 参数表示 等待的最长时间。如果在等待的最长时间里计数器还没有归零，将释放所有阻塞线程。
		System.out.println("Close Door");
	}
}
