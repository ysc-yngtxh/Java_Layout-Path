package J10_集合.集合Ⅱ_Map详解;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
  1、向Map集合中存取，都是先调用key的HashCode方法，然后再选择是否调用equals方法！
        拿put(K,V)举例，什么时候equals不调用？
             K.hashCode()方法返回哈希值
             哈希值经过哈希算法转换成数组下标
             数组下标位置上如果是null，equals不需要执行
        拿get<K>举例，什么时候equals不调用？
             K.hashCode()方法返回哈希值，
             哈希值经过哈希算法转换成数组下标
             数组下标位置上如果是null，equals不需要执行。

        Object类的 equals 方法比较的是对象的"内存地址"，而通常重写了的 equals 方法比较的是“对象内容值”
        Object类的 hashCode 方法是将对象的"内存地址"通过哈希算法转换成哈希值。

  2、注意⚠️：如果一个类的equals方法重写了，那么就必须重写其类的hashCode()方法！！！
       问：正常情况下比较两个类是否相等，只重写类的 equals 方法，调用其 equals 方法进行比较判断是否相等即可。
           那为什么还需要重写 hashCode 方法呢？
       答：假设有一个集合 Set<User> 中有上百万条数据，Set集合的特点是元素不重复，也就是Set集合是先进行去重之后再添加进去。
           如果Set集合通过 equals 方法进行数据去重，那插入数据的性能将会是灾难性的，因为去重比较次数将会是上百万次。
           因此，Set集合是先通过 hashCode 方法进行部分去重，然后再通过 equals 方法比较的。
           如果不重写 hashCode 方法，不同对象内存地址的hashCode()后哈希值可能相等，就会造成Set集合中数据被覆盖添加。
           例如：Set<User> = [User{id=1}] 集合添加元素 User{id=5}，但如果这两个User对象的哈希值相等，
                那么 Set<User> = [User{id=5}]，因为 User{id=1} 数据被覆盖掉了，这样肯定是不符合我们预期的。
           所以，这就必须规定了我们在重写 equals 方法时必须要重写 hashCode 方法，避免在某些场景下，出现程序执行的异常。
           类似场景：Map<User>......

  3、终极结论：
       hashCode 和 equals 两个方法是用来协同判断两个对象是否相等的，采用这种方式的原因是可以提高程序插入和查询的速度，
       如果在重写 equals 时，不重写 hashCode，就会导致在某些场景下，出现程序执行的异常，
       为了保证程序的正常执行，所以我们就需要在重写 equals 时，也一并重写 hashCode 方法才行。

      *** HashMap集合底层是哈希表数据结构，是非线程安全的。HashMap集合的默认初始化容量是16，默认加载因子是0.75
          在JDK8之后，如果哈希表单向链表中元素超过8个，单向链表这种数据结构会变成红黑树数据结构。
          当红黑树上的节点数量小于6时，会重新把红黑树变成单向链表数据结构。
          这种方式也是为了提高检索效率，二叉树的检索会再次缩小扫描范围，进而提高效率。
 */
public class 集合19_同时重写HashCode和Equals方法 {
    public static void main(String[] args) {
        Student s1 = new Student("曹玉敏");
        Student s2 = new Student("曹玉敏");
        System.out.println(s1.equals(s2));

        // hashCode是用来在散列存储结构中确定对象的存储地址的；
        System.out.println("s1的hashCode: " + s1.hashCode());
        System.out.println("s2的hashCode: " + s2.hashCode());

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