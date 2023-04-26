package D4数组.i9算法;

import java.util.Arrays;

/*数组常见算法
      排序算法：
          冒泡排序算法
          选择排序算法
      查找算法：
          二分法算法

      以上算法在以后的Java实际开发中我们不需要使用
      因为Java已经封装好了，直接调用就行
      只不过以后的面试的时候，可能会有机会碰上
      
* */
public class Arrays工具类1 {
    public static void main(String[] args) {
        int[] arr={112,56,987,2264};

        //工具类当中的方法大部分都是静态的、
        Arrays.sort(arr);   //按顺序排序

        //遍历输出
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        /*56
          112
          987
          2264*/
    }
}
