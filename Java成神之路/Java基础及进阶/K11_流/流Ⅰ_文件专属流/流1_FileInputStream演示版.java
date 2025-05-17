package K11_流.流Ⅰ_文件专属流;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* java.io.FileInputStream:
 *    1、文件字节输入流，万能的，任何类型的文件都可以采用这个流来读
 *    2、自己的方式，完成输入的操作，完成读的操作（硬盘--->内存）
 */
public class 流1_FileInputStream演示版 {

	public static void main(String[] args) {
		// 定义输入流在try语句外，避免final语句无法调用close方法
		FileInputStream fis1 = null;
		FileInputStream fis2 = null;
		FileInputStream fis3 = null;
		try {
			// 获取当前项目的绝对路径
			System.out.println(System.getProperty("user.dir"));

			// 创建文件字节输入流对象
			// 文件路径：/Users/yousc/IDEA/Java_Layout-Path/Java成神之路/** (IDEA会自动把\编程\\，因为Java中\表示转义)
			// 以下都是采用了：绝对路径的方式
			// 也可以写成这样：fis = new FileInputStream("/Users/yousc/IDEA/Java_Layout-Path/Java成神之路/**");
			fis1 = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/FileTemp");
			fis2 = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/FileTemp");
			fis3 = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/FileTemp");

			// fis1 开始读
			while (true) {
				int readData1 = fis1.read();
				if (readData1 == -1) {
					break;     // 当读不出文件元素后，返回-1
				}
				System.out.println("读出来的是ASCⅡ码: " + readData1);   // 读出来的是ASCⅡ码
			}

			// fis2 改造while循环
			int readData2 = 0;
			while ((readData2 = fis2.read()) != -1) {
				System.out.println("改造while循环的读: " + readData2);
			}

			// fis3 读取自定义的字节数组长度的元素，提高效率【⚠️：一个中文字符占三个字节，注意定义的数组长度】
			byte[] bytes = new byte[6];
			int readCount = fis3.read(bytes);
			// 读取的是字节数量（不是字节本身），将 bytes[] 字节数组全部转换成字符串
			System.out.println("fis3第一次读取字节数量 = " + readCount + "，读取的内容是："
					                   + new String(bytes, 0, 6, StandardCharsets.UTF_8));
			readCount = fis3.read(bytes);
			System.out.println("fis3第二次读取字节数量 = " + readCount + "，读取的内容是："
					                   + new String(bytes, 0, 6, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 在finally语句块当中确保流一定关闭
			if (fis1 != null) {   // 添加判断，避免空指针异常
				try {
					fis1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis2 != null) {
				try {
					fis2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis3 != null) {
				try {
					fis3.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
