package F6_String类.String类Ⅲ_String拼接;

import java.util.StringJoiner;

/**
 * @author 游家纨绔
 * @dateTime 2025-12-14 14:30
 * @apiNote TODO
 */
public class String类22_StringJoiner {
	static void main() {
		String[] words = {"Java", "Python", "C++", "JavaScript"};

		// 使用 String.join 方法进行字符串拼接
		String joinedString = String.join(", ", words);
		System.out.println("使用 String.join 拼接的字符串: " + joinedString);

		// 使用 StringJoiner 类进行字符串拼接
		StringJoiner stringJoiner1 = new StringJoiner(" | ");
		// 也可以指定前缀和后缀
		StringJoiner stringJoiner2 = new StringJoiner(" | ", "[", "]");
		for (String word : words) {
			stringJoiner1.add(word);
			stringJoiner2.add(word);
		}
		System.out.println("使用 StringJoiner1 拼接的字符串: " + stringJoiner1);
		System.out.println("使用 StringJoiner2 拼接的字符串: " + stringJoiner2);
	}
}
