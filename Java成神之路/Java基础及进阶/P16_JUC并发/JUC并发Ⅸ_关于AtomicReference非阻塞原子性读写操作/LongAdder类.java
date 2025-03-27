package P16_JUC并发.JUC并发Ⅸ_关于AtomicReference非阻塞原子性读写操作;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-03-27 19:00:00
 */
public class LongAdder类 {
	public static void main(String[] args) {
		// TODO 高并发环境下的性能优势 LongAdder，AtomicLong
		// 1、AtomicLong
		// 1.1、AtomicLong是一个原子性的long类型，它提供了原子性的递增和递减操作，是线程安全的。
		// 1.2、AtomicLong是通过CAS来保证原子性的，CAS是一种乐观锁，它的原理是在操作值的时候，会先比较当前内存值和期望值是否一样，如果一样才会进行操作，否则一直循环。
		// 1.3、AtomicLong的缺点是在高并发的情况下，如果有多个线程同时去操作一个原子变量，那么会有大量的线程自旋，这样会导致CPU资源的浪费。
		// 1.4、AtomicLong适用于低并发的情况，如果是高并发的情况下，推荐使用LongAdder。
		// 1.5、AtomicLong的使用方式如下：
		//     AtomicLong atomicLong = new AtomicLong(0);
		//     atomicLong.incrementAndGet();
		//     atomicLong.decrementAndGet();
		//     atomicLong.addAndGet(10);
		//     atomicLong.compareAndSet(0, 10);
		//     atomicLong.getAndIncrement();
		//     atomicLong.getAndDecrement();
		//     atomicLong.getAndAdd(10);
		//     atomicLong.getAndSet(10);
		// 2、LongAdder
		// 2.1、LongAdder是JDK1.8新增的一个类，它是AtomicLong的一个替代品，用于高并发的情况。
		// 2.2、LongAdder的原理是将热点数据分离，将原来的单点的竞争分散到不同的节点上，这样提高了并发量。

		// 一、什么是LongAdder？
		// LongAdder 位于 java.util.concurrent.atomic 包中，是一种用于高效计数的类。
		// 它的功能类似于AtomicLong，但设计上更适合在高并发环境下使用。
		// AtomicLong 依赖于底层的 CAS（Compare-And-Swap）机制，它通过不断重试来保证原子性。
		// 然而，在极高并发的场景中，CAS操作可能会频繁失败，导致性能下降。
		// 而LongAdder通过将计数分散到多个单独的变量中，并在最后累加，减少了竞争，从而在高并发场景下提升性能。
	}
}
