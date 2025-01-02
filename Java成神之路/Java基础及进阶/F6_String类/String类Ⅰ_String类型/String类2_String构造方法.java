package F6_String类.String类Ⅰ_String类型;

import java.util.Arrays;

/*
  关于String类中的构造方法。
     第一个：String s1 = new String("");
     第二个：String s2 = ""; 最常用
     第三个：String s3 = new String(char数组）;
     第四个：String s4 = new String(char数组, 起始下标, 长度);
     第五个：String s5 = new String(byte数下标, 长度);
     第六个：String s6 = new String(byte数组, 起始下标, 长度);
 */
public class String类2_String构造方法 {
    public static void main(String[] args) {
        // 创建字符串对象最常用的一种方式
        String s1 = "hello world!";
        // 输出引用的时候，会自动执行引用的toString()方法，如果引用没有重写toString()方法，会使用Object的toString()方法。
        // 通过输出结果我们得出一个结论：String类已经重写了toString()方法
        System.out.println(s1);
        // 使用new关键字创建字符串对象
        String s2 = new String("hello world!");
        System.out.println(s2);

        // 将char数组全部转换成字符串
        char[] chars = {'我', '是', '中', '国', '人'};
        String s3 = new String(chars);
        System.out.println(s3);
        // 将char数组的一部分转换成字符串
        String s4 = new String(chars, 2, 3);
        System.out.println(s4);

        // 将char数组全部转换成字符串
        byte[] bytes = {97, 98, 99};  // 97是a, 98是b, 99是c  参照的是ASCⅡ字符码
        // String构造器 会把整个字节数组转换成对应的字符串
        String s5 = new String(bytes);
        System.out.println(s5);
        // String(字节数组,数组元素下标的起始位置,长度)
        String s6 = new String(bytes,1,2);
        System.out.println(s6);

        // TODO 重点掌握：Arrays.toString(byte[]) 和 new String(byte[]) 两种不同的处理字节数组（byte[]）方式。
        // Arrays.toString(byte[])：方法则将这个数组转换为一个字符串表示形式，其中每个字节都被转换成十进制整数并以逗号分隔的形式显示在方括号内。
        //                          例如，对于包含字节 [65, 66, 67] 的数组，结果将是 "[65, 66, 67]"。
        // new String(byte[])：构造函数尝试根据平台默认字符集解码提供的字节数组，或者你可以指定一个字符集来解码字节数组。
        //                     例如，如果你有一个 ASCII 或 UTF-8 编码文本的字节数组，那么使用这个构造函数可以正确地将其转换为相应的字符串。
        System.err.println( "Arrays.toString(byte[]) = " + Arrays.toString(bytes) );
        System.err.println( "new String(byte[]) = " + new String(bytes) );
    }
}
