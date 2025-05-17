package M13_反射.反射Ⅲ_反射Method;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/*
 * 必须掌握，重要程度五颗星⭐️⭐️⭐️⭐️⭐️
 *   反射机制，让代码很具有通用性，可变化的内容都是写到配置文件当中，
 *   将来修改配置文件之后，创建的对象不一样了，调用的方法也不同了。
 *   但是Java代码不需要做任何改动，这就是反射机制的魅力。
 */
public class 反射12_反射机制调用方法 {

	public static void main(String[] args) throws Exception {
		// 不使用反射机制调用方法
		User users = new User();
		boolean logins = users.login("admin", "123");
		System.out.println(logins ? "登陆成功" : "登陆失败");

		// 使用反射机制调用方法
		Class<?> userse = Class.forName("M13_反射.反射Ⅲ_反射Method.User"); // 获取class
		Object obj = userse.getDeclaredConstructor().newInstance();       // 创建对象，底层调用的是无参构造方法
		Method loginMethod = userse.getDeclaredMethod("login", String.class, String.class); // 获取Method
		Object retValue = loginMethod.invoke(obj, "admin", "123"); // invoke英文翻译是调用，即调用方法
		System.out.println((boolean) retValue ? "登陆成功" : "登陆失败");

		// 通过判断返回值是否泛型，来决定执行逻辑
		Class<?> resultType = null;
		Method[] declaredMethods = userse.getDeclaredMethods();
		for (int i = 0; i < declaredMethods.length; i++) {
			// 获取通用的方法返回值类型
			Type genericReturnType = declaredMethods[i].getGenericReturnType();

			// 检查是否为 ParameterizedType（表示有泛型参数），表示返回值是泛型
			if (genericReturnType instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;

				// 获取 Map 的键类型（String）和值类型（List<Integer>）
				Type keyType = parameterizedType.getActualTypeArguments()[0];
				Type valueType = parameterizedType.getActualTypeArguments()[1];

				// 这里获取的是 Map<String,List<Integer>> 里的泛型String。
				resultType = (Class<?>) keyType;

				System.out.println("Key Type: " + keyType.getTypeName());     // java.lang.String
				System.out.println("Value Type: " + valueType.getTypeName()); // java.util.List<java.lang.Integer>

				// 进一步提取 List<Integer> 的泛型参数
				if (valueType instanceof ParameterizedType) {
					ParameterizedType listType = (ParameterizedType) valueType;
					Type elementType = listType.getActualTypeArguments()[0];
					System.out.println("List Element Type: " + elementType.getTypeName()); // java.lang.Integer
				}
			} else if (genericReturnType instanceof Class) {
				// 表示返回值不是泛型
				resultType = (Class<?>) genericReturnType;
			}
			System.out.println("泛型类型的" + resultType.getSimpleName());
		}
	}

}
