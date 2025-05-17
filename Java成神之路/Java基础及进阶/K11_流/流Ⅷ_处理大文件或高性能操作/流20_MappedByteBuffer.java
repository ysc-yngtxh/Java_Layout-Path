package K11_流.流Ⅷ_处理大文件或高性能操作;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-25 23:30:00
 * @apiNote TODO
 */
public class 流20_MappedByteBuffer {

	/*
	 *  映射文件到直接内存:
	 *     文件通道 FileChannel 可以将文件的指定范围映射到程序的地址空间中，
	 *  映射部分使用字节缓冲区的一个子类 MappedByteBuffer 的对象表示，只要对映射字节缓冲区进行操作就能够达到操作文件的效果。
	 *
	 *  内存映射原理：
	 *     普通 I/O：  IO设备(文件)  <--> 内核缓存  <-->  用户空间缓存(即JVM 堆内存)
	 *         ByteBuffer 和文件通道 FileChannel 对文件的操作使用的是 read()/write() 系统调用，使用的是堆缓冲区。
	 *         读取数据时数据从 I/O 设备读到内核缓存，再从内核缓存复制到用户空间缓存，这里是 JVM 的堆内存。
	 *
	 *     内存映射 I/O：  IO设备(文件)  <-->  用户空间缓存(即JVM 堆外内存)
	 *         映射磁盘文件是使用 mmap() 系统调用，将文件的指定部分映射到程序地址空间(JVM)中；
	 *         数据交互发生在 I/O 设备于用户空间之间，不需要经过内核空间。
	 *
	 *  虽然映射磁盘文件减少了一次数据复制，但对于大多数操作系统来说，将文件映射到内存这个操作本身开销较大；
	 *  如果操作的文件很小，只有数十KB，映射文件所获得的好处将不及其开销。因此，只有在操作大文件的时候才将其映射到直接内存。
	 */

    private static final String filePath = System.getProperty("user.dir")
                                         + "/Java基础及进阶/K11_流/FileTemp";

	public static void main(String[] args) throws IOException {
		// 以读写的方式打开文件通道
		FileChannel fileChannel = FileChannel.open(Paths.get(filePath), StandardOpenOption.READ, StandardOpenOption.WRITE);
		// 将整个文件映射到内存
		MappedByteBuffer buf = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());

		// mode 表示打开模式，为枚举值，其值可以为 READ_ONLY, READ_WRITE, PRIVATE。
		//
		// 模式为 READ_ONLY 时，不能对 buf 进行写操作；
		// 模式为 READ_WRITE 时，通道 fileChannel 必须具有读写文件的权限；对 buf 进行的写操作将对文件生效，但不保证立即同步到 I/O 设备；
		// 模式为 PRIVATE 时，通道 fileChannel 必须对文件有读写权限；但是对文件的修改操作不会传播到 I/O 设备，而是会在内存复制一份数据。此时对文件的修改对其它线程和进程不可见。
		// position 指定文件的开始映射到内存的位置；
		// size 指定映射的大小，值为非负 int 型整数。

		// 调用 map() 方法之后，返回的 MappedByteBuffer 就与 fileChannel 脱离了关系，关闭 fileChannel 对 buf 没有影响。
		// 同时，如果要确保对 buf 修改的数据能够同步到文件 I/O 设备中，需要调用 MappedByteBuffer 中的无参数的 force() 方法，
		// 而调用 FileChannel 中的 force(metaData) 方法无效。
		//
		// 此时可以通过操作缓冲区来操作文件了。不过映射的内容存在于 JVM 程序的堆外内存中，这部分内存是虚拟内存，
		// 意味着 buf 中的内容不一定都在物理内存中，要让这些内容加载到物理内存，可以调用 MappedByteBuffer 中的 load() 方法。
		// 另外，还可以调用 isLoaded() 来判断 buf 中的内容是否在物理内存中。

		FileChannel fileChannel2 = FileChannel.open(Paths.get(filePath), StandardOpenOption.WRITE, StandardOpenOption.READ);
		MappedByteBuffer buf2 = fileChannel2.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel2.size());
		fileChannel2.close();    // 关闭文件通道对 buf 没有影响
		System.out.println(buf2.capacity()); // 输出 fileChannel.size()
		System.out.println(buf2.limit());    // 输出 fileChannel.size()
		System.out.println(buf2.position()); // 输出 0
		buf2.put((byte) 'R'); // 写入内容
		buf2.compact();         // 截掉 position 之前的内容
		buf2.force();           // 将数据刷出到 I/O 设备


		// 小结
		// 1）文件通道 FileChannel 能够将数据从 I/O 设备中读入(read)到字节缓冲区中，或者将字节缓冲区中的数据写入(write)到 I/O 设备中。
		// 2）文件通道能够转换到 (transferTo) 一个可写通道中，也可以从一个可读通道转换而来（transferFrom）。这种方式使用于通道之间地数据传输，比使用缓冲区更加高效。
		// 3）文件通道能够将文件的部分内容映射（map）到 JVM 堆外内存中，这种方式适合处理大文件，不适合处理小文件，因为映射过程本身开销很大。
		// 4）在对文件进行重要的操作之后，应该将数据刷出刷出(force)到磁盘，避免操作系统崩溃导致的数据丢失。
	}
}
