package P16_JUC并发.JUC并发Ⅳ_集合类不安全;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMap线程安全 {
	/*
	 * 一、并发下HashMap是不安全的，为什么不安全呢？
	 *     看源码 HashMap -> putVal()方法
	 *     1、第一个if判断：判断当前 hashmap 中的哈希表是不是空或者长度等于 0。判断结果为true，则创建新的长度为 16 的哈希表
	 *     2、第二个if判断：判断当前插入的数组位置是否为null(即当前数组位置没有元素)。如果为null则赋值给p，并将数据放入当前位置。
	 *     3、对应第二个if判断的else：表示当前插入的数组位置有元素。就插入到链表或红黑树尾端。(Jdk1.8之后使用的是尾插法)
	 *     4、putVal() 方法执行完毕后，会调用 addCount() 方法
	 *     5、addCount() 方法会判断当前HashMap中的键值对数量是否大于阈值，如果大于则进行扩容为原来两倍（保证容量为2的幂次数）
	 *
	 *     假设 线程A、B 都在进行put操作，且put数据的key部分不同，只是 hash函数 计算出的插入下标是相同的。
	 *     当 线程A 执行【第二个if判断为true后】由于时间片耗尽导致被挂起，
	 *     而 线程B 得到时间片后也去执行 【第二个if判断为true】并在该下标处插入了元素，完成了正常的插入。
	 *     然后 线程A 获得时间片，由于之前已经进行了 hash碰撞 的判断，所以此时不会再进行判断，而是直接进行插入。
	 *     这就导致了 线程B 插入的数据被 线程A 覆盖了，造成了原本应该在同一个数组下标中形成链表结构的两组数据 如今却只有一条数据。
	 *     造成了数据被覆盖丢失，从而引发线程不安全。
	 *
	 *     HashMap的线程不安全主要体现在下面两个方面：
	 *       1.在Jdk1.7中，当并发执行扩容操作时会造成环形链和数据丢失的情况。
	 *       2.在Jdk1.8中，在并发执行put操作时会发生数据覆盖的情况。
	 *
	 *       HashMap死链问题‌主要发生在 Jdk1.7 版本中，由于多线程并发扩容时使用头插法导致链表顺序反转，从而形成死循环。‌
	 *       ①、‌线程T1 和 线程T2 同时对 HashMap 进行扩容操作‌。假设原链表顺序为A → B → C，线程T1 使用头插法将链表变为C → B → A。
	 *       ②、‌线程T2 在 T1 完成前开始遍历‌，此时 T2 的引用仍然是指向旧链表的A → B → C。
	 *       ③、‌当 T1 完成扩容后，T2恢复执行‌，此时 T2 将A插入新链表的头部，接着处理B时，B.next指向A，形成A ↔︎ B的循环，导致死循环。
	 *
	 * 二、ConcurrentHashMap是如何保证线程安全的？
	 *    在 Jdk1.8 中，ConcurrentHashMap放弃了之前版本中使用的分段锁技术，转而采用 桶级别锁+Node数组+链表+红黑树 的结构。
	 *    分段锁：
	 *        在 Jdk1.7 中，ConcurrentHashMap采用了分段锁技术，且数据结构类似于HashMap，只是在Node数组之上还有一个Segment。
	 *        首先将Node数组分成一段一段的存储（比如：3个Node数组为一段），然后给每一段数据配一把锁，
	 *        当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。
	 *    桶级别锁：
	 *        在 Jdk1.8 中，ConcurrentHashMap引入了更细粒度的锁机制，即将锁的粒度从Segment降低到了桶级别。
	 *        ConcurrentHashMap中的每个桶（或称为桶，即数组的每个元素空间）中的第一个节点（即tabAt(table, i)）作为锁的对象。
	 *        当一个线程试图修改某个桶中的数据时，它会尝试获取该桶第一个节点的锁。
	 *        由于每个桶都有自己的锁，因此多个线程可以同时修改不同桶中的数据，从而实现了更高的并发性能。
	 *
	 *    此外，Jdk1.8中的 ConcurrentHashMap 还引入了CAS算法(无锁算法)来进一步提高并发性能。它可以在多线程环境下实现无锁的数据修改。
	 *    在 ConcurrentHashMap 中，CAS算法 主要用于确保节点链接的原子性，从而避免在链表或红黑树的修改过程中产生线程安全问题。
	 *
	 *    总的来说，Jdk1.8中 ConcurrentHashMap 通过采用更细粒度的桶级别锁和CAS算法，实现了更高的并发性能和更好的线程安全性。
	 *    这种优化使得 ConcurrentHashMap 在多线程环境下能够更有效地处理大量的并发读写操作。
	 *    比 Hashtable 这种单纯使用 Synchronized 锁住全局的Map数据性能更优、效率更高，但是还比 HashMap 使用安全。
	 *
	 * 三、ConcurrentHashMap的key与value值可以为null或者为空吗？
	 *    HashMap 的 key 与 value 值都是可以为null的，且都可以为空
	 *    Hashtable 的 key 与 value 值都不能为null，且都可以为空
	 *    ConcurrentHashMap 的 key 与 value 值都不能为null，且都可以为空
	 *
	 *    分析：在多线程环境中，如果ConcurrentHashMap允许 key或value 为null，那么当调用get(key)方法返回null时，
	 *         我们无法判断这个null是因为key根本不存在，还是因为对应的value值就是null。
	 *         这种二义性在单线程环境中可以通过调用containsKey(key)方法来消除，
	 *         但在多线程环境中，由于并发操作的存在，这种判断可能变得不准确，从而引发线程安全问题。
	 *         因此在源码中：putVal(K key, V value, boolean onlyIfAbsent) {
	 *                 		   // 首先会判断 key 和 value 是否为 null,如果是则抛出异常
	 *                         if (key == null || value == null) throw new NullPointerException();
	 *                     }
	 *
	 *         至于 “空” 的概念，它通常指的是一个对象的内容为空，而不是对象引用本身为null。
	 *         在ConcurrentHashMap中，只要key和value不是null，即使它们的内容为空（例如，一个空字符串或一个空的集合），也是可以被接受的。
	 *         但需要注意的是，对于ConcurrentHashMap来说，它关注的是对象的引用而非内容，因此，即使内容为空，引用本身也不能为null。
	 *
	 *   public static void main(String[] args) {
	 *       Map<String, String> map = new HashMap<>();
	 *       for (int i = 1; i < 100; i++) {
	 *           new Thread(() -> {
	 *               map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
	 *               System.out.println(map);
	 *           }, String.valueOf(i)).start();
	 *       }
	 *   }
	 *   // 会出现java.util.ConcurrentModificationException异常，并发修改异常
	 *
	 *   方案一：使用集合Properties，他是继承于Hashtable的，都是线程安全的集合
	 *   方案二：Map<Object,String> map = Collections.synchronizedMap(new HashMap<>())
	 *   方案二：Map<String,String> map = new ConcurrentHashMap<>();
	 */
	public static void main(String[] args) {
		Map<Object, String> map = new ConcurrentHashMap<>();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
				System.out.println(Thread.currentThread().getName() + " --- " + map);
			}, String.valueOf(i)).start();
		}
	}
}
