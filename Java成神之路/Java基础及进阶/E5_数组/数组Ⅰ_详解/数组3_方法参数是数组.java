package E5_数组.数组Ⅰ_详解;

// 当一个方法的参数是一个数组的时候，我们还可以采用这种方式传
public class 数组3_方法参数是数组 {

	public static void Array(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	public static void main(String[] args) {
		// 静态初始化一维数组
		int[] a = {1, 2, 3};
		Array(a);
		// 如果直接传递一个静态数组结构，语法必须这样写
		Array(new int[]{1, 2, 3});

		// 动态初始化一维数组
		int[] a2 = new int[4];
		Array(a2);
		// 如果直接传递一个动态数组结构，语法必须这样写
		Array(new int[4]);
	}

}
