package K11_流.流Ⅰ_文件专属流;

import java.io.FileInputStream;
import java.io.IOException;

/*
  FileInputStream类的其他常用方法
     int  available()    返回流当中剩余的没有读到的字节数量
     long skip(long n)   跳过几个字节不读
 */
public class 流3_FileInputStream常用方法 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/FileTemp");
            // int available():返回流当中剩余的没有读到的字节数量
            System.out.println("总字节数量：" + fis.available());

            int readByte = fis.read();   // 读了一个字节
            System.out.println("还剩下多少个字节没有读：" + fis.available());

            // skip跳过几个字节不读取，这个方法也可能以后会用
            fis.skip(3);
            System.out.println("跳过三个字节后读取下一个字节的ASCⅡ码：" + fis.read());  // 读到e，ASCⅡ码是101

            // 这种方式不再需要循环了，直接读一次就好了。但是这种方式不太适合太大的文件，因为byte[]数组不能太大
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes); // 读数组bytes
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}