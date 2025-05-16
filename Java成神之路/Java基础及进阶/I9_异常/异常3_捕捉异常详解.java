package I9_异常;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * 深入try...catch
 *   1、catch后面的小括号中的类型可以是具体以的异常类型，也可以是该异常类型的父类型
 *   2、catch可以写多个，建议catch的时候，精确的一个一个处理。这样有利于程序的调试
 *   3、catch写多个的时候，从上带下，必须遵循从小到大（比如，IOException e不能写在FileNotFoundException e的上面）
 */
public class 异常3_捕捉异常详解 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("D:\\游家纨绔\\文档\\数据库作业sql");
        } catch(FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch(IOException e) {     // IOException是FileNotFoundException的父类型
            System.out.println("读文件报错！");
        } catch(ArithmeticException | NullPointerException e){    // catch中可以加上或符号是在JDK8开始才有的新特性
            System.out.println("数学异常？空指针异常？都有可能");
        }
    }
}
