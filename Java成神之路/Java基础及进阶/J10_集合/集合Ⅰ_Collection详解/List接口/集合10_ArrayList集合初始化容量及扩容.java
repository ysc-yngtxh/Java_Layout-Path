package J10_集合.集合Ⅰ_Collection详解.List接口;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/*
  ArrayList集合：是非线程安全的
    1、默认初始化容量是10
    2、集合底层是Object[]
    3、构造方法：
          new ArrayList();
          new ArrayList(20);
    4、ArrayList集合的扩容
          增长到原来的1.5倍。
          ArrayList集合底层是数组
             尽可能少的扩容。因为数组扩容效率比较低，建议在使用ArrayList集合的时候预估计元素的个数，给定一个初始化容量
    5、数组优点：
          检索效率比较高。
          (每个元素占用空间大小相同，内存地址是连续的，知道首元素内存地址，然后知道下标，通过数学表达式计算出元素的内存地址，所以检索效果高)
    6、数组缺点：
          随即增删元素效率比较低
          另外数组无法存储大数据量( 很难找到一块非常巨大的连续的内存空间)
    7、向数组末尾添加元素，效率很高，不受影响
    8、面试官经常问的一个问题？
          这么多的集合中，你用那个集合最多？
          答：ArrayList集合
             因为往数组末尾添加元素，效率不受影响
             另外，我们检索/查找某个元素的操作比较多
 */
public class 集合10_ArrayList集合初始化容量及扩容 {
    public static void main(String[] args) {
        // 默认初始化容量10
        List<Object> list = new ArrayList<>();
        System.out.println(list.size());  // 0

        // 指定初始化容量20
        List<Object> list2 = new ArrayList<>(100); // ArrayList集合扩容
        System.out.println(list2.size()); // 0
        // 集合的size()方法是获取当前集合中元素的个数。不是获取集合的容量

        // 创建一个HashSet集合
        Collection<Object> c = new HashSet<>();
        // 添加元素到Set集合
        c.add(100);
        c.add(200);
        c.add(900);
        c.add(50);

        // 通过这个构造方法就可以将HashSet集合转换成List集合。
        List<Object> list3 = new ArrayList<>(c);   // 将HashSet集合对象通过引用c传到ArrayList集合里
        for (int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i));
        }

        System.out.println(10 >> 1);   // 5
        // 右移1位   即00001010-->00000101
    }
}