package K11_流.流Ⅰ_文件专属流;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * FileReader:
 *       文件字符输入流，只能读取普通文本
 *       读取文本内容时，比较方便，快捷
 * FileWriter:
 *        文件字符输出流。写。
 *        只能输出普通文本
 *
 * 注意：这两个流都只能应用于普通文本当中，像音视频等媒体内容不行
 */
public class 流5_FileReader和FileWriter的使用 {

	public static void main(String[] args) {
		try (
				FileReader reader1 = new FileReader(System.getProperty("user.dir")
						                                    + "/Java基础及进阶/K11_流/ReaderFile");
				FileReader reader2 = new FileReader(System.getProperty("user.dir")
						                                    + "/Java基础及进阶/K11_流/ReaderFile");
				// 当写入文件不存在时会自动新建。
				// 当写入文件存在时，这里第二个参数设置为 true，表示以追加的方式在文件末尾写入，不会清空源文件内容
				FileWriter writer = new FileWriter(System.getProperty("user.dir")
						                                   + "/Java基础及进阶/K11_流/WriterFile", true);
		) {
			// reader1 字符输入流，这里配置每次读取4个字符
			char[] charsInput = new char[4];
			int readCount = 0;
			while ((readCount = reader1.read(charsInput)) != -1) {
				System.out.println(new String(charsInput, 0, readCount));
			}

			// writer 字符输出流
			char[] charsOut = {'我', '是', '中', '国', '人'};
			writer.write(charsOut);
			writer.write(charsOut, 2, 3);

			// reader2 + writer 读写文件【reader1 读取完文件后，指针偏移到文件末尾，无法继续读取数据，因此需要别的输入流】
			char[] c = new char[1024 * 3];
			int rc = 0;
			while ((rc = reader2.read(c)) != -1) {
				System.out.println(rc);
				writer.write(c, 0, rc);
			}

			// 刷新流，一定要将输出流刷新缓存，否则会出现：写入文件数据并没有成功写进去的情况
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
