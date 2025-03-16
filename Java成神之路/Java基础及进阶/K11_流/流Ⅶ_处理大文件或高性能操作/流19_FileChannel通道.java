package K11_流.流Ⅶ_处理大文件或高性能操作;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-21 22:05
 * @apiNote TODO
 */
public class 流19_FileChannel通道 {

    private static final String filePath = System.getProperty("user.dir")
                                         + "/Java基础及进阶/K11_流/FileTemp";

    public static void main(String[] args) throws IOException {

        // TODO 获取 FileChannel 的其他方法1：
        // 从 RandomAccessFile 中获取一个可读写文件通道
        FileChannel random = new RandomAccessFile(filePath, "rw").getChannel();

        // TODO 获取 FileChannel 的其他方法2：
        // 通过静态静态方法 FileChannel.open() 打开的通道可以指定打开模式，模式通过 StandardOpenOption 枚举类型指定。
        FileChannel open = FileChannel.open(Paths.get(filePath), StandardOpenOption.READ);

        // 读取到多个缓冲区
        // 文件通道 FileChannel 实现了 ScatteringByteChannel 接口，可以将文件通道中的内容同时读取到多个 ByteBuffer 当中，这在处理包含若干长度固定数据块的文件时很有用。
        ScatteringByteChannel channel = FileChannel.open(Paths.get(filePath), StandardOpenOption.READ);
        // 创建字节大小分别为 5、10 的 ByteBuffer缓冲区
        ByteBuffer key = ByteBuffer.allocate(5), value = ByteBuffer.allocate(10);
        ByteBuffer[] buffers = new ByteBuffer[]{key, value};
        while(channel.read(buffers) != -1){
            key.flip();
            value.flip();
            System.err.println("key：" + new String(key.array()));
            System.err.println("value：" + new String(value.array()));
            key.clear();
            value.clear();
        }
        channel.close();


        // 从多个缓冲区写入
        // FileChannel 实现了 GatheringByteChannel 接口，与 ScatteringByteChannel 相呼应。可以一次性将多个缓冲区的数据写入到通道中。
        FileChannel channel1 = FileChannel.open(Paths.get(filePath), StandardOpenOption.WRITE);
        ByteBuffer key1 = ByteBuffer.allocate(10), value1 = ByteBuffer.allocate(10);
        byte[] data = "017 Robot".getBytes();
        key1.put(data, 0, 3);
        value1.put(data, 4, data.length-4);
        ByteBuffer[] buffers1 = new ByteBuffer[]{key1, value};
        key1.flip();
        value1.flip();
        channel1.write(buffers1);
        channel1.force(false); // 将数据刷出到磁盘，但不包括元数据
        channel1.close();

        // 通道转换
        copy(new File(filePath), new File(filePath + "1"));
    }

    // 普通的读写方式是利用一个 ByteBuffer 缓冲区，作为数据的容器。但如果是两个通道之间的数据交互，利用缓冲区作为媒介是多余的。
    // transferFrom(ReadableByteChannel src, position, count) 和 transferTo(position, count, WritableChannel target)
    // 进行通道间的数据传输时，这两个方法比使用 ByteBuffer 作为媒介的效率要高；
    // transferFrom 或者 transferTo 在调用之后并不会改变 position 的位置。
    public static void copy(File source, File target) throws IOException {
        FileInputStream sourceOutStream = new FileInputStream(source);
        FileOutputStream targetOutStream = new FileOutputStream(target);
        FileChannel sourceChannel = sourceOutStream.getChannel();
        FileChannel targetChannel = targetOutStream.getChannel();
        long transfered = 0;
        // 需要注意，调用这两个转换方法，某些情况下并不保证数据能够全部完成传输，确切传输了多少字节数据需要根据返回值来进行判断
        while (transfered < sourceChannel.size()) {
            transfered += sourceChannel.transferTo(transfered, targetChannel.size(), targetChannel);
        }
        sourceChannel.close();
        targetChannel.close();
        sourceOutStream.close();
        targetOutStream.close();
    }
}
