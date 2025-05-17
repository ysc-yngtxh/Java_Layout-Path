package J10_集合.集合Ⅰ_Collection详解.Collection接口;

import java.util.ArrayList;
import java.util.Collection;

/*
 * 深入Collection集合的contains方法：
 *     boolean contains(Object o)判断集合中是否包含某个对象o
 *     如果包含返回true，如果不包含返回false
 *
 *     contains方法是用来判断集合中是否包含某个元素的方法。
 *     那么他在底层是怎么判断集合中是否包含某个元素的呢？
 *         调用了equals方法进行比对
 *         equals方法返回true，就表示包含这个元素
 */
public class 集合3_contains方法详解 {

	public static void main(String[] args) {
		// 创建集合对象
		Collection<Object> c = new ArrayList<>();

		String s1 = "abc";
		String s2 = "def";
		c.add(s1);
		c.add(s2);
		System.out.println("元素的个数是：" + c.size());
		String s3 = "abc";
		System.out.println(c.contains(s3)); // 判断集合中是否存在“abc”，可以在底层源码看到contains调用了equals方法
		System.out.println(s1.equals(s3));  // String类的equals方法已经重写，比较的内容.所以是true

		User u1 = new User("游家纨绔");
		User u2 = new User("曹家千金，我好想你！");
		c.add(u1);
		c.add(u2);
		System.out.println("元素的个数是：" + c.size());
		User u3 = new User("游家纨绔");
		System.out.println(c.contains(u3)); // User类并没有重写equals方法，所以调用的Object类的equals方法。所以是false

		Integer x = new Integer(1000);
		c.add(x);
		Integer y = new Integer(1000);
		System.out.println(c.contains(y)); // true,因为Integer也重写了equals方法
	}

}

class User {
	private String name;

	public User() {}

	public User(String name) {
		this.name = name;
	}
}
