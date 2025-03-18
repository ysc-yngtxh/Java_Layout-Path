package E5_数组.数组Ⅰ_详解;

/*
 * 1、怎么初始化一个一维数组呢？
 *        包括两种方式：静态初始化一维数组，动态初始化一维数组
 *
 *            静态初始化语法格式：
 *                int[] array = {100, 2100, 300, 55};
 *
 *            动态初始化语法格式：
 *                int[] array = new int[5];
 *                // 这里的5表示数组的元素个数，初始化一个5个长度的int类型数据，每个元素 默认值0.
 *
 *                String[] names = new String[6];
 *                // 初始化6个长度的String类型数组，每个元素默认值null。
 * 2、每个类型的默认值。
 *         数据类型            默认值
 *         -------------------------
 *         byte                 0
 *         short                0
 *         int                  0
 *         long                 0L
 *         float                0.0F
 *         double               0.0
 *         boolean              false
 *         char                 \u0000
 *         引用数据类型           null
 *
 * 3、什么时候采用静态初始化方式，什么时候使用动态初始化方式呢？
 *        当你创建数组的时候，确定数组中存储哪些具体的元素时，采用静态初始化方式。
 *        当你创建数组的时候，不确定将来数组中存储哪些数据，你可以采用动态初始化的方式，预先分配内存空间。
 */

public class 数组2_初始化数组 {
    public static void main(String[] args) {
        // 声明一个int类型的数组，使用静态初始化的方式
        int[] a = {1, 100, 10, 20, 55, 689};
        // 所有的数组对象都有length属性
        System.out.println("数组中元素的个数 = " + a.length);
        System.out.println("第一个元素 = " + a[0]);
        System.out.println("最后一个元素 = " + a[5]);
        System.out.println("最后一个元素 = " + a[a.length-1]);

        // 把第一个元素修改为111
        a[0] = 111;
        // 把最后一个元素修改为0
        a[a.length-1] = 0;
        System.out.println("第一个元素 = " + a[0]);
        System.out.println("最后一个元素 = " + a[5]);

        // 一维数组怎么遍历呢？
        for (int i = 0; i < a.length; i++){
            System.out.println("顺序输出第" + i + "个元素：" + a[i]);
        }

        // 从最后一个元素遍历到第一个元素
        for (int i = a.length-1; i >= 0; i--){
            System.out.println("倒序输出第" + i + "个元素：" + a[i]);
        }

        System.out.println("==========================================================");


        // 声明/定义一个数组，采用动态初始化的方式创建
        int[] a1 = new int[4];     // 创建长度为4的int数据，数据中每个元素的默认值是0
        for (int i = 0; i < a1.length; i++){
            System.out.println("数组中下标为" + i + "的元素是：" + a1[i]);  // 4个元素都是0
        }

        // 初始化一个Object类型的数组，采用动态初始化方式
        Object[] objs = new Object[3];     // 3个长度，动态初始化，所以每个元素默认值是null
        for (int i = 0; i < objs.length; i++){
            System.out.println(objs[i]);  // 3个元素都是null
        }

        String[] stra = new String[3];
        for (int i = 0; i < stra.length; i++) {
            System.out.println(stra[i]);
        }

        // 静态初始化
        String[] str = {"abc", "def", "hij"};
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }
}
