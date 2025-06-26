package P16_JUC并发.JUC并发Ⅰ_关于lock锁;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 游家纨绔
 * @Description TODO 请使用线程池创建 3 个线程，实现交替打印从 1 到 100的过程，并且打印到 100结束线程。
 * @Date 2025-06-26 09:10:00
 */
public class 使用Lock锁实现三线程交替打印 {

	private static final int THREAD_COUNT = 3;
	private static final int MAX_NUMBER = 100;

	private static Lock lock = new ReentrantLock();
	private static Condition[] conditions = new Condition[THREAD_COUNT];
	private static int currentNumber = 1;
	private static int currentThread = 0; // 0,1,2 分别表示三个线程

	public static void main(String[] args) {
		// 创建线程池
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

		// 初始化条件变量
		for (int i = 0; i < THREAD_COUNT; i++) {
			conditions[i] = lock.newCondition();
		}

		// 创建并提交任务
		for (int i = 0; i < THREAD_COUNT; i++) {
			final int threadId = i;
			executor.submit(() -> printNumbers(threadId));
		}

		// 关闭线程池
		executor.shutdown();
	}

	private static void printNumbers(int threadId) {
		while (true) {
			lock.lock();
			try {
				// 如果不是当前线程的轮次，则等待
				while (currentThread != threadId && currentNumber <= MAX_NUMBER) {
					conditions[threadId].await();
				}

				// 检查是否已经完成所有数字
				if (currentNumber > MAX_NUMBER) {
					// 唤醒下一个线程，确保所有线程都能退出
					int nextThread = (threadId + 1) % THREAD_COUNT;
					conditions[nextThread].signal();
					return;
				}

				// 打印数字
				System.out.println("Thread-" + (threadId + 1) + ": " + currentNumber);
				currentNumber++;

				// 切换到下一个线程
				currentThread = (currentThread + 1) % THREAD_COUNT;
				conditions[currentThread].signal();

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				lock.unlock();
			}
		}
	}

}
