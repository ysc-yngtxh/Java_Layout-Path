package H8_异常;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
深入try...catch
    1、catch后面的小括号中的类型可以是具体以的异常类型，也可以是该异常类型的父类型
    2、catch可以写多个，建议catch的时候，精确的一个一个处理。这样有利于程序的调试
    3、catch写多个的时候，从上带下，必须遵循从小到大（比如，IOException e不能写在FileNotFoundException e的上面）


    Exception下有两个分支：
         Exception的直接子类：编译时异常（要求程序员在编写程序阶段必须预先对这些异常进行处理）
                               **从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过

         RuntimeException:运行时异常。（编写程序时可以预先处理也可以不做处理）
                               **运行时异常的特点是Java编译器不会检查它，也就是说，当程序中可能出现这类异常，
                                 即使没有用try-catch语句捕获它，也没有用throws子句声明抛出它，也会编译通过。

            **在以后的开发中，处理编译时异常，应该是上报还捕捉呢？
              如果希望调用者来处理，选择throw上报（唯一标准）

            ***再次强调：所有异常都是发生在运行阶段
 */
public class C3捕捉异常详解 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("D:\\游诗成\\文档\\数据库作业sql");
        }catch(FileNotFoundException e){
            System.out.println("文件不存在");
        }catch(IOException e){     // IOException是FileNotFoundException的父类型
            System.out.println("读文件报错！");
        }catch(ArithmeticException | NullPointerException e){    // catch中可以加上或符号是在JDK8之后才有的新特性
            System.out.println("数学异常？空指针异常？都有可能");
        }
    }
}
