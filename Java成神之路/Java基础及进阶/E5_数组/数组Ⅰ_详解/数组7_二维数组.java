package E5_数组.数组Ⅰ_详解;

/*
 * 关于Java中的二维数组
 *      1、二维数组其实是一个特殊的一维数组，特殊在这个以为数组当中的每一个元素是一个一维数组
 *      2、二维数组静态初始化
 *             int[][] array={ {1,1,1},{0,0,0},{2,3,4,5}... };
 *      3、二维数组中元素的：读和改
 *             a[二维数组中的一维数组的下标][一维数组的下标]
 *             a[0][0]： 表示第一个一维数组的第一个元素
 *             a[0][100]：表示第一个一维数组中的底101个元素
 */
public class 数组7_二维数组 {
    public static void main(String[] args) {
        // 一维数组
        int[] array = {100, 200, 300};
        // 二维数组
        int[][] a = { {100,200,300} , {30,20,40,50,60} , {6,7,9,1} , {0} };
        System.out.println(a.length);       // 表示有4个一维数组
        System.out.println(a[0].length);    // 表示第一个一维数组中有几个元素

        System.out.println(a[0][0]);       // 表示第一个一维数组中的第一个元素
        // 取出元素
        System.out.println("第二个一维数组中第三个元素" +  a[1][2]);
        // 改元素
        a[2][0] = 11111;
        System.out.println(a[2][0]);

        System.out.println("=======================================================");

        String[][] ar = {
                {"java", "oracle", "c++", "python", "c#"},
                {"张三", "李四", "王五"},
                {"lucy", "jack", "rose"}
        };
        // 遍历二维数组
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                System.out.print(ar[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("=======================================================");

        printArray( new int[][] { {1,2,3,4}, {4,5,6,78}, {1,23,4} } );
    }

    public static void printArray(int[][] array){
        // 遍历二维数组。
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
