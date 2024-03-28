package S19_编译机试题;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-29 00:42
 * @apiNote TODO 给定 n 个字符串，请对 n 个字符串按照字典序排列。
 *               例如：[card cap two too boat] 按照字典排序 [boat cap card too two]
 */
public class L_字符串字典排序 {
    public static void main(String[] args) {
        // String[] strings = {"banana", "apple", "cherry", "date", "elderberry"};
        String[] strings = {"card", "cap", "two", "too", "boat", "cadr"};
        bubbleSort(strings);

        // 打印排序后的数组
        for (String str : strings) {
            System.out.println(str);
        }
    }

    public static void bubbleSort(String[] strings) {
        int n = strings.length;
        // 冒泡排序，这个还是比较好理解的
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // 可查看 compareTo 源码，是通过字典顺序比较两个字符串的
                if (strings[j].compareTo(strings[j + 1]) > 0) {
                    // 交换 strings[j] 和 strings[j+1]
                    String temp = strings[j];
                    strings[j] = strings[j + 1];
                    strings[j + 1] = temp;
                }
            }
        }
    }
}
