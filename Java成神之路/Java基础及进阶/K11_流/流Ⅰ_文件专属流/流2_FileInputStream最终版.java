package K11_流.流Ⅰ_文件专属流;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
  最终版，需要掌握
 */
public class 流2_FileInputStream最终版 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/Filetemp");
            byte[] bytes = new byte[4];
            int readCount = 0;
            while( (readCount = fis.read(bytes)) != -1 ){
                System.out.print(new String(bytes, 0, readCount, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
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