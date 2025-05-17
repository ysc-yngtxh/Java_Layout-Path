package I9_异常;

import java.io.FileInputStream;
import java.io.IOException;

/* 关于try..catch...中的finally子句：
 *     1、在finally子句中的代码是最后执行的，并且是一定会执行的，即使try语句块中的代码出现了异常。
 *        finally子句必须和try一起出现，不能单独编写
 *
 *     2、finally语句通常使用在哪些情况下？
 *        通常在finally语句块中完成资源的释放/关闭
 *        因为finally中的代码比较有保障
 *        即使try语句块中的代码有异常，finally中的代码也会正常执行
 */
public class 异常5_finally子句的使用 {

	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			// 创建输入流对象
			fis = new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/I9_异常/异常5_finally子句的使用.java");
			// 开始读文件。。。

			String s = null;
			// 这里一定会出现空指针异常
			s.toString();

			System.out.println("Hello World"); // try语句中出现异常后续程序不执行
			// 流使用完需要关闭，因为流是占用资源的.即使以上程序出现异常，流也必须要关闭
			// 但是放在这里关闭不了，因为程序异常了执行不到这儿
			fis.close();
		} catch (NullPointerException | IOException e) {
			e.printStackTrace(); // 打印异常追踪的堆栈信息
		} finally {
			// 流的关闭放在这儿比较保险
			// finally中的代码一定会执行
			if (fis != null) {   // 避免空指针异常
				try {
					// close()方法有异常，采用捕捉的方式
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("=========================================================================================");

		// 代码执行顺序：先执行try,再执行finally，最后执行return(return只要执行方法必然结束）
		try {
			System.out.println("try1...");
			return;
		} finally {
			System.out.println("finally...");
		}

        // try {
        //     System.out.println("try2...嘿嘿");
        //     System.exit(0); // 退出JVM之后，finally语句中的代码就不执行了
        // } finally {
        //     System.out.println("finally...");
        // }
	}

}
