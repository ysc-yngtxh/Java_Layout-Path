package J10流.B2文件专属流;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
最终版，需要掌握
 */
public class FileInputStream最终版2 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("D:\\IDEA\\Java成神之路\\Java基础及进阶\\src\\J10流\\Filetemp");
            byte[] bytes = new byte[4];
            int readCount = 0;
            while( (readCount = fis.read(bytes)) != -1 ){
                System.out.print(new String(bytes,0,readCount));
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
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
