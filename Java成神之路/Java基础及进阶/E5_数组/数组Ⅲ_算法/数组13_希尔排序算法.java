package E5_数组.数组Ⅲ_算法;

import java.util.Arrays;

public class 数组13_希尔排序算法 {
    public static void main(String[] args) {
        // 定义数组
        int[] arr = {99, 55, 2, 3, 9, 10, 22, 34, 67, 89, 69, 92, 101, 102};
        // 增量
        int gap = arr.length;
        // 排序
        sort(arr, gap);
        // 输出结果
        System.out.println("希尔排序结果：" + Arrays.toString(arr));
    }
    public static void sort(int[] arr, int gap) {
        // 确定新一轮分组的增量
        gap = gap / 2;
        // 对数组进行分组
        for (int i = 0; i < gap; i++) {
            for (int j = i + gap; j < arr.length; j += gap) {
                // 获取当前元素，然后在本组内部向前比较并排序
                int current = arr[j];
                for (int k = j - gap; k >= i; k -= gap) {
                    if (arr[k] > current) {
                        // 插入
                        arr[k + gap] = arr[k];
                        arr[k] = current;
                    }
                }
            }
        }
        if (gap > 1) {
            sort(arr, gap);
        }
    }
}
