package E5_数组.数组Ⅲ_算法;

import java.util.Arrays;

public class 数组24_Arrays工具类 {
    public static void main(String[] args) {
        // java.util.Arrays;  工具类中有哪些方法，我们开发的时候要参考API帮助文档,不要死记硬背
        int[] arr = {3, 6, 4, 5, 12, 1, 2, 32, 5, 5};
        // 数组转String "[3, 6, 4, 5, 12, 1, 2, 32, 5, 5]"
        System.err.println(Arrays.toString(arr));
        // 排序
        Arrays.sort(arr);
        // 输出
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        // 二分法查找（建立在排序基础之上）
        int index = Arrays.binarySearch(arr, 5);
        System.out.println(index == -1 ? "该元素不存在!" : "该元素下标是:" + index);
    }
}
