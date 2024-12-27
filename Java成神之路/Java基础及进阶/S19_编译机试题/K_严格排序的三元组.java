package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 21:07
 * @apiNote TODO 给定排序数组arr和整数k，不重复打印arr中所有相加和为k的严格升序的三元组
 *           例如, arr = [-8, -4, -3, 0, 1, 1, 2, 4, 5, 8, 9]是已经排序好的数组, k = 10.
 *           打印结果为：[-4 5 9] [-3 4 9] [-3 5 8] [0 1 9] [0 2 8] [1 4 5]，其中三元组 [1 1 8] 不满足严格升序所以不打印
 */
public class K_严格排序的三元组 {
    // 解题思路：由于该数组arr是已经排序好的数组，因此我们可以使用三指针法来解决这个问题。
    //         1、首先，我们选择三个指针，一个指向三元组的第一个元素，一个指向三元组的第二个元素，另一个指向三元组的最后一个元素。
    //         2、然后，使用while循环，条件为 【第一个指针】< arr.length-2，保证三元组至少得存在三个数。
    //         3、在循环内部，我们计算三元组的和，并判断是否等于k。
    //         4、如果三元组的和等于k，则打印三元组。
    //         5、如果三元组的和小于k，因为是排序数组，值是递增的，则将第二个指针向后移动一位，以增加三元组总值大小。
    //         6、如果三元组的和大于k，因为是排序数组，值是递增的，则将第三个指针向前移动一位，以减小三元组总值大小。
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数组arr：");
        String s = scanner.nextLine();
        String[] split = s.split(", ");
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            int parseInt = Integer.parseInt(split[i]);
            arr[i] = parseInt;
        }

        int K = 10;
        int l = 0;
        int r = arr.length - 1;
        int m;
        while (l < r - 1) {
            // l++;
            if (l == 0 || arr[l] != arr[l - 1]) {
                // l不重复在比较m,r
                m = l + 1;
                r = arr.length - 1;
                while (m < r) {
                    if (arr[l] + arr[m] + arr[r] == K) {
                        if (arr[m]!=arr[m-1] & arr[m]!=arr[r]){
                            System.out.println(arr[l] + " " + arr[m] + " " + arr[r]);
                        }
                        m++;
                        r--;
                    } else if (arr[l] + arr[m] + arr[r] < K) m++;
                    else r--;
                }
            }
            l++;
        }
    }
}
