package P16_JUC并发.JUC并发Ⅴ_阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;

public class 阻塞队列3_阻塞等待API {

	public static void main(String[] args) {
		test();
	}

	/**
	 * 队列溢出或者队列空取会造成阻塞，如果一直存不进去或者取不出来就会一直等待
	 */
	public static void test() {
		ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3); // 队列卡槽为3个
		// 往队列中添加元素
		try {
			queue.put("a");
			queue.put("b");
			queue.put("c");   // 这里是没有返回值的，所以没办法打印
			// queue.put("d");   // 队列卡槽只有3个，所以程序执行到这儿，会阻塞等待

			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			// System.out.println(queue.take()); // 这里同理，队列已经空了，没办法再取，会形成阻塞等待
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
