package P16_JUC并发.JUC并发Ⅹ_锁升级;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-24 16:30:00
 * @apiNote TODO
 */
public class 锁擦除和锁粗化 {
	/**
	 * 1. 锁擦除（Lock Elision）
	 * 定义：当 JVM 检测到某个同步块（如 synchronized）在运行时实际上不需要加锁（即不存在线程竞争的可能性）时，
	 * 会直接消除这个锁的获取和释放操作。
	 * <p>
	 * 2. 锁粗化（Lock Coarsening）
	 * 定义：JVM 将多个连续的细粒度锁合并为一个粗粒度锁，减少频繁的加锁/解锁操作。
	 */

	private static Object obj = new Object();

	public static void main(String[] args) {
		// TODO 锁擦除
		// 10 个线程，每个线程都会创建一个局部变量，然后对这个局部变量加锁。
		// 由于局部变量是线程私有的，所以不存在线程竞争，JVM 会直接消除这个锁的获取和释放操作。
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				Object localObj = new Object(); // 局部变量，仅当前线程可见
				synchronized (localObj) {       // 锁擦除：JVM 会移除这个无意义的锁
					// 操作局部对象...
					System.out.println(Thread.currentThread().getName() + " 加锁成功");
				}
			}, "t1").start();
		}

		// TODO 锁粗化
		// JVM 会将多个连续的细粒度锁合并为一个粗粒度锁，减少频繁的加锁/解锁操作。
		// 例如下面的代码，JVM 会将三个 synchronized 合并为一个粗粒度锁。
		new Thread(() -> {
			synchronized (obj) {
				System.out.println("a");
			}
			synchronized (obj) {
				System.out.println("b");
			}
			synchronized (obj) {
				System.out.println("c");
			}
		}, "t2").start();
		// 如果方法中首尾相接，前后相邻的都是同一个锁对象，那JVM就会把这几个synchronized块合并成一个大块
		// 加粗加大范围，一次申请锁即可，避免多次的申请和释放锁，提升性能。
		new Thread(() -> {
			synchronized (obj) {
				System.out.println("d");
				System.out.println("e");
				System.out.println("f");
			}
		}, "t3").start();
	}

}
