package I9集合.Collection详解2.Set接口9;

import java.util.Set;
import java.util.TreeSet;

/*
TreeSet集合存储元素特点：
      1、无序不可重复的，但是存储的元素可以自动按照大小顺序排序！
      称为：可排序集合

      2、无序：这里的无序指的是存进去的顺序和取出来的顺序不同。并且没有下标
 */
public class TreeSet集合2 {
    public static void main(String[] args) {

        Set<String> s = new TreeSet<>();
        s.add("A");
        s.add("B");
        s.add("Z");
        s.add("Y");
        s.add("Z");
        s.add("K");
        s.add("M");

        for (String t : s
             ) {
            System.out.println(t);
        }
    }
}
