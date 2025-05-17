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
 *      要求：必须交替，并且 t1线程 负责输出奇数，t2线程 负责输出偶数。
 *           两个线程共享一个数字，每个线程执行时都要对这个数字进行：++
 */
public class 线程15_线程作业 {

	public static void main(String[] args) {
		Number nb = new Number(1);

		Thread t1 = new Thread(new Youshicheng(nb));
		Thread t2 = new Thread(new Chenjiaqi(nb));

		t1.setName("t1");
		t2.setName("t2");

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

class Youshicheng implements Runnable {

	private Number nb;

	public Youshicheng(Number nb) {
		this.nb = nb;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (nb) {
				if (nb.n % 2 == 0) {
					try {
						nb.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + "-->" + nb.n);
				nb.n++;
				nb.notifyAll();
			}
		}
	}

}

class Chenjiaqi implements Runnable {

	private Number nb;

	public Chenjiaqi(Number nb) {
		this.nb = nb;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (nb) {
				if (nb.n % 2 == 1) {
					try {
						nb.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + "-->" + nb.n);
				nb.n++;
				nb.notifyAll();
			}
		}
	}

}
