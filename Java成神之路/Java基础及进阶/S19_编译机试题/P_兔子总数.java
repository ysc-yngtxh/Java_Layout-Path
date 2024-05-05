package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 03:16
 * @apiNote TODO 有一种兔子，从出生后第3个月起每个月都生一只兔子，小兔子长到第三个月后每个月又生一只兔子。
 *               例子：假设一只兔子第3个月出生，那么它第5个月开始会每个月生一只兔子。
 *                    一月的时候有一只兔子，假如兔子都不死，问第n个月的兔子总数为多少？
 */
public class P_兔子总数 {
    // 第一个月-----------------1
    // 第二个月-----------------1
    // 第三个月-----------------2
    // 第四个月-----------------3
    // 第五个月-----------------5
    // 第六个月-----------------8
    // 第七个月-----------------13
    // 。。。。。。
    // 从中发现，从第三个月开始，前两个月兔子数之后为第三个兔子总数。即符合 -- 斐波那契数列
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入第几个月【输入满足1<=n<=31】：\n");
        int month = scanner.nextInt();
        // 表示上上个月总共的兔子数量
        int num = 1;
        // 表示上个月总共的兔子数量
        int count = 1;
        // 表示当月总共的兔子数量
        int all = 1;
        if (month <= 2) {
            System.out.println("兔子总数为：" + all);
            return;
        }
        for (int i = 3; i <= month; ++i) {
            count = num;
            num = all;
            all = num + count;
        }
        System.out.println("兔子总数为：" + all);
    }
}
