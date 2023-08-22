package D4_数组.数组_Ⅲ_算法;

/*选择排序：
       每一次从这堆“参与比较的数据当中”找出最小值
       拿着这个最小的值和最前面的数据“交换位置”

       选择排序比冒泡排序好在：每一次的交换位置都是有意义的。

         参与比较的数据：3，1，6，2，5
         第一次循环之后的结果是：
         1，3，6，2，5

         参与比较的数据：3，6，2，5
         第二次循环的结果是：
         2，3，6，5

         参与比较的数据：3，6，5
         第三次循环的结果是：
         3，6，5

         参与比较的数据：6，5
         第四次循环的结果是：
         5，6

 */

public class 数组_11_选择排序算法 {
    public static void main(String[] args) {
        int[] arr={8,9,10,7,6,0,11};
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

        System.out.println("比较次数" + count);
        System.out.println("交换次数" + count2);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
