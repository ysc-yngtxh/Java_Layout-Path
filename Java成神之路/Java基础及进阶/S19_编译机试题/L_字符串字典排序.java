package S19_编译机试题;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-29 00:40:00
 * @apiNote TODO 给定 n 个字符串，请对 n 个字符串按照字典序排列。
 *               例如：[card cap two too boat] 按照字典排序 [boat cap card too two]
 */
public class L_字符串字典排序 {

	public static void main(String[] args) {
		// String[] arr = {"banana", "apple", "cherry", "date", "elderberry"};
		String[] arr = {"card", "cap", "two", "too", "boat", "cadr"};
		bubbleSort(arr);

		// 打印排序后的数组
		for (String str : arr) {
			System.out.println(str);
		}
	}

	public static void bubbleSort(String[] arr) {
		int n = arr.length;
		// 冒泡排序，这个还是比较好理解的
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				// 可查看 compareTo() 源码，是通过字典顺序比较两个字符串的
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					// 交换 arr[j] 和 arr[j+1]
					String temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

}
