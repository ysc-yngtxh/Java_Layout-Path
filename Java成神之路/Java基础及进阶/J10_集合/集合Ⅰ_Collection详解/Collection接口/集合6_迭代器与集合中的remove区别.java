package J10_集合.集合Ⅰ_Collection详解.Collection接口;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
 * 关于集合元素的remove
 *   重点：当集合的结构发生改变时，迭代器必须重新获取，
 *        如果还是用以前老的迭代器，会出现异常：java.util.ConcurrentModificationException（并发修改异常）

 *   重点：在迭代集合元素的过程中，不能调用集合对象的remove方法，删除元素：
 *         c.remove(o),迭代过程中不能这样
 *         否则会出现：java.util.ConcurrentModificationException

 *   重点：在迭代元素的过程中，一定要使用迭代器Iterator的remove方法，删除元素，不要使用集合自带的remove方法删除元素
 */
public class 集合6_迭代器与集合中的remove区别 {

	public static void main(String[] args) {
		// 创建集合
		Collection<Object> c = new ArrayList<>();

		Iterator<Object> it1 = c.iterator(); // 如果在此处获取集合的迭代器对象，那么集合中是没有元素的
		c.add("abc");
		c.add("def");
		c.add("xyz");
		// 在集合中添加元素后，集合的结构发生改变时，迭代器必须重新获取
		Iterator<Object> it2 = c.iterator();
		while (it2.hasNext()) {
			Object o = it2.next();
			// 删除元素之后，集合的结构发生了变化，应该重新去获取迭代器
			// 但是，循环下一次的时候并没有重新获取迭代器，所以会出现异常：java.util.ConcurrentModificationException
			// c.remove(o); // 通过集合去删除元素，没有通知迭代器。（导致迭代器的快照和原集合状态不同）
			it2.remove();   // 删除的一定是迭代器指向的当前元素
			// 迭代器去删除时，会自动更新迭代器，并且更新集合（删除集合中的元素）
			System.out.println(o);
		}
		System.out.println(c.size());


		// TODO 集合删除元素错误示范
		List<String> objects = new ArrayList<>();
		Collections.addAll(objects, "a", "abc", "def", "afr", "a");
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).contains("a")) {
				objects.remove(objects.get(i));
			}
		}
		System.out.println(objects);  // [abc, def, a]

		// 上述集合删除元素：导致删除一个元素，后续元素便向前自动补齐。
		// 比如：第一次循环i=0，删除下标为0的数据，原本下标为1的数据便自动补齐在下标为0上了。
		//      导致下一次i=1的循环就错过了判断原本下标为1的数据，最后结果就是数据没能处理干净。
		// 解决方法一：迭代器删除元素
		Collections.addAll(objects, "a", "abc", "def", "afr", "a");
		Iterator<String> iterator = objects.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().contains("a")) {
				iterator.remove();
			}
		}
		System.out.println(objects);

		// 方法二：for循环倒序遍历删除元素。删除掉元素，只会往前进行自动补位，那我倒着删除，后面判断过的数据补不补位与我何干？
		Collections.addAll(objects, "a", "abc", "def", "afr", "a");
		for (int i = objects.size() - 1; i > 0; i--) {
			if (objects.get(i).contains("a")) {
				objects.remove(objects.get(i));
			}
		}
		System.out.println(objects);
	}

}
/* 什么时候使用迭代器迭代，什么时候使用for循环遍历?
 *    1、for循环每次遍历都需要访问list长度，故可以在循环遍历中操作元素（增删）
 *    2、在进入迭代器循环前，集合长度已经确认了所以有的时候我们不太确定集合中到底有多少个元素，这个时候要想遍历集合，就可以使用迭代器
 */
