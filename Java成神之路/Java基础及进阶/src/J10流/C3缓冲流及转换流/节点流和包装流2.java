package J10流.C3缓冲流及转换流;

import java.io.*;

public class 节点流和包装流2 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            //字节流
            fis = new FileInputStream("D:\\IDEA\\Java成神之路\\Java基础及进阶\\src\\J10流\\A1iO流概述.java");

            //通过转换流转换（InputStreamReader将字节流转换成字符流）
            InputStreamReader isr = new InputStreamReader(fis);

            //这个构造方法只能传一个字符流，不能传FileInputStream字节流
            //isr是节点流，br是包装流
            BufferedReader br1 = new BufferedReader(isr);

            //*****br2就是转换流，包装流合并后的套娃程序
            BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\IDEA\\Java成神之路\\Java基础及进阶\\src\\J10流\\A1iO流概述.java")));


            String s = null;
            while( (s = br1.readLine()) != null){//这里包装流类型是String类型
                System.out.println(s);
            }

            //带有缓冲区的输出流
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("myFile4",true)));
            out.write("陈嘉琪");
            out.write("你在哪儿？");
            out.write("你知道吗？");
            out.write("我好想你啊！");
            out.write(br1.readLine());//这里一行应该加到while判断语句中，避免出现空指针异常

            //刷新
            out.flush();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
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
