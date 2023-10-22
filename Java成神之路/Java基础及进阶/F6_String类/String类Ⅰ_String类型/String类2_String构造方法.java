package F6_String类.String类Ⅰ_String类型;

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
        System.out.println(s1);

        // 这里只掌握常见的构造方法
        byte[] bytes = {97, 98, 99};  // 97是a, 98是b, 99是c  参照的是ASCⅡ字符码
        String s2 = new String(bytes);

        // 输出一个引用的时候，会自动调用toString()方法，默认Object的话，会自动输出对象的内存地址。
        // 通过输出结果我们得出一个结论：String类已经重写了toString()方法
        // 输出字符串对象的话，输出的不是对象的内存地址，而是字符串本身
        System.out.println(s2);

        // String(字节数组,数组元素下标的起始位置,长度)
        String s3 = new String(bytes,1,2);
        System.out.println(s3);

        // 将char数组全部转换成字符串
        char[] chars = {'我', '是', '中', '国', '人'};
        String s4 = new String(chars);
        System.out.println(s4);
        // 将char数组的一部分转换成字符串
        String s5 = new String(chars, 2, 3);
        System.out.println(s5);

        String s6 = new String("hello world!");
        System.out.println(s6);
    }
}
