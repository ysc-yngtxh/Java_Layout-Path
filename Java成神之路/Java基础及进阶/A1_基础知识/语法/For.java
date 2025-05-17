package A1_基础知识.语法;

/* 一、for循环的执行过程/执行原理？
 *     1、初始化表达式，布尔表达式，更新表达式都不是必须的！
 *     2、初始化表达式最先执行，并且在整个for循环当中只执行一次。
 *     3、布尔表达式必须是true/false，不能是其他值。
 *
 * 二、for执行过程：
 *     1、先执行初始化表达式，并且该表达式只执行一次
 *     2、判断布尔表达式的结果是true还是false
 *        - 布尔表达式true
 *            * 执行循环体
 *            * 执行更新循环体
 *            * 判断布尔表达式的结果是true还是false
 *               - 布尔表达式true
 *                    * 执行循环体
 *                    * 执行更新循环体
 *                    * 判断布尔表达式的结果是true还是false
 *                         - 布尔表达式true
 *                             * 执行循环体
 *                             * 执行更新循环体
 *                             * 判断布尔表达式的结果是true还是false
 *                         - 布尔表达式false
 *                             * 循环结束
 *                - 布尔表达式false
 *                    * 循环结束
 *        - 布尔表达式false
 *            * 循环结束
 */
public class For {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; ++i) {      // 初始化表达式int i=1，最先执行，并且只执行一次，然后再执行布尔表达式，后续更新表达式
			System.out.println("i =" + i);  // 1-10
		}
		// 执行顺序：初始表达式 -> 布尔表达式 -> 循环体 -> 更新表达式

		int i = 1;
		for (; i < 10; ++i) {
			System.out.println("i = " + i); // 1 - 9
		}
		System.out.println("i = " + i);     // 10


		for (int i1 = 1; i1 < 10; ) {
			i1++;
			System.out.println("i1 = " + i1); // 1-10
		}


		int sum = 0;
		for (int i2 = 1; i2 <= 100; i2 += 2) {
			sum += i;
		}
		System.out.println("sum = " + sum); // 所有100以内的奇数之和

		// 嵌套if
		for (int i3 = 1; i3 <= 100; i3 += 2) {
			if (i3 % 2 != 0) {
				System.out.println("i3 = " + i3);
			}
		}


		for (int a = 1; a <= 9; a++) {
			for (int b = 1; b <= a; b++) {
				System.out.print(a + "*" + b + "=" + (a * b) + " ");
			}
			System.out.println(); // 这个也可以写成System.out.print("\n");目的在于乘法口诀的分行。
		}
	}

}
