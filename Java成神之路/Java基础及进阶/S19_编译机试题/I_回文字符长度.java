package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 17:44
 * @apiNote TODO 小红拿到了一个只包含 'a','b','c' 三种字符的字符串。
 *               小红想知道，这个字符串最短的、长度超过 1 的回文子串的长度是多少？
 *               如果不存在长度超过1的回文子串，则输出-1。否则输出长度超过1的最短回文子串的长度
 *               回文子串定义：字符串取一段连续的区间。例如"cc"或者"baab"还有"bab "才是回文子串
 */
public class I_回文字符长度 {
    // 分析：要求返回字符串中最短的回文子串长度。
    //      最短的回文子串无非类似 "aa" 这样的，但是也考虑如果不存在"aa"结构的，那么存在的最短回文子串就是"aba"
    //      因此：结果有三种  -1[不存在回文结构]   2[存在"aa"的结构]   3[存在"aba"的结构]
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(getResult(s));
    }

    private static int getResult(String target) {
        int result = -1;

        // 字符串长度不小于2，且不超过100
        if (target.charAt(0) == target.charAt(1)) {
            return 2;
        }

        int length = target.length();
        if (length >= 3) {
            for (int i = 2; i < length; i++) {
                // 2
                if (target.charAt(i) == target.charAt(i - 1)) {
                    result = 2;
                    return result;
                }
                // 3
                if (target.charAt(i) == target.charAt(i - 2)) {
                    result = 3;
                }
            }
        }
        return result;
    }
}
