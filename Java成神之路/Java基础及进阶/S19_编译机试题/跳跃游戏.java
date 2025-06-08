package S19_编译机试题;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2025-01-03 22:00:00
 * @apiNote TODO 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *               判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *               示例 1：
 *                     输入：nums = [2, 3, 1, 1, 4]
 *                     输出：true
 *                     解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *               示例 2：
 *                     输入：nums = [3, 2, 1, 0, 4]
 *                     输出：false
 *                     解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0，所以永远不可能到达最后一个下标。
 */
public class 跳跃游戏 {

	public static void main(String[] args) {
		String line = new Scanner(System.in).nextLine();
		String[] arr = line.split(", ");
		int[] array = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
		int index = 0;    // 当前下标位置
		int[] sum = {0};  // 当前下标位置的值，也能理解为下标位置可以移动的步数
		int[] flag = {0}; // 判断是否能到达最后一个下标
		new 跳跃游戏().printArray(array, index, sum, flag);
		System.out.println(flag[0] != 2);

		System.out.println(new 跳跃游戏().canJump(array));
	}

	// 自己想的方法
	public void printArray(int[] arr, int index, int[] sum, int[] flag) {
		if (sum[0] >= arr.length - 1) {
			flag[0]++;
		} else if (sum[0] < arr.length - 1) {
			if (arr[index] == 0) {
				flag[0] = 2;
				return;
			}
			sum[0] += arr[index];
			printArray(arr, index + arr[index], sum, flag);
		}
	}

	// 方法二：贪心算法
	public boolean canJump(int[] nums) {
		int n = nums.length;
		int rightmost = 0;  // 记录当前能到达的最远位置
		for (int i = 0; i < n-1; ++i) {
			// 如果当前下标 i 超过了能到达的最远位置 rightmost，说明无法继续前进。因此这里加入判断最远距离应大于等于当前下标 i。
			if (i <= rightmost) {
				rightmost = Math.max(rightmost, i+nums[i]);
				// 因为下标值表示的是最大跳跃距离，但不代表每次跳跃时就得按照最大距离来进行。
				// 所以只要最后的跳跃距离超过最后一个下标，就也能代表可以到达最后一个下标处。
				if (rightmost >= n - 1) {
					return true;
				}
			}
		}
		return false;
	}

}
