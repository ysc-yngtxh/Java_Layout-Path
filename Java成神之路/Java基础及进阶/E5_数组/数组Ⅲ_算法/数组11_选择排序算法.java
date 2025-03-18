package E5_数组.数组Ⅲ_算法;

import java.util.Arrays;

/*
 * 选择排序：
 *      每一次从这堆“参与比较的数据当中”找出最小值
 *      拿着这个最小的值和最前面的数据“交换位置”
 *
 *      选择排序比冒泡排序好在：每一次的交换位置都是有意义的。
 */
public class 数组11_选择排序算法 {
    public static void main(String[] args) {
        int[] arr = {8, 9, 10, 7, 6, 0, 11};
        /*
         * 第一次循环：
         *   0, 9, 10, 7, 6, 8, 11 （从[8, 9, 10, 7, 6, 0, 11]中的最小值元素与arr[0]调换）
         * 第二次循环：
         *   0, 6, 10, 7, 9, 8, 11 （从[9, 10, 7, 6, 8, 11]中的最小值元素与arr[1]调换）
         * 第三次循环：
         *   0, 6, 7, 10, 9, 8, 11 （从[7, 10, 9, 8, 11]中的最小值元素与arr[2]调换）
         * 第四次循环：
         *   0, 6, 7, 8, 9, 10, 11 （从[8, 9, 10, 11]中的最小值元素与arr[3]调换）
         * 第五次循环：
         *   0, 6, 7, 8, 9, 10, 11 （从[9, 10, 11]中的最小值元素与arr[4]调换）
         * 第六次循环：
         *   0, 6, 7, 8, 9, 10, 11 （从[10, 11]中的最小值元素与arr[5]调换）
         */
        int count = 0;  // 记录循环次数
        int count2 = 0; // 记录交换次数
        for (int i = 0; i < arr.length-1; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                count++;
                if (arr[j] < arr[min]){
                    min=j;
                }
            }
            if (min != i){
                int temp;
                temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
                count2++;
            }
        }
        System.out.println("比较次数" + count + "; 交换次数" + count2);
        // 输出结果
        System.out.println("选择排序结果：" + Arrays.toString(arr));
    }
}
