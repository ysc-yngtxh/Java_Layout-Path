package G7_包装类;

public class 包装类4_int和String和Integer之间的转换 {

	public static void main(String[] args) {
		// int转换String:
		String s = 1 + "";
		System.out.println(s + 3 + "1"); // String字符串拼接
		System.out.println(String.valueOf(100));

		// String转换int:
		int i = Integer.parseInt("123");
		System.out.println(i);

		System.out.println("=========================================================================================");

		// int转换Integer:
		Integer x = 100;  // 自动装箱
		// Integer转换int：
		int y = x;       // 自动拆箱

		System.out.println("=========================================================================================");

		// String转换Integer:
		Integer k = Integer.valueOf("456");
		System.out.println(k);
		// Integer转换String:
		String r = String.valueOf(k);
		System.out.println(r);
	}

}
