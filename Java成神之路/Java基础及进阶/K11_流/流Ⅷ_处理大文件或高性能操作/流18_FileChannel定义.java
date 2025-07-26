package K11_流.流Ⅷ_处理大文件或高性能操作;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/*  FileChannel 介绍：（用于读取、写入、映射和操作文件的通道。）
 *        在开发中，我们经常需要与文件打交道，以往我们使用传统方式如 FileInputStream 和 FileOutputStream 来读写文件。
 *    但是，当我们处理大文件或需要高性能操作时，这些传统方式可能会显得捉襟见肘。
 *        为了解决传统方式的瓶颈，Java NIO 库引入了 FileChannel，它就像一把锋利的武器，可以让你以更高的效率读写文件。
 *    FileChannel 绕过了操作系统缓冲区，直接与底层文件系统交互，从而大幅提升了读写速度。
 *        FileChannel 提供了一种通过通道来访问文件的方式，它可以通过带参数 position(int) 方法定位到文件的任意位置开始进行操作，
 *    还能够将文件映射到直接内存，提高大文件的访问效率。
 *
 *  FileChannel 的特点
 *      线程安全： FileChannel 支持多线程并发使用，让你的代码在多线程环境下也能游刃有余。
 *      高性能： 得益于直接 IO 的方式，FileChannel 在读写大文件时性能飙升，让你体验风驰电掣般的快感。
 *      易用性： FileChannel 提供了简洁明了的 API，让文件读写操作变得轻松惬意。
 *
 *  FileChannel 的优势
 *      速度飞快： FileChannel 的直接 IO 机制让你在处理大文件时速度倍增，告别龟速时代。
 *      操作灵活： FileChannel 提供了丰富的 API，让你可以轻松实现顺序读写、随机访问、文件锁等各种文件操作。
 *      扩展性强： FileChannel 可以与其他 NIO 类库无缝衔接，让你构建更加复杂的网络和文件操作。
 *
 *  FileChannel 的应用场景
 *      大文件传输： FileChannel 是传输大文件的利器，让你轻松告别漫长的等待。
 *      文件加密解密： 结合加密库，FileChannel 可以为你打造固若金汤的文件保护屏障。
 *      文件锁： FileChannel 支持文件锁，让你对文件操作独享掌控权。
 *      网络文件传输： FileChannel 与网络套接字强强联手，让你在网络世界中畅快无阻地传输文件。
 *
 *  总结
 *      FileChannel 是 JAVA NIO 中不可多得的宝藏，它为文件读写带来了革命性的提升。
 *      其线程安全、高性能、易用的特点让它成为应对大文件处理、高并发操作和复杂文件处理场景的理想选择。
 *      掌握 FileChannel，你将成为文件读写领域的超级英雄，让你的代码闪耀夺目！
 *
 *  常见问题解答
 *      FileChannel 与传统文件读写方式有什么区别？
 *      FileChannel 采用直接 IO，绕过操作系统缓冲区，而传统方式使用缓冲区，速度较慢。
 *
 *      FileChannel 可以用在哪些场景中？
 *      大文件传输、文件加密解密、文件锁、网络文件传输等场景。
 *
 *      FileChannel 的使用是否复杂？
 *      FileChannel 提供了简洁易用的 API，上手非常容易。
 *
 *      FileChannel 是否支持多线程？
 *      是的，FileChannel 是线程安全的，支持多线程并发使用。
 *
 *      FileChannel 的性能优势体现在哪里？
 *      在处理大文件时，FileChannel 的性能优势尤为明显，读写速度大幅提升。
 */
public class 流18_FileChannel定义 {

    private static final String filePath = System.getProperty("user.dir")
                                         + "/Java基础及进阶/K11_流/FileTemp";

	public static void main(String[] args) throws IOException {
		// 获取一个只读FileChannel通道
		FileChannel inChannel = new FileInputStream(filePath).getChannel();
		// 获取一个只写FileChannel通道
		FileChannel outChannel = new FileOutputStream(filePath).getChannel();

		// 创建一个1024字节大小的 ByteBuffer（内存缓冲区，不同于其他缓冲区），用于读取或写入数据。
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		inChannel.read(buffer);
		// 向 buffer缓冲区 中写入了一些数据.
		// 打印出的数据有很多空格，因为 buffer缓冲区 的容量是1024字节，但实际写入的数据并没有占满。
		buffer.put("\nNew String to write to file...".getBytes(StandardCharsets.UTF_8));
		// 从写模式切换到读模式。这会将位置（position）设置为0，并将限制（limit）设置为当前位置。
		buffer.flip();
		// 将缓冲区中的数据读取到控制台。不要使用 Arrays.toString(buffer.array())，因为这样打印的是ASCII码。
		System.out.println(new String(buffer.array()));
		// get()方法读取数据，读取操作会更新位置（position）。
		System.out.println("get()方法读取数据，且读取操作会更新位置（position）：" + (char) buffer.get());
		System.out.println("get()方法读取数据，且读取操作会更新位置（position）：" + (char) buffer.get());
		System.out.println("get()方法读取数据，且读取操作会更新位置（position）：" + (char) buffer.get());
		// 重置缓冲区，准备再次写入数据。这会将位置（position）设置为0。
		buffer.rewind();
		System.out.println("重置缓冲区后，读取的数据：" + (char) buffer.get());
		System.out.println("重置缓冲区后，读取的数据：" + (char) buffer.get());
		System.out.println("重置缓冲区后，读取的数据：" + (char) buffer.get());

		// 检查是否还有未读的数据，这里的 位置（position）< 限制（limit），所以这里返回true，表示存在未读的数据
		while (buffer.hasRemaining()) {
			// 重置缓冲区，否则下面的写文件会从已经变动的位置（position）开始写
			buffer.rewind();
			// 将缓冲区中的数据通过通道写入到文件中（从位置(position)开始写）
			outChannel.write(buffer);
		}

		// 重置到初始状态，即位置（position）设置为0，限制（limit）会被重置为容量，不会删除原内容数据，准备接收新数据。
		buffer.clear();
		System.out.println("clear()方法读取数据，且读取操作会更新位置（position）" + (char) buffer.get());

		// 关闭通道
		inChannel.close();
		// 在对文件进行重要的操作之后，应该将数据刷出刷出(force)到磁盘，避免操作系统崩溃导致的数据丢失。
		outChannel.force(false); // 将数据刷出到磁盘，但不包括元数据[修改人，时间，地点等等]
		outChannel.close();
	}

}
