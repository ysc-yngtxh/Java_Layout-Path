package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-29 12:30:00
 * @apiNote TODO 给定一个字符串数组arr，再给定两个字符串str1和str2，返回在arr中str1和str2的最小距离，
 * 如果str1或str2为null，或不在arr中，返回-1。
 * 例如：arr=[CD AB]  str1=AB  str2=null  返回-1
 * 例如：arr=[QWER 1234 qwe 666 QWER]  str1=QWER  str2=666  返回1
 */
public class M_数组中字符串最近距离 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// scanner.next()方法会读取输入中的下一个单词，并会消耗掉一个空格，这样下一个next()就能直接读取到数据。
		String str1 = scanner.next();
		String str2 = scanner.next();
		// 比如输入：123 456
		//         123, dfas, bvf34, dsfasdf, 456, dfashf, dafbqide, 123
		// 需要在while循环前执行 scanner.nextLine() 消耗掉当前位置行与换行符。否则后续的 nextLine() 方法会读取不到数组数据。
		// 原因：next()只会消耗掉一个空格，那么执行 nextLine()，读取到的是 [123 456 ] 后的这一行数据，即为空。
		scanner.nextLine();

		while (scanner.hasNext()) {
			String s = scanner.nextLine();
			String[] arr = s.split(", ");

			if (str1 == null || str2 == null) {
				System.out.println(-1);
				return;
			}

			int exitStr1 = -1, exitStr2 = -1, min = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].equals(str1)) {
					exitStr1 = i;
					if (exitStr2 != -1) {
						min = Math.min(min, exitStr1 - exitStr2);
					}
				} else if (arr[i].equals(str2)) {
					exitStr2 = i;
					if (exitStr1 != -1) {
						min = Math.min(min, exitStr2 - exitStr1);
					}
				}
			}
			if (exitStr1 < 0 || exitStr2 < 0) {
				System.out.println(-1);
				return;
			}
			System.out.println(min);
		}
	}

}

class 最近距离_动态规划 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str1 = scanner.next();
		String str2 = scanner.next();
		scanner.nextLine();
		String s = scanner.nextLine();
		String[] arr = s.split(", ");

		if (str1 == null || str2 == null) {
			System.out.println(-1);
			return;
		}

		int distance = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length; i++) {
			// 判断 arr[i] 是否为指定的字符串str1或str2
			if (arr[i].equals(str1) || arr[i].equals(str2)) {
				for (int j = 0; j < i; j++) {
					// 判断 arr[j] 为另一个字符串，则计算 arr[i] 与 arr[j] 的距离
					if (arr[j].equals(str1) || arr[j].equals(str2) && !arr[j].equals(arr[i])) {
						distance = Math.min(distance, Math.abs(i-j));
					}
				}
			}
		}
		System.out.println(distance);
	}

}
