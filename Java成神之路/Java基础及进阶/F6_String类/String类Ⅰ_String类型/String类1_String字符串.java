package F6_String类.String类Ⅰ_String类型;

/*
 * 1、String表示字符串类型，属于引用数据类型，不属于基本数据类型
 *
 * 2、在Java中随便使用双引号括起来的都是String对象。例如：“abc”，“def”，“hello world”,这是三个String对象
 *
 * 3、在Java中规定，双引号括起来的字符串是不可变的，也就是说“abc”自出生到最终死亡，不可变，不能变成“abcd”
 *
 * 4、在JDK当中双引号括起来的字符串，例如：“abc”，“def”都是直接存储在“方法区”的“字符串常量池”当中的
 *    因为字符串在实际开发中使用太频繁，为了执行效率，所以把字符串放到方法区的字符串常量池当中
 */
public class String类1_String字符串 {

	public static void main(String[] args) {
		// 这两行代码表示各自创建了1个字符串对象，都在字符串常量池当中
		String s1 = "abcd";   // 在常量池创建一个对象"abcd"，然后返回常量池中"abcd"的地址给变量s1.
		String s2 = "abcd" + "xy";

		// 这里创建了两个对象  ①、凡是双引号括起来的都在字符串常量池中有一份
		//                  ②、new对象的时候一定在堆内存当中开辟空间
		String s3 = new String("xyz");
	}

}
