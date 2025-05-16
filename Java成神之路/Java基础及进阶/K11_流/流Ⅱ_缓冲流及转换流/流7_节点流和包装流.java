package K11_流.流Ⅱ_缓冲流及转换流;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 流7_节点流和包装流 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            // 字节流
            fis = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/IO流概述.md");

            // 通过isr转换流转换（InputStreamReader将字节流转换成字符流）fis是isr的节点流
            InputStreamReader isr = new InputStreamReader(fis);

            // 这个构造方法只能传一个字符流，不能传FileInputStream字节流
            // isr是br1的节点流，br1是包装流
            BufferedReader br1 = new BufferedReader(isr);

            // br2是通过转换流、包装流合并后的套娃程序
            BufferedReader br2 = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/IO流概述.md")));

            String s = null;
            while( (s = br1.readLine()) != null) { // 这里包装流类型是String类型
                System.out.println(s);
            }

            // 带有缓冲区的输出流
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(System.getProperty("user.dir")
                                                   + "/Java基础及进阶/K11_流/FileTemp1", true)));
            out.write("曹家千金");
            out.write("你在哪儿？");
            out.write("你知道吗？");
            out.write("我好想你啊！");

            // 刷新
            out.flush();
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
