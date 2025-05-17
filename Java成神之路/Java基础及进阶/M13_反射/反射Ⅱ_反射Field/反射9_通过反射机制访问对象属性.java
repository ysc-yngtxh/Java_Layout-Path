package M13_反射.反射Ⅱ_反射Field;

import java.lang.reflect.Field;

/*
 * 通过反射机制访问一个Java对象的属性
 *     给属性赋值（set）
 *     获取属性值（get）
 */
public class 反射9_通过反射机制访问对象属性 {

	public static void main(String[] args) throws Exception {
		Class<?> studentClass = Class.forName("M13_反射.反射Ⅱ_反射Field.Student");
		Student obj = new Student("1级"); // obj就是student对象，(底层调用无参构造方法)

		// 获取name属性
		Field nameField = studentClass.getDeclaredField("name");
		// 给 obj对象(Student对象) 的name属性赋值。
		nameField.set(obj, "游家纨绔");
		// 读取属性的值
		System.out.println(nameField.get(obj));

		System.out.println("=========================================================================================");

		Field noField = studentClass.getDeclaredField("no");
		// 打破封装(反射机制的缺点：打破封装，可能会不安全)
		// 这样设置完之后，在外部也是可以访问 private 修饰的属性
		noField.setAccessible(true);
		// 给no属性赋值。
		noField.set(obj, 11);
		// 读取属性的值
		System.out.println(noField.get(obj));

		System.out.println("-------------------------------------------------------------------------------");

		// 给加有 final 关键字的属性修改值
		Field levelField = studentClass.getDeclaredField("level");
		levelField.setAccessible(true);
		levelField.set(obj, "2级");
		// TODO 可以发现通过反射得到的 level值 与通过 Student对象 得到的 level值 一样
		System.out.println("通过反射得到的 level值 >>>>>>>>>>>>>>>>> " + levelField.get(obj));
		System.out.println("通过Student对象得到的 level值 >>>>>>>>>> " + obj.getLevel());

		// 给加有 final 关键字的一开始就有值的属性修改值
		Field boundsField = studentClass.getDeclaredField("bounds");
		boundsField.setAccessible(true);
		boundsField.set(obj, "22");
		// TODO 可以发现通过反射得到的 bounds值 与通过 Student对象 得到的 bounds值 不一样，自己去理解
		System.out.println("通过反射得到的 bounds值 >>>>>>>>>>>>>>>>> " + boundsField.get(obj));
		System.out.println("通过Student对象得到的 bounds值 >>>>>>>>>> " + obj.getBounds());

		// 当你通过对象直接获取这个final字段的值时，你可能看到的是被缓存的值，而不是内存中的实际值。
		// 而当你通过反射获取这个字段的值时，你看到的是内存中的实际值，不受缓存的影响。
	}

}
