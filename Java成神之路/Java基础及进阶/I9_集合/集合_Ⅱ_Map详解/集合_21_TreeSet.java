package I9_集合.集合_Ⅱ_Map详解;

import java.util.TreeSet;

/*
1、TreeSet集合底层实际上是一个TreeMap

2、TreeMap集合底层是一个二叉树

3、放到TreeSet集合中的元素，等同于放到TreeMap集合key部分了。

4、TreeSet集合中的元素：无序不可重复，但是可以按照元素的 可排序集合

5、TreeSet集合中元素可排序的方式：
      第一种：使用comparable接口（需要重写compareTo方法）
      第二种：使用comparator(比较器)的方式----详解在自平衡二叉树6

 */
public class 集合_21_TreeSet {
    public static void main(String[] args) {

        // 创建一个TreeSet集合
        TreeSet<String> tr = new TreeSet<>();
        // 添加String
        tr.add("zhangsan");
        tr.add("lisi");
        tr.add("wangwu");
        tr.add("zhangsi");
        tr.add("wangliu");
        // 遍历
        for (String s: tr
             ) {
            System.out.println(s);
        }


        System.out.println("==========================================================================================");

        // 创建一个TreeSet集合
        TreeSet<person> tr1 = new TreeSet<>();
        // 添加person元素
        tr1.add(new person("youpeng",20));
        tr1.add(new person("youshicheng",23));
        tr1.add(new person("youting",30));
        tr1.add(new person("youhuan",27));
        tr1.add(new person("youshichena",23));
        for (person s1: tr1
             ) {
            System.out.println(s1);
        }
    }
}

// String类和Integer类都有Comparable接口，和重写compareTo方法

class person implements Comparable<person> {
    private String name;
    private int age;

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /*
      compareTo方法的返回值很重要：
         返回0表示相同，value会覆盖
         返回>0，会继续在右子树上找。【10-9=1，1>0的说明左边这个数字比较大。所以在右子树上找。】
         返回<0，会继续在左子树上找
     */

    @Override
    public int compareTo(person o) {
        if(this.age == o.age){
            // 年龄相同时按照名字排序
            // 姓名是String类型，可以直接比。调用compareTo来完成比较
            return this.name.compareTo(o.name);
        }else{
            // 年龄不一样
            return this.age-o.age;
        }
    }
   /*
    // 比较age的底层源代码
    else {
            if (key == null)      // 调用的是无参的TreeSet集合
                throw new NullPointerException();
            @SuppressWarnings("unchecked")
                Comparable<? super K> k = (Comparable<? super K>) key;  // 将key强制转换成Comparable(转换器)类型
            do {
                parent = t;
                cmp = k.compareTo(t.key);
                if (cmp < 0)            // 二叉树
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else
                    return t.setValue(value);  // value覆盖
            } while (t != null);
        }
     */
}