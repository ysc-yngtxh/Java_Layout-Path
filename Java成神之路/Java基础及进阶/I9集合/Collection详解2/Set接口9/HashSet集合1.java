package I9集合.Collection详解2.Set接口9;

import java.util.HashSet;
import java.util.Set;

/*
HashSet集合：
      无序不可重复
 */
public class HashSet集合1 {

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
        for (String r : s
             ) {
            System.out.println(r);
        }
         /*
           1、存储时顺序和取出时顺序不同
           2、不可重复
           3、放到HashSet集合中的元素实际上是放到HashMap集合的key部分了。
          */
    }
}
