package J10_集合.集合Ⅰ_Collection详解.List接口;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
  测试List接口中的常用方法
     1、List集合存储元素的特点：有序可重复
          有序：List集合中的元素有下标。从0开始，以1递增
          可重复：存储一个1，还可以再存储1

     2、List既然是Collection接口的子接口，那么肯定List接口有自己"特色"的方法：
           以下只列出List接口特有的常用的方法：
               void add(int index, E element)     // 在列表的指定位置插入指定元素（第一个参数是下标）
               E set(int index, Object element)   // 修改指定位置的元素
               E get(int index)                   // 根据下标获取元素
               int indexOf(Object o)              // 获取指定对象第一次出现出的索引
               int lastIndexOf(Object o)          // 获取指定对象最后一次出现的索引
               E remove(int index)                // 删除指定下标位置的元素

      计算机英语：
          增删改查这几个单词要知道：
              增：add,save,new
              删：delete,drop,remove
              改：update,set,modify
              查：find,get,query,select
 */
public class 集合9_List接口的常用方法 {
    public static void main(String[] args) {
        // 创建List对象
        List<Object> myList = new ArrayList<>();

        // 添加元素。
        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("C");
        myList.add("D");

        // 在列表的指定位置插入指定元素（第一个参数是下标）
        // 这个方法使用不多，因为对于ArrayList集合来说效率比较低
        // myList.add(index:下标, element:元素);
        myList.add(1, "KING");

        // 迭代
        Iterator<Object> it = myList.iterator();
        while(it.hasNext()){
            Object o =it.next();
            System.out.println(o);
        }

        // 根据下标获取元素
        Object firstObj = myList.get(0);
        System.out.println(firstObj);

        // 因为有下标，所以List集合有自己比较特殊的遍历方式
        // 通过下标遍历.[List集合特有的方式，Set没有]
        for (int i = 0; i < myList.size(); i++) {
            Object obj = myList.get(i);
            System.out.println(obj);
        }

        System.out.println("=========================================================================================");

        // 获取指定对象第一次出现出的索引
        System.out.println(myList.indexOf("KING"));

        // 获取指定对象最后一次出现的索引
        System.out.println(myList.lastIndexOf("C"));

        // 删除指定下标位置的元素
        // 删除下标为0的元素
        myList.remove(0);
        System.out.println(myList.size());

        // 修改指定位置的元素
        myList.set(2, "Soft");

        // 遍历集合
        for (int i = 0; i < myList.size(); i++) {
            Object obj = myList.get(i);
            System.out.println(obj);
        }
    }
}