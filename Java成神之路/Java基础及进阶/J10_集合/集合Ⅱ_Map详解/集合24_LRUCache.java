package J10_集合.集合Ⅱ_Map详解;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 继承 LinkedHashMap 类实现自定义LRU缓存
public class 集合24_LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxCapacity;

    public 集合24_LRUCache(int maxCapacity) {
        // LinkedHashMap构造方法参数：
        //     initialCapacity：表示哈希表的初始容量（即底层数组的初始大小）
        //     loadFactor：负载因子（Load Factor），表示哈希表的填充比例阈值。
        //     accessOrder：设置访问顺序模式，默认值为 false。
        //                  当 accessOrder = true 时，每次访问（get 或 put）一个键值对，该条目会被移动到链表尾部。
        //                  当 accessOrder = false（默认值）时，链表仅维护插入顺序。
        super(maxCapacity, 0.75f, true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }

    // 获取热点数据（最近访问的 N 个）
    public List<K> getHotKeys(int n) {
        List<K> hotKeys = new ArrayList<>();
        Iterator<K> iterator = keySet().iterator();
        while (iterator.hasNext() && n-- > 0) {
            hotKeys.add(iterator.next());
        }
        return hotKeys;
    }
}
