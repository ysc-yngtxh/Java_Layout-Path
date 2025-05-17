package J10_集合.集合Ⅰ_Collection详解.Collection接口;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
 * 1、JDK5.0之后推出了一个新特性：叫做增强for循环，或者叫做foreach
 * 2、foreach有一个缺点：没有下标。在需要下标的循环中不建议使用增强for循环
 */
public class 集合8_增强for循环 {

	public static void main(String[] args) {
		// int数组
		int[] a = {432, 4, 65, 46, 54, 76, 54};

		// 遍历数组（普通for循环）
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}

		// 遍历数组（增强for循环）
		for (int data : a) {
			// data就是数组中的元素(数组中的每一个元素)
			System.out.println(data);
		}

		System.out.println("=========================================================================================");

		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("world");
		list.add("kitty");

		// 遍历，使用迭代器方式
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// 使用下标方式（只针对有下标的集合）
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		// 使用foreach
		for (String s : list) {
			System.out.println(s);
		}

		// TODO 不要在增强for循环中删除元素，会发生并发修改异常：java.util.ConcurrentModificationException
		// 增强for循环内部原理其实是个Iterator迭代器，所以这里循环时候实际上使用的是集合 remove() 方法，
		// 导致集合的结构发生改变，那么迭代器也应该改变才行。但是循环中的迭代器无法改变，因此会报错。
		List<String> objects = new ArrayList<>();
		Collections.addAll(objects, "a", "abc", "def", "afr", "a");
		for (String item : objects) {
			if (item.contains("a")) {
				objects.remove(item);
			}
		}
	}

}
