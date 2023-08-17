package J10流.F6File类;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
File类的常用方法：
          getName()获取文件名 ,不是目录名
          isDirectory()判断是否是一个目录(文件名)
          isFile()判断是否是一个文件
          lastModified()获取文件最后一次修改时间
          length()获取文件大小

          ***重点方法
          listFiles()获取当前目录下所有的子文件及子目录
 */
public class File类的常用方法2 {
    public static void main(String[] args) {
        File f1 = new File("D:\\酷我音乐\\音乐.txt");
        //getName()获取文件名
        System.out.println("文件名：" + f1.getName());

        //isDirectory()判断是否是一个目录(文件名)
        System.out.println(f1.isDirectory());

        //isFile()判断是否是一个文件
        System.out.println(f1.isFile());

        //lastModified()获取文件最后一次修改时间
        long haomiao = f1.lastModified();
        //将总毫秒数转换成日期
        Date time = new Date(haomiao);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss sss");
        String s = sdf.format(time);
        System.out.println(s);

        //length()获取文件大小
        System.out.println(f1.length());

        System.out.println("=========================================================================================");

        //File[] listFiles()获取当前目录下所有的子文件及子目录
        File f2 = new File("D:\\IDEA\\Java成神之路\\src");
        File[] files = f2.listFiles();
        for (File file:files
             ) {
            System.out.println(file.getAbsolutePath());
        }

    }
}
