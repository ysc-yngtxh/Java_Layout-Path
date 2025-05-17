package F6_String类.String类Ⅱ_String方法;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-05 23:50:00
 * @apiNote TODO
 */
public class String类15_join方法 {

	public static void main(String[] args) {
		// (掌握) String join(CharSequence delimiter, CharSequence... elements)
		// 将多个字符串通过指定的拼接符拼接在一起
		String s = String.join(" -- ", "java", "python", "c++");
		System.out.println(s);
	}

}
