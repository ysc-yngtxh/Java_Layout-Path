package K11_流.流Ⅰ_文件专属流;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
  最终版，需要掌握
 */
public class 流2_FileInputStream最终版 {
    public static void main(String[] args) {
        // try-with-resources语法糖
        // 在try语句之后在括号中打开的资源仅在此处和现在需要。close()方法在try块中完成工作后，我将立即调用它们的方法。
        // 如果在try块中抛出异常，无论如何我都会关闭这些资源。
        // 在引入此方法之前，关闭资源是手动完成(在finally语句块中手动调用close方法)的，在代码库中冗余甬长，严重影响了可读性并使它们难以维护。
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/FileTemp")) {
            byte[] bytes = new byte[4];
            int readCount = 0;
            while ((readCount = fis.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, readCount, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}