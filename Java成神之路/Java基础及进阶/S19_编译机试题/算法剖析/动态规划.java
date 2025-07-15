package S19_编译机试题.算法剖析;

import java.util.Arrays;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-06-06 11:40:00
 */
public class 动态规划 {
	/* 动态规划（英语：Dynamic programming，简称 DP），是一种在数学、管理科学、计算机科学、经济学和生物信息学中使用的，
	 * 通过把原问题分解为相对简单的子问题的方式求解复杂问题的方法。动态规划常常适用于有重叠子问题和最优子结构性质的问题。
	 * 简单来说，动态规划其实就是，给定一个问题，我们把它拆成一个个子问题，直到子问题可以直接解决。
	 * 然后呢，把子问题答案保存起来，以减少重复计算。再根据子问题答案反推，得出原问题解的一种方法。
	 *
	 * 什么样的问题可以考虑使用动态规划解决呢？
	 *     如果一个问题，可以把所有可能的答案穷举出来，并且穷举出来后，发现存在重叠子问题，就可以考虑使用动态规划。
	 *     比如一些求最值的场景，如最长递增子序列、最小编辑距离、背包问题、凑零钱问题等等，都是动态规划的经典应用场景。
	 *
	 * 动态规划的解题思路
	 *     动态规划的核心思想就是拆分子问题，记住过往，减少重复计算。
	 *     并且动态规划一般都是自底向上的，因此到这里，基于青蛙跳阶问题，我总结了一下我做动态规划的思路：
	 *     穷举分析
	 *     确定边界
	 *     找出规律，确定最优子结构
	 *     写出状态转移方程
	 */

	// TODO 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
	//      例如：输入：nums = [10,9,2,5,3,7,101,18]
	//           输出：4
	//           解释：最长递增子序列是 [2,3,7,101]，因此长度为 4
	public static void main(String[] args) {
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		System.out.println(lengthOfLIS(nums)); // 输出应为4
	}

	public static int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1); // 初始化为1，因为每个元素本身就是一个长度为1的子序列
		int maxLength = 1;         // 这里定义最长的递增子序列长度至少为1

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLength = Math.max(maxLength, dp[i]);
		}

		return maxLength;
	}
}
