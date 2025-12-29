package K11_流.流Ⅵ_Path类;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-05-09 19:21:00
 */
public class 流15_Files工具类 {
	/*
	 * Files类只包含对文件，目录或其他类型文件进行操作的静态方法（主要和Path接口的对象进行配合使用）
	 */
	public static void main(String[] args) throws IOException {
		String currentPath = System.getProperty("user.dir");
		Path path = Paths.get(currentPath, "Java基础及进阶", "K11_流", "FileTemp2");
		// 创建文件
		Files.createFile(path);
	}

	// TODO 请参考资源：https://blog.csdn.net/qq877728715/article/details/104499687
}
