package J10_集合.集合Ⅲ_工具类;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
  java.util.Collection  集合接口
  java.util.Collections  集合工具类，方便集合的操作
 */
public class 集合25_Collections工具类 {
    public static void main(String[] args) {
        // ArrayList集合不是线程安全的
        List<String> i = new ArrayList<>();
        // 变成线程安全的
        Collections.synchronizedList(i);

        // 排序
        i.add("adf");
        i.add("abx");
        i.add("abc");
        i.add("abe");
        Collections.sort(i);
        System.out.println(i);

        // 对Set集合怎么排序呢？
        Set<String> set = new HashSet<>();
        set.add("king");
        set.add("kingSoft");
        set.add("king1");
        set.add("king2");
        // 将set集合转换成List集合
        List<String> myList = new ArrayList<>(set);
        Collections.sort(myList);
        System.out.println(myList);

        // 对自己创建的类进行List集合排序
        List<WuGui2> wuGui2s = new ArrayList<>();
        wuGui2s.add(new WuGui2(1000));
        wuGui2s.add(new WuGui2(5000));
        wuGui2s.add(new WuGui2(400));
        wuGui2s.add(new WuGui2(8000));
        // TODO 注意：对List集合中元素排序，需要保证List集合中的元素实现了：Comparable接口
        Collections.sort(wuGui2s);
        // 使用外部定义的比较器实现对List集合中元素排序，比Comparable需要实现要方便
        Collections.sort(wuGui2s, Comparator.comparing(WuGui2::getAge));
        System.out.println(wuGui2s);
        // Collections.sort(list集合, 比较器对象); // 这种方式也可以排序

        // 对List集合中的元素进行倒序排序
        Collections.reverse(wuGui2s);
        System.out.println(wuGui2s);
    }
}

class WuGui2 implements Comparable<WuGui2> {
    int age;

    public int getAge() {
        return age;
    }

    public WuGui2(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "WuGui2{" +
                "age=" + age +
                '}';
    }

    @Override
    public int compareTo(WuGui2 o) {
        return this.age-o.age;
    }
}