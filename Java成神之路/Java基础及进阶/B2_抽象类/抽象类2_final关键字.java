package B2_抽象类;
/*
   final
     1、final是Java语言中的一个关键字。

     2、final表示：
              最终的，不可变的
     3、final可以修饰变量以及方法，还有类等。

     4、final修饰的变量
             【final修饰的局部变量，一旦赋值不能重新赋值】
             【final修饰的实例变量，系统不负责赋默认值，要求程序员必须手动赋值。这个手动赋值，在变量后面赋值可以，在构造方法中赋值也可以】

     5、final修饰的方法
             【final修饰的方法无法被覆盖，被重写】

     6、final修饰的类
             【final修饰的类无法继承】

     7、final修饰的引用：
             【该引用只能指向一个对象，并且它只能永远指向一个对象】

          // 实例变量在什么时候赋值？ -- （初始化）

          // 常量：final String COUNTRY = "中国"
             常量存储在堆栈内存中，每次使用时都会重新创建。

          // 静态常量：static final String YSC = "湖北武汉"
             静态常量存储在常量方法区中，只会在类加载时被初始化一次，并且在整个程序运行期间保持不变。
             引用对象时候不需要开辟堆栈空间创建对象，相较节省空间。

          // 静态变量：static Sting DATA = "123456"
             静态变量是存储在方法区，只会在类加载时被初始化一次，并且在整个程序运行期间保持不变。
 */

/*
class MyMath{
    public static final double PI = 3.1415926; // 静态常量
}

final class A{
    // 没有子孙，不能被继承
}
class B extends A{

}
*/     // 编译错误

import A1_基础知识.初识Java.方法;

class T{
    int abc = 12;
    public final void doSome(){
        System.out.println("class T doSome...");
    }
}
class Y extends T{
    final int age; // 实例变量加上关键字final，必须赋值，或者在构造函数中赋值。换种说法，赶在构造函数赋默认值之前赋值就行
    Y(){
        age = 10;
    }
    public void dOoSome(){
        System.out.println("class Y doSome...");
    }
}

class person extends T{
    int a;
    public person(){
    }
    public person(int b){
        this.a=b;
    }
}

public class 抽象类2_final关键字 {
    // 多态：就是指一个引用（类型）在不同的情况下的多种状态。
    //      也可以理解为，多态是指通过指向父类的指针，来调用在不同子类中实现的方法。
    public static void main(String[] args) {
        T c = new Y();   // 多态，父类引用子类对象

        // c.dOoSome();    // 不能被调用，T类引用中没有dOoSome() 方法

        if(c instanceof Y){
            Y d1 = (Y)c;
            d1.dOoSome();
        }     //向下强制转型

        //或者
        Y d = new Y();
        d.dOoSome();

        person p1 = new person(30);
        System.out.println(p1.a);

        final person p2 = new person(20);
        // p2 = new person(50);
		/* 编译错误，new person(20)可以看成地址x0111,所以final p2 = x0111
		   final 关键字的p2重新赋值new person(50)可以看作p2 = x4567;
		   所以编译错误
		*/

    }
}
