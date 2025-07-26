package J10_集合.集合Ⅱ_Map详解;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 游家纨绔
 * @date 2022-08-13 19:10:00
 * @description: TODO
 */
public class 集合23_LinkedHashMap {
	/* LinkedHashMap 可以认为是 HashMap+LinkedList，即它既使用HashMap操作数据结构，又使用LinkedList维护插入元素的先后顺序。
	 *   HashMap 数组部分为"桶"，哈希冲突部分为节点，LinkedHashMap 是 HashMap 的子类，底层使用双向链表维护节点的插入顺序。
	 *   LinkedHashMap的Entry类中包含两个指针（before 和 after），即保证了元素插入的先后顺序的实现
	 *   特点：
	 *   1、Key 和 Value值 都允许为null或者空
	 *   2、Key值 重复会覆盖、Value值 允许重复
	 *   3、有序 --> 指的是存进去的数据跟取出来的数据顺序是一样的【这与HashMap最大的区别】
	 *   4、非线程安全
	 *   5、应用场景：
	 *         ①、在需要按照插入顺序遍历的场景中使用
	 *         ②、在LRU缓存中使用（最近最少使用）
	 *             通过覆盖 removeEldestEntry 方法，实现固定大小的缓存，淘汰最久未使用的条目。
	 */
	public static void main(String[] args) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("A", "1");
		map.put("B", "2");
		map.put("D", "4");
		map.put("C", "3");
		map.put("E", "5");
		map.put(null, "6");
		map.put(null, "6");
		map.put("", "6");
		map.put("", "");
		map.put(" ", "");
		System.err.println("LinkedHashMap = " + map);


		// TODO LRU（Least Recently Used）缓存是一种常用的缓存淘汰策略，用于在有限的缓存空间中存储数据，
		//      其基本思想：如果数据最近被访问过，那么在未来它被访问的概率也更高。
		//                因此，LRU缓存会保留最近访问过的数据，并在缓存满时淘汰最久未使用的数据。
		// LinkedHashMap构造方法参数：
		//     initialCapacity：表示哈希表的初始容量（即底层数组的初始大小）
		//     loadFactor：负载因子（Load Factor），表示哈希表的填充比例阈值。
		//     accessOrder：设置访问顺序模式。
		//                  当 accessOrder = true 时，每次访问（get 或 put）一个键值对，该条目会被移动到链表尾部。
		//                  当 accessOrder = false（默认值）时，链表仅维护插入顺序。
		Map<String, Integer> lruCache = new LinkedHashMap<String, Integer>(10, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
				return size() > 5; // 最多保留5个条目
			}
		};
		lruCache.put("A", 1);
		lruCache.put("B", 2);
		lruCache.put("C", 3);
		lruCache.put("D", 4);
		lruCache.put("E", 5);
		System.err.println("LRU缓存 = " + lruCache); // {A=1, B=2, C=3, D=4, E=5}
		lruCache.get("A");
		lruCache.get("C");
		lruCache.put("F", 6);
		System.out.println("LRU缓存 = " + lruCache); // {D=4, E=5, A=1, C=3, F=6}
		// 获取热点数据【LinkedHashMap 链表尾部是最近被访问的数据】
		List<Map.Entry<String, Integer>> list = new ArrayList<>(lruCache.entrySet());
		Collections.reverse(list);
		System.out.println("热点数据 = " + list);    // [F=6, C=3, A=1, E=5, D=4]
		/* 总结：LinkedHashMap 通过覆盖 removeEldestEntry 方法，实现固定大小的缓存，淘汰最久未使用的条目。
		 *      并将最新的元素放在链表的尾部，最老的元素放在链表的头部。
		 */
	}

}
