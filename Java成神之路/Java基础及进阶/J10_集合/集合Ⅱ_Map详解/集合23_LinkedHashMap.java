package J10_集合.集合Ⅱ_Map详解;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/08/13 19:10
 */
public class 集合23_LinkedHashMap {
    /*
     * LinkedHashMap 可以认为是 HashMap+LinkedList，即它既使用HashMap操作数据结构，又使用LinkedList维护插入元素的先后顺序。
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


        // LRU(Least Recently Used)，即最近最少使用，也称淘汰算法【选择最近最久未使用的页面予以淘汰】
        Map<String, Integer> lruCache = new LinkedHashMap<>(100, 0.75f, true) {
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
        /*
         * 总结：LinkedHashMap 通过覆盖 removeEldestEntry 方法，实现固定大小的缓存，淘汰最久未使用的条目。
         *      并将最新的元素放在链表的尾部，最老的元素放在链表的头部。
         */
    }
}
