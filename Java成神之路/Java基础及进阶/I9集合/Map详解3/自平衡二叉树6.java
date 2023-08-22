package I9集合.Map详解3;

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
         当比较规则不会发生改变的时候，或者说当比较规则只有1个的时候，建议实现Comparable接口
         如果比较规则多个，并且需要多个比较规则之间频繁切换，建议使用Comparator接口

         Comparator接口的设计符合OCP原则
 */

import java.util.Comparator;
import java.util.TreeSet;

public class 自平衡二叉树6 {
    public static void main(String[] args) {

        // 创建TreeSet集合的时候，需要使用这个比较器
        // TreeSet<WuGui> t = new TreeSet<>(new WuGuiComparator); 第一种写法，传进Comparator对象，但要有WuGuiComparator类
        TreeSet<WuGui> t = new TreeSet<>(new Comparator<WuGui>() {
            @Override
            public int compare(WuGui o1, WuGui o2) {
                return o1.age-o2.age;
            }   // C3匿名内部类
        });
        t.add(new WuGui(100));
        t.add(new WuGui(500));
        t.add(new WuGui(1800));
        t.add(new WuGui(900));
        for (WuGui w: t
             ) {
            System.out.println(w);
        }
    }
}

class WuGui{
    int age;

    public WuGui(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "WuGui{" +
                "age=" + age +
                '}';
    }

}

/*
class WuGuiComparator implements Comparator<WuGui>{

    @Override
    public int compare(WuGui o1, WuGui o2) {
        // 指定比较规则
        // 按照年龄排序
        return o1.age-o2.age;
    }
}
*/
