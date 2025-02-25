package B2_抽象类;
/*
   final
     1、final是Java语言中的一个关键字。

     2、final表示：
              【最终的，不可变的】

     3、final修饰的变量
        3.1、final修饰基本数据类型的变量
               当你将变量声明为 final 时，这个变量就成为了一个常量，意味着它的值一旦被初始化之后就不能再改变。
               这是因为基本类型的变量直接存储它们的值，而不是指向某个对象的引用。
               因此，将一个基本类型的变量声明为 final 就是说这个变量的值是不可变的。
        3.2、final修饰引用类型的变量
               final修饰的引用类型变量，确保的是该引用本身的不可变性，即你不能将这个引用指向另一个不同的对象。
               但是，这并不影响通过该引用来修改对象的状态（比如Map、List 等集合添加、删除或更新对象的元素）。
               这是因为引用类型变量中存储的是对象的地址，而 final 保证的是这个地址（引用）不可变，而不是该地址所指向的对象内容不可变。

     4、final修饰的属性存储位置：
              常量：final String COUNTRY = "中国"
              常量：对于基本数据类型和字符串的编译时常量，存储在运行时常量池【方法区(Java8后为元空间 Metaspace)】中；
                   非编译时常量【例如：final 变量在构造函数中初始化】则存储在堆上。

              静态常量：static final String YSC = "湖北武汉"
              静态常量：通常存储在方法区(Java 8及之后为元空间)的运行时常量池中，只会在类加载时被初始化一次，并且在整个程序运行期间保持不变。
                      引用对象时候不需要开辟堆栈空间创建对象，相较节省空间。

              静态常量：static final String YSC = "湖北武汉"
              静态变量：存储在方法区（Java 8及之后为元空间），只会在类加载时被初始化一次，并且在整个程序运行期间保持不变。
                      并且可以被整个应用程序生命周期内访问和修改。

     5、final修饰的方法
             【final修饰的方法无法被覆盖，被重写】

     6、final修饰的类
             【final修饰的类无法继承】
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

class T{
    int abc = 12;
    public final void doSome(){
        System.out.println("class T doSome...");
    }
}
class Y extends T{
    final int age; // 实例变量加上关键字final，必须赋值，或者在构造函数中赋值。换种说法，赶在构造函数赋默认值之前赋值就行
    Y() {
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
