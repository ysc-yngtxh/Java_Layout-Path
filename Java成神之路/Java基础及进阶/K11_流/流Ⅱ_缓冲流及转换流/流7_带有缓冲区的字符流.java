package K11_流.流Ⅱ_缓冲流及转换流;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
  BufferedReader:
       带有缓冲区的字符输入流
       使用这个流的时候不需要自定义char数组，或者说不需要自定义byte数组，自带缓冲
       读取时候是一行一行的读，效率比FileInputStream高
 */
public class 流7_带有缓冲区的字符流 {
    public static void main(String[] args)  {
        FileReader fr = null;
        try {
            fr = new FileReader(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/IO流概述.md");
            // 当一个流的构造方法中需要一个流的时候，这个被传进来的流叫做：节点流
            // 外部负责包装的这个流，叫做：包装流。还有一个名字叫做：处理流
            // 像当前这个程序来说：FileReader就是一个节点流。BufferedReader就是包装流/处理流
            BufferedReader br = new BufferedReader(fr);  // 传的参数只能是字符流的构造方法

            // 读一行
            String s1 = br.readLine(); // readLine()是读文件一行的方法
            System.out.println(s1);

            // 读到文件结束
            String s2 = null;
            while((s2 = br.readLine()) != null){
                System.out.println(s2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    // 对于包装流来说，只需要关闭最外层流就行，里面的节点流会自动关闭
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}