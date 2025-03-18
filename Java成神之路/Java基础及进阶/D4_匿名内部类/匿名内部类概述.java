package D4_匿名内部类;

/*
 * 匿名内部类：
 *     1、内部类：在类的内部有定义了一个新的类。被称为内部类
 *
 *     2、内部类的分类：
 *           静态内部类：类似于静态变量
 *           实例内部类：类似于实例变量
 *           局部内部类：类似于局部变量
 *
 *    3、使用内部类编写的代码，可读性很差，能不用就尽量不用
 *
 *    4、匿名内部类是局部内部类的一种。因为这个类没有名字而得名，叫做匿名内部类
 *
 *    5、不建议使用匿名内部类，因为一个类没有名字，没有办法重复使用。另外代码太乱，可读性太差
 *
 *    6、学习匿名内部类并不代表以后都要这样写，但至少阅读别人代码的时候我们能看懂。
 *       匿名内部类有两个缺点：
 *          缺点一：太复杂，太乱，可读性差
 *          缺点二：类没有名字，以后想要重复使用，不能用
 */
class Test {
    static String country;   // 静态变量

    // 该类在类的内部，所以被称为内部类
    // 由于前面有static，所以称为“静态内部类”
    static class Inner1 {}

    int age;  // 实例变量

    // 该类在类的内部，所以被称为内部类
    // 没有static叫做实例内部类
    class Inner2{}

    // 方法
    public void doSome() {
        int i = 100;
        // 该类在类的内部，所以被称为内部类
        // 局部内部类
        class Inner3 {

        }
    }

    public void doOther() {
        // doSome()方法中的局部内部类Inner3，在doOther()中不能使用。
    }
}


// 负责计算的接口
interface Compute {
    // 抽象方法
    int sum(int a, int b);
}
// 数学类
class MyMath{
    // 数学求和方法
    public void mysum(Compute c, int x, int y){
        int retValue = c.sum(x, y);
        System.out.println(x + "+" + y + "=" + retValue);
    }
}
public class 匿名内部类概述 {
    public static void main(String[] args) {
        MyMath mm = new MyMath();
        // 这里看起来是new了一个接口Compute。但是实际上并不是接口可以new了，后面的{}代表了对接口的实现。其实不就是通过多态嘛
        mm.mysum(new Compute() {
            public int sum(int a, int b){
                return a + b;
            }
        }, 100, 200);

        // 以下两种写法同样能够实现相同的功能，使用的是Lambda表达式方式
        // mm.mysum((a,b) -> {
        //     return a + b;
        // }, 100, 200);
        //
        // mm.mysum(Integer::sum, 100, 200);
    }
}
