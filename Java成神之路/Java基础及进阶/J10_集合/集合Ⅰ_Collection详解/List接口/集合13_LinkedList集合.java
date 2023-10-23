package J10_集合.集合Ⅰ_Collection详解.List接口;

import java.util.LinkedList;
import java.util.List;

/*
  LinkedList集合底层采用了双向链表数据结构
     1、LinkedList集合是双向链表
     2、对于链表数据结构来说，随即增删效率较高。检索效率较低
     3、链表中的元素在空间存储上，内存地址不连续
 */
public class 集合13_LinkedList集合 {
    public static void main(String[] args) {
        // LinkedList集合有初始化容量吗？没有
        // 不管是LinkedList还是ArrayList，以后写代码时不需要关心具体是哪个集合
        // 因为我们要面向接口编程，调用的方法都是接口中的方法
        // List list2 = new ArrayList();           // 这样写表示底层你用了数组
        List<Object> list3 = new LinkedList<>();   // 这样写表示底层你用了双向链表

        //以下这些方法你面向的都是接口编程
        list3.add(123);
        list3.add(456);
        list3.add(789);

        for (int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i));
        }
    }
}