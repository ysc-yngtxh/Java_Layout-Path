package J10流.B2文件专属流;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
java.io.FileInputStream:
     1、文件字节输入流，万能的，任何类型的文件都可以采用这个流来读
     2、自己的方式，完成输入的操作，完成读的操作（硬盘---->内存）
 */
public class FileInputStream演示版1 {
    public static void main(String[] args) {
        FileInputStream fis1 = null;  //避免写进try语句中，final语句无法调用close方法
        FileInputStream fis2 = null;
        FileInputStream fis3 = null;
        try{

            //创建文件字节输入流对象
            //文件路径：C:\Users\游诗成\Documents\io流文件\草稿.txt (IDEA会自动把\编程\\，因为Java中\表示转义)
            //以下都是采用了：绝对路径的方式
            //也可以写成这样：fis = new FileInputStream("C:\\Users\\游诗成\\Documents\\io流文件\\草稿.txt");
            fis1 = new FileInputStream("C:/Users/游诗成/Documents/io流文件/草稿.txt");

            //绝对路径：D:\IDEA\Java成神之路\src\J10流\Filetemp   相对路径：从IDEA模块路径出发
            fis2 = new FileInputStream("D:\\IDEA\\Java成神之路\\src\\J10流\\Filetemp");
            fis3 = new FileInputStream("D:\\IDEA\\Java成神之路\\src\\J10流\\Filetemp");
            //开始读
            while(true){
                int readData1 = fis1.read();
                if (readData1 == -1){
                    break;     //当读不出文件元素后，返回-1
                }
                System.out.println(readData1);   //读出来的是ASCⅡ码
            }
            //改造while循环
            int readData2 = 0;
            while( (readData2 = fis2.read()) != -1 ){
                System.out.println(readData2);
            }

            //每一次读文件，都只会取出文件中一个元素，效率太低了
            //使用byte数组，一次取出数组长度的元素，效率较高
            byte[] bytes = new byte[4];
            int readCount = fis3.read(bytes);
            //读取的是字节数量，（不是字节本身）
            System.out.println(readCount);   //4
            //将字节数组全部转换成字符串
            System.out.println(new String(bytes,0,4));  //输出文件元素
            readCount = fis3.read(bytes);
            System.out.println(readCount);  //4
            System.out.println(new String(bytes,0,2));   //输出文件元素


        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            //在finally语句块当中确保流一定关闭
            if (fis1 != null) {   //空指针异常
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
