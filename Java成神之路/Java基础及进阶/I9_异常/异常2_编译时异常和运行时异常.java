package I9_异常;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
 * 再次强调：所有异常都是发生在运行阶段
 *     注意：异常和错误的区别：异常能被程序本身可以处理，错误是无法处理。

 * 处理异常的第一种方式：
 *     在方法声明的位置上使用throw关键字抛出，谁调用我这个方法，我就抛给谁，抛给调用者来处理
 *     这种处理异常的态度：上报

 * 处理异常的第二种方式：
 *     使用try...catch语句对异常进行捕捉
 *     这个异常不会上报，自己把这个事儿处理了
 *     异常抛到此处为止，不再上报

 * 注意⚠️：
 *     只要异常没有捕捉，采用上报的方式，此方法的后续代码不会执行
 *     另外需要注意，try语句块中的某一行出现异常，该行后面的在 try 中的代码不会执行
 *     try...catch捕捉异常之后， try...catch之外的后续代码可以执行
 */
public class 异常2_编译时异常和运行时异常 {
    public static void main(String[] args) {
        /*
          一般不建议在main方法中使用throw,因为这个异常如果真正发生了，一定会抛给JVM，JVM只有终止程序
          异常处理异常的作用就是增强程序的健壮性。所以一般main方法中的异常建议try...catch进行捕捉。main就不要继续上抛了
         */
        System.out.println("main begin!");

        try {
            m1(); // 尝试执行m1
            System.out.println("Hello World!"); // m1异常，后续try程序不执行
        } catch (FileNotFoundException e) {
            // catch是捕捉异常之后走的分支，在这里处理其他逻辑
            System.out.println("文件不存在，可能路径错误，也可能是该文件被删除了！");
            System.out.println(e); // 这个分支中可以使用e引用，e引用保存的内存地址是那个new出来异常对象的内存地址
        }
        // try...catch就算捕捉了异常也不影响后续代码的执行
        System.out.println("main over!");
    }

    private static void m1() throws FileNotFoundException {
        System.out.println("m1 begin!");
        m2();
    }

    private static void m2() throws FileNotFoundException {
        System.out.println("m2 begin!");
        m3();
    }

    private static void m3() throws FileNotFoundException {
        System.out.println("m3 begin");
        /*
          编译报错的原因：
              第一：这里调用了一个构造方法：FileInputStream(String name)
              第二：这个构造方法的声明位置上有：throws FileNotFoundException
              第三：通过类的继承结构看到：FileNotFoundException父类是IOException，IOException父类是Exception
              最终得知：FileNotFoundException是编译时异常
        */
        new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/I9_异常/异常2_编译时异常和运行时异常.java");
        System.out.println("如果以上代码出现异常，m3方法中的后续代码不会执行");
    }
}
