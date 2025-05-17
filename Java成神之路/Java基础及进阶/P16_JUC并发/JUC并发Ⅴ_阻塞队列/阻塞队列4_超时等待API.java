package P16_JUC并发.JUC并发Ⅴ_阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class 阻塞队列4_超时等待API {

	public static void main(String[] args) {
		test();
	}

	/**
	 * 队列溢出或者队列空取会造成阻塞，如果一直存不进去或者取不出来就会超时，一旦超时就会返回false或者null
	 */
	public static void test() {
		ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3); // 队列卡槽为3个
		try {
			// 往队列中添加元素
			System.out.println(queue.offer("a"));
			System.out.println(queue.offer("b"));
			System.out.println(queue.offer("c"));
			System.out.println(queue.offer("d", 2, TimeUnit.SECONDS)); // 超时时间为2秒，一旦超时返回false，表示数据没有成功添加到队列中

			System.out.println("===========================");

			// 从队列中取出元素
			System.out.println(queue.poll());
			System.out.println(queue.poll());
			System.out.println(queue.poll());
			System.out.println(queue.poll(2, TimeUnit.SECONDS)); // 超时时间为2秒，一旦超时返回null，表示没有数据了
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
