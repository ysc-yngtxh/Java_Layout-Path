package J10_集合.集合Ⅰ_Collection详解.Collection接口;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/*
 *  迭代器是个对象(Iterator)。
 *  负责遍历/迭代集合当中元素的。
 *  注意：迭代器最初并没有指向第一个元素
 *
 *  迭代器有三个方法：
 *  hasNext()和next()交替使用；
 *  remove()
 *
 *  boolean hasNext = it.hasNext();
 *  这个方法返回true，便是还有元素可以迭代。这个方法返回false便是没有更多的元素可以迭代了
 *
 *  Object obj = it.next();
 *  这个方法让迭代器前进一位，并且将指向的元素返回（拿到）
 */
public class 集合4_迭代器 {

	public static void main(String[] args) {
		// 创建一个集合对象
		// Collection c = new Collection();  // 接口是抽象化的，无法实例化,所以这种语法是错误的
		// 多态
		Collection<Object> c = new ArrayList<>(); // 后面的集合无所谓，主要是看前面的Collection接口，怎么遍历/迭代
		// ArrayList集合：有序可重复的
		// 有序:存进去的顺序和取出来的顺序是一样的

		// 添加元素
		c.add("abc");
		c.add("def");
		c.add(100);
		c.add(200);
		c.add(90);
		c.add(100);
		c.add(new Object());

		// 对集合Collection进行遍历/迭代
		// 第一步：获取集合对象的迭代器对象Iterator
		Iterator<Object> it = c.iterator();
		// 第二步：通过以上获取的迭代器对象开始迭代/遍历集合
		while (it.hasNext()) {
			System.out.println(it.next());
			// 存进去什么类型，取出来的就是什么类型。只不过在输出的时候会转换成字符串，因为这里println调用了toString()方法
		}

		System.out.println("=========================================================================================");

		// HashSet集合：无序不可重复
		Collection<Object> c2 = new HashSet<>();
		// 无序：存进去和取出的顺序不一定相同
		// 不可重复：存储100，不能再存储100
		c2.add(100);
		c2.add(200);
		c2.add(300);
		c2.add(90);
		c2.add(400);
		c2.add(50);
		c2.add(60);
		c2.add(100);
		System.out.println(c2);
		Iterator<Object> it2 = c2.iterator();
		while (it2.hasNext()) {
			int next = (int) it2.next();
			if (next == 90) {
				continue;
			}
			System.out.println(next);
		}
	}

}
