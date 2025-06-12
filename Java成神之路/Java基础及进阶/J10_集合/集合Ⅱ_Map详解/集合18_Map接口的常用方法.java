package J10_集合.集合Ⅱ_Map详解;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* java.util.Map 接口中常用方法：
 *    1、Map和Collection没有继承关系
 *    2、Map集合以key和value的方式存储数据：键值对
 *        key和value都是引用数据类型
 *        key和value都是存储对象的内存地址
 *        key起到主导的地位，value是key的一个附属品
 *    3、Map接口中的常用方法：
 *       V put(K key, V value) 向Map集合中添加键值对
 *       V get(Object key)     通过key获取value
 *       void clear()          清空Map集合
 *       boolean containsKey(Object key)      判断Map中是否包含某个key
 *       boolean containsValue(Object value)  判断Map中是否包含某个value
 *       boolean isEmpty()     判断Map集合中元素是否为0
 *       Set<K> keySet()       获取Map集合所有的key(所有的键是一个set集合)
 *       V remove(Object key)  通过key删除键值对
 *       int size()            取Map集合中键值对的个数
 *       Collection<V> values()获取Map集合中所有的value，返回一个Collection
 *
 *       Set<Map.Entry<K,V>> entrySet()
 *          将Map集合转换成Set集合
 *          假设有一个Map集合，如下图所示：
 *             map集合对象
 *             key      value
 *             ---------------
 *             1         zhangSan
 *             2         lisi
 *             3         wangWu
 *             4         zhaoLiu
 *
 *             Set set = map.entrySet();
 *
 *             Set集合对象
 *             1=ZhangSan   [注意：Map集合通过entrySet()方法转换成的这个Set集合，Set集合中元素的类型是Map.Entry<K,V>]
 *             2=lisi       [Map.Entry和String一样，都是一种类型的名字，只不过：Map.Entry是静态内部类，是Map中的静态内部类]
 *             3=WangWu
 *             4=ZhaoLiu
 *
 *       效率比较：对于 keySet() 其实是遍历了2次，一次是转为iterator，一次就从HashMap中取出Key所对应的Value。
 *               而 entrySet() 只是遍历了第一次(转为iterator)，他把Key和Value都放到了Entry中，所以就快了。
 *
 *               对于 entrySet() 来说：entry.getValue() 可以直接拿到 value,
 *               对于 keySet() 来说：hashmap.get(key) 是先得到Entry对象(遍历key的hash值【Map数组】)，再通过entry.getValue()去拿，
 *                                 直白点说就是 hashmap.get(key) 走了一个弯路，所以它慢一些；
 */
public class 集合18_Map接口的常用方法 {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "张三"); // 1在这里自动装箱
		map.put(3, "王五");
		map.put(2, "李四");
		map.put(4, "赵六");

		// 尝试计算指定键的新映射值（不管键是否存在）
		String compute1 = map.compute(4, (k, v) -> Objects.isNull(v) ? "钱七" : v);
		String compute2 = map.compute(5, (k, v) -> Objects.isNull(v) ? "钱七" : v);
		// 仅在【键不存在】或对应的值为 null 时执行计算
		//     key为5的值存在，不会执行 Function计算，返回值为原值"钱七"；
		//     key为6的值不存在，所以执行 Function计算 并将键值对放入Map，返回值为新值"朱八"。
		String computedIfAbsent1 = map.computeIfAbsent(5, (key) -> "朱八");
		String computedIfAbsent2 = map.computeIfAbsent(6, (key) -> "朱八");
		// 仅在【键存在】且对应的值不为 null 时执行计算
		//     key为6的值存在，会执行 BiFunction计算 并将键值对放入Map，返回值为新值"郑九"；
		//     key为7的值不存在，不会执行 BiFunction计算，返回值为null。
		String computedIfPresent1 = map.computeIfPresent(6, (k, v) -> "郑九");
		String computedIfPresent2 = map.computeIfPresent(7, (k, v) -> "郑九");
		// 获取指定键的值，如果键不存在则返回默认值
		String mapOrDefault = map.getOrDefault(8, "吴十");
		// 合并指定键的当前值和给定值
		//     如果key为4的值已经存在，则将key为4的value值和 "萧十一" 进行拼接，并更新Map值
		String merge = map.merge(4, "萧十一", (oldValue, newValue) -> oldValue + newValue);

		// 第一种方式：获取所有的key，通过遍历key,来遍历value
		Set<Integer> keys = map.keySet();

		// 方法一：建立迭代器对象，遍历key，可以通过key获取value，
		Iterator<Integer> it = keys.iterator();
		while (it.hasNext()) {
			Integer key = it.next();
			String value = map.get(key);
			System.out.println(key + "=" + value);
		}
		// 方法二：增强for循环
		for (Integer key : keys) {
			System.out.println(map);
			System.out.println(key + "=" + map.get(key));
		}

		System.out.println("=========================================================================================");

		// 第二种方式：Set<Map.Entry<K,V>> entrySet()。这个方法是把Map集合直接全部转换成Set集合，效率比KeySet高
		Set<Map.Entry<Integer, String>> set = map.entrySet();
		// 遍历Set集合，每一次取出一个Node

		// 方法一：迭代器
		Iterator<Map.Entry<Integer, String>> it1 = set.iterator();
		while (it1.hasNext()) {
			Map.Entry<Integer, String> node = it1.next();
			Integer key = node.getKey();
			String value = node.getValue();
			System.out.println(key + "===" + value);
		}
		// 方法二：增强for循环
		// 这种方式效率比较高，因为获取key和value都是直接从node对象中获取的属性值
		// 这种方式比较适合于大数据量
		for (Map.Entry<Integer, String> node : set) {
			System.out.println(node.getKey() + "--->" + node.getValue());
			System.out.println(node);
			System.out.println(map);
		}
	}

}
