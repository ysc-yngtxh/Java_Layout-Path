package E5_数组.数组Ⅲ_算法;

import java.util.Arrays;

/**
 * 根据字符出现频率排序采用 桶排序
 */
public class 数组14_桶排序算法 {
    public static void main(String[] args) {
        int[] arr = new int[] {2, 4, 6, 8, 5, 2, 9, 6};
        bucketSort(arr);
        System.out.println("桶排序结果：" + Arrays.toString(arr));
    }
    public static void bucketSort(int[] arr) {
        // 遍历原始数组，找到数组中的最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 创建一个下标为原始数组中最大值的桶数组，现在这个桶数组所有元素值都为默认值0
        // 该桶数组的下标值代表原数组元素值，桶数组元素值表示原数组各个元素出现的次数
        int[] bucketArray = new int[max + 1];
        // 再次遍历原始数组，得到原数组中存在的各个元素，以及出现的次数
        for (int i = 0; i < arr.length; i++) {
            // 下标i的元素值自增
            bucketArray[arr[i]]++;
        }
        // 遍历桶数组。外层循环从桶的第一位开始（即下标为零）；内层循环遍历桶数组中下标为i的值出现的次数【排序的数组可能会出现重复的数值】
        // 当桶数组元素值为 0 时，是不走内层循环的，因此不影响 arr 数组排序。
        int index = 0;
        for (int i = 0; i < bucketArray.length; i++) {
            for (int j = 0; j < bucketArray[i]; j++) {
                arr[index++] = i;
            }
        }
    }
}