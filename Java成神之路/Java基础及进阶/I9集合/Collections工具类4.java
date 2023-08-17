package I9集合;

import java.util.*;

/*
java.util.Collection  集合接口
java.util.Colletions  集合工具类，方便集合的操作
 */
public class Collections工具类4 {
    public static void main(String[] args) {

        //ArrayList集合不是线程安全的
        List<String> i = new ArrayList<>();

        //变成线程安全的
        Collections.synchronizedList(i);

        //排序
        i.add("adf");
        i.add("abx");
        i.add("abc");
        i.add("abe");

        Collections.sort(i);
        for (String s : i
             ) {
            System.out.println(s);
        }

        //对自己创建的类进行List集合排序
        List<WuGui2> wuGui2s = new ArrayList<>();
        wuGui2s.add(new WuGui2(1000));
        wuGui2s.add(new WuGui2(5000));
        wuGui2s.add(new WuGui2(400));
        wuGui2s.add(new WuGui2(8000));
        //注意：对List集合中元素排序，需要保证List集合中的元素实现了：Comparable接口
        Collections.sort(wuGui2s);
        for (WuGui2 w : wuGui2s
        ) {
            System.out.println(w);
        }

        //对Set集合怎么排序呢？
        Set<String> set = new HashSet<>();
        set.add("king");
        set.add("kingsoft");
        set.add("king1");
        set.add("king2");
        //将set集合转换成List集合
        List<String> myList = new ArrayList<>(set);
        Collections.sort(myList);
        for (String s1 : myList
             ) {
            System.out.println(s1);
        }


        //Collections.sort(list集合,比较器对象);这种方式也可以排序
    }
}

class WuGui2 implements Comparable<WuGui2>{
    int age;

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