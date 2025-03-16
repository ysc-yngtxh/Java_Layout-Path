package K11_流.流Ⅰ_文件专属流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 使用 FileInputStream + FileOutputStream 完成文件的拷贝，拷贝的过程应该是一边读一边写
 * 使用以上的字节流拷贝文件的时候，文件类型随意，万能的，什么样的文件都能拷贝
 */
public class 流4_Input和Output文件复制 {
    public static void main(String[] args) {
        try (// ⚠️：当读取文件不存在时会抛出异常 FileNotFoundException
             FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                                                     + "/Java基础及进阶/K11_流/IO流概述.md");
             // 当写入文件不存在时会自动新建。
             // 当写入文件存在时，会将原文件内容清空，然后重新写入，请谨慎使用。
             FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")
                                                       + "/Java基础及进阶/K11_流/IO流概述(副本).md")
        ) {
            // 一边读一边写
            byte[] bytes = new  byte[1024 * 1024];
            int readCount = 0;
            while((readCount = fis.read(bytes)) != -1){
                fos.write(bytes, 0, readCount);
            }

            // 刷新，输出流最后要刷新
            fos.flush();
        } catch (IOException e) {
	        throw new RuntimeException(e);
        }
    }
}
