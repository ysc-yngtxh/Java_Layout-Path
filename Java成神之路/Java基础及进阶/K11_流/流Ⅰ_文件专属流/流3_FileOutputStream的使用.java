package K11_流.流Ⅰ_文件专属流;

import java.io.FileOutputStream;
import java.io.IOException;

public class 流3_FileOutputStream的使用 {
	public static void main(String[] args) {
		try (// 当写入文件不存在时会自动新建。
		     // 当写入文件存在时，会将原文件内容清空，然后重新写入，请谨慎使用。
		     FileOutputStream fos1 = new FileOutputStream(System.getProperty("user.dir")
			                                            + "/Java基础及进阶/K11_流/WriterFile");
		     // 写入文件存在时，第二个参数设置为 true，表示以追加的方式在文件末尾写入，不会清空源文件内容
		     FileOutputStream fos2 = new FileOutputStream(System.getProperty("user.dir")
				                                        + "/Java基础及进阶/K11_流/WriterFile", true)
		) {
			// fos1 开始写
			byte[] b = {97, 98, 99, 100, 101, 102}; // 将byte[]数组全部写出
			fos1.write(b);
			// 将byte数组的一部分写出: 数据, 从指定数据元素下标开始写入, 从指定数据元素下标结束
			fos1.write(b, 0, 2);

			// fos2 开始写
			String str = "我好想你，曹玉敏！";
			// 将字符串转换成byte数组
			byte[] bytes = str.getBytes();
			fos2.write(bytes);

			// 写完之后，最后一定要刷新缓存，否则可能会出现：写入文件数据并没有成功写进去的情况
			fos1.flush();
			fos2.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
