package K11_流.流Ⅰ_文件专属流;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
 * 最终版，需要掌握
 * 原写法缺点：在引入语法糖方法之前，关闭资源是手动完成的【在 finally语句块 中手动调用 close() 方法】
 *           在代码库中冗余甬长，严重影响了可读性并使它们难以维护。
 * FileInputStream类的常用方法
 *    int  available()    返回流当中剩余的没有读到的字节数量
 *    long skip(long n)   跳过几个字节不读
 */
public class 流2_FileInputStream最终版 {
    public static void main(String[] args) {
        // try-with-resources 语法糖【close() 方法在 try代码块 中完成工作后，将立即调用】
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                                                         + "/Java基础及进阶/K11_流/FileTemp")
        ) {
            System.err.println("打印的字符有乱码，这是因为使用的字节流，无法避免\n");
            byte[] bytes = new byte[6];
            int readCount;
            while ((readCount = fis.read(bytes)) != -1) {
                System.err.print(new String(bytes, 0, readCount, StandardCharsets.UTF_8));
            }

            // int available()：返回流当中剩余的没有读到的字节数量
            System.err.println("总字节数量：" + fis.available());
            int readByte = fis.read();   // 读了一个字节
            System.err.println("还剩下多少个字节没有读：" + fis.available());

            // long skip(long n)：跳过几个字节不读取，这个方法也可能以后会用
            fis.skip(3);
            System.err.println("跳过三个字节后读取下一个字节的ASCⅡ码：" + fis.read());  // 读到e，ASCⅡ码是101

            // 这种方式不再需要循环了，直接读一次就好了。但是这种方式不太适合太大的文件，因为 byte[] 数组不能太大
            byte[] byteArr = new byte[fis.available()];
            fis.read(byteArr); // 读数组bytes
            System.err.println(new String(byteArr, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
