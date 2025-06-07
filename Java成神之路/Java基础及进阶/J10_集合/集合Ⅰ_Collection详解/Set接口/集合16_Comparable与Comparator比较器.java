package J10_集合.集合Ⅰ_Collection详解.Set接口;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-27 19:20:00
 * @apiNote TODO 两种比较排序方法
 */
public class 集合16_Comparable与Comparator比较器 {
	/* 1、Comparable：可以直接在需要进行排序的类中实现，重写compareTo(T o)方法；缺点是要实现Comparable接口
	 * 2、Comparator：需要另外一个实现 Comparator 接口的实现类来作为 “比较器”。不用实现接口，直接编写排序逻辑
	 * 3、Comparable 和 Comparator 如何选择？
	 *     Comparable 可以直接在需要进行排序的类中实现，重写compareTo(T o)方法；缺点是要实现Comparable接口
	 *     Comparator 需要另外一个实现 Comparator 接口的实现类来作为 “比较器”。不用实现接口，直接使用匿名内部类即可实现
	 *     当比较规则不会发生改变的时候，或者说当比较规则只有1个的时候，建议实现Comparable接口
	 *     如果比较规则多个，并且需要多个比较规则之间频繁切换，建议使用Comparator接口
	 */
	public static void main(String[] args) {
		// 这里使用的元素类型为Object，没有实现Comparable接口并重写compareTo()方法，所以这里代码会报错
		// List<Object> list1 = new ArrayList<>();
		// Collections.addAll(list1, 1,3,6,34,67,98,34);
		// Collections.sort(list1);

		List<Integer> list = new ArrayList<>();
		Collections.addAll(list, 1, 3, 6, 34, 67, 98, 34);
		// 这里可以参考源码，Integer是实现了Comparable接口的，而Collections排序就是通过CompareTo()方法比较实现的
		Collections.sort(list);

		// 在Java中，基本数据类型（如int、double、char等）并不是对象，因此它们不能实现接口，包括Comparable接口。
		// 对于基本数据类型的数组，如int[]，Java提供了特化的Arrays.sort()方法重载版本，
		// 这些版本内部实现了针对基本数据类型的比较逻辑，因此不需要依赖Comparable接口。
		int[] arr = {4, 6, 8, 786, 432, 768, 42};
		Arrays.sort(arr);

		List<Available> arrayList = new ArrayList<Available>() {{
			add(new Available(1000, "马克"));
			add(new Available(5000, "冉冰"));
			add(new Available(400, "麦朵"));
			add(new Available(8000, "白月魁"));
		}};
		// 使用Comparable接口实现排序，前提要实现Comparable接口，并重写compareTo()方法
		Collections.sort(arrayList);
		// 假如Available没有实现Comparable接口。可以使用Comparator实现排序，直接在代码中实现排序逻辑，比较灵活
		Collections.sort(arrayList, Comparator.comparing(Available::getAge));

		// 返回相反的排序规则
		Collections.sort(arrayList, Comparator.comparing(Available::getName).reversed());
		// 当集合中存在null元素时，null元素排在集合的最前面
		Collections.sort(arrayList, Comparator.nullsFirst(Comparator.comparing(Available::getName)));
		// 当集合中存在null元素时，null元素排在集合的最后面
		Collections.sort(arrayList, Comparator.nullsLast(Comparator.comparing(Available::getName)));
		// 首先使用 ege 排序，紧接着在使用 name 排序
		Collections.sort(arrayList, Comparator.comparing(Available::getAge).thenComparing(Available::getName));


		List<List<Integer>> result = new ArrayList<List<Integer>>() {{
			add(Arrays.asList(1, 3));
			add(Arrays.asList(1, 2));
			add(Arrays.asList(2, 3));
			add(Arrays.asList(5, 6, 7));
			add(Arrays.asList());
			add(Arrays.asList(1));
		}};
		// 对嵌套集合进行排序：先进行子集合的个数排序，然后对子集合的字典排序
		// ⚠️：Comparator.comparing() 和 thenComparing() 是泛型方法，需要根据上下文推断类型 T。
		//     当嵌套泛型（如 List<List<Integer>>）时，编译器可能无法正确推断 a 和 b 的具体类型。
		//     因此需要显式指定泛型类型 <List<Integer>, Integer>
		//     < T > - 要比较的元素类型
		//     < U > - Comparable排序键的类型
		result.sort(Comparator.<List<Integer>, Integer>comparing(List::size)
		                      .thenComparing((a, b) -> {
			                      for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
				                      int cmp = Integer.compare(a.get(i), b.get(i));
				                      if (cmp != 0) return cmp;
			                      }
			                      return Integer.compare(a.size(), b.size());
		                      })
		           );
	}

}

class Available implements Comparable<Available> {

	int age;
	String name;

	public Available(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Available{" +
				"age=" + age +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public int compareTo(Available other) {
		// return this.age-o.age;  直接使用差值，当数值极大时可能导致整数溢出，应该使用包装类的compare方法
		return Integer.compare(this.age, other.age);
		/* 注意⚠️：compareTo()方法默认是升序排序，this.age - other.age 为升序排序，other.age - this.age 为降序排序。
		 *        如果想要降序排序，只需要将返回值的正负号调换即可
		 *        return -Integer.compare(this.age, other.age); // 降序排序
		 *        return Integer.compare(other.age, this.age);  // 降序排序
		 */
	}

}
