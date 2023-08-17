package L12反射.JDBC时候要用的只执行静态代码块;

/*
研究一下：Class.forName()发生了什么？
     记住，重点：
       如果你只希望一个类的静态代码块执行，其他代码一律不执行
       你可以使用：
           Class.forName("完整类名");
       这个方法的执行会导致类加载，类加载时，静态代码块执行


       提示：
           后面JDBC技术时候我们要用
 */

public class 只执行静态代码块1 {
    public static void main(String[] args) {
        try {
            //Class.forName()这个方法的执行会导致：类加载
            Class.forName("L12反射.JDBC时候要用的只执行静态代码块.MyClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


