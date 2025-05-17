package K11_流.流Ⅴ_File类;

import java.io.File;
import java.io.IOException;

/* File:
 *    1、File类和四大家族（流）无关，，File类不能对文件进行读和写也就是输入（读）和输出（写）！
 *    2、File主要表示文件夹（Directory）或者文件（file），而File类就是操作这两者的类。
 *    3、File对象代表什么？
 *         文件和目录路径名的抽象表示形式
 *
 *         目录也可以叫做文件名
 *    4、File类比较常用的构造方法有三个：
 *       ①、 public File(String pathname) ：通过给定的路径来创建新的 File实例。
 *       ②、 public File(String parent, String child) ：从父路径(字符串)和子路径创建新的 File实例。
 *       ③、 public File(File parent, String child) ：从父路径(File)和子路径名字符串创建新的 File实例。
 */
public class 流11_File概述 {

	public static void main(String[] args) {
		// 创建一个file对象
		File f1 = new File(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/流Ⅴ_File类/file");

		// 判断是否存在！
		System.out.println("该文件" + (f1.exists() ? "" : "不") + "存在");

		// 如果 ../file 文件不存在，则以文件的形式创建出来
		if (!f1.exists()) {
			try {
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 如果 ../file 文件不存在，则以目录(文件名)的形式创建出来
		if (!f1.exists()) {
			f1.mkdir();
		}

		// 如果 ../file 文件不存在，则以多重目录(文件名)的形式创建出来
		File f2 = new File(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/流Ⅴ_File类"
				                   + "/a/b/c/d/e/f/g");
		if (!f2.exists()) {
			f2.mkdirs();
		}

		// getAbsolutePath()：获取绝对路径
		File f3 = new File("idea快捷键.md");
		System.out.println("绝对路径：" + f3.getAbsolutePath());

		// getParent()：获取文件的父路径
		File f4 = new File(System.getProperty("user.dir") + "/idea快捷键.md");
		System.out.println("文件父路径：" + f4.getParent());
	}

}
