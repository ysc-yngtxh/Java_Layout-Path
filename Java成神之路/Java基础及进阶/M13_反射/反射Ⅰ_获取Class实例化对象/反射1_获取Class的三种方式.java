package M13_反射.反射Ⅰ_获取Class实例化对象;

import java.util.Date;

/*
  要操作一个类的字节码，需要首先获取到这个类的字节码，怎么获取java.lang.Class实例？
  ClassNotFoundException是一个类没找到的异常，父类是Exception，编译时异常，需要上抛或者try,catch

  三种方式：
          第一种：Class c2 = Class.forName("完整类名带包名");
          第二种：Class c = 对象.getClass();
          第三种：class c = 任何类型.class;
 */
public class 反射1_获取Class的三种方式 {
    public static void main(String[] args) {
        /*
          第一种：Class.forName()
           1、静态方法
           2、方法的参数是一个字符串
           3、字符串需要的是一个完整类名
           4、完整类名必须带有包名。java.lang包也不能省略
         */
        Class<?> c1 = null;
        Class<?> c4 = null;
        try {
            c1 = Class.forName("java.lang.String");           // c1代表String.class文件，或者说c1代表String类型
            Class<?> c2 = Class.forName("java.lang.Integer"); // c2代表Integer类型
            Class<?> c3 = Class.forName("java.lang.System");  // c3代表System类型
            c4 = Class.forName("java.util.Date");             // c4代表Date类型
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("========================================================================================");

        // 第二种方式：java中任何一个对象都有一个方法 getClass()
        String s = "abcd";
        Class<?> x = s.getClass();     // x代表String.class字节码文件。x代表String类型
        System.out.println(c1 == x);   // true(==判断的是对象的内存地址)

        Date t = new Date();
        Class<?> a = t.getClass();
        System.out.println(c4 == a);

        System.out.println("========================================================================================");

        // 第三种方式：Java语言中任何一种类型，包括基本数据类型，它都有.class属性。
        Class<?> z = String.class;      // z代表String类型
        Class<?> k = int.class;         // k代表int类型
        Class<?> f = double.class;      // f代表double类型
        Class<?> e = Date.class;        // e代表Date类型
        System.out.println(x == z);
    }
}