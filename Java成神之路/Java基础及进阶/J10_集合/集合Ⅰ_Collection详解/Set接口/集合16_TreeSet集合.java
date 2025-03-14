package J10_集合.集合Ⅰ_Collection详解.Set接口;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
  TreeSet集合存储元素特点：
      1、无序不可重复的，但是存储的元素可以自动按照大小顺序排序！
         称为：可排序集合
      2、无序：这里的无序指的是存进去的顺序和取出来的顺序不同。并且没有下标
 */
public class 集合16_TreeSet集合 {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>(){{
            add("A");
            add("B");
            add("Z");
            add("Y");
            add("Z");
        }};
        s.add("K");
        s.add("M");
        System.out.println("TreeSet集合：" + s);

        // Set集合转换成List集合
        List<String> strings = new ArrayList<>(s);
        System.out.println("ArrayList集合：" + strings);

        // List集合转换成Set集合
        Set<String> set = new TreeSet<>(strings);
        System.out.println("List集合转换成Set集合：" + set);
    }
}
