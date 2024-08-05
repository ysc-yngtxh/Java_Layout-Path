package F6_String类.String类Ⅲ_String拼接;

/*
  如果以后需要进行大量字符串的拼接操作，建议使用JDK自带的：
         java.lang.StringBuffer
         java.lang.StringBuilder
  如何优化StringBuffer的性能：
         在创建StringBuffer的时候尽可能给定一个初始化容量。
         最好减少底层数组的扩容次数。预估一下，给一个大一些的初始化容量
 */
public class String类20_StringBuffer进行字符串拼接 {
    public static void main(String[] args) {
        String s = "";
        for (int i = 0; i < 10; i++) {
            s += i;
            System.out.println(s);
        }
        // 0
        // 01
        // 012
        // 0123
        // 01234
        // 012345
        // 0123456
        // 01234567
        // 012345678
        // 0123456789
        // 一次循环：""是一个String对象，i是个对象，s += i又是个对象。下一次循环又会创建三个对象
        // 每一次循环都会新建String对象，并开辟一个方法区常量池空间，会给方法区常量池带来很大的压力

        // 创建一个初始化容量为16个byte[] E5_数组。（字符串缓冲区对象）
        StringBuffer  stringBuffer = new StringBuffer();
        // 拼接字符串，以后拼接字符串统一调用append()方法，append是追加的意思
        stringBuffer.append("a");
        stringBuffer.append("b");  // 直接会在byte数组中填，并不会重新开辟一个方法区
        stringBuffer.append("c");
        stringBuffer.append("Hello");
        stringBuffer.append("World");
        stringBuffer.append(100L); // append方法底层在进行追加的时候，如果byte数组满了，会自动扩容
        System.out.println(stringBuffer);

        // 指定初始化容量的StringBuffer对象（字符串缓冲对象）
        StringBuffer sb = new StringBuffer(100);
        sb.append("a");
        sb.append("b");
        sb.append("c");
        sb.append("Hello");
        sb.append("World");
        System.out.println(sb);  // 在println()方法中引用会自动调用toString()方法
    }
}
