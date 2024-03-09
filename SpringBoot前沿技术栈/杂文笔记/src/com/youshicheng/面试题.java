package com.example;

public class 面试题 {
    public static void main(String[] args) {
    }
}

/*
一、关于JVM的自增算法及方法的参数传递机制
           public static void main(String[] args) {
              int i = 1;
              i = i++;
              System.out.println(i);
              int j = i++;
              int k = i+ ++i*i++;
              System.out.println("i="+i);
              System.out.println("j="+j);
              System.out.println("k="+k);
           }
       1、首先我们要知道i++与++i的区别：i++表示后自增，++i表示先自增
                  例1:  int i = 0;                     例2: int i = 0;
                        int temp = i++;                    int temp = ++i;
              过程 =>  temp = i, i = i + 1            过程 =>  i = i + 1, temp = i
              结果 =>  temp = 0, i = 1                结果 =>  i = 1,     temp = 1
       2、再看上述题目： i 在自增后又被等式右边的值覆盖了，也就是说 i=i++ 的值发生了三次变化 , 第一次是我们定义 i = 1;
                      第二次就是 i 自增后变成了 2 , 第三次就是 i 又被 1 值覆盖了
       3、网上还有一种理解：①、i=i++ 式子右边操作数栈的 i 值是 1, 赋给左边的局部变量表的 i 值, 所以 i 值为 1。
                        ②、然后局部变量表的 i自增 等于2, 然而操作数栈的 i 值还是 1
                        ③、语句结束,操作数栈的 i 值会赋给局部变量表的 i 。因此最终结果是 1
       4、j = i++, 先将 i 值赋给 j ,所以 j = 1。 然后 i 自增 , i = 2
       5、int k = i + ++i * i++;
                 \/   \/    \/
                 2    3     3
                i=2  ++i=3  i++=3(这里局部变量表的i值为3)


          public class Exam {
               public static void main(String[] args) {
                   int i = 1;
                   String str = "hello";
                   Integer num = 200;
                   int[] arr = {1, 2, 3, 4, 5};
                   MyData my = new MyData();

                   change(i, str, num, arr, my);

                   System.out.println("i=" + i);
                   System.out.println("str=" + str);
                   System.out.println("num=" + num);
                   System.out.println("arr=" + Arrays.toString(arr));
                   System.out.println("my.a=" + my.a);
               }
               public static void change(int j, String s, Integer n, int[] a, MyData m){
                   j += 1;
                   s += "word";
                   n += 1;
                   a[0] += 1;
                   m.a += 1;
               }
          }
          class MyData{
              int a = 10;
          }

      综上分析：i=1, str=hello, num=200, arr={2,2,3,4,5}, my.a=11
 */
