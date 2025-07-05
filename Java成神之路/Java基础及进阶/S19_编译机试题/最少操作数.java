package S19_编译机试题;

/**
 * @Author 游家纨绔
 * @Date 2025-06-08 11:40:00
 * @Description TODO 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。
 *                   你可以对一个单词进行如下三种操作：
 *                       插入一个字符
 *                       删除一个字符
 *                       替换一个字符
 *                   示例 1：
 *                       输入：word1 = "horse", word2 = "ros"
 *                       输出：3
 *                       解释：
 *                       horse -> rorse (将 'h' 替换为 'r')
 *                       rorse -> rose (删除 'r')
 *                       rose -> ros (删除 'e')
 *                   示例 2：
 *                       输入：word1 = "intention", word2 = "execution"
 *                       输出：5
 *                       解释：
 *                       intention -> inention (删除 't')
 *                       inention -> enention (将 'i' 替换为 'e')
 *                       enention -> exention (将 'n' 替换为 'x')
 *                       exention -> exection (将 'n' 替换为 'c')
 *                       exection -> execution (插入 'u')
 */
public class 最少操作数 {
	public static void main(String[] args) {
		System.out.println(minDistance("horse", "ros")); // 输出 3
		System.out.println(minDistance("intention", "execution")); // 输出 5
	}

	// 动态规划
	public static int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		// dp[i][j] 表示 word1 前 i 个字符和 word2 前 j 个字符的编辑距离
		int[][] dp = new int[m+1][n+1];
		// 初始化
		for (int i = 0; i <= m; i++) {
			dp[i][0] = i; // word2 为空，需要删除 i 次
		}
		for (int j = 0; j <= n; j++) {
			dp[0][j] = j; // word1 为空，需要插入 j 次
		}
		// 动态规划填表
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i-1) == word2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];     // 字符相同，无需操作
				} else {
					dp[i][j] = Math.min(
							dp[i-1][j-1],        // 替换
							Math.min(dp[i][j-1], // 插入
							         dp[i-1][j]  // 删除
							)
					) + 1;
				}
			}
		}
		return dp[m][n];
	}

}
