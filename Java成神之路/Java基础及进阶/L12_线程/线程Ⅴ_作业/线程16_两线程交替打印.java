package L12_线程.线程Ⅴ_作业;

/* 使用生产者和消费者模式实现，交替输出：
 * 假设只有两个线程，输出以下结果
 *      t1 --> 1
 *      t2 --> 2
 *      t1 --> 3
 *      t2 --> 4
 *      t1 --> 5
 *      t2 --> 6
 *      ...
 *
 *      要求：必须交替，并且 t1线程 负责输出奇数，t2线程 负责输出偶数，交替打印至 100.
 *           两个线程共享一个数字，每个线程执行时都要对这个数字进行：++
 */
public class 线程16_两线程交替打印 {

	public static void main(String[] args) {
		Number nb = new Number(1);

		Thread t1 = new Thread(new Current(nb, 0), "t1");
		Thread t2 = new Thread(new Current(nb, 1), "t2");

		t1.start();
		t2.start();
	}

}

class Number {
	int n;
	public Number(int n) {
		this.n = n;
	}
}

class Current implements Runnable {
	private final Number nb;
	private final int targetRemainder;

	public Current(Number nb, int targetRemainder) {
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
				// ⚠️：多线程中判断尽量使用 while 而非 if 语句，因为 if 语句会出现【虚假唤醒】的问题
				while (nb.n % 2 == targetRemainder) {
					try {
						nb.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 再次检查终止条件
				if (nb.n > 100) {
					nb.notifyAll(); // 唤醒其他线程结束
					break;
				}
				System.out.println(Thread.currentThread().getName() + "-->" + nb.n);
				nb.n++;
				nb.notifyAll();
			}
		}
	}

}
