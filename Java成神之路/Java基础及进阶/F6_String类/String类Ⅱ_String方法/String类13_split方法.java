package F6_String类.String类Ⅱ_String方法;

import java.util.Arrays;

public class String类13_split方法 {
	public static void main(String[] args) {

		// (掌握) String[] split(String regex) 拆分字符串
		String[] ymd = "1997-04-29".split("-");
		System.out.println(Arrays.toString(ymd));  // [1997, 04, 29]

		String[] ysc = "name=zhangsan & password=123 & age=20".split("&");
		System.out.println(Arrays.toString(ysc)); // [name=zhangsan ,  password=123 ,  age=20]


		// (掌握) String[] split(String regex, int limit) 拆分字符串
		// limit: 限制返回的字符串数组个数
		//        如果 limit 为负数，则不限制返回的字符串数组个数
		//        如果 limit 为 0，则不限制返回的字符串数组个数，但会舍弃末位的空串
		System.out.println(Arrays.toString("@2@3@".split("@", -1)));  // [, 2, 3, ]
		System.out.println(Arrays.toString("@2@3@".split("@", 0)));   // [, 2, 3]
		System.out.println(Arrays.toString("@2@3@".split("@", 2)));   // [, 2@3@]
		System.out.println(Arrays.toString("@2@3@".split("@", 3)));   // [, 2, 3@]
		System.out.println(Arrays.toString("@2@3@".split("@", 4)));   // [, 2, 3, ]
		System.out.println(Arrays.toString("@2@3@".split("@", 5)));   // [, 2, 3, ]
	}
}
