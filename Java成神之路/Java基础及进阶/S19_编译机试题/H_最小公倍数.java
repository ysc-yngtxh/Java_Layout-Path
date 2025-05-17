package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 15:30:00
 * @apiNote TODO 正整数A和正整数B的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。
 */
public class H_最小公倍数 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		// TODO 方法一：最小公倍数 = 两数的积 / 最大公约数
		int maxY = 1;
		int minNum = Math.min(a, b);
		for (int i = 2; i <= minNum; i++) {
			if (a % i == 0 && b % i == 0) {
				maxY = i;
			}
		}
		System.out.println(a * b / maxY);


		// TODO 方法二
		int max = Math.max(a, b);
		int x = max;
		// 这种写法没有判断条件，意味着会一直循环，并且每一次循环后 x=x+max。
		// 写法比较简单，但是就是一个数一个数去尝试满足条件，性能要求不大好，不推荐
		for (; ; x += max) {
			if (x % a == 0 && x % b == 0) {
				System.out.println(x);
				return;  // 当我们的条件符合后，使用return;结束for循环
			}
		}
	}

}
