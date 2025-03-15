package K11_流.流Ⅶ_处理大文件或高性能操作;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-03 22:50
 * @apiNote TODO
 */
public class 流21_ByteArrayOutputStream {

    // ByteArrayOutputStream：
    //         是一个输出流, 它将数据写入到内存中, 而不是写入到文件或网络中.
    //         是一个方便的工具类, 可以用于将数据收集到一个字节数组中, 然后可以将该数组传递给其他方法或写入到文件中
    // ByteArrayOutputStream 有一个构造函数, 可以指定初始容量, 默认为32字节.
    // write(byte[] b, int off, int len)方法, 可以将字节数组的一部分写入到 ByteArrayOutputStream 中.
    // toByteArray()方法, 可以将 ByteArrayOutputStream 中的数据转换为一个字节数组.
    // toString()方法, 可以将 ByteArrayOutputStream 中的数据转换为一个字符串.
    // close()方法, 可以关闭 ByteArrayOutputStream, 但实际上它什么也不做.

    private static final String filePath = System.getProperty("user.dir")
            + "/Java基础及进阶/K11_流/FileTemp";

    public static void main(String[] args) {
        // 简单理解 ByteArrayOutputStream 就是将数据写入到内存中，不需要指定文件路径。
        try (FileInputStream fis = new FileInputStream(filePath);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            System.out.println(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
