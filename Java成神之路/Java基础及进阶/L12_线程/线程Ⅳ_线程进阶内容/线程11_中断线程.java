package L12_线程.线程Ⅳ_线程进阶内容;

import java.util.logging.Logger;

/*
 * 中断阻塞线程：interrupt()
 *        比如：sleep睡眠太久了，如果希望半道上醒来，怎么叫醒一个正在睡眠的线程？
 *             注意：这个不是中断线程的执行，是终止线程的睡眠
 *
 *        interrupt() 方法只是改变中断状态而已，不会中断一个正在运行的线程。
 *    这一方法实际完成的是，给受阻塞的线程发出一个中断信号，这样受阻线程就得以退出阻塞的状态。
 *    更确切的说，如果线程被 Object.wait(), Thread.join() 和 Thread.sleep() 三种方法之一阻塞，
 *    此时调用该线程的 interrupt() 方法，那么该线程将抛出一个 InterruptedException中断异常，从而提早地终结被阻塞状态。
 *    如果线程没有被阻塞，这时调用interrupt()将不起作用，直到执行到 wait()、sleep()、join() 时，才马上会抛出异常。
 *
 *    线程A在执行sleep, wait, join时，线程B调用线程A的interrupt方法，这个时候A的确会有 InterruptedException 异常抛出来。
 *    但这其实是在 sleep, wait, join 这些方法内部会不断检查中断状态的值，而自己抛出的InterruptedException。
 *    如果线程A正在执行一些指定的操作时如值：for、while、if 调用方法等,不会去检查中断状态,则线程A不会抛出 InterruptedException,而会一直执行着自己的操作。
 *
 * 注意:
 *     当线程A执行到wait(),sleep(),join()时，抛出InterruptedException后，中断状态已经被系统复位了，即中断状态为执行前的状态
 *  线程A调用Thread.interrupted()返回的是false。如果线程被调用了interrupt()，此时该线程并不在wait(),sleep(),join()时，
 *  下次执行wait(),sleep(),join()时，一样会抛出InterruptedException，当然抛出后该线程的中断状态也会被系统复位。
 *
 * 总结一下：调用 interrupt() 方法，立刻改变的是中断状态，但如果不是在阻塞状态，会抛出异常；
 *          如果在进入阻塞态后，中断状态为已中断，就会立刻抛出异常
 *
 *     1. sleep() & interrupt()
 *           线程A正在使用 sleep() 暂停着: Thread.sleep(100000)，
 *        如果要取消它的等待状态，可以在正在执行的线程里(比如这里是B)调用 a.interrupt()［a是线程A对应到的Thread实例］，
 *        令线程A放弃睡眠操作。即，在线程B中执行a.interrupt()，处于阻塞中的线程a将放弃睡眠操作。
 *        当在sleep中时线程被调用 interrupt() 时，就马上会放弃暂停的状态并抛出InterruptedException。抛出异常的,是A线程。
 *
 *     2. wait() & interrupt()
 *           线程A调用了 wait() 进入了等待状态，也可以用 interrupt() 取消。不过这时候要注意锁定的问题。
 *        线程在进入等待区，会把锁定解除，当对等待中的线程调用 interrupt() 时，会先重新获取锁定，再抛出异常。
 *        在获取锁定之前，是无法抛出异常的。
 *
 *     3. join() & interrupt()
 *        当线程以 join() 等待其他线程结束时，当它被调用interrupt()，它与sleep()时一样，会马上跳到catch块里。
 *
 *     注意，调用的 interrupt() 方法，一定是调用被阻塞线程的interrupt方法。如在线程a中调用线程t.interrupt()。
 */
public class 线程11_中断线程 {

	public static void main(String[] args) {
		Thread t = new Thread(new MyRunnable2(), "t");
		t.start();
		try {
			Thread.sleep(1000 * 3); // 睡眠 3000 毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 中断 t线程 的睡眠
		t.interrupt();
		System.out.println("main线程调用t线程的interrupt()方法后，此时t线程的中断标志位为：" + t.isInterrupted());

		Thread m = new Thread(new InterruptedTask(), "m");
		m.start();
		try {
			Thread.sleep(1000);  // 睡眠 1000 毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 中断 m线程 的睡眠
		m.interrupt();
		System.out.println("main线程调用m线程的interrupt()方法后，此时t线程的中断标志位为：" + t.isInterrupted());
	}
}

class MyRunnable2 implements Runnable {

	// 重点：run() 当中的异常不能throw，只能 try-catch
	//      因为 run() 方法在父类中没有抛出任何异常，子类不能比父类抛出更多的异常
	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " --> begin"
				         + "\nt线程开始时的中断标志位为：" + Thread.currentThread().isInterrupted());
		try {
			// 睡眠一年
			Thread.sleep(1000L * 60 * 60 * 24 * 365);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 一年后才会执行在这儿
		System.out.println("t线程结束时的中断标志位为：" + Thread.currentThread().isInterrupted() + "\n"
				         + thread.getName() + " --> end");
	}

}

class InterruptedTask implements Runnable {

	public static final Logger log = Logger.getLogger(InterruptedTask.class.getName());

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		System.out.println("m线程开始时的中断标志位为：" + currentThread.isInterrupted());
		while (true) {
			if (currentThread.isInterrupted()) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				log.severe("m线程的睡眠已经被中断了，此时中断标志为：" + currentThread.isInterrupted());
				// TODO
				//  问题：由于我们会在main线程里中断了当前线程阻塞，该线程的中断标志位值理应为 true，但实际结果为 false。
				//  原因：Java官方就是设计成在抛出 Interrupted Exception 异常后清除标志位。
				//       即触发 Interrupted Exception 异常的同时，JVM会把执行线程的中断状态自动重置为false。
				//  目的：是为了能让开发者显式处理线程的中断请求，避免中断状态在开发过程中被忽略、遗忘。造成应用程序的不稳定。
				//  方案：如果需要 显式传递中断标志位，可以在 catch 中再次调用 interrupt() 方法
				currentThread.interrupt();  // 再次设置中断标志位为 true
				break;
			}
		}
		System.out.println("m线程结束时的中断标志位为：" + currentThread.isInterrupted());
	}

}
