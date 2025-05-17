package P16_JUC并发.JUC并发Ⅴ_阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;

public class 阻塞队列2_不抛出异常API {

	public static void main(String[] args) {
		test();
	}

	/**
	 * 队列溢出或者队列空取不会抛出异常，且有返回值
	 */
	public static void test() {
		ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3); // 队列卡槽为3个
		// 往队列中添加元素
		System.out.println(queue.offer("a"));
		System.out.println(queue.offer("b"));
		System.out.println(queue.offer("c"));
		System.out.println(queue.offer("d")); // 没有抛异常，返回值为false,表示这条数据没有成功添加到 Queue 队列中

		System.out.println("===========================");

		// 从队列中取出元素
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll()); // 没有抛异常，返回值为null
	}

}
