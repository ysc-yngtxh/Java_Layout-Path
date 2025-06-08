package S19_编译机试题;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2025-01-03 00:00:00
 * @apiNote TODO 给你一个整数数组 nums，数组中的元素互不相同。返回该数组所有可能的子集
 *               解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *               示例：
 *               输入：nums = [1,2,3]
 *               输出：[[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]
 */
public class 子集 {

	// 方法：回溯算法
	private static List<List<Integer>> res = new ArrayList<>();

	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		List<Integer> selectList = new ArrayList<>();
		dfs(nums, 0, selectList);
		System.out.println(res);
	}

	private static void dfs(int[] nums, int start, List<Integer> selectList) {
		// 无临界条件，添加当前子集
		res.add(new ArrayList<>(selectList));
		// 遍历
		for (int i = start; i < nums.length; i++) {
			// 选择当前数字
			selectList.add(nums[i]);
			System.out.println("add last -- " + selectList);
			// 下一层，递归处理后续数字
			dfs(nums, i + 1, selectList);
			// 撤销选择（回溯）
			selectList.remove(selectList.size() - 1);
			System.out.println("remove last -- " + selectList);
		}
	}

}

class 逐步扩展法 {
	public static void main(String[] args) {
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>()); // 初始空子集，添加一个空元素（对应数组的[]）
		int[] nums = {1, 2, 3};
		for (int num : nums) {
			int size = result.size();
			// 分析每次循环：{}、{1} / {2}、{1,2} / {3}、{1,3}、{2,3}、{1,2,3}
			for (int i = 0; i < size; i++) {
				List<Integer> newSubset = new ArrayList<>(result.get(i)); // 添加子集
				newSubset.add(num);
				result.add(newSubset);
			}
		}
		// 额外操作：进行排序
		result.sort(Comparator.<List<Integer>, Integer>comparing(List::size)
		                      .thenComparing((a, b) -> {
			                      for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
				                      int cmp = Integer.compare(a.get(i), b.get(i));
				                      if (cmp != 0) return cmp;
			                      }
			                      return Integer.compare(a.size(), b.size());
		                      })
		           );
		System.out.println(result);
	}
}
