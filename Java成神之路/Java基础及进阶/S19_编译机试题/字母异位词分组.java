package S19_编译机试题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-26 10:30:00
 * @apiNote TODO 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *               字母异位词：简意为两字符串长度相同，字母相同，顺序不同。例如：["ate","eat","tea"]
 *               示例：输入：eat tea tan ate nat bat
 *                    输出：[[eat, tea, ate], [bat], [tan, nat]]
 */
public class 字母异位词分组 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		String[] arr = s.split(" ");
		System.out.println(new 字母异位词分组().groupAnagrams(arr));
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		// 处理空输入
		if (strs == null || strs.length == 0) {
			return new ArrayList<>();
		}

		// 哈希表：键为排序后的字符串，值为异位词分组
		Map<String, List<String>> map = new HashMap<>();

		for (String s : strs) {
			// 将字符串转换为字节数组并排序
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String sortedKey = new String(chars);

			// 将原始字符串加入对应的分组
			if (!map.containsKey(sortedKey)) {
				map.put(sortedKey, new ArrayList<>());
			}
			map.get(sortedKey).add(s);
		}

		// 将哈希表的值转换为结果列表
		return new ArrayList<>(map.values());
	}

}
