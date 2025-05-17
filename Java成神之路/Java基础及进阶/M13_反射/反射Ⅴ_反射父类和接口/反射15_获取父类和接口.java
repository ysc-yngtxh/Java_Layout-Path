package M13_反射.反射Ⅴ_反射父类和接口;

public class 反射15_获取父类和接口 {

	public static void main(String[] args) throws Exception {
		// String举例
		Class<?> stringClass = Class.forName("java.lang.String");

		// 获取String的父类
		Class<?> superClass = stringClass.getSuperclass();
		System.out.println(superClass);

		// 获取String类实现的所有接口(一个类可以实现多个接口)
		Class<?>[] interfaces = stringClass.getInterfaces();
		for (Class<?> in : interfaces) {
			System.out.println(in.getName());
		}
	}

}
