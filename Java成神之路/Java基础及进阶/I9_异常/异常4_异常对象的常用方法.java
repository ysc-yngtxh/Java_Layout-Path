package I9_异常;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
 * 异常对象有两个非常重要的方法：
 *      获取异常简单的描述信息：
 *          String msg = exception.getMessage();
 *      打印异常追踪的堆栈信息：
 *          exception.printStackTrace();
 *
 * 我们以后查看异常的追踪信息，我们应该怎么看，可以快速的调试程序？
 *      1、异常信息追踪信息，从上往下一行一行看
 *      2、但是需要注意的是：SUN公司写的代码就不用看了
 *      3、主要问题是出现在自己编写的代码上
 */
public class 异常4_异常对象的常用方法 {
    public static void main(String[] args) {
        // 这里只是为了测试getMessage()方法和printStacktrace()方法
        // 这里只是new了异常对象，但是没有将异常对象抛出。JVM会认为这是一个普通的Java对象
        NullPointerException e = new NullPointerException("空指针异常");

        // 获取异常简单描述信息：这个信息实际上就是构造方法上面String参数
        String msg = e.getMessage();
        System.out.println(msg);

        // 打印异常堆栈信息
        // Java后台打印异常堆栈追踪信息的时候，采用了异步线程的方式打印的
        e.printStackTrace();
        System.out.println("=========================================================================================");

        try {
            m1();
        } catch(FileNotFoundException f) {
            // 打印异常堆栈追踪信息
            // 在实际的开发中建议使用这个，养成好习惯
            f.printStackTrace();
            // f.getMessage();
        }

        // 这里程序不耽误执行，很健壮/（服务器不会因为遇到异常而宕机）
        System.out.println("Hello World!");
    }

    private static void m1() throws FileNotFoundException {
        m2();
    }

    private static void m2() throws FileNotFoundException {
        // 路径错误，系统无法访问
        new FileInputStream(System.getProperty("user.dir") + "Java基础及进阶/I9_异常/异常4_异常对象的常用方法.java");
        // 这个是正确的文件地址
        // new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/I9_异常/异常4_异常对象的常用方法.java");
    }
}
