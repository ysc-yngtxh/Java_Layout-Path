package H8_Java对日期的处理;

import java.math.BigDecimal;

/*
1、BigDecimal 属于大数据，精度极高，不属于基本数据类型，属于Java对象（引用数据类型）
     这是SUN提供的一个类。专门用在财务软件当中

2、注意：财务软件中double是不够的。咱们之前有一个学生去面试，经理就问了这样的一个问腿：
         你处理过财务数据吗！？用的是哪一种类型？
         千万别说double,说java.math,BigDecimal
 */
public class Java5_高精度BigDecimal {
    public static void main(String[] args) {

        // 这个100不是普通的100，是精度极高的100
        BigDecimal v1= new BigDecimal("100.00");
        // 精度极高的200
        BigDecimal v2= new BigDecimal("200.12");

        // 求和
        // v1 + v2;这样不行，v1和v2都是引用，不能直接使用+求和
        BigDecimal v3 = v1.add(v2);  // 调用方法求和
        System.out.println(v3);

        v3.setScale(1, BigDecimal.ROUND_CEILING);
        System.out.println(v3);

        BigDecimal v4 = v2.divide(v1, 1, BigDecimal.ROUND_HALF_DOWN);   // 调用方法求两数除数
        System.out.println(v4);
    }
}
