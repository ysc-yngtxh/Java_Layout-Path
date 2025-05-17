package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 15:30:00
 * @apiNote TODO 给定一个字符串str，判断是不是整体有效的括号字符串
 *               (整体有效：即存在一种括号匹配方案，使每个括号字符均能找到对应的反向括号，且字符串中不包含非括号字符)。
 */
public class G_括号有效性 {

	// 这道题相当于是 完全匹配括号长度 的变种题
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		int max = 0;
		int[] angle = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					angle[i] = (i > 2 ? angle[i - 2] : 0) + 2;
				} else if (i - angle[i - 1] - 1 >= 0 && s.charAt(i - angle[i - 1] - 1) == '(') {
					angle[i] = angle[i - 1] + 2;
				}
			}
			max = Math.max(max, angle[i]);
		}
		System.out.println(max == s.length() ? "YES" : "NO");
	}

}
