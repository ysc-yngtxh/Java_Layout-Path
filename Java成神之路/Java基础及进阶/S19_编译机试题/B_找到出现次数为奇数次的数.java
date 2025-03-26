package S19_编译机试题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-27 21:17
 * @apiNote TODO 给一个数组arr，其中只有一个数出现了奇数次，其它数出现了偶数次，打印这个奇数次的数。
 */
public class B_找到出现次数为奇数次的数 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String[] split = nextLine.split(", ");
            List<Integer> array = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
            // 使用HashMap记录每个元素的出现次数
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : array) {
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }

            // 查找出现次数为奇数的元素
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() % 2 != 0) {
                    System.out.println(entry.getKey()); // 假设只有一个元素出现次数为奇数，直接返回
                }
            }
        }
    }
}

class 纯逻辑代码_B {
    // 运用异或(^)的相关性质:
    // a^a = 0
    // a^0 = a
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= arr[i];
        }
        System.out.println(res);
    }
}
