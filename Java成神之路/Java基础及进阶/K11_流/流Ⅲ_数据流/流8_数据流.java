package K11_流.流Ⅲ_数据流;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * java.io.DataOutputStream：数据专属的流
 *   这个流可以将数据连同数据的类型一同写入文件
 *   注意：这个文件不是普通文本文件。（这个文件用记事本打不开）bytes
 *
 * java.io.DataInputStream：数据字节输入流
 *   DataOutputStream写的文件，只能使用DataInputStream去读，并且读的时候你需要提前知道写入的顺序。
 *   读的顺序需要和写的顺序一致，才可以正常取出数据
 */
public class 流8_数据流 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            // 创建数据专属的字节输出流
            fos = new FileOutputStream("data");
            DataOutputStream dos = new DataOutputStream(fos);

            // 写数据
            byte b = 100;
            short s = 200;
            int i = 300;
            long l = 400L;
            float f = 3.0F;
            double d = 3.14;
            boolean sex = false;
            char c = 'a';

            // 写
            dos.writeByte(b);  // 把数据以及数据类型一起写进去
            dos.writeShort(s);
            dos.writeInt(i);
            dos.writeLong(l);
            dos.writeFloat(f);
            dos.writeDouble(d);
            dos.writeBoolean(sex);
            dos.writeChar(c);

            // 创建数据专属的字节输入流
            fis = new FileInputStream("data");
            DataInputStream dis = new DataInputStream(fis);
            // 开始读
            byte b1 = dis.readByte();
            short s1 = dis.readShort();
            int i1 = dis.readInt();
            long l1 = dis.readLong();
            float f1 = dis.readFloat();
            double d1 = dis.readDouble();
            boolean sex1 = dis.readBoolean();
            char c1 = dis.readChar();

            System.out.println(b1);
            System.out.println(s1);
            System.out.println(i1 + 100);
            System.out.println(l1);
            System.out.println(f1);
            System.out.println(d1);
            System.out.println(sex1);
            System.out.println(c1);

            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis == null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
