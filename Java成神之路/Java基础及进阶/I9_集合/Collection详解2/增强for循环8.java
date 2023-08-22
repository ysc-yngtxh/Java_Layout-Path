package I9_集合.Collection详解2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
1、JDK5.0之后推出了一个新特性：叫做增强for循环，或者叫做foreach
2、foreach有一个缺点：没有下标。在需要下标的循环中不建议使用增强for循环
 */
public class 增强for循环8 {
    public static void main(String[] args) {
        // int数组
        int[] a = {432, 4, 65, 46, 54, 76, 54};

        // 遍历数组（普通for循环）
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

        /*
        增强for循环语法
        for (数据类型 变量名: 数组或集合) {
            System.out.println(变量名);
        }
        */

        // 遍历数组（增强for循环）
        for(int data : a){
            // data就是数组中的元素(数组中的每一个元素)
            System.out.println(data);
        }

        System.out.println("=========================================================================================");

        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("kitty");

        // 遍历，使用迭代器方式
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        // 使用下标方式（只针对有下标的集合）
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 使用foreach
        for (String s: list
             ) {
            System.out.println(s);
        }
    }
}
