package M13_反射.反射Ⅳ_反射Constructor;

import java.lang.reflect.Constructor;

/*
 * 通过反射机制调用构造方法实例化Java对象
 */
public class 反射14_反射机制调用构造方法 {
    public static void main(String[] args) throws Exception {
        // 不使用反射机制怎么创建对象
        Vip v1 = new Vip();
        Vip v2 = new Vip(10, "曹家千金", "2001-10-11", true);

        // 使用反射机制怎么创建对象
        Class<?> c = Class.forName("M13_反射.反射Ⅳ_反射Constructor.Vip");
        Object obj = c.newInstance();  // TODO 这种通过class实例获取对象的方式只适用于到JDK8，到了JDK9就过时了
        System.out.println(obj);

        // TODO JDK9以及9之后：通过反射机制调用构造方法的方式获取对象
        // 第一步：先获取到这个有参数的构造方法
        Constructor<?> con = c.getDeclaredConstructor(int.class, String.class, String.class, boolean.class);
        // 第二步：想要调用构造方法，就相当于是new对象
        Object newObj = con.newInstance(110, "游家纨绔", "1997-03-22", true);
        System.out.println(newObj);

        // 调用无参构造方法
        Constructor<?> con1 = c.getDeclaredConstructor();
        Object newObj1 = con1.newInstance();
        System.out.println(newObj1);
    }
}
