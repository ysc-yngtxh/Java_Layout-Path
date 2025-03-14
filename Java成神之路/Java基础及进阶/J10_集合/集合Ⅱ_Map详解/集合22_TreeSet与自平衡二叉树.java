package J10_集合.集合Ⅱ_Map详解;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/*
 * 1、TreeSet集合底层实际上是一个TreeMap，TreeSet/TreeMap集合底层是自平衡二叉树，遵循左小右大原则存放
 *
 * 2、放到TreeSet集合中的元素，等同于放到TreeMap集合Key部分了。
 *
 * 3、TreeSet集合中的元素：无序不可重复，但是可以按照元素的 可排序集合
 *
 * 4、遍历二叉树的时候有三种方式：
 *       前序遍历：根左右
 *       中序遍历：左根右
 *       后序遍历：左右根
 *
 *       注意：前中后说的时 “根” 的位置
 *            “根”在前面是前序，“根”在中间是中序，“根”在后面是后续
 *
 * 5、TreeSet集合/TreeMap集合采用的是：中序遍历方式
 *       Iterator迭代器采用的是中序遍历方式，左根右。
 *
 * 6、TreeSet或者TreeMap集合中根据Key部分元素可排序的方式：
 *       第一种：放在集合中的元素实现java.lang.Comparable接口（需要重写compareTo方法）
 *       第二种：在构造TreeSet或者TreeMap集合的时候给他传一个比较器对象
 *
 */
public class 集合22_TreeSet与自平衡二叉树 {
    public static void main(String[] args) {
        // TODO 创建TreeSet集合的时候，需要使用这个比较器

        // 第一种写法：使用Comparable的内部比较规则，前提是Person类实现了Comparable，并重写了compareTo方法。不够灵活
        TreeSet<Person> person1 = new TreeSet<>();
        // 第二种写法，传进Comparator实现对象，只不过把比较规则写在了实现类中。简直就是脱裤子放屁
        TreeSet<Person> person3 = new TreeSet<>(new PersonComparator());
        // 第三种写法：使用Comparator的静态方法。推荐
        TreeSet<Person> person2 = new TreeSet<>(Comparator.comparing(Person::getAge));
        // 第四种写法：匿名内部类，把比较规则写在了匿名内部类中。可读性太差，但会更加灵活
        TreeSet<Person> person4 = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                // 年龄相同时按照名字排序
                if (o1.age == o2.age) {
                    // 姓名是String类型，可以直接比。调用String类的compareTo来完成比较
                    return o1.name.compareTo(o2.name);
                } else {
                    return o1.age - o2.age; // 年龄不相同时按照年龄大小排序
                }
            }
        }){{
            add(new Person("ZhangSan", 20));
            add(new Person("LiSi",     23));
            add(new Person("WangWu",   23));
            add(new Person("WangW",    23));
        }};
        System.err.println(person4);
    }
}

class Person implements Comparable<Person> { // String类和Integer类都有Comparable接口，和重写compareTo方法
    String name;
    int age;
    public int getAge() {
        return age;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /* compareTo() 方法的返回值很重要：
     *     返回=0，表示相同，value会覆盖
     *     返回>0，会继续在右子树上找。【10-9=1，1>0的说明左边这个数字比较大。所以在右子树上找。】
     *     返回<0，会继续在左子树上找
     */
    @Override
    public int compareTo(Person o) {
        // 年龄相同时按照名字排序
        if (this.age == o.age) {
            // 姓名是String类型，可以直接比。调用String类的compareTo来完成比较
            return this.name.compareTo(o.name);
        } else {
            return this.age - o.age;  // 年龄不相同时按照年龄大小排序
        }
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        // 指定比较规则：按照年龄排序
        return o1.age-o2.age;
    }
}
