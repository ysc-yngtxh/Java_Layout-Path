package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 15:02
 * @apiNote TODO 给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌。
 *               规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左和最右的纸牌，玩家A和玩家B绝顶聪明。请返回最后的获胜者的分数。
 */
public class E_纸牌分数 {
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

            int N = arr.length;
            // 先手表
            int[][] fmap = new int[N][N];
            // 后手表
            int[][] gmap = new int[N][N];

            for (int i = 0; i < N; i++) {
                fmap[i][i] = arr[i];
            }
            for (int startCol = 1; startCol < N; startCol++) {
                int L = 0;  // 行
                int R = startCol; // 列
                while (R < N) {
                    fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                    gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                    R++;
                    L++;
                }
            }
            System.out.println(Math.max(fmap[0][N - 1], gmap[0][N - 1]));
        }
    }
}
