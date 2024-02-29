package K11_流.流Ⅰ_文件专属流;

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
public class 流6_FileReader和FileWriter的使用 {
    public static void main(String[] args) {
        FileReader reader = null;
        FileWriter writer = null;

        FileReader r = null;
        FileWriter w = null;
        try {
            reader = new FileReader("myFile2");
            writer = new FileWriter("myFile3", true);

            // 字符输入流
            char[] charsInput = new char[4];  // 每次读取4个字符
            int readCount = 0;
            while ((readCount = reader.read(charsInput)) != -1) {
                System.out.println(new String(charsInput, 0, readCount));
            }

            // 字符输出流
            char[] charsOut = {'我', '是', '中', '国', '人'};
            writer.write(charsOut);
            writer.write(charsOut, 2, 3);

            // 普通文本文件的拷贝
            r = new FileReader(System.getProperty("user.dir") + "/Java基础及进阶/idea快捷键.md");
            w = new FileWriter("idea快捷键.md", true);
            char[] c = new char[1024 * 3];
            int rc = 0;
            while((rc = r.read(c)) != -1){
                System.out.println(c);
                System.out.println(rc);
                w.write(c, 0, rc);
            }

            // 刷新流,记得一定要将输出流刷新缓存，否则会出现：写入文件数据并没有成功写进去的情况
            writer.flush();
            w.flush();
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
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}