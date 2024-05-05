package J10_集合.集合Ⅱ_Map详解;

import java.util.TreeSet;

/*
  1、TreeSet集合底层实际上是一个TreeMap

  2、TreeMap集合底层是一个二叉树

  3、放到TreeSet集合中的元素，等同于放到TreeMap集合key部分了。

  4、TreeSet集合中的元素：无序不可重复，但是可以按照元素的 可排序集合

  5、TreeSet集合中元素可排序的方式：
        第一种：使用 comparable接口（需要重写compareTo方法）
        第二种：使用 comparator(比较器)的方式 ---- 详解在 集合23_自平衡二叉树及比较器
 */
public class 集合22_TreeSet {
    public static void main(String[] args) {
        // 创建一个TreeSet集合，其排序规则是按照集合元素String类重写的compareTo方法进行排序
        TreeSet<String> tr = new TreeSet<>();
        // 添加String。看String源码可以发现实现了Comparable接口
        tr.add("zhangSan");
        tr.add("lisi");
        tr.add("wangWu");
        tr.add("zhangSi");
        tr.add("wangLiu");
        // 遍历
        for (String s : tr) {
            System.out.println(s);
        }

        System.out.println("==========================================================================================");

        // 创建一个TreeSet集合，其排序规则是按照集合元素Person类重写的compareTo方法进行排序
        TreeSet<Person> tr1 = new TreeSet<>();
        // 添加person元素
        tr1.add(new Person("youpeng", 20));
        tr1.add(new Person("youshicheng", 23));
        tr1.add(new Person("youting", 30));
        tr1.add(new Person("youhuan", 27));
        tr1.add(new Person("youshichen", 23));
        for (Person s1 : tr1) {
            System.out.println(s1);
        }
    }
}

class Person implements Comparable<Person> { // String类和Integer类都有Comparable接口，和重写compareTo方法
    private String name;
    private int age;

    /*
      compareTo方法的返回值很重要：
          返回=0表示相同，value会覆盖
          返回>0，会继续在右子树上找。【10-9=1，1>0的说明左边这个数字比较大。所以在右子树上找。】
          返回<0，会继续在左子树上找
     */
    @Override
    public int compareTo(Person o) {
        // 年龄相同时按照名字排序
        if (this.age == o.age) {
            // 姓名是String类型，可以直接比。调用String类的compareTo来完成比较
            return this.name.compareTo(o.name);
        } else {
            // 年龄不相同时按照年龄大小排序
            return this.age - o.age;
        }
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}