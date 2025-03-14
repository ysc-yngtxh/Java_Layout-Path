package J10_集合.集合Ⅱ_Map详解;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-27 19:20
 * @apiNote TODO 两种比较排序方法
 */
public class 集合21_Comparable与Comparator比较器 {
    /*
     * 1、Comparable：可以直接在需要进行排序的类中实现，重写compareTo(T o)方法；缺点是要实现Comparable接口
     * 2、Comparator：需要另外一个实现 Comparator 接口的实现类来作为 “比较器”。不用实现接口，直接编写排序逻辑
     * 3、Comparable 和 Comparator 如何选择？
     *     Comparable 可以直接在需要进行排序的类中实现，重写compareTo(T o)方法；缺点是要实现Comparable接口
     *     Comparator 需要另外一个实现 Comparator 接口的实现类来作为 “比较器”。不用实现接口，直接使用匿名内部类即可实现
     *     当比较规则不会发生改变的时候，或者说当比较规则只有1个的时候，建议实现Comparable接口
     *     如果比较规则多个，并且需要多个比较规则之间频繁切换，建议使用Comparator接口
     */
    public static void main(String[] args) {
        // 这里使用的元素类型为Object，没有实现Comparable接口并重写compareTo()方法，所以这里代码会报错
        // List<Object> list1 = new ArrayList<>();
        // Collections.addAll(list1, 1,3,6,34,67,98,34);
        // Collections.sort(list1);

        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1,3,6,34,67,98,34);
        // 这里可以参考源码，Integer是实现了Comparable接口的，而Collections排序就是通过CompareTo()方法比较实现的
        Collections.sort(list);
        
        // 在Java中，基本数据类型（如int、double、char等）并不是对象，因此它们不能实现接口，包括Comparable接口。
        // 对于基本数据类型的数组，如int[]，Java提供了特化的Arrays.sort()方法重载版本，
        // 这些版本内部实现了针对基本数据类型的比较逻辑，因此不需要依赖Comparable接口。
        int[] arr = {4,6,8,786,432,768,42};
        Arrays.sort(arr);

        List<Available> arrayList = new ArrayList<>(){{
            add(new Available(1000));
            add(new Available(5000));
            add(new Available(400));
            add(new Available(8000));
        }};
        // 使用Comparable接口实现排序，前提要实现Comparable接口，并重写compareTo()方法
        Collections.sort(arrayList);
        // 假如Available没有实现Comparable接口。可以使用Comparator实现排序，直接在代码中实现排序逻辑，比较灵活
        Collections.sort(arrayList, Comparator.comparing(Available::getAge)); 
    }
}

class Available implements Comparable<Available> {
    int age;
    public int getAge() {
        return age;
    }
    public Available(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Available{" + "age=" + age + '}';
    }
    @Override
    public int compareTo(Available o) {
        return this.age-o.age;
    }
}
