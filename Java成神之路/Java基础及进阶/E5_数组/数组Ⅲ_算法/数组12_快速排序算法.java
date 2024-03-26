package E5_数组.数组Ⅲ_算法;

import java.util.Arrays;

public class 数组12_快速排序算法 {
    public static void main(String[] args){
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length-1);
        // 输出结果
        System.out.println("选择排序结果：" + Arrays.toString(arr));
    }
    /*
     10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19
     10, 7, 2, 4, 7, 9, 3, 4, 2, 1, 8, 62, 19
     8, 7, 2, 4, 7, 9, 3, 4, 2, 1, 10, 62, 19
     8, 7, 2, 4, 7, 1, 3, 4, 2, 9, 10, 62, 19
     2, 7, 2, 4, 7, 1, 3, 4, 8, 9, 10, 62, 19
     1, 2, 2, 3, 4, 4, 7, 7, 8, 9, 10, 62, 19
     */
    public static void quickSort(int[] arr, int low, int high){
        int i, j, temp, t;
        if(low > high){
            return;
        }
        i = low;
        j = high;
        // temp就是基准位
        temp = arr[low];

        while (i < j) {
            // 先看右边，依次往左递减
            while (temp<=arr[j] && i<j) {
                j--;
            }
            // 再看左边，依次往右递增
            while (temp>=arr[i] && i<j) {
                i++;
            }
            // 如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        // 最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        // 递归调用左半数组
        quickSort(arr, low, j-1);
        // 递归调用右半数组
        quickSort(arr, j+1, high);
    }
}
