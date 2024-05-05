package K11_流.流Ⅴ_File类;

import java.io.File;
import java.io.IOException;

/*
  File:
     1、File类和四大家族没有关系，所以File类不能完成文件的读和写
     2、File对象代表什么？
          文件和目录路径名的抽象表示形式

          目录也可以叫做文件名
 */
public class 流12_File概述 {
    public static void main(String[] args) {
        // 创建一个file对象
        File f1 = new File(System.getProperty("user.dir") + "file");

        // 判断是否存在！
        System.out.println("该文件" + (f1.exists() ? "" : "不") + "存在");

        // 如果 ../Java成神之路/file 不存在，则以文件的形式创建出来
        if(!f1.exists()){
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 如果 ../Java成神之路/file 不存在，则以目录(文件名)的形式创建出来
        if(!f1.exists()){
            f1.mkdir();
        }

        // 如果 ../Java成神之路/file 不存在，则以多重目录(文件名)的形式创建出来
        File f2 = new File("D:\\a\\b\\c\\d\\e\\f\\g");
        if(!f2.exists()){
            f2.mkdirs();
        }

        // 获取绝对路径
        File f3 = new File("idea快捷键.md");
        System.out.println("绝对路径：" + f3.getAbsolutePath());

        // getParent()获取文件的父路径
        File f4 = new File(System.getProperty("user.dir") + "/idea快捷键.md");
        System.out.println("文件父路径：" + f4.getParent());
    }
}