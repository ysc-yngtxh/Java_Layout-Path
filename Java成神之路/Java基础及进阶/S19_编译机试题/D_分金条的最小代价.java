package S19_编译机试题;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-27 21:40:00
 * @apiNote TODO 给定一个正数数组arr，arr的累加之和代表金条的总长度，arr的每个数代表金条要分成的长度。
 *               规定长度为k的金条分成两块，费用为k个铜板。返回把金条分出arr中的每个数字需要的最小代价。
 */
public class D_分金条的最小代价 {
	// 贪心解法  哈夫曼树只保证一件事情：非叶节点的总和最小。
	// 因为一个非叶节点就是一次合并的花费。所以只要所有非叶节点的总和最小，那就是整体的费用最小。
	// 例如：{3, 9, 5, 2, 4, 4}
	//      其中最小数为2，3，选择作为叶子节点，合并为5，并将结果5放入数组中{4,4,5,5,9}
	//      其中最小数为4，4，选择作为叶子节点，合并为8，并将结果8放入数组中{5,5,8,9}
	//      其中最小数为5，5，选择作为叶子节点，合并为10，并将结果10放入数组中{8,9,10}
	//      其中最小数为8，9，选择作为叶子节点，合并为17，并将结果17放入数组中{10，17}
	//      其中最小数为10，17，选择作为叶子节点，合并为27，并将结果27放入数组中{27}
	//             27
	//          /      \
	//         10      17
	//        /  \    /  \
	//       5    5   8   9
	//      / \      / \
	//     2   3    4   4
	// 非叶子节点总和：5 + 8 + 10 + 17 + 27 = 67
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String nextLine = scanner.nextLine();
			String[] split = nextLine.split(", ");
			List<Integer> arr = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());

            // 1：创建一个小根堆
            PriorityBlockingQueue<Integer> heap = new PriorityBlockingQueue<>();
            // 2：入堆。会根据小根堆的规则，自动排序，小的在前面。
            heap.addAll(arr);
            // 3：一次弹出两个，进行相加，得到的结果进行标注一下，然后再存放在堆中。直到堆里只剩下一个元素循环才停止。
            int count = 0;
            while (heap.size() > 1) {
                int cur = heap.poll() + heap.poll(); // 注意空指针异常，poll()方法表示取出数据并删除。
                count += cur;
                heap.add(cur);
            }
            System.out.println(count);
        }
    }
}

class 纯逻辑代码_双指针法 {

	public static void main(String[] args) {
		int[] arr = {3, 9, 5, 2, 4, 4};
		System.out.println(process(arr, 0));
	}

	// 构造一个函数：arr：等待合并的数。  pre：之前合并产生的总的最小代价。  返回值的意义是：返回整体最小的。
	public static int process(int[] arr, int pre) {
		if (arr.length == 1) { // 数组为1，表示说明都合并完了。
			return pre;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				// process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j])：会返回一个整体的最小值。
				// 这个整体的最小值会因为i,j的选择不同从而值不同。因此我们要选出一个最小的。
				ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
			}
		}
		return ans;
	}

	// 传进一个数组以及这个数组上的两个指针，删除两个指针指向的内容，添加两指针指向的内容之和，然后再将剩下的内容拷贝一份返回一个新数组。
	public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		int index = 0;
		// 这个for循环主要是将除了i,j指向的内容之外，将剩下的内容拷贝一份到ans数组中。
		for (int k = 0; k < arr.length; k++) {
			if (k != i && k != j) {
				ans[index++] = arr[k];
			}
		}
		ans[index] = arr[i] + arr[j];
		return ans;
	}
}

class 纯逻辑代码_循环 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		String[] arr = str.split(", ");
		int[] array = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray(); // 将字符串数组转换为整数数组
		Arrays.sort(array); // 排序数组
		int totalCost = 0;

		// 循环直到只剩一个有效元素
		for (int i = 0; i < array.length-1; i++) {
			// 取出前两个最小的数（arr[i] 和 arr[i+1]）
			int cost = array[i] + array[i+1];
			totalCost += cost;

			// 将合并后的数插入到合适位置（保持有序）
			int j = i + 2;
			while (j < array.length && array[j] < cost) {
				// 将数组中没有进行运算的元素向前替换一位，并插入合并后的数
				array[j-1] = array[j];
				j++;
			}
			array[j-1] = cost;
		}
		System.out.println(totalCost);
	}
}
