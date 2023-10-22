package C3_Object类;
/*关于Object类中的finalize（）方法
 *   1、在Object类中的源代码
 *      protected void finalize() throws Throwable{  }
 *      GC：负责调用finalize（）方法
 *
 *   2、finalize（）方法只有一个方法体，里面没有代码，因为这个方法是protected修饰的
 *
 *   3、这个方法不需要程序员手动调用，JVM的垃圾回收器负责调用这个方法
 *      不像equals toString,equals和toString()方法是需要你写代码调用的
 *      finalize（）只需要重写，重写完将来自动会有程序来调用
 *
 *   4、finalize（）方法的执行时机：
 *      当一个Java对象即将被垃圾回收器回收的时候，垃圾回收器负责调用finalize（）方法
 *
 *   5、finalize（）方法实际上是SUN公司为Java程序员准备的一个时机，垃圾销毁时机。
 *      如果希望在对象销毁实际执行一段代码的话，这段代码要写到finalize（）方法当中。
 *
 *   6、静态代码块的作用是什么？
 *      static{
 *         ...
 *      }
 *      静态代码块是在类加载时刻自动执行，并且只执行一次。这是一个SUN准备的类加载时机。
 *      finalize()方法同样也是SUN为程序员准备的一个时机，这个时机是垃圾回收时机
 *
 *   7、提示：
 *      Java中的垃圾回收器是不会轻易启动的
 *      垃圾太少或者时间未到，种种条件下，有可能启动，也有可能不启动
 * */
/*
 * 项目开发中有这样的业务需求：所有对象在JVM中被释放的时候，请记录一下施放时间！！！
 * 记录对象被释放的时间点，这个负责记录的代码写在finalize（）方法中
 */
class Person{
    protected void finalize() throws Throwable{
        System.out.println("即将被销毁");
    }
}
public class Object类4_Finalize {
    public static void main(String[] args) {
        for(int i = 0; i < 1000; i++){
            Person p = new Person();
            p = null;
            if (i % 2 == 0) {
                System.gc();
            }
        }
    }
}

