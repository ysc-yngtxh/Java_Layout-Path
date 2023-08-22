package L12_反射.E5反射Constructor;

import java.lang.reflect.Constructor;

/*
通过反射机制调用构造方法实例化Java对象
 */
public class 反射机制调用构造方法2 {
    public static void main(String[] args) throws Exception{

        // 不使用反射机制怎么创建对象
        Vip v1 = new Vip();
        Vip v2 = new Vip(10, "曹玉敏", "2001-10-11",true);

        // 使用反射机制怎么创建对象
        Class<?> c = Class.forName("L12_反射.E5反射Constructor.Vip");
        Object obj = c.newInstance();   // 调用无参构造方法,这种方法适用于JDK8，已过时
        System.out.println(obj);

        // 使用反射机制调用有参构造方法
        // 第一步：先获取到这个有参数的构造方法
        Constructor<?> con = c.getDeclaredConstructor(int.class, String.class, String.class, boolean.class);
        // 第二步：调用构造方法new对象
        Object newobj = con.newInstance(110, "游诗成", "1997-03-22", true);
        System.out.println(newobj);

        //获取无参数构造方法
        Constructor<?> con1 = c.getDeclaredConstructor();
        Object newobj1 = con1.newInstance();
        System.out.println(newobj1);
    }
}
