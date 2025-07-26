package O15_泛型及练习题.泛型;

public class 泛型2_示例 {

	public static void main(String[] args) {
		Double[] num = {1.11, 2.22, 3.33, 4.44, 5.55, 6.66};
		String[] str = {"Hello", "World", "你好", "世界"};

		Generic01<Double> generic01 = new Generic01<>();
		Double generic011 = generic01.toGeneric01(num);

		Generic02 generic02 = new Generic02();
		generic02.toGeneric02(num);

		Generic03 generic03 = new Generic03();
		Double generic031 = generic03.toGeneric03(num);
		String generic032 = generic03.toGeneric03(str);

		System.out.println("Generic01<T>：" + generic011.getClass().getSimpleName() + " " + generic011 + "\n" +
				           "Generic03：" + generic031.getClass().getSimpleName() + " " + generic031 + "\n" +
				           "Generic03：" + generic032.getClass().getSimpleName() + " " + generic032);
	}

	static class Generic01<T> {
		// 这里的 T 表示返回的是 T 类型的数据
		// 虽然有T泛型标识，但是它仅是一个普通的成员方法，方法返回类型是T，采用了泛型类的泛型类型（必须指定类泛型，否则报错）。
		public T toGeneric01(T[] arr) {
			return arr[arr.length - 1];
		}
	}

	static class Generic02 {
		// 只有声明了 <T> 方法才表示这是一个泛型方法
		// 泛型方法与普通方法区别在于：无需指定类泛型
		public static <T> void toGeneric02(T[] arr) {
			System.out.println("Generic02：" + arr[arr.length - 1]);
		}
	}

	static class Generic03 {
		// <T> T 表示这是一个泛型方法，且返回T类型数据，无需指定类泛型
		public static <T> T toGeneric03(T[] arr) {
			return arr[arr.length - 1];
		}
	}

	// 总结：
	//     调用普通成员方法，强制要求采用的泛型必须遵从类的泛型。即方法使用泛型的前提是类为泛型；
	//     但是泛型方法里的泛型，都是我们在调用时去独立指定的，无需为泛型类。
}
