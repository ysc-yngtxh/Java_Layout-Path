package J10_集合.集合Ⅱ_Map详解;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* HashMap集合：
 *    1、HashMap集合底层是哈希表/散列表的数据结构
 *
 *    2、哈希表是一个怎样的数据结构:
 *         哈希表是一个数组和单向链表的结合体
 *         数组：在查询方面效率很高，随即增删方面效率较低
 *         单向链表：在随即增删方面效率较高，在查询方面效率较低
 *         哈希表将以上的两种数据结构融合在一起，充分发挥他们各自的优点
 *
 *    3、HashMap集合底层的源码：
 *       public class HashMap{
 *            // HashMap底层实际上就是一个数组（一维数组）
 *            Node<K,V>[] table;
 *            // 静态的内部类HashMap.Node
 *            static class Node<K, V> {
 *                 // Node对象中有四个元素
 *                 final int hash;  // 哈希值是key的hashCode()方法的执行结果。hash值通过哈希函数/算法，可以转换成key数组的下标
 *                 final K key;     // 存储到Map集合中的那个key
 *                 V value;         // 存储到Map集合中的那个value
 *                 Node<K,V> next;  // 下一个节点的内存地址
 *            }
 *        }
 *        哈希表/散列表：一维数组，这个数组中每一个元素是一个单向链表/红黑树（数组和链表的结合体）
 *                     当链表长度超过阈值（默认8）时，链表转为红黑树；当红黑树节点数低于阈值（默认6）时，退化为链表。
 *
 *        注意：同一个单向链表上所有结点的hash相同，因为他们的数组下标是一样的，
 *             但同一个链表上K和K的equals方法肯定返回的是false，都不相等。
 *
 *   4、最主要掌握的是：
 *      ①、map.put<K,V>实现原理：
 *           第一步：先将<K,V>封装到Node对象当中。
 *           第二步：底层会调用K的hashCode()方法得出hash值。
 *           然后通过哈希函数/哈希算法，将hash值转换成数组的下标，下标的位置上如果没有任何元素，就把Node添加到这个位置上了。
 *           如果下标对应的位置上存在链表，说明K的hash值冲突，此时就需要将K和链表上每个节点中的K值进行 equals() 比较【注意：比较的是K值】
 *           如果所有的 equals() 方法返回都是false，说明该Map不存在该值，会将这个新节点添加到链表的头部【jdk8之前是尾插法】
 *           如果其中有一个 equals() 返回了true，那么Map原本就存在对应K的数据，这个新的节点会把旧节点的 value值 替换掉
 *       ②、V = map.get(K)实现原理：
 *           先调用K的hashCode()方法得出哈希值，通过哈希算法转换成数组下标，通过数组下标快速定位到某个位置上。
 *           如果这个位置上什么都没有，返回null，如果这个位置上有单向链表，那么会拿着参数K和单向链表上的每个节点中的K进行equals，
 *           如果所有的equals方法返回false，那么get方法返回null，只要其中有一个节点的K和参数K进行equals的时候返回true，
 *           那么此时这个节点的value就是我们要找的value，get方法最终返回这个要找的value
 *
 *   5、HashMap集合的key部分特点：
 *         无序，不可重复。
 *         为什么无序？因为不一定挂到哪个单向链表上
 *         不可重复是怎么保证的？equals方法来保证HashMap集合的key不可重复
 *         如果key重复了，value会覆盖
 *         HashMap集合的key和value都是可以为null的
 *
 *         放在HashMap集合的key部分元素其实就是放到HashMap集合中了
 *         所以HashMap集合中的元素也需要同时重写 hashCode() + equals() 方法
 *
 *   6、哈希表HashMap使用不当时无法发挥性能！
 *       假设将所有的hashCode()方法返回值固定为某个值，那么会导致底层哈希表变成了纯单向链表。这种情况我们称为：散列分布不均匀
 *
 *       什么是散列分布不均匀？
 *           假设有100个元素，10个单向链表，那么每个单向链表上有10个节点，这是最好的，是散列分布均匀的。
 *           假设将所有的hashCode()方法返回值都设定为不一样的值，可以吗？有什么问题？
 *              不行，因为这样的话导致底层哈希值就成为一维数组了，没有链表的概念了，也是散列分布不均匀。
 *
 *       散列分布均匀需要你重写hashCode()方法时有一定的技巧
 *
 *    7、重点：放在HashMap集合key部分的元素，以及放在HashSet集合中的元素，需要同时重写hashCode和equals方法
 *
 *    8、HashMap集合的默认初始化容量是16，默认加载因子是0.75
 *          这个默认加载因子是当HashMap集合底层数组的容量达到75%的时候，数组开始扩容，扩容倍数是原来的2倍。
 *
 *          重点记住：HashMap集合初始化容量必须是2的幂数，这也是官方推荐的，
 *                   这是因为散列分布均匀，为了提高HashMap集合的存取效率所必须的
 */
public class 集合19_哈希表数据结构 {

	public static void main(String[] args) {
        /* 为什么哈希表的随机增删，以及查询效率都很高？
         *    增删是在链表上完成
         *    查询是在数组上进行的，不需要都扫描，只需要部分扫描
         *
         * 重点：通过讲解可以得出HashMap集合的key，会先后调用两个方法，
         *       一个是hashCode(),一个方法是equals()，那么这两个方法都需要重写
         */

		// 测试HashMap集合key部分的元素特点
		// Integer是key，他的hashCode和equals都重写了
		Map<Integer, String> map = new HashMap<>();
		map.put(111, "张三");
		map.put(666, "李四");
		map.put(777, "王五");
		map.put(222, "赵六");
		map.put(222, "游家纨绔"); // key重复，value覆盖
		System.out.println(map.size());

		// 遍历方法一：获取所有的key，来遍历value
		Set<Integer> s = map.keySet();
		Iterator<Integer> it = s.iterator();
		while (it.hasNext()) {
			Integer key = it.next();
			String value = map.get(key);
			System.out.println(key + "==" + value);
		}
		// 遍历方法二：将Map集合转换成Set集合
		Set<Map.Entry<Integer, String>> set = map.entrySet();
		Iterator<Map.Entry<Integer, String>> it1 = set.iterator();
		while (it1.hasNext()) {
			Map.Entry<Integer, String> node = it1.next();
			Integer get1 = node.getKey();
			String value1 = node.getValue();
			System.out.println(get1 + "---" + value1);
			System.out.println(node);
		}
	}

}
