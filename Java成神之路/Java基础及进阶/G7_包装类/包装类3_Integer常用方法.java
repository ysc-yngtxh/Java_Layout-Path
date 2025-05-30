package G7_包装类;

/*
 * 1、在JDK1.5之后，支持自动拆箱和自动装箱了
 *        自动装箱：基本数据类型自动转换成包装类
 *        自动拆箱：包装类自动转换成基本数据类型
 *
 *        自动装箱和自动拆箱的优点：方便编程
 * 2、总结一下之前所学的经典异常
 *         空指针异常：NullPointerException
 *         类型转换异常：ClassCastException
 *         数组下标越界异常：ArrayIndexOutOfBoundsException
 *         数字格式化异常：NumberFormatException
 */
public class 包装类3_Integer常用方法 {

	public static void main(String[] args) {
		// 900是基本数据类型
		// x是包装类型
		// 基本数据类型 -->（自动转换）--> 包装类型：自动装箱
		Integer x = 900; // 等同于Integer x= new Integer(900);
		System.out.println(x);

		// x是包装类型
		// y是基本数据类型
		// 包装类型 -->（自动转换）--> 基本数据类型：自动拆箱
		int y = x;
		System.out.println(y);

		Integer z = 1000;
		System.out.println(z + 1);
		// +号两边要求是基本数据类型，z是包装类，不属于基本数据类型，这里会进行自动拆箱，将z转换成基本数据类型
		// 在Java5之前这样写你肯定会报错

		System.out.println("-----------------------------------------------------------------------------------------");

		// TODO 重要的面试题
		Integer a = 128;
		Integer b = 128;
		System.out.println(a == b); // false

        /*
         * 默认范围：Integer 类内部静态类 IntegerCache 默认缓存了 -128 到 127 之间的所有整数对象。
         * 自动装箱与缓存：当通过 自动装箱 或调用 Integer.valueOf(int) 方法创建 Integer 对象时，
         *               若值在缓存范围内，直接返回缓存对象；否则创建新对象。
         *               这些缓存对象存储在 Java 虚拟机的堆内存（Heap） 中。
         *
         * 原理：a1变量中保存的对象的内存地址和b1变量中保存的对象的内存地址是一样的。
         */
		Integer a1 = 127;
		Integer b1 = 127;
		System.out.println(a1 == b1); // true

		// 直接使用 new Integer() 会强制创建新对象，无论值是否在缓存范围内。
		Integer a2 = new Integer(127);
		Integer b2 = new Integer(127);
		System.out.println(a2 == b2); // false

		System.out.println("-----------------------------------------------------------------------------------------");

		// 手动装箱
		Integer m = new Integer(100);
		// 手动拆箱
		int n = m.intValue();
		System.out.println(n);
		Integer t = new Integer("123");
		// Integer t = new Integer("中文");
		// 当Integer方法里面""不是数字而出现的是字符串的时候，虽然能通过编译，但运行会报错

		System.out.println("-----------------------------------------------------------------------------------------");

		// 重点方法
		// static int parseInt(String s)
		// 静态方法，传参String,返回int
		// 网页上文本框中输入的100实际上是"100"字符串。后台数据库中要求存储100数字，此时Java程序需要将"100"转换成100数字
		int retValue = Integer.parseInt("123"); // String-(转换) -> int
		System.out.println(retValue + 100);

		float f = Float.parseFloat("1.0"); // String-(转换) -> float
		System.out.println(f + 2.0);
	}

}
