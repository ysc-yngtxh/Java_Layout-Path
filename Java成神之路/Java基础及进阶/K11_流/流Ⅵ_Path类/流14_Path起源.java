package K11_流.流Ⅵ_Path类;

import java.nio.file.FileSystems;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-05-09 19:00:00
 */
public class 流14_Path起源 {

	/* 一、在Java中文件或是目录习惯用java.io.File对象来表示，但是File类有很多缺陷:
	 *     1、它的很多方法不能抛出异常
	 *     2、它的delete方法经常莫名其妙的失败等，旧的File类经常是程序失败的根源。
	 *
	 * 二、因此在Java7中有了更好的替代：java.nio.file.Path 及 java.nio.file.Files。
	 *     1、Path接口的名字非常恰当，就是表示路径的，API中讲Path对象可以是一个文件，一个目录，或是一个符号链接，也可以是一个根目录。
	 *        创建Path并不会创建物理文件或是目录，path实例经常引用并不存在的物理对象，要真正创建文件或是目录，需要用到Files类。
	 *     2、Files类是一个非常强大的类，它提供了处理文件和目录以及读取文件和写入文件的静态方法。
	 *        可以用它创建和删除路径、复制文件、检查路径是否存在等。 此外。Files还拥有创建流对象的方法。
	 *
	 * 三、Path就是取代File的，用于来表示文件路径和文件。可以有多种方法来构造一个Path对象来表示一个文件路径，或者一个文件：
	 *    Path path = FileSystems.getDefault().getPath("d:/users/日记5.txt");
	 *    // 并没有实际创建路径，而是一个指向d:/users/日记5.txt路径的引用
	 *    Path path = Paths.get("d:/users/日记5.txt");  // Paths类提供了这个快捷方法，直接通过它的静态get方法创建path
	 *    Path path= = new File("d:/users/日记5.txt").toPath();
	 */
	public static void main(String[] args) {
		// 获取当前项目的路径
		String currentPath = System.getProperty("user.dir");
		System.out.println("当前项目的路径: " + currentPath);

		// 获取当前项目的路径分隔符
		String pathSeparator = System.getProperty("path.separator");
		System.out.println("当前项目的路径分隔符: " + pathSeparator);

		// 获取当前项目的文件分隔符
		String fileSeparator = FileSystems.getDefault().getSeparator();
		// 等同于 String fileSeparators = System.getProperty("file.separator");
		System.out.println("当前项目的文件分隔符: " + fileSeparator);
	}

}
