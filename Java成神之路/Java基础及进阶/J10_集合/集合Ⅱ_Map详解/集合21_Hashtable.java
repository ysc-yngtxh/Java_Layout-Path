package J10_集合.集合Ⅱ_Map详解;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/*
 * 1、Hashtable集合底层也是哈希表数据结构，是线程安全的，其中所有的方法都带有 synchronized 关键字，效率较低，现在使用较少了，
 *    因为控制线程安全有其他更好的方案。比如：ConcurrentHashMap
 *
 * 2、Hashtable的 Key 可以为 null 吗？
 *      Hashtable 的 Key 和 Value 不允许 null 的，且都可以为空
 *      HashMap 集合的 Key 和 Value 都是可以为 null 的，且都可以为空
 *      ①、HashTable 的保守设计：
 *         作为 Java 早期的线程安全集合（JDK 1.0），HashTable 的设计偏向保守，
 *         禁止 null 键值以减少潜在的错误（如 NullPointerException）和并发场景的复杂性。
 *      ②、HashMap 的灵活设计：
 *         HashMap（JDK 1.2 引入）作为非线程安全的替代方案，更注重灵活性和开发便利性。
 *         允许 null 键值使得某些场景（如表示“未设置”或“缺失”状态）更简洁，无需引入特殊占位符。
 *
 *    分析：在多线程环境中，如果Hashtable允许 key或value 为null，那么当调用 get(key)方法 返回null时，
 *         我们无法判断这个null是因为key根本不存在，还是因为对应的value值就是null。
 *         这种二义性在单线程环境中可以通过调用 containsKey(key)方法 来消除，
 *         但在多线程环境中，由于并发操作的存在，这种判断可能变得不准确，从而引发线程安全问题。
 *         因此在源码中：putVal(K key, V value, boolean onlyIfAbsent) {
 *                 		  // 首先会判断 key 和 value 是否为 null,如果是则抛出异常
 *                         if (key == null || value == null) throw new NullPointerException();
 *                     }
 *         至于 “空” 的概念，它通常指的是一个对象的内容为空，而不是对象引用本身为null。
 *         在Hashtable中，只要key和value不是null，即使它们的内容为空（例如，一个空字符串或一个空的集合），也是可以被接受的。
 *         但需要注意的是，对于Hashtable来说，它关注的是对象的引用而非内容，因此，即使内容为空，引用本身也不能为null。
 *
 * 3、Hashtable集合初始化容量11
 *      Hashtable集合扩容，原容量*2+1
 *
 * 4、目前只需要掌握 Properties 属性类对象的相关方法即可
 *      Properties是一个 Map集合，继承 Hashtable,
 *      Properties的 Key 和 Value 都是String类型
 *      Properties被称为属性类对象
 *      Properties是线程安全的
 */
public class 集合21_Hashtable {
    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        // HashMap集合转换成线程安全的集合
        Map<Object, Object> map1 = Collections.synchronizedMap(map);
        map1.put(null,  "100");
        map1.put(100,   "null");
        map1.put("",    "null");
        map1.put("200", "");
        System.out.println(map);

        Map<Object, Object> map2 = new Hashtable<>();
        map2.put("",   "游家纨绔");
        map2.put(null, "游家纨绔");
        map2.put(222,  "null");
        map2.put(333,  "曹家千金");
        // Hashtable的key和value不允许null的，否则会出现空指针异常：java.lang.NullPointerException
        System.out.println(map.get(null));
        System.out.println(map.get(222));

        System.out.println("=========================================================================================");

        // 创建一个Properties对象
        Properties pro = new Properties();
        // 需要掌握Properties的两个方法，一个存，一个取
        pro.setProperty("url", "mysql:jdbc://localhost:3306/springdb");
        pro.setProperty("driver", "com.mysql.cj.jdbc");
        pro.setProperty("username", "root");
        pro.setProperty("password", "123");
        // 通过key获取value
        String ur1 = pro.getProperty("url");
        String ur2 = pro.getProperty("driver");
        String ur3 = pro.getProperty("username");
        String ur4 = pro.getProperty("password");
        System.out.println(ur1);
        System.out.println(ur2);
        System.out.println(ur3);
        System.out.println(ur4);
    }
}
