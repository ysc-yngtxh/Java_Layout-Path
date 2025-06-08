package S19_编译机试题;

/**
 * @Author 游家纨绔
 * @Date 2025-06-08 11:50:00
 * @Description TODO 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *                   岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *                   此外，你可以假设该网格的四条边均被水包围。
 *                   示例 1：
 *                       输入：grid = [
 *                         ["1","1","1","1","0"],
 *                         ["1","1","0","1","0"],
 *                         ["1","1","0","0","0"],
 *                         ["0","0","0","0","0"]
 *                       ]
 *                       输出：1
 *                   示例 2：
 *                       输入：grid = [
 *                         ["1","1","0","0","0"],
 *                         ["1","1","0","0","0"],
 *                         ["0","0","1","0","0"],
 *                         ["0","0","0","1","1"]
 *                       ]
 *                       输出：3
 *                   提示：
 *                       m == grid.length
 *                       n == grid[i].length
 *                       1 <= m, n <= 300
 *                       grid[i][j] 的值为 '0' 或 '1'
 */
public class 岛屿数量 {
	public static void main(String[] args) {
		char[][] grid1 = {
			{'1', '1', '1', '1', '0'},
			{'1', '1', '0', '1', '0'},
			{'1', '1', '0', '0', '0'},
			{'0', '0', '0', '0', '0'}
		};
		System.out.println(new 岛屿数量().numIslands(grid1)); // 输出 1

		char[][] grid2 = {
			{'1', '1', '0', '0', '0'},
			{'1', '1', '0', '0', '0'},
			{'0', '0', '1', '0', '0'},
			{'0', '0', '0', '1', '1'}
		};
		System.out.println(new 岛屿数量().numIslands(grid2)); // 输出 3
	}

	// 深度优先搜索(DFS)
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int numIslands = 0;
		int rows = grid.length;
		int cols = grid[0].length;

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == '1') {
					numIslands++;
					dfs(grid, r, c);
				}
			}
		}

		return numIslands;
	}

	private void dfs(char[][] grid, int r, int c) {
		int rows = grid.length;
		int cols = grid[0].length;

		if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
			return;
		}

		// 将当前陆地标记为已访问（即置为'0'）
		grid[r][c] = '0';

		// 检查四个方向
		dfs(grid, r - 1, c); // 上
		dfs(grid, r + 1, c); // 下
		dfs(grid, r, c - 1); // 左
		dfs(grid, r, c + 1); // 右
	}
}
