package E5_数组.数组Ⅲ_算法;

/*
 * 不使用二分法只能挨个比较进行查找
 *    public class 数组23_二分法算法 {
 *        public static void main(String[] args) {
 *            int[] arr = {11, 12, 16, 10, 8, 9, 12};
 *            int index = arraySearch(arr, 12);
 *            System.out.println(index == -1 ? "该元素不存在!" : "该元素下标是:" + index);
 *        }
 *        public static int arraySearch(int[] arr, int ele) {
 *            for (int i = 0; i < arr.length; i++) {
 *                if (ele == arr[i]){
 *                    return i;
 *                }
 *            }
 *            return -1; // 当数组中有重复的需要查找的数字出现，这种挨个比较查找方法，只会查找出第一个数字的下标。
 *        }
 *    }
 *
 * 二分法算法（建立在排序基础之上）
 *     1、数组工具类：自己写的，不是SUN的
 *
 *     2、关于查找算法中的：二分法查找。
 *        10(下标0) 11 12 13 14 15 16 17 18 19 20(下标10)  arr数组
 *
 *        通过二分法查找，找出18这个元素的下标：
 *           (0+10)/2 ---> 中间元素的下标：5
 *
 *        拿着中间这个元素和目标要查找的元素进行
 *            中间元素是：arr[5] ---> 15
 *            15 < 18(被查找的元素)
 *            被查找的元素18在中间元素15的右边。
 *
 *     3、二分法查找算法是基于排序的基础之上。(没有排序的数据是无法查找的)
 */
public class 数组23_二分法算法 {
    public static void main(String[] args) {
        int[] arr = {100, 200, 300, 235, 600, 1000, 2000, 9999};
        int index = binarySear(arr, 600);
        System.out.println(index == -1 ? "该元素不存在!" : "该元素下标是:" + index);
    }
    private static int binarySear(int[] arr, int ele) {
        int begin = 0;            // 开始下标
        int end = arr.length-1;   // 结束下标
        while (begin <= end) {
            int mid = (begin + end)/2; // 中间下标
            System.out.println(mid);

            if (arr[mid] == ele) {
                return mid;
            } else if (arr[mid] < ele) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
