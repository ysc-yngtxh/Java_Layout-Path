package M13_反射.反射Ⅵ_JDBC时候要用的只执行静态代码块;

/*
  研究一下：Class.forName()发生了什么？
     记住，重点：
       如果你只希望一个类的静态代码块执行，其他代码一律不执行
       你可以使用：
           Class.forName("完整类名", true, null);
       这个方法的执行会导致类加载和初始化。初始化时，静态代码块会执行

       提示：
           后面JDBC技术时候我们要用
 */
public class 反射16_只执行静态代码块 {
    public static void main(String[] args) {
        try {
            // Class.forName(String name, boolean initialize, ClassLoader loader);
            // 默认第二个参数为false，该方法只会加载类，并不会初始化它。静态字段不会被设置，静态初始化块也不会被执行。
            // 设置第二个参数为true，那么该方法不仅会加载类，还会初始化类。意味着静态字段会被设置，静态初始化块也会被执行。
            // 第三个参数为加载器，通常为null。表示使用当前类的类加载器。

            // Class.forName()这个方法的执行只会类加载，并不会初始化它。
            Class.forName("M13_反射.反射Ⅵ_JDBC时候要用的只执行静态代码块.MyClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}