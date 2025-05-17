package L12_线程.线程Ⅳ_线程进阶内容;

/* 守护线程
 *   Java语言中线程分为两大类：
 *        一类是：用户线程
 *        一类是：守护线程（后台线程）
 *        其中具有代表性的就是：垃圾回收线程（守护线程）
 *
 *    守护线程的特点：
 *         一般守护线程是一个死循环，所有的用户线程只要结束，守护线程自动结束。
 *
 *    注意：主线程main方法是一个用户线程
 *
 *    守护线程用在什么地方？
 *         每天00：00的时候系统数据自动备份，这个需要使用到定时器，并且我们可以将定时器设置为守护线程
 *         一直在那里看着，每到00：00的时候就备份一次，所有的用户线程如果结束了，守护线程自动退出，没有必要进行数据备份了
 */
public class 线程12_守护线程 {

	public static void main(String[] args) {
		Thread t = new BakDataThread();
		t.setName("备份数据的线程");

		// 启动线程之前，将线程设置为守护线程
		t.setDaemon(true);

		t.start();

		// 主线程：主线程是用户线程
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "-->" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class BakDataThread extends Thread {

	public void run() {
		int i = 0;
		// 虽然while是死循环，但是守护线程会随着主线程的结束而结束
		while (true) {
			System.out.println(Thread.currentThread().getName() + "-->" + (++i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
