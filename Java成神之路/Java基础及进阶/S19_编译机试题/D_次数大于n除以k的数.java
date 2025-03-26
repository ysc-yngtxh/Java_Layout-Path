package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 00:00
 * @apiNote TODO 给定一个整型数组arr，再给定一个整数k，打印所有出现次数大于n/k的数，如果没有这样的数，请打印”-1“。
 *               输入包含两行，第一行输入包含两个整数n和k，第二行包含n个整数，代表数组arr。
 */
public class D_次数大于n除以k的数 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] split = s.split(", ");
            int[] arr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                int parseInt = Integer.parseInt(split[i]);
                arr[i] = parseInt;
            }

            int n = arr.length;
            int k = 4;

            if (arr == null || arr.length == 0 || k <= 0) {
                System.out.println("-1");
                return;
            }

            int minNum = arr[0];
            int maxNum = arr[0];

            // 找出数组中的最大值和最小值
            for (int i = 1; i < n; i++) {
                if (arr[i] < minNum) {
                    minNum = arr[i];
                }
                if (arr[i] > maxNum) {
                    maxNum = arr[i];
                }
            }
            // 初始化计数数组 -- 这里使用的桶排序算法的思想
            int[] count = new int[maxNum - minNum + 1];
            // 统计每个数字出现的次数
            for (int num : arr) {
                count[num-minNum]++;
            }
            // 检查是否有出现次数大于n/k的数，并打印它们
            boolean found = false;
            for (int i = 0; i < count.length; i++) {
                if (count[i] > n / k) {
                    System.out.print(minNum + i + " ");
                    found = true;
                }
            }
            // 如果没有找到符合条件的数，则打印"-1"
            if (!found) {
                System.out.println("-1");
            }
        }
    }
}
