package J10_集合.集合Ⅱ_Map详解;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-03-14 15:00:00
 */
public class 集合22_TreeMap {
	/* 1、TreeMap 的底层实现基于红黑树（Red-Black Tree）。红黑树是一种自平衡的二叉查找树，具有以下特点：
	 *      - 每个节点要么是红色，要么是黑色。
	 *      - 根节点是黑色。
	 *      - 所有叶子节点（NIL 节点，通常不显示）都是黑色。
	 *      - 如果一个节点是红色的，则它的两个子节点都是黑色的（即不能有两个连续的红色节点）。
	 *      - 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
	 * 2、TreeMap 通过以下方式保证元素的顺序：
	 *      - 自然顺序：如果键实现了 Comparable`接口，那么 TreeMap 将按照键的自然顺序进行排序。
	 *      - 自定义比较器：在创建 TreeMap 时，可以传入一个自定义Comparator对象，TreeMap 将根据这个比较器的规则来排序键。
	 * 3、TreeMap 的K键不能为null但可以为空，因为要使用 compareTo() 方法进行比较，为null可能会出现空指针异常。
	 */
	public static void main(String[] args) {
		// TreeMap的元素在存进去后，会根据键进行排序
		SortedMap<String, Object> treeMap = new TreeMap<>() {{
			put("D", "D");
			put("E", "E");
			put("C", "C");
			put("A", "A");
			put("F", "F");
			put("B", "B");
			put("", "");
			put(" ", " ");
			// put(null, "null"); // TreeMap的K键不能为null但可以为空
		}};
		System.err.println("TreeMap = " + treeMap);

		// TreeMap 是非线程安全的，可以通过 Collections.synchronizedSortedMap 方法来获取一个有序且线程安全的 TreeMap
		SortedMap<String, Object> sortedMap = Collections.synchronizedSortedMap(treeMap);
		sortedMap.put("D", "D²");
		System.err.println("SortedMap = " + sortedMap);
		// SortedMap 集合类的 subMap()方法：截取子Map【左闭右开】
		System.err.println("SortedMap集合截取子Map = " + sortedMap.subMap("D", "F"));

		// 自定义比较器
		TreeMap<Integer, QueueUp> treeMap2 =
				new TreeMap<>(Comparator.comparing(Integer::intValue)) {{
					put(3, new QueueUp("C", 22));
					put(1, new QueueUp("A", 20));
					put(2, new QueueUp("B", 21));
					put(4, new QueueUp("D", 23));
				}};
		System.err.println("自定义比较器 = " + treeMap2);
	}

}

class QueueUp {

	String name;
	int age;

	public QueueUp(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "QueueUp{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

}
