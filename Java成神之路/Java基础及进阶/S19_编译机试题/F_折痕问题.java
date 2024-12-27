package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 00:06
 * @apiNote TODO 请把一张纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 *               此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 *               如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 *               给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向。
 */
public class F_折痕问题 {
    // 基本思路
    //        对折第1次产生的折痕：　　　　　　　　下
    //        对折第2次产生的折痕：　　　　下　　　　　　　上
    //        对折第3次产生的折痕：　　下　　　上　　　下　　　上
    //        对折第4次产生的折痕：　下　上　下　上　下　上　下　上
    //
    // 总结关系：1、产生第 i+1 次折痕的过程，就是在对折 i 次产生的每一条折痕的左右两侧，依次插入上折痕和下折痕
    //         2、所有折痕的结构都是一颗满二叉树，在这颗满二叉树中，头节点为下折痕，每一颗左子树的头节点为上折痕，
    //            每一颗右子树的头节点为下折痕
    //         3、从上到下打印所有折痕的方向的过程，就是二叉树先右，再中，最后左的中序遍历。
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // 接收用户输入的数字
            int fTimes = Integer.parseInt(scanner.nextLine().trim());
            if (fTimes < 1) return;
            // 定义根节点：为true表示下折痕
            printProcess(1, fTimes, true);
        }
    }
    public static void printProcess(int index, int n, boolean isDown) {
        if(index > n) return;
        // 调用printProcess()方法，打印下折痕
        printProcess(index + 1, n, true);
        // 打印上/下折痕
        System.out.println(isDown ? "下折痕" : "上折痕");
        // 调用printProcess()方法，打印上折痕
        printProcess(index + 1, n, false);
    }
}
