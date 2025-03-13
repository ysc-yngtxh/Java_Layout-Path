package J10_集合.集合Ⅰ_Collection详解.Set接口;

import java.util.HashSet;
import java.util.Set;

/*
  HashSet集合：
      无序不可重复
           1、存储时顺序和取出时顺序不同
           2、不可重复
           3、放到HashSet集合中的元素实际上是放到HashMap集合的key部分了。
 */
public class 集合15_HashSet集合 {
    public static void main(String[] args) {
        // 演示一下HashSet集合特点
        Set<String> s = new HashSet<>();
        s.add("hello3");
        s.add("hello4");
        s.add("hello1");
        s.add("hello2");
        s.add("hello3");
        s.add("hello3");
        s.add("");
        s.add(null);

        // 遍历
        for (String r : s) {
            System.out.println(r);
        }
    }
}
