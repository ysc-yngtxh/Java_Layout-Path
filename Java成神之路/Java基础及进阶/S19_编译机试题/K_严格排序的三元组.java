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
