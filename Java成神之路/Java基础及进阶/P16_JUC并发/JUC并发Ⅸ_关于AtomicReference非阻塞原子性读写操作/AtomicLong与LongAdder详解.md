## 一、AtomicLong

<figure>
<h4>

    1、AtomicLong是一个原子性的long类型，它提供了原子性的递增和递减操作，是线程安全的。
    2、AtomicLong是通过CAS来保证原子性的，CAS 是一种乐观锁，它的原理是在操作值的时候，会先比较当前内存值和期望值是否一样，如果一样才会进行操作，否则一直循环。
    3、AtomicLong的缺点是在高并发的情况下，如果有多个线程同时去操作一个原子变量，那么会有大量的线程自旋，这样会导致CPU资源的浪费。
    4、AtomicLong适用于低并发的情况，如果是高并发的情况下，推荐使用LongAdder。
    5、AtomicLong的使用方式如下：
       AtomicLong atomicLong = new AtomicLong(0);
       atomicLong.incrementAndGet();
       atomicLong.decrementAndGet();
       atomicLong.addAndGet(10);
       atomicLong.compareAndSet(0, 10);
       atomicLong.getAndIncrement();
       atomicLong.getAndDecrement();
       atomicLong.getAndAdd(10);
       atomicLong.getAndSet(10);

</h4>
</figure>

## 二、LongAdder

<figure>

### 1、什么是LongAdder？

  <figure>
  <h4>

       LongAdder 是Jdk1.8新增的一个类，位于 java.util.concurrent.atomic 包中，是一种用于高效计数的类。
    它的功能类似于 AtomicLong，但 AtomicLong 依赖于底层的 CAS（Compare-And-Swap）机制，它通过不断重试来保证原子性。
    并且在极高并发的场景中，CAS操作可能会导致大量线程进行重试，从而降低性能。
       而 LongAdder 通过将计数分散到多个单独的变量中，并在最后累加，减少了竞争。
    本质上是通过空间换时间，优化高并发写入性能，LongAdder 适用于频繁累加但较少实时读取结果的场景（如统计请求量）

  </h4>
  </figure>

### 2、LongAdder 的工作原理（可参考文章：https://zhuanlan.zhihu.com/p/269240636）

  <figure>
  <h4>

    LongAdder的计数主要分为2个对象
      一个long类型的字段：base
      一个Cell对象数组，Cell对象中就维护了一个long类型的字段value，用来计数
    ①、当没有发生线程竞争的时候，累加都会发生在 base 字段上，这就相当于是一个单线程累加2次，只不过 base 的累加是一个cas操作。
    ②、当发生线程竞争的时候，必然有一个线程对 base 的cas累加操作失败，于是它先去判断 Cell数组 是否已经被初始化了，
        如果没有则初始化一个长度为2的数组，并根据线程的hash值找到对应的数组索引，并对该索引的Cell对象中的value值进行累加，
        这个累加也是cas的操作。
    ③、如果一共有3个线程发生了竞争，那么其中第一个线程对 base 的cas累加成功，剩下2个线程都需要去对 Cell数组 中的元素进行累加。
        因为对 Cell数组 中value值的累加也是一个cas操作，如果第二个线程和第三个线程的hash值对应的数组下标是同一个，那么同样会发生竞争，
        如果第二个线程成功了，第三个线程就会去rehash自己的hash值，如果得到的新的hash值对应的是另一个元素为null的数组下标，那么就new一个Cell对象并对value值进行累加。
    ④、如果此时有线程4同时参与竞争，那么对于线程4来说，即使rehash后还是可能在和线程3的竞争过程中cas失败，
        此时如果当前数组的容量小于系统可用的cpu的数量，那么它就会对数组进行扩容，之后再次rehash，重复尝试对Cell数组中某个下标对象的累加

  </h4>
  </figure>

