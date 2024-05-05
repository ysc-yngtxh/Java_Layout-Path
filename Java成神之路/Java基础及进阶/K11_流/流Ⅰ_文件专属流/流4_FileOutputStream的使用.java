package K11_流.流Ⅰ_文件专属流;

import java.io.FileOutputStream;
import java.io.IOException;

public class 流4_FileOutputStream的使用 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        FileOutputStream fos2 = null;
        try {
            // myFile文件不存在的时候会自动新建。
            // 这种方式请谨慎使用，因为在myFile文件存在的时候会将原文件内容清空，然后重新写入
            fos = new FileOutputStream(System.getProperty("user.dir") + "/myFile");
            // 开始写
            byte[] b = {97, 98, 99, 100, 101, 102}; // 将byte数组全部写出
            fos.write(b);
            // 将byte数组的一部分写出: 数据, 从指定数据元素下标开始写入, 从指定数据元素下标结束
            fos.write(b, 0, 2);

            // 第二个参数设置为 true，表示以追加的方式在文件末尾写入，不会清空源文件内容
            fos2 = new FileOutputStream(System.getProperty("user.dir") + "/myFile2", true);
            String s = "我好想你，曹玉敏！";
            // 将字符串转换成byte数组
            byte[] bytes = s.getBytes();
            fos2.write(bytes);

            // 写完之后，最后一定要刷新缓存，否则会出现：写入文件数据并没有成功写进去的情况
            fos.flush();
            fos2.flush();
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