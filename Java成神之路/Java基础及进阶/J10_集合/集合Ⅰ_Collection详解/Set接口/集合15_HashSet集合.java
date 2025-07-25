package J10_集合.集合Ⅰ_Collection详解.Set接口;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* HashSet集合：
 *     无序不可重复
 *         1、存储时顺序和取出时顺序不同
 *         2、不可重复
 *         3、放到HashSet集合中的元素实际上是放到HashMap集合的key部分了。
 */
public class 集合15_HashSet集合 {

	public static void main(String[] args) {
		// 演示一下HashSet集合特点
		Set<String> set = new HashSet<String>() {{
			add("hello3");
			add("hello4");
			add("hello1");
			add("hello2");
			add("hello3");
			add("hello3");
		}};
		set.add("");
		set.add(null);
		System.out.println("HashSet集合：" + set);


		// Set集合转换成List集合
		List<String> setConvertList = new ArrayList<>(set);
		System.out.println("Set集合转换成List集合：" + setConvertList);

		// List集合转换成Set集合
		Set<String> listConvertSet = new HashSet<>(setConvertList);
		System.out.println("List集合转换成Set集合：" + listConvertSet);
	}

}
