package J10_集合.集合Ⅰ_Collection详解.Collection接口;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * 关于java.util.Collection接口中的常用方法：
 *     1、Collection中能存放什么元素？
 *         没有使用“泛型”之前，Collection中可以存储Object的所有子类型
 *         使用了“泛型”之后，Collection中只能存储某个具体的类型
 *         Collection中什么都能存，只要是Object的子类型就行(集合中不能直接存储基本数据类型，也不能存Java对象，只是存储Java对象的内存地址)

 *     2、Collection中的常用方法
 *          boolean add(Object e)  向集合中添加元素
 *          int size()  获取集合中元素的个数
 *          void clear()  清空集合
 *          boolean contains(Object o)  判断当前集合中是否包含元素o,包含返回true，不包含返回false
 *          boolean remove(Object o)  删除集合中的某个元素
 *          boolean isEmpty()  判断该集合中元素的个数是否为空
 *          Object[] toArray()  调用这个方法可以把集合转换成数组
 */
public class 集合2_Collection接口常用方法 {

	public static void main(String[] args) {
		Collection<Object> c = new ArrayList<>();
		c.add(100); // 自动装箱（Java5的新特性），实际上放进去了
		// 一个对象的内存地址。Integer x = new Integer();
		c.add(3.14); // 自动装箱
		c.add(new Object());
		c.add(new Student());
		c.add(true); // 自动装箱

		// 获取集合中元素的个数
		System.out.println("集合中元素的个数是：" + c.size());

		// 清空集合
		c.clear();
		System.out.println("集合中元素的个数是：" + c.size());

		// 再向集合中添加元素
		c.add("Hello");
		c.add("World");
		c.add("浩克");
		c.add("绿巨人");
		c.add(1);

		// 判断集合中是否包含"绿巨人"
		boolean flag = c.contains("绿巨人");
		System.out.println(flag);
		boolean flag2 = c.contains("绿巨人2");
		System.out.println(flag2);
		System.out.println(c.contains(1));

		System.out.println("集合中元素的个数是：" + c.size());

		// 删除集合中某个元素
		c.remove(1);
		System.out.println("集合中元素的个数是：" + c.size());

		// 判断集合是否为空（集合中是否存在元素）
		System.out.println(c.isEmpty()); // false
		// 清空
		c.clear();
		System.out.println(c.isEmpty()); // true,(true表示集合中没有元素了！)

		c.add("abc");
		c.add("def");
		c.add(100);
		c.add("helloWorld!");

		// 转换成数组(了解，使用的不多)
		Object[] obj = c.toArray();
		for (int i = 0; i < obj.length; i++) {
			// 遍历数组
			Object o = obj[i];
			System.out.println(o);
		}


		// 以下示例我们使用双括号初始化创建了一个ArrayList，并添加了三个元素。
		List<String> names = new ArrayList<String>() {{
			// 注意，双括号初始化实际上是创建了ArrayList的匿名子类，并在该子类的实例化代码块中进行了元素添加。
			add("Alice");
			add("Bob");
			add("Charlie");
		}};
		// TODO 尽管双括号初始化可以方便地初始化集合，但也存在一些问题和潜在的陷阱：
		//      双括号初始化会导致额外的内存开销，因为它实际上创建了一个匿名子类。此外，它还可能与一些静态分析工具产生冲突，因为它需要对子类进行解析。
		// 因此，尽管双括号初始化在某些情况下可能很方便，但在实际的生产代码中，最好考虑使用更简洁和可读性更好的初始化方式，如使用普通的add()方法逐个添加元素。
	}

	private static class Student {}

}
