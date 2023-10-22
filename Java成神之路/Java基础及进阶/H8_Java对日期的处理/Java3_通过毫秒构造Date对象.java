package H8_Java对日期的处理;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Java3_通过毫秒构造Date对象 {
    public static void main(String[] args) {
        Date time = new Date(1); // 注意：参数是一个毫秒
        // 1970-01-01 00：00：00 001

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss SSS");
        String strTime = sdf.format(time);
        System.out.println(strTime); // 1970-01-01  08:00:00 001
        // 格林威治时间为标准，北京是东八区，相差八个小时

        System.out.println(new Date(System.currentTimeMillis()));
        // 获取昨天此时的时间
        Date time2 = new Date(System.currentTimeMillis() - 24*60*60*1000);
        // 表示的是：自1970年1月1日 00：00：00  000到当前系统时间的总毫秒数 - 一天一夜的总毫秒数
        String strTime2 = sdf.format(time2);
        System.out.println(strTime2);
    }
}