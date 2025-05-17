package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 02:30:00
 * @apiNote TODO 假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半;
 *               再落下，求它在第5次落地时，共经历多少米? 第5次反弹多高？
 */
public class Q_小球自由落体 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入小球自由落体的高度：\n");
		double high = scanner.nextDouble();
		// 反弹的高度
		double currentHigh = 0;
		// 落下的高度
		double fallingHigh = high;
		// 共经历的高度
		double sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += currentHigh + fallingHigh;
			currentHigh = fallingHigh / 2;
			fallingHigh = currentHigh;
		}
		System.out.println("第5次落地时，共经历" + sum + "米");
		System.out.println("第5次反弹" + currentHigh + "米");
	}

}
