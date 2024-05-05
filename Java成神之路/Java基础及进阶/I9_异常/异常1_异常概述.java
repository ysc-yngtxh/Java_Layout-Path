package I9_异常;

/*
  一、什么是异常，Java提供异常处理机制有什么用？
     以下程序执行过程中的发生了不正常的情况，而这种不正常的情况叫做：异常
     Java把异常信息打印输出到控制台，供程序员参考。程序员看到异常信息之后，可以对程序进行修改，让程序更加的健壮

     什么是异常：程序执行过程中的不正常情况
     异常的作用：增强程序的健壮性

     异常信息打印输出到控制台，这个信息被我们称为：异常信息。这个信息是JVM打印的

  二、Java语言中一场是以什么形式存在的呢？
     异常在Java中以类的形式存在，每一个异常类都可以创建异常对象
     Object下有 Throwable类(可抛出的)
     Throwable类是 Java 语言中所有错误或异常的超类
     Throwable下有两个分支：
          1、Error(不可处理，直接退出JVM)
             1️⃣、Error 类描述的是内部系统錯误(system error) ，是由 Java 虚拟机抛出的.
                 这样的错误很少发生。如果发生，除了通知用户以及尽量稳妥地终止程序外，几乎什么也不能做。
             2️⃣、例如：OutOfMemoryError ：内存耗尽 ；
                      NoClassDefFoundError ：无法加载某个Class ；
                      StackOverflowError ：栈溢出。
          2、Exception(可处理的)
             1️⃣、Exception的直接子类：在编译时期抛出的异常，在编译期间检查程序是否可能会出现问题，如果可能会有，则预先防范：捕获 声明。
                                    从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过
                                    例如：NumberFormatException ：数值类型的格式错误；
                                         IOException：当读取不存在的文件或网络连接失败时，都可能抛出此异常。
                                                 IOException及其子类（如FileNotFoundException）都是编译时异常。

             2️⃣、RuntimeException：描述的是程序设计错误，例如，错误的类型转换、访问一个越界数组或数值错误。
                                   运行时异常通常是由Java虚拟机抛出的。
                                   运行时异常的特点是Java编译器不会检查它，也就是说，当程序中可能出现这类异常，
                                   即使没有用try-catch语句捕获它，也没有用throws子句声明抛出它，也会编译通过。
                                   例如：ArithmeticException：整数除以0。数学运算异常 非法的数学运算。
                                        IndexOutOfBoundsException ：数组索引越界。
                                        NullPointerException ：对某个 null 的对象调用方法或字段；
 */
public class 异常1_异常概述 {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        // 实际上JVM在执行到此处的时候，会new异常对象（算术异常）：new ArithmeticException("by zero")
        // 并且JVN将new的异常对象，打印输出信息到控制台
        int c = a/b;
        System.out.println(a + "/" + b + "=" + c);

        // 发生异常时，JVM执行内容
        NullPointerException npe = new NullPointerException("空指针异常发生了");
        System.out.println(npe);
    }
}