package J10_集合.集合Ⅰ_Collection详解.Set接口;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* 1、TreeSet集合存储元素特点：
 *    ①、无序不可重复的，但是存储的元素可以按照大小顺序排序！
 *       称为：可排序集合
 *    ②、无序：这里的无序指的是存进去的顺序和取出来的顺序不同。并且没有下标
 *
 * 2、TreeSet集合底层实际上是一个TreeMap，TreeSet/TreeMap集合底层是自平衡二叉树，遵循左小右大原则存放
 *
 * 3、放到TreeSet集合中的元素，等同于放到TreeMap集合Key部分了。
 *
 * 4、遍历二叉树的时候有三种方式：
 *       前序遍历：根左右
 *       中序遍历：左根右
 *       后序遍历：左右根
 *
 *       注意：前中后说的时 “根” 的位置
 *            “根”在前面是前序，“根”在中间是中序，“根”在后面是后续
 *
 * 5、TreeSet集合/TreeMap集合采用的是：中序遍历方式
 *       Iterator迭代器采用的是中序遍历方式，左根右。
 *
 * 6、TreeSet或者TreeMap集合中根据Key部分元素可排序的方式：
 *       第一种：放在集合中的元素实现 java.lang.Comparable 接口（需要重写compareTo方法）
 *       第二种：在构造TreeSet或者TreeMap集合的时候给他传一个比较器对象
 */
public class 集合17_TreeSet集合与自平衡二叉树 {

	public static void main(String[] args) {
		Set<String> s = new TreeSet<>();
		Collections.addAll(s, "A", "B", "Z", "Y", "Z", "K", "M");
		System.out.println("TreeSet集合：" + s);

		// Set集合转换成List集合
		List<String> setToList = new ArrayList<>(s);
		System.out.println("ArrayList集合：" + setToList);

		// List集合转换成Set集合
		Set<String> listToSet1 = new TreeSet<>(setToList);
		System.out.println("List集合转换成Set集合：" + listToSet1);

		// List集合转换成Set集合，并且Set集合进行倒序排列
		Set<String> listToSet2 = new TreeSet<>(Comparator.reverseOrder());
		listToSet2.addAll(setToList);
		System.out.println("List集合转换成Set集合，并且Set集合进行倒序排列：" + listToSet2);

		// TODO 创建TreeSet集合的时候，需要使用这个比较器

		// 第一种写法：使用Comparable的内部比较规则，前提是Person类实现了Comparable，并重写了compareTo方法。不够灵活
		TreeSet<Person> person1 = new TreeSet<>();
		// 第二种写法，传进Comparator实现对象，只不过把比较规则写在了实现类中。简直就是脱裤子放屁
		TreeSet<Person> person2 = new TreeSet<>(new PersonComparator());
		// 第三种写法：使用Comparator的静态方法。推荐
		TreeSet<Person> person3 = new TreeSet<>(Comparator.comparing(Person::getAge).reversed());
		// 第四种写法：匿名内部类，把比较规则写在了匿名内部类中。可读性太差，但会更加灵活
		TreeSet<Person> person4 = new TreeSet<Person>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				// 年龄相同时按照名字排序
				if (o1.age == o2.age) {
					// 姓名是String类型，可以直接比。调用String类的 compareTo() 来完成比较
					return o1.name.compareTo(o2.name);
				} else {
					return o1.age - o2.age; // 年龄不相同时按照年龄大小排序
				}
			}
		}) {{
			add(new Person("ZhangSan", 20));
			add(new Person("LiSi", 23));
			add(new Person("WangWu", 23));
			add(new Person("WangW", 23));
		}};
		System.err.println(person4);
	}

}

class Person implements Comparable<Person> { // String类和Integer类都有Comparable接口，和重写compareTo方法

	String name;
	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	/* 注意⚠️：compareTo()方法默认是升序排序，this.age - other.age 为升序排序，other.age - this.age 为降序排序。
	 *        如果想要降序排序，只需要将返回值的正负号调换即可
	 *        return -Integer.compare(this.age, other.age); // 降序排序
	 *        return Integer.compare(other.age, this.age);  // 降序排序
	 */
	@Override
	public int compareTo(Person o) {
		// 年龄相同时按照名字排序
		if (this.age == o.age) {
			// 姓名是String类型，可以直接比。调用String类的compareTo来完成比较
			return this.name.compareTo(o.name);
		} else {
			// 年龄不相同时按照年龄大小排序
			// return this.age-o.age;  直接使用差值，当数值极大时可能导致整数溢出，应该使用包装类的compare方法
			return Integer.compare(this.age, o.age);
		}
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

}

class PersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		// 指定比较规则：按照年龄排序
		return o1.age - o2.age;
	}
}
