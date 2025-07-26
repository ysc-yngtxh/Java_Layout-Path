package J10_集合.集合Ⅰ_Collection详解.List接口;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/* ArrayList集合：是非线程安全的
 *   1、默认初始化容量是10
 *   2、集合底层是Object[]
 *   3、构造方法：
 *         new ArrayList();
 *         new ArrayList(20);
 *   4、ArrayList集合的扩容
 *         增长到原来的1.5倍。
 *         ArrayList集合底层是数组
 *            尽可能少的扩容。因为数组扩容效率比较低，建议在使用ArrayList集合的时候预估计元素的个数，给定一个初始化容量
 *   5、数组优点：
 *         检索效率比较高。
 *         (每个元素占用空间大小相同，内存地址是连续的，知道首元素内存地址，然后知道下标，通过数学表达式计算出元素的内存地址，所以检索效果高)
 *   6、数组缺点：
 *         随即增删元素效率比较低
 *         另外数组无法存储大数据量(很难找到一块非常巨大的连续的内存空间)
 *   7、向数组末尾添加元素，效率很高，不受影响
 *   8、ArrayList 集合的线程不安全主要体现在以下几个方面：
 *      ①、并发修改问题（Modification During Iteration）
 *         问题表现：当一个线程正在迭代 ArrayList，而另一个线程同时修改它时，会抛出 ConcurrentModificationException。
 *      ②、添加元素时的扩容问题
 *         问题表现：多个线程同时添加元素可能导致数组越界或元素丢失。
 *         原因：ArrayList 的 add() 方法不是原子操作，它包含三个步骤：
 *              检查容量
 *              添加元素
 *              增加 size
 *         在多线程环境下，可能出现：
 *              两个线程同时执行 ensureCapacityInternal，都认为不需要扩容
 *              然后都尝试在相同位置写入数据
 *              最终导致数据覆盖或数组越界
 *      ③、size 的自增非原子性
 *         问题表现：size 的最终值可能小于实际添加的元素数量。
 *         原因：size++ 不是原子操作，它实际上是：
 *              读取 size 值
 *              增加 size 值
 *              写回 size 值
 *         多线程环境下可能出现多个线程读取到相同的 size 值，然后各自增加后写回，导致部分增加操作"丢失"。
 *   9、面试官经常问的一个问题？
 *         这么多的集合中，你用那个集合最多？
 *         答：ArrayList集合
 *            因为往数组末尾添加元素，效率不受影响
 *            另外，我们检索/查找某个元素的操作比较多
 */
public class 集合11_ArrayList集合初始化容量及扩容 {

	public static void main(String[] args) {
		// 默认初始化容量10
		List<Object> list = new ArrayList<>();
		System.out.println(list.size());  // 0

		// 指定初始化容量20
		List<Object> list2 = new ArrayList<>(100); // ArrayList集合扩容
		System.out.println(list2.size()); // 0
		// 集合的size()方法是获取当前集合中元素的个数。不是获取集合的容量

		// 创建一个HashSet集合
		Collection<Object> set = new HashSet<>();
		set.add(100);
		set.add(200);
		set.add(900);
		set.add(50);

		// 通过构造方法传入参数【HashSet集合对象set】，将HashSet集合转换成List集合。
		List<Object> list3 = new ArrayList<>(set);
		for (int i = 0; i < list3.size(); i++) {
			System.out.println(list3.get(i));
		}

		System.out.println(10 >> 1);   // 5
		// 右移1位   即00001010-->00000101
	}

}
