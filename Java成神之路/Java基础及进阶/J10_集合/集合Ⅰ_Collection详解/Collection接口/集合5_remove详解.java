package J10_集合.集合Ⅰ_Collection详解.Collection接口;

import java.util.ArrayList;
import java.util.Collection;

/*
 * boolean remove(Object o)  删除集合中的某个元素
 */
public class 集合5_remove详解 {
    public static void main(String[] args) {
        // 创建对象
        Collection<Object> c = new ArrayList<>();

        Integer i1 = new Integer(200);
        Integer i2 = new Integer(100);
        c.add(i1);
        c.add(i2);
        Integer i3 = new Integer(100);
        System.out.println(c.remove(i3)); // Integer类中的equals方法已重写


        User1 u1 = new User1("游诗成");
        User1 u2 = new User1("曹玉敏，我好想你！");
        c.add(u1);
        c.add(u2);
        System.out.println("元素的个数是：" + c.size());
        User1 u3 = new User1("游诗成");
        System.out.println(c.contains(u3));
        System.out.println(c.remove(u3));
        // User1类中的equals方法没有重写，所以调用的会是Object的equals方法，比较的是对象内存地址。
    }
}

class User1{
    private String name;

    public User1() {
    }

    public User1(String name) {
        this.name = name;
    }
}