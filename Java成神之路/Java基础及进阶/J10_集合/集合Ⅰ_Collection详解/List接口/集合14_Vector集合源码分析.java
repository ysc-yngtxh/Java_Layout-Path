package J10_集合.集合Ⅰ_Collection详解.List接口;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/* Vector：
 *   1、底层也是一个数组
 *   2、初始化容量：10
 *   3、怎么扩容？
 *        扩容之后是原容量的2倍
 *        10 --> 20 --> 40 --> 80
 *
 *   4、ArrayList集合扩容特点：
 *        ArrayList集合扩容是原容量的1.5倍
 *
 *   5、Vector中多有的方法都是线程同步的，都带有synchronized关键字，
 *      是线程安全的，效率比较低，使用较少了
 *
 *   6、怎么讲一个线程不安全的ArrayList集合转换成线程安全的呢？
 *       使用集合工具类：java.util.Collections;
 *
 *          java.util.Collection是集合接口
 *          java.util.Collections是集合工具类
 */
public class 集合14_Vector集合源码分析 {

	public static void main(String[] args) {
		// 创建一个Vector集合
		List<Object> vector = new Vector<>();

		// 添加元素，默认容量10个
		vector.add(1);
		vector.add(2);
		vector.add(3);
		vector.add(4);
		vector.add(5);
		vector.add(6);
		vector.add(7);
		vector.add(8);
		vector.add(9);
		vector.add(10);

		// 满了之后扩容(扩容之后的容量是20)
		vector.add(11);
		for (int i = 0; i < vector.size(); i++) {
			System.out.println(vector.get(i));
		}
        /*
        Iterator it = vector.iterator();
        while(it.hasNext()){
            Object obj = it.next();
            System.out.println(obj);
        }
        */

		System.out.println("======================================================================================");

		// 这个可能以后要使用！！！
		List<Object> mylist = new ArrayList<>();
		// 变成线程安全的
		Collections.synchronizedList(mylist); // 这里暂时没办法看效果，因为多线程没学，你记住先！

		// mylist就是线程安全的了
		mylist.add("111");
		mylist.add("222");
		mylist.add("333");
		for (int i = 0; i < mylist.size(); i++) {
			System.out.println(mylist.get(i));
		}
	}

}
