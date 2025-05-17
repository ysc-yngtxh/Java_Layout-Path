package P16_JUC并发.JUC并发Ⅸ_关于AtomicReference非阻塞原子性读写操作;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-21 07:00:00
 * @apiNote TODO 高并发环境下的性能优势 LongAdder，AtomicLong
 */
public class Atomic2_AtomicReference示例 {

	/**
	 * AtomicReference类提供了对象引用的非阻塞原子性读写操作，并且提供了其他一些高级的用法
	 * <p>
	 * 对象的引用其实是一个4字节的数字，代表着在JVM堆内存中的引用地址，对一个4字节数字的读取操作和写入操作本身就是原子性的操作，
	 * 通常情况下，我们对对象引用的操作一般都是获取该引用或者重新赋值（写入操作），我们也没有办法对对象引用的4字节数字进行加减乘除运算，
	 * 那么为什么JDK要提供 AtomicReference 类用于支持引用类型的原子性操作呢？
	 */

	// volatile关键字修饰，每次对 DebitCard对象引用 的写入操作都会被其他线程看到。创建初始DebitCard，账号金额为0元
	static volatile DebitCard debitCard = new DebitCard("ZhangSan", 0);

	public static void main(String[] args) throws InterruptedException {
		// TODO 使用 volatile 的非原子性解决方案（不理想）
		for (int i = 0; i < 10; i++) {
			new Thread("T-" + i) {
				@Override
				public void run() {
					final DebitCard dc = debitCard;
					// 基于全局 DebitCard 的金额增加10元，并且产生一个新的DebitCard
					DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
					// 输出全新的DebitCard
					System.out.println("只使用 volatile 打印的金额：" + newDC);
					// 修改全局DebitCard对象的引用
					debitCard = newDC;
					try {
						// Java 提供了 Random 类用于生成随机数，这个类也是线程安全的，就是有一点不好，在多线程下，它的性能不佳。
						// 因为，它采用了多个线程共享一个 Random 实例。这样就会导致多个线程争用。
						// 为了解决这个问题，Java 7 引入了 java.util.concurrent.ThreadLocalRandom 类，用于在多线程环境中生成随机数。
						TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		// 根据执行结果可以发现：总是有重复的打印金额
		// 1、原因就在于我们使用的 volatile 关键字是不保证原子性的，以上程序 debitCard = newDC; 执行就不是原子性的
		// 2、该行程序包含以下操作：
		//       (1)、首先从主内存中读取到共享变量 debitCard 最新值，并将最新值复制到自己的工作内存中;
		//       (2)、然后在工作内存中将 newDC 赋值给 debitCard;
		//       (3)、最后将工作内存里将赋值后的结果 刷新到主内存的共享变量 debitCard 里
		// 3、设：线程1、2同时执行，线程1执行完第(2)步后被挂起、线程2执行完并且把debitCard的新值(金额值为10)刷回主存，那么主存中debitCard的金额值为10
		//       随后线程1的开始执行第(3)步，这时由于 volatile 的可见性，线程1的工作内存读取到主存的 debitCard 最新金额值为10，
		//       但是这个 debitCard 最新值影响不了线程1中执行过 dc.getAmount()+10 过的  newDC 值。且 newDC 的金额值为10。
		//       所以线程1工作内存中 debitCard 的新值(金额值为10)刷新到主存。即 dc.getAmount() + 10 执行了2次，但2次都是从0变为10，故有重复的打印金额
	}
}

class AtomicReference简易示例 {

	// 定义AtomicReference并且初始值为 DebitCard("ZhangSan", 0)
	static AtomicReference<DebitCard> debitCardRef =
			new AtomicReference<>(new DebitCard("ZhangSan", 0));

	public static void main(String[] args) {
		/** TODO 使用 AtomicReference 的非阻塞原子性解决方案（写法上有缺陷）
		 * synchronized是一种阻塞式的解决方案，同一时刻只能有一个线程真正在工作，其他线程都将陷入阻塞。
		 * 因此这并不是一种效率很高的解决方案。这个时候就可以利用 AtomicReference 的非阻塞原子性解决方案提供更加高效的方式了
		 */
		for (int i = 0; i < 10; i++) {
			new Thread("T-" + i) {
				@Override
				public void run() {
					// 获取AtomicReference的当前值
					final DebitCard dc = debitCardRef.get();
					// 基于AtomicReference的当前值创建一个新的DebitCard
					DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
					// compareAndSet()方法（Compare-And-Swap，CAS）是单次原子操作。
					// 当它执行失败时（即当前值不等于期望值），该方法会直接结束并返回false，不会自动重试。
					if (debitCardRef.compareAndSet(dc, newDC)) {
						// 更新成功
						System.out.println("使用 AtomicReference 打印的金额：" + newDC);
					}
					try {
						TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		// 根据打印结果可以发现：打印的金额虽然没有出现重复，但是最终的金额还是有少于100元的情况。
		// 原因就在于：AtomicReference 的 compareAndSet() 方法在多线程环境下，CAS 操作的原子性竞争导致部分线程更新失败。
		// 示例：1、假设初始金额为 0 元。
		//      2、线程 T1 读取当前值 dc = 0，计算后新值 newDC = 10。
		//      3、同时，线程 T2 也读取当前值 dc = 0，计算后新值 newDC = 10。
		//      4、线程 T1 执行 CAS(0 → 10) 成功，金额变为 10。
		//      5、线程 T2 执行 CAS(0 → 10) 失败（因为此时实际值已是 10），直接放弃更新，不会进行重试。
		//      6、因此，循环10次中总有几个线程执行失败。所以，最终的金额是有可能少于 100 元。
	}
}

class AtomicReference手动循环重试版 {

	// 定义AtomicReference并且初始值为 DebitCard("ZhangSan", 0)
	static AtomicReference<DebitCard> debitCardRef =
			new AtomicReference<>(new DebitCard("ZhangSan", 0));

	public static void main(String[] args) {
		// TODO 使用 AtomicReference 循环重试 CAS（最终版）
		for (int i = 0; i < 10; i++) {
			new Thread("T-" + i) {
				@Override
				public void run() {
					// compareAndSet() 方法更新失败后不会自动重试，因此可以通过 while循环逻辑进行重试处理
					while (true) {
						DebitCard dc = debitCardRef.get();
						DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
						if (debitCardRef.compareAndSet(dc, newDC)) {
							System.out.println("使用 AtomicReference 打印的金额：" + newDC);
							break;  // 成功则退出循环
						}
						// 失败后稍作等待（可选）
						try {
							TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
		// 根据打印结果：打印的金额没有出现重复，且最终的金额总是 100元。完成了高并发场景下的金额累加操作。
		// 但是在高并发情况下，还可能出现：线程一直在CAS循环重试，导致线程长时间自旋，浪费 CPU 资源。
		// 因此，我们可以通过改造代码 ①、限制重试次数；②、结合其他状态判断 的方式来优化循环重试逻辑。
	}
}

class AtomicReference限制循环重试版 {

	// 定义AtomicReference并且初始值为 DebitCard("ZhangSan", 0)
	static AtomicReference<DebitCard> debitCardRef =
			new AtomicReference<>(new DebitCard("ZhangSan", 0));

	public static void main(String[] args) {
		int maxRetries = 5; // 设置最大重试次数
		int retryCount = 0;
		while (retryCount < maxRetries) {
			DebitCard dc = debitCardRef.get();
			DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
			if (debitCardRef.compareAndSet(dc, newDC)) {
				System.out.println("成功更新：" + newDC);
				break;
			} else {
				retryCount++;
				// 退避策略优化（减少竞争）
				try {
					// 指数退避：等待时间随重试次数增加（例如 2^retryCount * 基础时间）
					long sleepTime = (long) (Math.pow(2, retryCount) * 10);
					TimeUnit.MILLISECONDS.sleep(sleepTime);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		if (retryCount >= maxRetries) {
			System.err.println(Thread.currentThread().getName() + " 更新失败，已达最大重试次数");
		}
	}
}

class AtomicReference自动循环重试版 {

	public static void main(String[] args) {
		// TODO 利用 getAndUpdate() 方法自动重试 CAS，简化循环逻辑。
		// 定义AtomicReference并且初始值为 DebitCard("ZhangSan", 0)
		AtomicReference<DebitCard> debitCardRefUpdateAndGet = new AtomicReference<>(new DebitCard("ZhangSan", 0));
		for (int i = 0; i < 10; i++) {
			new Thread("T-" + i) {
				@Override
				public void run() {
					// getAndUpdate()：表示获取当前值并更新，返回更新前的值(旧值)。内部实现了循环重试，直到成功。
					// updateAndGet() 表示获取当前值并更新，返回更新后的值(新值)。内部实现了循环重试，直到成功.
					// 但在高竞争场景中可能导致忙等待（busy waiting），可能长时间自旋，浪费 CPU 资源
					debitCardRefUpdateAndGet.getAndUpdate(
							dc ->
									new DebitCard(dc.getAccount(), dc.getAmount() + 10)
					                                     );
					System.out.println("使用 getAndUpdate 打印的金额：" + debitCardRefUpdateAndGet.get());
				}
			}.start();
		}
	}
}
