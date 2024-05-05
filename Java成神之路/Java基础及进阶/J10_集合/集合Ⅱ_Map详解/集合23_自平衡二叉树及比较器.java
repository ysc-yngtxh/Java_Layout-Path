package J10_集合.集合Ⅱ_Map详解;

import java.util.Comparator;
import java.util.TreeSet;
/*
  1、TreeSet/TreeMap是自平衡二叉树。遵循左小右大原则存放
  2、遍历二叉树的时候有三种方式：
           前序遍历：根左右
           中序遍历：左根右
           后序遍历：左右根

           注意：
               前中后说的时“根”的位置
               根在前面是前序，根在中间是中序，根在后面是后续
  3、TreeSet集合/TreeMap集合采用的是：中序遍历方式
        Iterator迭代器采用的是中序遍历方式，左根右。

  4、最终的结论：
           放到TreeSet或者TreeMap集合key部分的元素要想做到排序，包括两种方式：
                第一种：放在集合中的元素实现java.lang.Comparable接口---详情转到TreeSet5
                第二种：在构造TreeSet或者TreeMap集合的时候给他传一个比较器对象

  5、Comparable和Comparator如何选择？
        Comparable 可以直接在需要进行排序的类中实现，重写compareTo(T o)方法；缺点是要实现Comparable接口
        Comparator 需要另外一个实现 Comparator 接口的实现类来作为 “比较器”。不用实现接口，直接使用匿名内部类即可实现
        当比较规则不会发生改变的时候，或者说当比较规则只有1个的时候，建议实现Comparable接口
        如果比较规则多个，并且需要多个比较规则之间频繁切换，建议使用Comparator接口

        Comparator接口的设计符合OCP原则
 */
public class 集合23_自平衡二叉树及比较器 {
    public static void main(String[] args) {
        // 创建TreeSet集合的时候，需要使用这个比较器

        // 第一种写法：使用Comparable的内部比较规则，前提是WuGui类实现了Comparable，并重写了compareTo方法。不够灵活
        TreeSet<WuGui> wuGuis1 = new TreeSet<>();
        // 第二种写法：使用Comparator的静态方法。推荐
        TreeSet<WuGui> wuGuis2 = new TreeSet<>(Comparator.comparing(WuGui::getAge));
        // 第三种写法，传进Comparator实现对象，只不过把比较规则写在了实现类中。简直就是脱裤子放屁
        TreeSet<WuGui> t1 = new TreeSet<>(new WuGuiComparator());
        // 第四种写法：匿名内部类，把比较规则写在了匿名内部类中。可读性太差
        TreeSet<WuGui> t = new TreeSet<>(new Comparator<WuGui>() {
            @Override
            public int compare(WuGui o1, WuGui o2) {
                return o1.age-o2.age;
            }   // 匿名内部类
        });
        t.add(new WuGui(100));
        t.add(new WuGui(500));
        t.add(new WuGui(1800));
        t.add(new WuGui(900));
        for (WuGui w : t) {
            System.out.println(w);
        }
    }
}

class WuGui implements Comparable<WuGui>{
    int age;
    public WuGui(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    @Override
    public int compareTo(WuGui o) {
        return this.age-o.age;
    }
    @Override
    public String toString() {
        return "WuGui{" +
                "age=" + age +
                '}';
    }
}

class WuGuiComparator implements Comparator<WuGui>{
    @Override
    public int compare(WuGui o1, WuGui o2) {
        // 指定比较规则：按照年龄排序
        return o1.age-o2.age;
    }
}
