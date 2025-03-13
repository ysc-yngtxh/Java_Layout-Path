package J10_集合.集合Ⅰ_Collection详解.List接口;

import java.util.LinkedList;
import java.util.List;

/*
  对于链表数据结构来说：基本的单元节点Node

  对于单向链表来说：任何一个节点Node中都有两个属性：
         第一：存储的数据。  第二：下一节点的内存地址

  链表优点：
          由于链表上的元素在空间存储上内存地址不连续。
          所以随即增删元素的时候不会有大量元素位移，因此随即增删效率较高。
          在以后的开发中，如果遇到随即增删集合中的元素的业务比较多时，建议使用LinkedList.

  链表缺点：
          不能通过数学表达式计算被查找元素的内存地址，每一次查找都是从头节点开始遍历
          直到找到为止。所以LinkedList集合检索/查找的效率较低

  LinkedList集合底层采用了双向链表数据结构
     1、LinkedList集合是双向链表
     2、对于链表数据结构来说，随即增删效率较高。检索效率较低
     3、链表中的元素在空间存储上，内存地址不连续

  ArrayList：把检索发挥到极致(末尾添加元素效率还是很高的)
  LinkedList：把随即增删发挥到极致
  加元素都是往末尾添加：所以ArrayList用的比LinkedList多
*/

public class 集合12_LinkedList集合的链表结构 {
    public static void main(String[] args) {
        // LinkedList集合底层也是有下标的。LinkedList集合有初始化容量吗？没有
        // 注意：ArrayList之所以检索效率比较高，不是单纯因为下标的原因，是因为底层数组发挥的作用
        // LinkedList集合照样有下标，但是检索/查找某个元素的时候效率比较低，因为只能从头节点开始一个一个遍历
        // List<Object> list = new ArrayList();           // 这样写表示底层你用了数组
        List<Object> list = new LinkedList<>();           // 这样写表示底层你用了双向链表
        list.add("a");
        list.add("b");
        list.add("c");

        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            System.out.println(obj);
        }
        for (Object str : list) {
            System.out.println(str);
        }
    }
}
