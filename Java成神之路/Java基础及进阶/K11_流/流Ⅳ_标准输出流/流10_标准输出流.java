package K11_流.流Ⅳ_标准输出流;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/*
  java.io.PrintStream：标准的字节输出流。默认输出到控制台
 */
public class 流10_标准输出流 {
    public static void main(String[] args) {
        // 联合起来写
        System.out.println("Hello World!");

        // 分开写
        PrintStream ps = System.out;
        ps.println("叶诗琪");
        ps.println("没有你的每一天都好像无比煎熬");
        ps.println("我好想你啊");
        // 标准输出流不需要手动close()关闭

        /*
          这是之前System类使用过的方法和属性
            System.gc();
            System.currentTimeMillis()
            PrintStream ps = System.out;
            System.in
            System.exit(0)
            System.arraycopy()
            System.getProperty("user.dir")
         */

        // 标准输出流不再指向控制台，指向“log”文件
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("log");
            PrintStream Ps = new PrintStream(fos);
            System.setOut(Ps);

            // 再输出
            System.out.println("今天是");
            System.out.println("2020年6月13号");
            System.out.println("我游诗成又想她了");
            System.out.println("曹玉敏，我好想你啊！");

            // 刷新流
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
