package G7_包装类;

/*
  1、8种基本数据类型对应的包装类型名是什么

         基本数据类型                      包装类型
    ------------------------------------------------------------
           byte                 java.lang.Byte      (父类是Number)
           short                java.lang.Short     (父类是Number)
           int                  java.lang.Integer   (父类是Number)
           long                 java.lang.Long      (父类是Number)
           float                java.lang.Float     (父类是Number)
           double               java.lang.Double    (父类是Number)
           boolean              java.lang.Boolean   (父类是Object)
           char                 java.lang.Character (父类是Object)

  2、以上8种包装类中，重点以 java.lang.Integer 为代表进行学习，其他类型照葫芦画瓢就行

  3、八种包装类中其中六种都是数字对应的包装类，他们的父类都是Number，Number是一个抽象类，无法实例化对象

  4、关于Integer类的构造方法，有两个：
         Integer(int);
         Integer(String)
*/
public class 包装类2_装箱和拆箱概念 {
    public static void main(String[] args) {
        // 123这个基本数据类型，进行构造方法的包装达到了：基本数据类型向引用数据类型的转换
        // 基本数据类型（转换为）-> 引用数据类型（装箱）
        Integer i1 = new Integer(123);
        float f = i1.floatValue();
        System.out.println(f);

        Integer i2 = new Integer("123");
        System.out.println(i2);

        // 将引用数据类型 --（转换为）-> 基本数据类型（拆箱）
        int reValue = i1.intValue();
        System.out.println(reValue);

        // 通过访问包装类的常量，来获取最大值和最小值
        System.out.println("int的最大值：" + Integer.MAX_VALUE);
        System.out.println("int的最小值：" + Integer.MIN_VALUE);
        System.out.println("byte的最大值：" + Byte.MAX_VALUE);
        System.out.println("byte的最小值：" + Byte.MIN_VALUE);
    }
}