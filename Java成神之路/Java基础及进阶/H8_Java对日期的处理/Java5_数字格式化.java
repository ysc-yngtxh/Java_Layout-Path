package H8_Java对日期的处理;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Java5_数字格式化 {
    public static void main(String[] args) {
        // java.text.DecimalFormat 专门负责数字格式化的。
        // DecimalFormat df = new DecimalFormat("数字格式")

        /*
          数字格式化有哪些？
              #  代表任意数字
              ,  代表千分位
              .  代表小数点
              0  代表不够时补上0
              ###,###.##  表示：加入千分位，保留两个小数
         */
        DecimalFormat df = new DecimalFormat("###,###.##");
        String s = df.format(1234.56789);
        System.out.println(s);          // 1,234.57

        DecimalFormat df2 = new DecimalFormat("###,###.00000"); // 保留4个小数位，不够补上0
        String s2 = df2.format(1234.56);
        System.out.println(s2);         // 1,234.56000


        // TODO 货币格式化
        NumberFormat currency = NumberFormat.getCurrencyInstance(); // 建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();   // 建立百分比格式化引用
        percent.setMaximumFractionDigits(3); // 百分比小数点最多3位

        BigDecimal loanAmount = new BigDecimal("15000.48"); // 贷款金额
        BigDecimal interestRate = new BigDecimal("0.008");  // 利率
        BigDecimal interest = loanAmount.multiply(interestRate); // 相乘

        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));
    }
}