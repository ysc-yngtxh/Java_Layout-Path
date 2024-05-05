package K11_流.流Ⅰ_文件专属流;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
  java.io.FileInputStream:
     1、文件字节输入流，万能的，任何类型的文件都可以采用这个流来读
     2、自己的方式，完成输入的操作，完成读的操作（硬盘---->内存）
 */
public class 流1_FileInputStream演示版 {
    public static void main(String[] args) {
        FileInputStream fis1 = null;  // 定义输入流在try语句外，避免final语句无法调用close方法
        FileInputStream fis2 = null;
        FileInputStream fis3 = null;
        try {
            // 获取当前项目的绝对路径
            System.out.println(System.getProperty("user.dir"));

            // 创建文件字节输入流对象
            // 文件路径：/Users/youshicheng/IDEA/java-layout-path/Java成神之路/log (IDEA会自动把\编程\\，因为Java中\表示转义)
            // 以下都是采用了：绝对路径的方式
            // 也可以写成这样：fis = new FileInputStream("/Users/youshicheng/IDEA/java-layout-path/Java成神之路/log");
            fis1 = new FileInputStream(System.getProperty("user.dir") + "/log");

            // 绝对路径：D:IDEA\java-layout-path\Java成神之路\Java基础及进阶\K11_流\Filetemp
            // 相对路径：从IDEA模块路径出发
            fis2 = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/Filetemp");
            fis3 = new FileInputStream("log");
            // 开始读
            while(true) {
                int readData1 = fis1.read();
                if (readData1 == -1){
                    break;     // 当读不出文件元素后，返回-1
                }
                System.out.println("读出来的是ASCⅡ码: " + readData1);   // 读出来的是ASCⅡ码
            }
            // 改造while循环
            int readData2 = 0;
            while( (readData2 = fis2.read()) != -1 ){
                System.out.println("改造while循环的读: " + readData2);
            }

            // 每一次读文件，都只会取出文件中一个元素，效率太低了
            // 使用byte数组，一次取出数组长度的元素，效率较高
            byte[] bytes = new byte[4];
            int readCount = fis3.read(bytes);
            // 读取的是字节数量，（不是字节本身）
            System.out.println( "fis3读第一次" + readCount);   // 4
            // 将字节数组全部转换成字符串
            System.out.println(new String(bytes, 0, 4, StandardCharsets.UTF_8));     // 输出文件元素
            readCount = fis3.read(bytes);
            System.out.println( "fis3读第二次" + readCount);  // 4
            System.out.println(new String(bytes, 0, 2));    // 输出文件元素
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            // 在finally语句块当中确保流一定关闭
            if (fis1 != null) {   // 添加判断，避免空指针异常
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}