### 3、LongAdder的使用方式如下：

  <figure>
  <h4>

    LongAdder longAdder = new LongAdder();
    longAdder.increment();
    longAdder.decrement();
    longAdder.add(10);
    longAdder.sum();
    longAdder.reset();
    longAdder.sumThenReset();
    longAdder.sumThenReset();
    longAdder.sumThenReset();
    longAdder.sumThenReset();

  </h4>
  </figure>

### 4、LongAdder的优缺点

  <figure>
  <h4>

    优点：
      1、LongAdder在高并发场景下性能更好，因为它通过分散计数来减少竞争。
      2、LongAdder的累加操作是非阻塞的，不会导致线程阻塞。
    缺点：
      1、LongAdder的内存占用比AtomicLong大，因为它使用了一个Cell数组来存储计数。
      2、LongAdder的读取操作不是原子性的，如果需要获取当前值，需要调用sum()方法，这个方法会遍历Cell数组进行累加，性能较差。

  </h4>
  </figure>

### 5、LongAdder的使用场景

  <figure>
  <h5>

      适用于高并发的计数场景，如统计请求量、访问量等。
      不适用于需要频繁读取当前值的场景，因为LongAdder的读取操作不是原子性的，性能较差。

  </h5>
  </figure>

### 6、LongAdder的简单使用

  <figure>
  <h4>

   ```java 
   // TODO 第一种写法：使用LongAdder进行计数（不规范写法）
   public static void main(String[] args) throws InterruptedException {
        LongAdder counter = new LongAdder();
        // 多线程并发累加
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.add(1);
            }
        };
        for (int i = 0; i < 10000; i++) {
            new Thread(task, i + "").start();
        }
        System.out.println("Total count: " + counter.sum());
	    // 这里打印输出的值可能会小于10000000，因为主线程未等待所有子线程执行完毕，就直接调用 sum() 读取了中间状态的值。
        // 而 LongAdder 的 sum() 在并发过程中可能不是精确值，但在所有线程结束后调用 sum() 可以保证正确性。
   }

   
   // TODO 第二种写法：使用LongAdder进行计数（规范写法）
   public static void main(String[] args) throws InterruptedException {
       LongAdder counter = new LongAdder();
       // 多线程并发累加
       Runnable task = () -> {
           for (int i = 0; i < 1000; i++) {
               counter.add(1);
           }
       };
       for (int i = 0; i < 10000; i++) {
           Thread thread = new Thread(task, i + "");
           thread.start();
           thread.join(); // 使用 Thread.join() 等待所有子线程执行完毕
       }
       System.out.println("Total count: " + counter.sum());
	   // 这里打印输出的值即是10000000，因为主线程等待所有子线程执行完毕后再调用 sum() 读取了最终的值。
   }
   
   
   // TODO 第三种写法：使用LongAdder进行计数（使用线程池写法）
   public static void main(String[] args) throws InterruptedException {
       LongAdder counter = new LongAdder();
       // 多线程并发累加
       Runnable task = () -> {
           for (int i = 0; i < 1000; i++) {
               counter.add(1);
           }
       };
	   // 上述第二种写法创建了10000个线程，使用了大量的系统资源。因此在高并发的情况下，推荐使用线程池来管理线程。
       // newFixedThreadPool 创建的线程池不会在线程满时直接触发拒绝策略，而是会将任务放入无界工作队列中等待执行。
       ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
       for (int i = 0; i < 10000; i++) {
           executor.submit(task);
       }
       executor.shutdown(); // 等待所有线程全部执行完毕后关闭线程池
	   // 注意：awaitTermination()方法需结合shutdown()方法使用，并在shutdown()方法后调用。
	   //      表示线程池的有效执行时间为1min，1min之后不管子任务有没有执行完毕，都要关闭线程池。
	   if (!es.awaitTermination(1, TimeUnit.MINUTES)) {
		   System.out.println(" 到达指定时间，还有线程没执行完，不再等待，关闭线程池!");
		   es.shutdownNow();
	   }
       System.out.println("Total count: " + counter.sum());
   }
   ```

  </h4>
  </figure>
</figure>
