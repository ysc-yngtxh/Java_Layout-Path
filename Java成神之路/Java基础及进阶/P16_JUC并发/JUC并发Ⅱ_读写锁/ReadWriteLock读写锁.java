package P16_JUC并发.JUC并发Ⅱ_读写锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock读写锁 {
    /**
     * 独占锁(写锁) 一次只能被一个线程占有
     * 共享锁(读锁) 多个线程可以同时占有，区别于Lock、synchronized的一次只能被一个线程占有
     *
     * 在没有读写锁之前，ReentrantLock 虽然可以保证了线程安全，但是也浪费了一定的资源，因为多个读操作同时进行，并没有线程安全问题，
     * 因此我们可以允许在多个读操作并行，以便提高程序效率。但是这样写操作就不是线程安全的，如果多个线程同时写，或者在写的同时进行读操作，便会造成线程安全问题。
     * 我们的读写锁就解决了这样的问题，它设定了一套规则，既可以保证多个线程同时读的效率，同时又可以保证有写入操作时的线程安全。
     *
     * 这里的话，我有个想法，就是如果读写操作线程不安全的情况下，是否可以使用ConcurrentHashMap()?
     *     仔细琢磨一下，你就明白了，两者有本质上的区别，不能混淆
     *        ConcurrentHashMap解决的是put写入的线程安全，就是说在并发写入数据时侯，当前线程A写数据写一半，时间片耗尽，
     *        结果被后来的线程B执行写入数据，刚巧写入数据的key是相同的。然后当前线程A终于抢到时间片去执行写入操作，
     *        那么最终结果只能是A线程的值。
     *
     *        ReadWriteLock解决的是一个方法或者同步代码块写入的线程安全，保证整个代码在没有完全执行完之前是不会被其他线程插入
     *
     *        ReadWriteLock 适用于读多写少的情况
     */
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

		// 写入
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			new Thread(() -> myCache.put(temp + "", temp), String.valueOf(i)).start();
		}

		// 读取
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			new Thread(() -> myCache.get(temp + ""), String.valueOf(i)).start();
		}

		System.out.println("主线程执行完毕！！！");
	}
}

class MyCache {

	// volatile：可见性
	private volatile Map<String, Object> map = new HashMap<>();
	// 读写锁：更加细粒度的控制
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	// 存。写入的时候，只希望同时只有一个线程写
	public void put(String key, Object value) {
		// System.out.println(Thread.currentThread().getName() + "写入" + key);
		// map.put(key, value);
		// System.out.println(Thread.currentThread().getName() + "写入完成");

		// 当不使用'写锁'的时候，我们在运行时发现：比如写入1时会插入其他线程，然后才写入完成。这就很尴尬了啊，线程不安全了呀
		readWriteLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "写入" + key);
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "写入完成");
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	// 取，读，所有人都可以读
	public void get(String key) {
		/*
		 * System.out.println(Thread.currentThread().getName() + "读取" + key);
		 * Object o = map.get(key);
		 * System.out.println(Thread.currentThread().getName() + "读取完成");
		 */
		readWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "读取" + key);
			map.get(key);
			System.out.println(Thread.currentThread().getName() + "读取完成");
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readWriteLock.readLock().unlock();
		}
	}

}
