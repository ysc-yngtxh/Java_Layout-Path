package J10_集合.集合Ⅱ_Map详解;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/*
  1、Hashtable集合底层也是哈希表数据结构，是线程安全的，其中所有的方法都带有synchronized关键字，效率较低，现在使用较少了，
     因为控制线程安全有其他更好的方案。比如：ConcurrentHashMap

  2、Hashtable的key可以为null吗？
       Hashtable的key和value不允许null的，且都可以为空
       HashMap集合的key和value都是可以为null的，且都可以为空

  3、Hashtable集合初始化容量11
     Hashtable集合扩容，原容量*2+1

  4、目前只需要掌握Properties属性类对象的相关方法即可
     Properties是一个Map集合，继承Hashtable,
     Properties的key和value都是String类型
     Properties被称为属性类对象
     Properties是线程安全的
 */
public class 集合20_Hashtable {
    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        map.put(null, "100");
        map.put(100, "null");
        map.put("", "null");
        map.put("200", "");
        System.out.println(map);

        Map<Object, Object> map2 = new Hashtable<>();
        map2.put("", "游诗成");
        map2.put(null, "游诗成");
        map2.put(222, "null");
        map2.put(333, "曹玉敏");
        // Hashtable的key和value不允许null的,否则会出现空指针异常：java.lang.NullPointerException
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