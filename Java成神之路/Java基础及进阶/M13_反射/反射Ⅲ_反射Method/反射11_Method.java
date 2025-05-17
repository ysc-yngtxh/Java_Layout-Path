package M13_反射.反射Ⅲ_反射Method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class 反射11_Method {

	public static void main(String[] args) throws Exception {
		// 获取类
		Class<?> userClass = Class.forName("M13_反射.反射Ⅲ_反射Method.User");
		// 获取所有的Method(包括私有的)
		Method[] methods = userClass.getDeclaredMethods();
		// 遍历
		for (Method method : methods) {
			// 获取修饰符列表
			System.out.println(Modifier.toString(method.getModifiers()));
			// 获取方法的返回值类型
			System.out.println(method.getReturnType().getSimpleName());
			// 获取方法名
			System.out.println(method.getName());
			// 获取方法参数列表（一个方法可能有多个形参）
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (Class<?> parameterType : parameterTypes) {
				System.out.println(parameterType.getSimpleName());
			}
		}

		System.out.println("=========================================================================================");

		// 反编译Method
		StringBuilder s = new StringBuilder(); // 拼接字符串
		s.append(Modifier.toString(userClass.getModifiers()) + " class " + userClass.getSimpleName() + " {\n");
		for (Method method1 : methods) {
			s.append("\t");  //  "\t"是制表符，即每个类下代码前的空格
			s.append(Modifier.toString(method1.getModifiers()) + " [");
			s.append(method1.getReturnType().getSimpleName() + " | ");
			// 很明显 getGenericReturnType()方法可以表示出带有泛型的返回值
			s.append(method1.getGenericReturnType().getTypeName() + "] ");
			s.append(method1.getName() + "(");
			// 参数列表
			Class<?>[] parameterTypes = method1.getParameterTypes();
			// TODO 在Java8之前，代码编译为class文件后，方法参数的类型固定，但是方法名称会丢失，方法名称会变成arg0、arg1....。
			//      在Java8开始可以在class文件中保留参数名，这就给反射带来了极大的便利。
			//      像 mybatis 等需要使用反射机制获取方法参数的时候就可以不用像以前一样需要使用类似于 @Param 之类的注解。
			//  解决方案：在编译时添加参数：-parameters
			Parameter[] parameters = method1.getParameters();
			if (parameterTypes.length != 0) {
				for (int i = 0; i < parameters.length; i++) {
					// 检查参数名是否可用
					if (parameters[i].isNamePresent()) {
						s.append(parameterTypes[i].getSimpleName() + " " + parameters[i].getName());
					} else {
						System.err.println("方法" + method1.getName() + "中第" + i + "个参数名未保留");
					}
					if (i < parameters.length - 1) {
						s.append(", ");
					}
				}
			}
			s.append(") {}\n");
		}
		s.append("}");
		System.out.println(s);
	}

}
