package K11_流.流Ⅰ_文件专属流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  使用FileInputStream + FileOutputStream完成文件的拷贝
  拷贝的过程应该是一边读一边写
  使用以上的字节流拷贝文件的时候，文件类型随意，万能的，什么样的文件都能拷贝
 */
public class 流5_FileInputStream文件复制 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/idea快捷键.md");
            fos = new FileOutputStream(System.getProperty("user.dir") + "/idea快捷键.md");

            // 一边读一边写
            byte[] bytes = new  byte[1024 * 1024];
            int readCount = 0;
            while((readCount = fis.read(bytes)) != -1){
                fos.write(bytes, 0, readCount);
            }

            // 刷新，输出流最后要刷新
            fos.flush();
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