package S19_编译机试题;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2025-01-03 00:00:00
 * @apiNote TODO 给你一个整数数组 nums，数组中的元素互不相同。返回该数组所有可能的子集
 *               解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *               示例：
 *                     输入：nums = [1,2,3]
 *                     输出：[[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]
 */
public class 子集 {

	// 方法：回溯算法
	private List<List<Integer>> res = new ArrayList<>();

	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		System.out.println(new 子集().subsets(nums));
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<Integer> selectList = new ArrayList<>();
		int start = 0;
		dfs(nums, start, selectList);
		return res;
	}

	private void dfs(int[] nums, int start, List<Integer> selectList) {
		// 无临界条件
		res.add(new ArrayList<>(selectList));
		// 遍历
		for (int i = start; i < nums.length; i++) {
			// 选择
			selectList.add(nums[i]);
			// 下一层
			dfs(nums, i + 1, selectList);
			// 撤销
			selectList.remove(selectList.size() - 1);
		}
	}

}
