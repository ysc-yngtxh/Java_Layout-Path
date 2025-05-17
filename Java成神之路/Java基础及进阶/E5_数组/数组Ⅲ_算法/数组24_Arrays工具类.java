package E5_数组.数组Ⅲ_算法;

import java.util.Arrays;

public class 数组24_Arrays工具类 {

	public static void main(String[] args) {
		// java.util.Arrays;  工具类中有哪些方法，我们开发的时候要参考API帮助文档,不要死记硬背
		int[] arr = {3, 6, 4, 5, 12, 1, 2, 32, 5, 5};

		// TODO 1、数组转String "[3, 6, 4, 5, 12, 1, 2, 32, 5, 5]"
		String arrStr = Arrays.toString(arr);
		System.err.println("打印数组：" + arrStr);

		// TODO 2、排序
		Arrays.sort(arr);
		System.err.println("排序后的数组：" + Arrays.toString(arr));

		// TODO 3、二分法查找（建立在排序基础之上），该方法返回的是元素的下标
		int index = Arrays.binarySearch(arr, 5);
		System.err.println("二分法查找：" + (index == -1 ? "该元素不存在!" : "该元素下标是 --> " + index));

		// TODO 4、填充(遵循左闭右开原则)
		Arrays.fill(arr, 2, 4, 0);
		System.err.println("填充后的数组：" + Arrays.toString(arr));

		// TODO 5、拷贝
		int[] arr2 = Arrays.copyOf(arr, arr.length);
		System.err.println("拷贝的数组：" + Arrays.toString(arr2));

		// TODO 6、拷贝指定范围下标元素(遵循左闭右开原则)
		int[] arr3 = Arrays.copyOfRange(arr, 2, 5);
		System.err.println("填充后的数组：" + Arrays.toString(arr3));

		// TODO 7、比较
		boolean equals = Arrays.equals(arr, arr2);
		System.err.println("比较两数组元素是否相同：" + equals);
	}

}
