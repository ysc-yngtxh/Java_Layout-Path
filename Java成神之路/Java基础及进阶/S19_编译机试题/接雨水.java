package S19_编译机试题;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 @author 游家纨绔
 @dateTime 2024-12-28 11:10:00
 @apiNote TODO 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
           示例：
                 ⬆︎
               3 |                     ██
               2 |         ██ □  □  □  ██ ██ □  ██
               1 |   ██ □  ██ ██ □  ██ ██ ██ ██ ██ ██
                 +——————————————————————————————————————>   ██：表示数组高度   □：表示可以接住的雨水
                  0  1  0  2  1  0  1  3  2  1  2  1
                 <p>
                 输入：height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
                 输出：6
                 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水。
 */
public class 接雨水 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String arr = scanner.nextLine();
		int[] split = Arrays.stream(arr.split(", ")).mapToInt(Integer::new).toArray();
		System.out.println(trap(split));
		System.out.println(trap2(split));
		System.out.println(trap3(split));
	}

	// 方法一：动态规划
	// 解析：对于每个柱子i，它能接的雨水量取决于它左边最高的柱子和右边最高的柱子中的较小值
	//     （因为水的高度不能超过两者中的较小值），然后减去当前柱子的高度。
	//      即：对于每个位置 i，能接住的雨水量等于 min(下标i左边最大高度, 下标i右边最大高度) - height[i]。
	public static int trap(int[] height) {
		int n = height.length;
		if (n == 0) return 0;

		// 获取每个下标位置左边的最大高度
		int[] leftMax = new int[n];
		leftMax[0] = height[0];
		for (int i = 1; i < n; ++i) {
			leftMax[i] = Math.max(leftMax[i - 1], height[i]);
		}

		// 获取每个下标位置左边的最大高度
		int[] rightMax = new int[n];
		rightMax[n - 1] = height[n - 1];
		for (int i = n - 2; i >= 0; --i) {
			rightMax[i] = Math.max(rightMax[i + 1], height[i]);
		}

		// 获取到每个下标位置左右两边的最大高度后，取其最小值，然后减去当前柱子的高度。即为当前下标位置的接水量
		int ans = 0;
		for (int i = 0; i < n; ++i) {
			ans += Math.min(leftMax[i], rightMax[i]) - height[i];
		}
		return ans;
	}

	// 方法二：单调栈
	public static int trap2(int[] height) {
		int ans = 0;
		Deque<Integer> stack = new LinkedList<>();
		int n = height.length;
		for (int i = 0; i < n; ++i) {
			while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
				int top = stack.pop();
				if (stack.isEmpty()) {
					break;
				}
				int left = stack.peek();
				int currWidth = i - left - 1;
				int currHeight = Math.min(height[left], height[i]) - height[top];
				ans += currWidth * currHeight;
			}
			stack.push(i);
		}
		return ans;
	}

	// 方法三：双指针
	public static int trap3(int[] height) {
		int ans = 0;
		int left = 0, right = height.length - 1;
		int lMax = 0, rMax = 0;
		while (left < right) {
			lMax = Math.max(lMax, height[left]);
			rMax = Math.max(rMax, height[right]);
			if (height[left] < height[right]) {
				ans += lMax - height[left];
				++left;
			} else {
				ans += rMax - height[right];
				--right;
			}
		}
		return ans;
	}

}
