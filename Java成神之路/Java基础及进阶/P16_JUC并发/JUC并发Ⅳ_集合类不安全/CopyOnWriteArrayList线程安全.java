package P16_JUC并发.JUC并发Ⅳ_集合类不安全;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayList线程安全 {
	/*
	 *  并发下 ArrayList 是不安全的。
	 *  具体来说，ArrayList的add操作不是原子性的，它包含两个步骤：先设置元素值，然后增加size的值。
	 *  在多线程环境下，如果两个线程同时执行add操作，可能会出现一个线程覆盖另一个线程的值的情况，导致数据不一致。
	 *
	 *  例如：当列表为空，size等于0时，线程A执行完 elementData[size]=e 后挂起，不曾执行size++操作，此时size仍然等于0。
	 *       然后线程B执行 elementData[size]=e，因为size仍然等于0，所以线程B将元素存放在下标为0的位置，覆盖了线程A的数据。
	 *       接着，线程B和线程A都增加size的值，导致size等于2，但实际上只有一个元素被添加到了列表中。
	 *
	 *  此外，ArrayList的默认数组大小为10，当添加第10个元素时，会进行数组扩容，这个扩容操作也不是线程安全的。
	 *  如果在多线程环境下，一个线程正在扩容，而另一个线程同时尝试访问或修改列表，就可能导致异常。
	 *
	 *     public static void main(String[] args) {
	 *         List<String> list = new ArrayList<>();
	 *         for (int i = 0; i < 10; i++) {
	 *             new Thread(() -> {
	 *                 list.add(UUID.randomUUID().toString().substring(0,5));
	 *                 System.out.println(list);
	 *             }, String.valueOf(i)).start();
	 *         }
	 *     }
	 *     // 会出现java.util.ConcurrentModificationException异常，并发修改异常
	 *     // 我们知道，出现并发修改异常通常是因为在迭代器中，进行使用集合对象进行add、remove等操作，使集合结构发生改变，因此迭代器操作的就会抛异常。
	 *     // 但是在这里我们并没有使用迭代器循环操作集合，那为什么还会出现并发修改异常呢？
	 *     // 原因在于：System.out.println(list);
	 *     //         println()会默认调用list的 toString() 方法输出到控制台。
	 *     //         点开了 ArrayList<E> 的源码，发现没有重写 toString() 方法，
	 *     //         进入它的父类 AbstractList<E>，也没有重写 toString() 方法，
	 *     //         再进入它的父类 AbstractCollection<E>，终于找到了，源码中调用了 Iterator 对集合list的输出打印
	 *
	 *
	 * 方案一：Collections.SynchronizedList(new ArrayList())
	 * 方案二：使用Vector集合,我们可以查看源码发现Vector是有Synchronized关键字的
	 * 方案三：List<String> list = new CopyOnWriteArrayList()
	 *        理解：
	 *           写操作：当我们往CopyOnWriteArrayList容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，
	 *                  复制出一个新的容器(副本)，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。
	 *                  并且在这个过程中是有使用 ReentrantLock 进行上锁，保证修改元素线程安全。
	 *           读操作：而 CopyOnWrite容器进行并发的读都是在原容器环境下，不需要加锁。
	 *                  因为所有的修改操作都只会在副本容器里，对于原容器环境没有修改行为，自然就没有线程安全问题。
	 *
	 *        优势：CopyOnWriteArrayList 类，相比于使用 读写锁(ReentrantReadWriteLock) 处理List数据具有更优性能。
	 *             可以说是对读写锁规则的升级。为了将读取的性能发挥到极致，CopyOnWriteArrayList 读取是完全不用加锁的。
	 *             并且更厉害的是：写入也不会阻塞读取操作，只有写入和写入之间需要进行同步等待，读操作的性能得到大幅度提升。
	 *
	 *        总结：CopyOnWriteArrayList由于写时进行复制，内存里面同时存在两个对象占用内存，如果对象过大容易发送YongGc和FullGc，
	 *             如果使用场景的写操作十分频繁的话，建议还是不要实现CopyOnWriteArrayList。
	 */
	public static void main(String[] args) {
		List<String> list = new CopyOnWriteArrayList<>();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 5));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}

}
