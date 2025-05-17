package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 15:00:00
 * @apiNote TODO 给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌。
 *               规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B绝顶聪明。请返回最后的获胜者的分数。
 */
public class E_纸牌分数 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String s = scanner.nextLine();
			String[] split = s.split(", ");
			int[] arr = new int[split.length];
			for (int i = 0; i < split.length; i++) {
				int parseInt = Integer.parseInt(split[i]);
				arr[i] = parseInt;
			}
			System.out.println(win(arr));
		}
	}

	private static int win(int[] arr) {
		if (arr.length == 0 || arr == null) {
			return 0;
		}
		// 先拿的最终分数；后拿的最终分数
		return Math.max(first(arr, 0, arr.length - 1), after(arr, 0, arr.length - 1));
	}

	// first 函数用于计算先手在给定区间 [i, j] 的最大分数。i，j分别代表左边、右边的位置
	private static int first(int[] arr, int i, int j) {
		if (i == j) {
			return arr[i];
		}
		return Math.max(arr[i] + after(arr, i + 1, j), arr[j] + after(arr, i, j - 1));
	}

	// after 函数用于计算后手在给定区间 [i, j] 的最大分数。
	private static int after(int[] arr, int i, int j) {
		if (i == j) {
			return 0;
		}
		// 先手拿了最大的，那么后手肯定是拿了一副最小的牌，所以是Min
		return Math.min(first(arr, i + 1, j), first(arr, i, j - 1));
	}

}
