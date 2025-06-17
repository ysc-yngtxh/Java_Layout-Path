package S19_编译机试题;

/**
 * @Author 游家纨绔
 * @Date 2025-06-17 09:00:00
 * @Description TODO 一个无序数组，里面有若干个正整数，其中 98 个数出现了偶数次，有两个数出现了奇数次，如何找出这两个数？
 */
public class C_找到出现次数为奇数次的两个数 {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 7};
		int[] result = findOddOccurrences(arr);
		System.out.println("出现奇数次的两个数是: " + result[0] + " 和 " + result[1]);
	}

	public static int[] findOddOccurrences(int[] arr) {
		int xor = 0;
		for (int num : arr) {
			xor ^= num; // 计算所有数的异或
		}

		// 找到 xorAll 二进制中最右边为 1 的位
		// 示例：xorAll=6(110)，-xorAll 是补码表示的 ...1111010，xorAll & -xorAll = 2(10)
		int mask = xor & -xor;

		// 通过分治思想将数组分成两组
		//    数字在 mask 位为 1 的分到一组，异或结果为 a。
		//    数字在 mask 位为 0 的分到另一组，异或结果为 b。
		int num1 = 0, num2 = 0;
		for (int num : arr) {
			if ((num & mask) == 0) {
				num1 ^= num; // 在第一个组中
			} else {
				num2 ^= num; // 在第二个组中
			}
		}

		return new int[]{num1, num2};
	}
}
