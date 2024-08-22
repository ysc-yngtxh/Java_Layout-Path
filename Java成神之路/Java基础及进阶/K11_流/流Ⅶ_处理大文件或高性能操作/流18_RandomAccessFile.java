package K11_流.流Ⅶ_处理大文件或高性能操作;

import java.io.IOException;
import java.io.RandomAccessFile;

public class 流18_RandomAccessFile {

    /*
      RandomAccessFile是Java中的一个类，它属于java.io包。
      这个类提供了一种方式来访问文件，允许你读取和写入文件的任意位置，因此被称为“随机访问”。

      构造函数：
          通过提供 文件路径 和 模式字符串 来创建RandomAccessFile对象。
          模式字符串可以是：
             "r"：只读模式。
             "rw"：读写模式。
             "rws" 或 "rwd"：这些模式与"rw"类似，但在每次调用write方法后会强制将数据刷新到磁盘。
      文件指针：
          RandomAccessFile维护了一个文件指针，用于指示当前读写操作的位置。
          你可以使用seek(long pos)方法来移动文件指针到指定的位置。
      读写操作：
          提供了多种方法来读写基本类型的数据（如readByte(), writeByte(byte b), readInt(), writeInt(int i)等）。
          支持更高级的操作，比如读写字符串或对象。
      关闭资源：
          使用完毕后，应该调用close()方法来释放资源。
     */

    private static final String filePath = System.getProperty("user.dir")
            + "/Java基础及进阶/K11_流/FileTemp1";

    public static void main(String[] args) {
        try {
            // 创建一个RandomAccessFile对象
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");

            // 写入一些数据
            raf.writeInt(12345);
            raf.writeUTF("Hello, World!");

            // 移动文件指针，回到文件开头，并读取数据
            raf.seek(0);

            // 输出读取的数据
            System.out.println("Number: " + raf.readInt());
            System.out.println("Text: " + raf.readUTF());

            System.out.println("初始文件指针位置：" + raf.getFilePointer());
            raf.write("你好，world!".getBytes());
            System.out.println("第一次写完之后文件指针位置：" + raf.getFilePointer());

            // 手动修改读写指针位置
            raf.seek(9);
            System.out.println("修改后的文件指针位置：" + raf.getFilePointer());

            byte b = raf.readByte();
            System.out.println("第一次读完之后文件指针位置：" + raf.getFilePointer() + "，读到的内容：" + (char) b);

            // 修改指针位置10处的字节内容
            raf.writeBytes("K");
            System.out.println("第二次写完之后文件指针位置：" + raf.getFilePointer());

            byte[] dst = new byte[(int) raf.length()];
            // 指针归零
            raf.seek(0);
            // 将数据全部读取出来
            raf.read(dst);
            System.out.println("第二次读完之后文件指针位置：" + raf.getFilePointer() + "，读到的内容：" + new String(dst));

            // 关闭文件
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}