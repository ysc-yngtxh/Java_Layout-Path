package M13_反射.反射Ⅰ_获取Class实例化对象;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * 获取class能干什么？
 *      通过Class的 newInstance() 方法来实例化对象
 *      注意：newInstance() 方法内部实际上调用了无参构造方法，必须保证无参构造方法存在才可以
 *
 * 有三种方法实例化对象
 *    第一种：(new对象方式)
 *           User User = new User();  通过new类方法实例化对象
 *
 *    第二种：(反射机制)
 *           Class c2 = Class.forName("完整类名带包名");  通过反射机制获取Class，通过Class来实例化对象
 *           再用 newInstance() 这个方法会调用User类的无参构造方法，完成对象的创建
 *
 *    第三种：(灵活多变方式)
 *           通过 IO流 读取 *.properties 配置文件
 *           调用 Properties对象 的 load() 方法将文件中的数据加载到 Map集合 中
 *           通过 Key 获取 Value，即你实例化对象的地址名。然后再通过反射机制实例化对象
 *
 *    相较以往，反射机制实例化对象的好处有哪些？
 *         代码是灵活的，不需要改动，可以通过修改配置文件，来创建出不同的实例对象
 */
public class 反射2_实例化对象 {
    public static void main(String[] args) {
        // 第一种方式
        // 这不是用反射机制，创建对象
        User user = new User();
        System.out.println(user);

        System.out.println("==================================================================");

        // 第二种方式
        // 下面这段代码是以反射机制的方式创建对象
        try {
            // 通过反射机制获取Class，通过Class来实例化对象
            Class<?> c = Class.forName("M13_反射.反射Ⅰ_获取Class实例化对象.实例化对象2.User");
            System.out.println(c);

            // newInstance() 这个方法会调用 User类 的无参构造方法，完成对象的创建
            // 重点是：newInstance() 调用的是无参构造，必须保证无参构造是存在的
            Object obj = c.newInstance();  // 这种方法在JDK9之后就过时了，改为 c.getDeclaredConstructor().newInstance();
            System.out.println(obj);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("=========================================================================================");

        // 第三种方式
        // 通过 IO流 读取 *.properties 配置文件
        FileInputStream fis = null;
        try {
            // 创建输入流对象。
            fis = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/M13_反射/反射Ⅰ_获取Class实例化对象/实例化对象2/application.properties");
            // 创建属性类对象Map
            Properties pro = new Properties();
            // 加载
            pro.load(fis);
            System.out.println(pro);
            // 通过 Key 获取 Value
            String className11 = pro.getProperty("className1");
            String className22 = pro.getProperty("className2");

            // 通过反射机制实例化对象
            Class<?> c1 = Class.forName(className11);
            Object obj1 = c1.newInstance();
            System.out.println(obj1);

            Class<?> c2 = Class.forName(className22);
            Object obj2 = c2.newInstance();
            System.out.println(obj2);

        } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class User {
        public User() {
            System.out.println("无参构造方法");
        }
    }
}
