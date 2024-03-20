package P16_JUC并发.JUC并发Ⅱ_集合类不安全;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMap线程安全 {
    /*
      一、并发下HashMap是不安全的,为什么不安全呢？
          因为在并发状况，去进行写的操作，很有可能会被插队，就可能导致读的结果与预期不一样，就会出现线程不安全。
          假设两个线程A、B都在进行put操作，并且hash函数计算出的插入下标是相同的，当线程A执行完第六行代码后由于时间片耗尽导致被挂起，
          而线程B得到时间片后在该下标处插入了元素，完成了正常的插入。
          然后线程A获得时间片，由于之前已经进行了hash碰撞的判断，所以此时不会再进行判断，而是直接进行插入。
          这就导致了线程B插入的数据被线程A覆盖了，从而线程不安全。

      二、ConcurrentHashMap是如何保证线程安全的？
         在JDK 1.8中，ConcurrentHashMap放弃了之前版本中使用的锁分段技术，转而采用数组+链表或数组+红黑树的结构。
         每个hash桶（或称为桶,即数组的每个元素空间）中的第一个节点（即tabAt(table, i)）作为锁的对象。
         当一个线程试图修改某个桶中的数据时，它会尝试获取该桶第一个节点的锁。
         由于每个桶都有自己的锁，因此多个线程可以同时修改不同桶中的数据，从而实现了更高的并发性能。

         此外，JDK 1.8中的ConcurrentHashMap还引入了CAS算法来进一步提高并发性能。
         CAS算法是一种无锁算法，它可以在多线程环境下实现无锁的数据修改。
         在ConcurrentHashMap中，CAS算法主要用于确保节点链接的原子性，从而避免在链表或红黑树的修改过程中产生线程安全问题。

         总的来说，JDK 1.8中ConcurrentHashMap通过采用更细粒度的锁和CAS算法，实现了更高的并发性能和更好的线程安全性。
         这种优化使得ConcurrentHashMap在多线程环境下能够更有效地处理大量的并发读写操作。
         比HashTable这种使用Synchronized锁住全部的Map数据性能更优。

      三、ConcurrentHashMap的key与value值可以为null或者为空吗？
         HashMap 的 key与value 值都是可以为null的，且都可以为空
         Hashtable 的 key与value 值都不能为null，且都可以为空
         ConcurrentHashMap 的 key与value 值都不能为null，且都可以为空

         分析：在多线程环境中，如果ConcurrentHashMap允许 key或value 为null，那么当调用get(key)方法返回null时，
              我们无法判断这个null是因为key根本不存在，还是因为对应的value值就是null。
              这种二义性在单线程环境中可以通过调用containsKey(key)方法来消除，
              但在多线程环境中，由于并发操作的存在，这种判断可能变得不准确，从而引发线程安全问题。
              因此在源码中：putVal(K key, V value, boolean onlyIfAbsent) {
                      		  // 首先会判断 key 和 value 是否为 null,如果是则抛出异常
                              if (key == null || value == null) throw new NullPointerException();
                          }

              至于“空”的概念，它通常指的是一个对象的内容为空，而不是对象引用本身为null。
              在ConcurrentHashMap中，只要key和value不是null，即使它们的内容为空（例如，一个空字符串或一个空的集合），也是可以被接受的。
              但需要注意的是，对于ConcurrentHashMap来说，它关注的是对象的引用而非内容，因此，即使内容为空，引用本身也不能为null。

        public static void main(String[] args) {
            Map<String, String> map = new HashMap<>();
            for (int i = 1; i < 100; i++) {
                new Thread(() -> {
                    map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                    System.out.println(map);
                }, String.valueOf(i)).start();
            }
        }
        // 会出现java.util.ConcurrentModificationException异常，并发修改异常

        方案一：使用集合Properties,他是继承于Hashtable的，都是线程安全的集合
        方案二：Map<Object,String> map = Collections.synchronizedMap(new HashMap<>())
        方案二：Map<String,String> map = new ConcurrentHashMap<>();
     */
    public static void main(String[] args) {
        Map<Object,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put( Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5) );
                System.out.println(Thread.currentThread().getName() + "---" + map);
            }, String.valueOf(i)).start();
        }
    }
}
