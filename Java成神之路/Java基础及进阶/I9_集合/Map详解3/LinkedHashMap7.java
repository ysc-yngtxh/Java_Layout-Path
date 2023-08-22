package I9_集合.Map详解3;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/08/13 19:10
 */
public class LinkedHashMap7 {
    public static void main(String[] args) {

        Map<String, String> m = new LinkedHashMap<>();
        m.put("A", "1");
        m.put("B", "2");
        m.put("C", "3");
        m.put("D", "4");
        m.put("E", "5");
        m.put(null, "6");
        m.put(null, "6");
        m.put("", "6");
        System.out.println(m);
    }
}
