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

class 纯逻辑写法_动态规划 {

	public static void main(String[] args) {
		int[] arr1 = {1, 2, 100, 4};
		System.out.println(getWinnerScore(arr1)); // 输出：101

		int[] arr2 = {1, 100, 2};
		System.out.println(getWinnerScore(arr2)); // 输出：100
	}

	public static int getWinnerScore(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int n = arr.length;
		int[][] dp = new int[n][n]; // dp[i][j] 表示：纸牌剩余 arr[i...j]

		// 初始化：只有一张牌时，直接拿走
		for (int i = 0; i < n; i++) {
			dp[i][i] = arr[i];
		}

		// 初始化：两张牌时，拿较大的那张
		for (int i = 0; i < n - 1; i++) {
			dp[i][i + 1] = Math.max(arr[i], arr[i + 1]);
		}

		// 填充dp表，从子问题长度为3开始
		for (int len = 3; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1;
				// 当前玩家拿左端牌，对手在[i+1...j]上拿最优
				int pickLeft = arr[i] + Math.min(
						(i + 2 <= j ? dp[i + 2][j] : 0),
						(i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0)
				);
				// 当前玩家拿右端牌，对手在[i...j-1]上拿最优
				int pickRight = arr[j] + Math.min(
						(i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0),
						(i <= j - 2 ? dp[i][j - 2] : 0)
				);
				dp[i][j] = Math.max(pickLeft, pickRight);
			}
		}

		int totalSum = 0;
		for (int num : arr) {
			totalSum += num;
		}
		int playerAScore = dp[0][n-1];
		int playerBScore = totalSum - playerAScore;

		return Math.max(playerAScore, playerBScore);
	}
}
