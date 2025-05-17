package H8_Java对日期的处理;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Java6_产生随机数 {

	public static void main(String[] args) {
		// 产生随机数对象
		Random random = new Random();
		// 随机产生一个int类型取值范围内的数字
		int num1 = random.nextInt();
		System.out.println(num1);

		// 产生[0-101)之间的随机数，不能产生101。遵循左闭右开
		// nextInt翻译为：下一个int类型的数据是101，表示只能取到100
		int num2 = random.nextInt(101);  // 不包括101
		System.out.println(num2);

		System.out.println("======================================================================");

		// TODO 编写程序，生成5个不重复的随机数[0-100]。重复的话重新生成
		// 最终生成的5个随机数放到数组中，要求数组中这5个随机数不重复
		int[] arr = new int[5];  // 数组arr的默认值是0
		// 为区别数组中的默认值0和随机数中的0，直接赋给数组-1
		for (int i = 0; i < arr.length; i++) {
			arr[i] = -1;
		}
		// 循环，生成随机数
		int index = 0;
		while (index < arr.length) {
			// 生成随机数
			int num3 = random.nextInt(101);
			// 判断arr数组中有没有这个num3
			if (!contains(arr, num3)) {
				arr[index++] = num3;
			}
		}
		System.out.println(Arrays.toString(arr));

		System.out.println("======================================================================");

		// 在网上学的,也不知道语法正不正确,反正是能运行了。可以用来做验证码,这一点与以上代码无关系
		String s = "123456789abcdefghijklmnopqrstuvwxyz";
		// 这段代码表示的是在字符串s中取出一个
		char a = s.charAt(random.nextInt(s.length()));
		System.out.println(a);

		// 随机取一个double类型数据，并格式化为保留两位小数
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		String format = decimalFormat.format(new Random().nextDouble());
		double parseDouble = Double.parseDouble(format);
		System.out.printf("%.2f%n", parseDouble);
	}

	// 编写方法用来判断数组中是否包含某个元素
	private static boolean contains(int[] arr, int key) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) {
				return true;
			}
		}
		return false;
	}

}
