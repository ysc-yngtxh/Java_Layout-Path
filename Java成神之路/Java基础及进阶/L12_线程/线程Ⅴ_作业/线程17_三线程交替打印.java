package L12_线程.线程Ⅴ_作业;

/* 使用生产者和消费者模式实现，交替输出：
 * 假设有三个线程，输出以下结果
 *      t1 --> 1
 *      t2 --> 2
 *      t3 --> 3
 *      t1 --> 4
 *      t2 --> 5
 *      t3 --> 6
 *      t1 --> 7
 *      t2 --> 8
 *      t3 --> 9
 *      ...
 *
 *      要求：必须实现三线程交替输出从 1-100。
 *           三个线程共享一个数字，每个线程执行时都要对这个数字进行：++
 */
public class 线程17_三线程交替打印 {
	public static void main(String[] args) {
		NumberObject nb = new NumberObject(1);

		Thread t1 = new Thread(new PrintTask(nb, 1), "t1");
		Thread t2 = new Thread(new PrintTask(nb, 2), "t2");
		Thread t3 = new Thread(new PrintTask(nb, 0), "t3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class NumberObject {
	int n;
	public NumberObject(int n) {
		this.n = n;
	}
}

class PrintTask implements Runnable {
	private final NumberObject nb;
	private final int targetRemainder;

	public PrintTask(NumberObject nb, int targetRemainder) {
		this.nb = nb;
		this.targetRemainder = targetRemainder;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (nb) {
				// 终止条件
				if (nb.n > 100) {
					nb.notifyAll(); // 唤醒其他线程结束
					break;
				}

				// 只有余数等于目标时才打印【⚠️：多线程中判断尽量使用 while 而非 if 语句，因为 if 语句会出现虚假唤醒的问题】
				while (nb.n % 3 != targetRemainder) {
					try {
						nb.wait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						return;
					}
					// 再次检查终止条件
					if (nb.n > 100) {
						nb.notifyAll();
						return;
					}
				}

				System.out.println(Thread.currentThread().getName() + "-->" + nb.n);
				nb.n++;
				nb.notifyAll(); // 唤醒其他线程
			}
		}
	}

}
