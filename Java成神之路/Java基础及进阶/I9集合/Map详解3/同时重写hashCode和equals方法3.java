package I9集合.Map详解3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
1、向Map集合中存取，都是先调用key的HashCode方法，然后再调用equals方法！
   equals方法有可能调用，也有可能不调用
      拿put(K,V)举例，什么时候equals不调用？
           K.hashCode()方法返回哈希值
           哈希值经过哈希算法转换成数组下标
           数组下标位置上如果是null，equals不需要执行

      拿get<K>举例，什么时候equals不调用？
           K.hashCode()方法返回哈希值，
           哈希值经过哈希算法转换成数组下标
           数组下标位置上如果是null，equals不需要执行。

2、注意：如果一个类的equals方法重写了，那么hashCode()方法必须重写。
并且equals方法返回如果是true，hashCode()方法返回的值必须一样。
        equals方法返回true表示两个对象相同，在同一个单向链表上比较。
        那么对于同一个单向链表上的节点来说，他们的哈希值是相同的。
        所以hashCode()方法的返回值也应该相同

3、hashCode()方法和equals()方法不用研究了，直接使用IDEA工具生成，但是这两个方法需要同时生成。

4、终极结论：
       放在HashMap集合key部分的，以及放在HashSet集合中的元素，需要同时重写hashCode方法和equals方法。


    ***HashMap集合底层是哈希表数据结构，是非线程安全的。HashMap集合的默认初始化容量是16，默认加载因子是0.75
       在JDK8之后，如果哈希表单向链表中元素超过8个，单向链表这种数据结构会变成红黑树数据结构。
       当红黑树上的节点数量小于6时，会重新把红黑树变成单向链表数据结构。
       这种方式也是为了提高检索效率，二叉树的检索会再次缩小扫描范围，进而提高效率。
 */
public class 同时重写hashCode和equals方法3 {
    public static void main(String[] args) {
        Student s1 = new Student("曹玉敏");
        Student s2 = new Student("曹玉敏");

        System.out.println(s1.equals(s2));

        // hashCode是用来在散列存储结构中确定对象的存储地址的；
        System.out.println("s1的hashCode:" + s1.hashCode());
        System.out.println("s2的hashCode:" + s2.hashCode());

        // s1.equals(s2)的结果已经是true了，那么往HashSet集合中放（HashSet集合特点：无序不可重复）
        Set<Student> set = new HashSet<>();
        set.add(s1);
        set.add(s2);
        System.out.println(set.size());
    }


}
class Student{
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
