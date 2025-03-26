package P16_JUC并发.JUC并发Ⅴ_阻塞队列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/* 一、阻塞队列
 *    当阻塞队列是空时，从队列中获取元素的操作将会被阻塞
 *    当阻塞队列是满时，往队列中添加元素的操作将会被阻塞
 *
 * 二.阻塞队列的种类和方法
 *    阻塞队列有主要以下几种：
 *       ArrayBlockingQueue: 由数组结构组成的有界阻塞队列
 *       LinkedBlockingDeque: 由链表结构组成的有界(但大小默认值Integer>MAX_VALUE)阻塞队列
 *       PriorityBlockingQueue: 支持优先级排序的无界阻塞队列。默认情况下，优先级由对象的自然顺序决定。队列构建时提供的比较器可以覆盖默认优先级。
 *       DelayQueue: 使用优先级队列实现的延迟无界阻塞队列
 *       SynchronousQueue: 不存储元素的阻塞队列，即是单个元素的队列
 *       LinkedTransferQueue: 由链表结构组成的无界阻塞队列
 */
public class 阻塞队列1_抛出异常API {
    /**
     * ArrayBlockingQueue 是数组实现的线程安全的有界的阻塞队列。
     * 线程安全是指，ArrayBlockingQueue 内部通过“互斥锁”保护竞争资源，实现了多线程对竞争资源的互斥访问。
     * 有界，则是指 ArrayBlockingQueue 对应的数组是有界限的。
     * 阻塞队列，是指多线程访问竞争资源时，当竞争资源已被某线程获取时，其它要获取该资源的线程需要阻塞等待；
     * 而且，ArrayBlockingQueue 是按 FIFO（先进先出）原则对元素进行排序，元素都是从尾部插入到队列，从头部开始返回。
     *
     * 什么时候使用队列？多线程并发处理，线程池！
     * 四组API
     *    1、抛出异常
     *    2、不会抛异常
     *    3、阻塞等待
     *    4、超时等待
     */
    public static void main(String[] args) {
        test();
    }

    /**
     * 队列溢出或者队列空取会抛出异常，且异常没有返回值
     */
    public static void test() {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3); // 队列卡槽为3个
        // 往队列中添加元素
        System.out.println(queue.add("a")); // true
        System.out.println(queue.add("b")); // true
        System.out.println(queue.add("c")); // true
        // System.out.println(queue.add("d")); // 队列已满，再添加就会抛异常IllegalStateException: Queue full

        // 从队列中取出元素
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        // System.out.println(queue.remove()); // 队列已空，再取就会抛异常NoSuchElementException

        System.out.println("队列元素：" + queue);

        System.out.println("===========================================================================");

        queue.add("A");
        queue.add("B");
        queue.add("C");
        List<Object> list = new ArrayList<>();
        // 移除此队列中所有可用的元素，并将它们添加到给定 collection 中。
        int drain = queue.drainTo(list);
        System.out.println("drainTo方法的返回值：" + drain + " - 队列元素：" + queue);
        System.out.println("drainTo方法中转移的collection集合：" + list);

        queue.add("E");
        queue.add("F");
        queue.add("G");
        List<Object> list2 = new ArrayList<>();
        // 最多从此队列中移除给定数量的可用元素，并将这些元素添加到给定 collection 中。
        int drains = queue.drainTo(list2, 1);
        System.out.println("drainTo方法两形参的返回值：" + drains + " - 队列元素：" + queue);
        System.out.println("drainTo方法两形参中转移的collection集合：" + list2);

        // 获取但不移除此队列的头部元素；如果此队列为空，则返回 null。
        System.out.println("peek方法获取队列头部元素，不移除元素：" + queue.peek());
        // 获取但不移除此队列的头部元素；如果此队列为空，则报错抛异常。
        System.out.println("element方法获取队列头部元素，不移除元素：" + queue.element());
        System.out.println(queue);
        // 如果此队列包含指定的元素，则返回 true。
        System.out.println("contains方法判断队列包含指定元素：" + queue.contains("B"));

        // 从此队列中移除指定元素的单个实例（如果存在）。
        System.out.println("remove方法删除队列中指定的元素：" + queue.remove("B"));
        // 返回此队列中元素的数量。
        System.out.println("size方法查看此队列中元素的数量：" + queue.size());

        // 返回一个按适当顺序包含此队列中所有元素的数组。
        Object[] array = queue.toArray();
        System.out.println("toArray方法将队列元素转为Object类型数组：" + Arrays.toString(array));
        // 返回一个按适当顺序包含此队列中所有元素的数组；返回数组的运行时类型是指定数组的运行时类型。
        String[] str = new String[3];
        String[] array1 = queue.toArray(str);
        System.out.println("toArray方法形参将队列元素转为指定类型数组：" + Arrays.toString(array1));

        // 自动移除此队列中的所有元素。
        queue.clear();

        System.out.println("队列元素：" + queue);
    }
}
