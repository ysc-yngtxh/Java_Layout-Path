package J10流.B2文件专属流;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
FileReader:
      文件字符输入流，只能读取普通文本
      读取文本内容时，比较方便，快捷
FileWriter:
       文件字符输出流。写。
       只能输出普通文本

注意：这两个流都只能应用于普通文本当中，像音视频等媒体内容不行
 */
public class FileReader和FileWriter的使用6 {
    public static void main(String[] args) {
        FileReader reader = null;
        FileWriter writer = null;

        FileReader r = null;
        FileWriter w = null;
        try {
            reader = new FileReader("myFile2");
            writer = new FileWriter("myFile3", true);

            //字符输入流
            char[] chars = new char[4];  //每次读取4个字符
            int readCount = 0;
            while ((readCount = reader.read(chars)) != -1) {
                System.out.println(new String(chars, 0, readCount));
            }

            //字符输出流
            char[] chars1 = {'我', '是', '中', '国', '人'};
            writer.write(chars1);
            writer.write(chars1, 2, 3);

            //普通文本文件的拷贝
            r = new FileReader("D:\\IDEA\\Java成神之路\\src\\idea快捷键.java");
            w = new FileWriter("idea快捷键.java");
            char[] c = new char[1024*512];
            int rc = 0;
            while((rc = r.read(c)) != -1){
                w.write(c, 0,rc);
            }


            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}