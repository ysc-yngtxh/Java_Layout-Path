package L12_线程.线程Ⅱ_线程基础知识;

/*
 * 1、老版 jdk 版本有专门的 Thread.stop() 方法进行线程停止。
 *    但是这个方法不推荐使用，仅做了解即可，因为这个方法有点类似于 kill -9 pid 的命令，
 *    会 强制线程停止 可能致使一些清理工作得不到完成，还有可能会对锁的对象进行解锁，导致数据得不到同步处理，数据不一致。
 *    不能保证线程资源的正常释放，导致可能会出现一些不可预期的未知状态
 *
 * 2、正常我们启动线程后，会去异步执行 run() 方法中的代码逻辑。
 *       假如现在我们的线程 run() 方法还没执行完的时候我想终止这个线程继续往下执行，这里就需要我们自己去做一下逻辑处理，
 *    以方便我们可以能够随时终止掉这个线程的继续执行。
 *
 * 3、在我们定义的线程逻辑中去添加一个布尔标记，这样我们就可以在外部通过这个标记去控制线程是否去执行 run() 方法中代码逻辑
 *    从而保证线程不用执行程序就顺利结束 run() 方法，run()结束即该线程结束
 */
public class 线程6_终止一个线程的执行 {

	public static void main(String[] args) {
		try {
			MyT m = new MyT();
			Thread t = new Thread(m, "t");
			// 代码执行到此处后，去执行MyT线程中方法，但是多线程不影响进度，所以他在执行MyT线程时候main线程继续往下走
			t.start();

			// 睡5秒
			Thread.sleep(5000);

			// 终止程序
			m.run = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			// 自定义线程
			MyThread2 runnable = new MyThread2();
			Thread thread = new Thread(runnable, "子线程");
			thread.start();

			// 睡5秒
			Thread.sleep(5000);

			// 停止线程
			runnable.stopThread(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class MyT implements Runnable {

	// 打一个布尔标记
	boolean run = true;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			if (run) {
				System.out.println(Thread.currentThread().getName() + " --> " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				// 注意：这里的 return 不是指代码执行结束，而是结束 for 循环。
				return;
			}
		}
	}

}

class MyThread2 implements Runnable {

	private int number;
	// 使用volatile关键字修饰，可以在多线程之间共享，成员变量来控制线程的停止。布尔类型默认值为false。
	private volatile boolean isStop;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " == 进入");
		while (!isStop) {
			synchronized (MyThread2.class) {
				try {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + " == " + number);
					number++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 设置线程标识
	 *
	 * @param isStop true：停止 false：不停止
	 */
	public void stopThread(boolean isStop) {
		this.isStop = isStop;
	}

}
