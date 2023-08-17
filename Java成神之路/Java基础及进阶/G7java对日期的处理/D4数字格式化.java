package G7java对日期的处理;

import java.text.DecimalFormat;

public class D4数字格式化 {
    public static void main(String[] args) {

        //java.text.DecimalFormat专门负责数字格式化的。
        //DecimalFormat df = new DecimalFormat("数字格式")

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
        System.out.println(s);          //1,234.57

        DecimalFormat df2 = new DecimalFormat("###,###.00000");//保留4个小数位，不够补上0
        String s2 = df2.format(1234.56);
        System.out.println(s2);         //1,234.56000
    }
}
