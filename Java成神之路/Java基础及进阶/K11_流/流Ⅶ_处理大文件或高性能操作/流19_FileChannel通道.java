package K11_流.流Ⅶ_处理大文件或高性能操作;

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

    public static void main(String[] args) throws IOException {

        // 从 RandomAccessFile 中获取
        // 从 RandomAccessFile 中获取的通道取决于 RandomAccessFile 对象是以什么方式创建的，"r", "w", "rw" 分别对应着读模式，写模式，以及读写模式。
        RandomAccessFile file = new RandomAccessFile("a.txt", "rw");
        FileChannel channel0 = file.getChannel(); // 获取一个可读写文件通道

        // 通过 FileChannel.open() 打开
        // 通过静态静态方法 FileChannel.open() 打开的通道可以指定打开模式，模式通过 StandardOpenOption 枚举类型指定。
        // 读取到多个缓冲区
        // 文件通道 FileChannel 实现了 ScatteringByteChannel 接口，可以将文件通道中的内容同时读取到多个 ByteBuffer 当中，这在处理包含若干长度固定数据块的文件时很有用。
        ScatteringByteChannel channel = FileChannel.open(Paths.get("a.txt"), StandardOpenOption.READ);
        ByteBuffer key = ByteBuffer.allocate(5), value = ByteBuffer.allocate(10);
        ByteBuffer[] buffers = new ByteBuffer[]{key, value};
        while(channel.read(buffers) != -1){
            key.flip();
            value.flip();
            System.out.println(new String(key.array()));
            System.out.println(new String(value.array()));
            key.clear();
            value.clear();
        }
        channel.close();


        // 从多个缓冲区写入
        // FileChannel 实现了 GatherringByteChannel 接口，与 ScatteringByteChannel 相呼应。可以一次性将多个缓冲区的数据写入到通道中。
        FileChannel channel1 = FileChannel.open(Paths.get("a.txt"), StandardOpenOption.WRITE);
        ByteBuffer key1 = ByteBuffer.allocate(10), value1 = ByteBuffer.allocate(10);
        byte[] data = "017 Robothy".getBytes();
        key1.put(data, 0, 3);
        value1.put(data, 4, data.length-4);
        ByteBuffer[] buffers1 = new ByteBuffer[]{key1, value};
        key1.flip();
        value1.flip();
        channel1.write(buffers1);
        channel1.force(false); // 将数据刷出到磁盘
        channel1.close();


    }
}
