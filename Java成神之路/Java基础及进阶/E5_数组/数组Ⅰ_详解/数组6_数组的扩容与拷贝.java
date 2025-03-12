package E5_数组.数组Ⅰ_详解;

/* 关于一维数组的扩容
    在Java开发中，数组长度一旦确定不可变，那么数组满了怎么办？
        数组满了，需要扩容。Java中对数组的扩容是：
              先新建一个大容量的数组，然后将小容量数组中数据一个一个拷贝到大数组当中

              结论：数组扩容效率较低。因为涉及到拷贝的问题，所以在以后的开发中请注意：尽可能少的进行数组的拷贝
                    可以再创建数组对象的时候预估计一下多长合适，最好预估准确，这样可以减少数组的扩容次数，提高效率。
*/
public class 数组6_数组的扩容与拷贝 {
    public static void main(String[] args) {
        // 拷贝源（从这个数组中拷贝）
        int[] src = {1, 11, 22, 3, 4};

        // 拷贝目标
        int[] dest = new int[20];     // 动态初始化一个长度为20的数组，每一个元素默认值为0

        // 调用JDK System类中的 arraycopy() 方法，来完成数组的拷贝
        // System.arraycopy(源数组，从下标1开始，目标数组，从下标3开始，拷贝几个元素);
        System.arraycopy(src, 1, dest, 3, 2);

        // 遍历目标数组
        for (int i = 0; i < dest.length; i++) {
            System.out.println(dest[i]);
        }

        // 数组中如果存储的元素是引用，可以拷贝吗？当然可以。
        String[] str = {"hello", "world!", "study", "java", "mysql", "jdbc", "oracle"};
        String[] newStr = new String[20];
        System.arraycopy(str, 0, newStr, 0, str.length);
        for (int i = 0; i < newStr.length; i++) {
            System.out.println(newStr[i]);
        }

        // 还有数组拷贝的是对象地址
        Object[] objs = {new Object(), new Object(), new Object()};
        Object[] newObjs = new Object[10];
        System.arraycopy(objs, 0, newObjs, 0, objs.length);
        for (int i = 0; i < newObjs.length; i++) {
            System.out.println(newObjs[i]);
        }
    }
}
