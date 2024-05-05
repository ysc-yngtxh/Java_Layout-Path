package H8_Java对日期的处理;

import java.math.BigDecimal;
import java.util.Arrays;

/*
  1、BigDecimal 属于大数据，精度极高，不属于基本数据类型，属于Java对象（引用数据类型）
       这是SUN提供的一个类。专门用在财务软件当中

  2、注意：财务软件中double是不够的。
           你处理过财务数据吗！？用的是哪一种类型？
           千万别说double，说java.math.BigDecimal
 */
public class Java4_高精度BigDecimal {

    public static void main(String[] args) {
        // 这个100不是普通的100，是精度极高的100
        BigDecimal v1= new BigDecimal("100.00");
        // 精度极高的200
        BigDecimal v2= new BigDecimal("200.12");

        // 求和
        // v1 + v2;这样不行，v1和v2都是引用，不能直接使用+求和，调用方法求和
        BigDecimal v3 = v1.add(v2);
        System.out.println("求和 v3 = " + v3);

        // 相减
        // v1 - v2;这样不行，v1和v2都是引用，不能直接使用-相减，调用方法求两数相减
        BigDecimal v4 = v1.subtract(v2);
        System.out.println("相减 v4 = " + v4);

        // 相乘
        // v1 * v2;这样不行，v1和v2都是引用，不能直接使用*相乘，调用方法求两数相乘
        BigDecimal v5 = v1.multiply(v2);
        System.out.println("相乘 v5 = " + v5);

        /**
           BigDecimal.ROUND_UP         进位制：不管保留数字后面是大是小(0 除外)都会进1。比如2.3001变成2.4
           BigDecimal.ROUND_DOWN       舍去制，截断操作，后面所有数字直接去除。比如2.35会变成2.3
           BigDecimal.ROUND_HALF_UP    根据保留数字后一位 >=5 进行四舍五入。比如2.35变成2.4
           BigDecimal.ROUND_HALF_DOWN  根据保留数字后一位 >5 进行五舍六入。比如2.35变成2.3
           BigDecimal.ROUND_CEILING    向正无穷方向对齐，转换为正无穷方向最接近的数值。如果为正数，行为和 ROUND_UP 一样；如果为负数，行为和 ROUND_DOWN 一样。
         */

        // 相除
        // v1 / v2;这样不行，v1和v2都是引用，不能直接使用/相除，调用方法求两数除数
        BigDecimal v6_1 = v2.divide(v1);
        BigDecimal v6_2 = v2.divide(v1, 3, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("相除 v6_1 = " + v6_1);
        System.out.println("相除舍入模式 v6_2 = " + v6_2);

        // 取余
        BigDecimal[] v7 = v2.divideAndRemainder(v1);
        System.out.println("取余 v7 = " + Arrays.toString(v7));

        // 处理小数位
        BigDecimal v8 = new BigDecimal("-0.094").setScale(1, BigDecimal.ROUND_DOWN);
        System.out.println("处理小数位 v8 = " + v8);

        // BigDecimal数值比较：不使用equals方法，通常使用compareTo
        BigDecimal v9 = new BigDecimal("3.14");
        BigDecimal v10 = new BigDecimal("3.140");
        System.out.println("使用equals方法，数值比较会加入精度：" + v9.equals(v10));
        System.out.println("使用compareTo方法，比较数值大小：" + v9.compareTo(v10));
    }
}