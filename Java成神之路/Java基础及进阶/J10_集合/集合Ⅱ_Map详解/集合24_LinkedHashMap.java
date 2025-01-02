package J10_集合.集合Ⅱ_Map详解;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/08/13 19:10
 */
public class 集合24_LinkedHashMap {
    /**
     * LinkedHashMap
     * 1、Key和Value都允许空
     * 2、Key重复会覆盖、Value允许重复
     * 3、有序 -- 指的是存进去的数据跟取出来的数据顺序是一样的[这与HashMap最大的区别]
     * 4、非线程安全
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

        Map<String, String> hashMap = new HashMap<String, String>(){{
            put("A", "1");
            put("B", "2");
            put("D", "4");
            put("C", "3");
            put("E", "5");
            put(null, "6");
            put(null, "6");
            put("", "6");
            put("", "");
            put(" ", "");
        }};
        System.err.println("HashMap = " + hashMap);
    }
}