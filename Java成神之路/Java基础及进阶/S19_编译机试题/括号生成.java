package S19_编译机试题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2025-01-03 22:00:00
 * @apiNote TODO 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *               示例 1：
 *               输入：n = 3
 *               输出：["((()))","(()())","(())()","()(())","()()()"]
 *               示例 2：
 *               输入：n = 1
 *               输出：["()"]
 */
public class 括号生成 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int n = new Scanner(System.in).nextInt();
		new 括号生成().backTrack(list, new StringBuilder(), 0, 0, n);
		System.err.println(list);
	}

	/**
	 * 回溯算法
	 *
	 * @param ans   返回的目标集合
	 * @param cur   括号字符串组成的情况，通过拼接
	 * @param left  左括号的数目
	 * @param right 右括号的数目
	 * @param max   代表生成括号的对数
	 */
	public void backTrack(List<String> ans, StringBuilder cur, int left, int right, int max) {
		if (cur.length() == max * 2) {
			System.out.println("cur. Length()== max*2: " + cur);
			ans.add(cur.toString());
			return;
		}
		// 如果左括号的数目小于需要生成的括号数目，证明还可以添加左括号
		if (left < max) {
			cur.append('(');
			System.out.println("Left < max: " + cur);
			backTrack(ans, cur, left + 1, right, max);
			cur.deleteCharAt(cur.length() - 1); //
			System.out.println("left < max: cur. Length()-1: " + cur);
		}
		// 如果右括号的数目小于左括号的数目，放一个右括号
		if (right < left) {
			cur.append(')');
			System.out.println("right < Left: " + cur);
			backTrack(ans, cur, left, right + 1, max);
			cur.deleteCharAt(cur.length() - 1);
			System.out.println("right < Left: cur.Length()-1: " + cur);
		}
	}

}
