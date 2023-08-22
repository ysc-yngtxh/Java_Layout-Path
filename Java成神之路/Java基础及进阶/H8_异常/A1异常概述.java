package H8_异常;

/*
1、什么是异常，Java提供异常处理机制有什么用？
   以下程序执行过程中的发生了不正常的情况，而这种不正常的情况叫做：异常
   Java把异常信息打印输出到控制台，供程序员参考。程序员看到异常信息之后，可以对程序进行修改，让程序更加的健壮

   什么是异常：程序执行过程中的不正常情况
   异常的作用：增强程序的健壮性

   异常信息打印输出到控制台，这个信息被我们称为：异常信息。这个信息是JVM打印的

2、Java语言中一场是以什么形式存在的呢？
   异常在Java中以类的形式存在，每一个异常类都可以创建异常对象
   Object下有Throwable(可抛出的)
   Throwable下有两个分支：Error(不可处理，直接退出JVM)和Exception(可处理的)
   Exception下有两个分支：
            Exception的直接子类：编译时异常（要求程序员在编写程序阶段必须预先对这些异常进行处理）
                               **从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过

            RuntimeException:运行时异常。（编写程序时可以预先处理也可以不做处理）
                               **运行时异常的特点是Java编译器不会检查它，也就是说，当程序中可能出现这类异常，
                                 即使没有用try-catch语句捕获它，也没有用throws子句声明抛出它，也会编译通过。


            ***再次强调：所有异常都是发生在运行阶段

 */
public class A1异常概述 {
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
