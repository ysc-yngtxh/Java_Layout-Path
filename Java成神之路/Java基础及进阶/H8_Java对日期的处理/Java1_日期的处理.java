package H8_Java对日期的处理;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Java1_日期的处理 {

	public static void main(String[] args) throws ParseException {
		Date nowTime = new Date();
		System.out.println(nowTime); // Tue May 26 17:53:04 CST 2020
		Date date = new Date(9999999);
		System.out.println(date);

		// 判断nowTime是否早与date
		System.out.println("nowTime是否早与date:" + nowTime.before(date));
		// 判断nowTime是否晚与date
		System.out.println("nowTime是否晚与date:" + nowTime.after(date));

		System.out.println("获取当前时间的LocalDateTime类型：" + LocalDateTime.now());

		// java.util.Date类的toString()方法已经重写
		// 输出的应该不是一个对象的内存地址，应该是一个日期字符串。

		/* yyyy     年（年是四位）
		 * MM       月（月是两位）
		 * dd       日
		 * HH       时
		 * mm       分
		 * ss       秒
		 * SSS      毫秒(毫秒3位，最高999.1000毫秒表示1秒)
		 */

		System.out.println("=========================================================================================");

		// 假设现在有一个日期字符串String，怎么转换成Date类型？
		String time = "2020-05-26 08:08:08 888";
		// SimpleDateFormat是java.text包下的，专门负责日期格式化。
		// 注意：字符串的日期格式和SimpleDateFormat对象指定的日期格式要一致。不然会出现异常：java.text.ParseException
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		Date dateTime = sdf1.parse(time);   // 使用parse方法将日期字符串String,转换成Date类型.
		System.out.println(dateTime);       // Tue May 26 08:08:08 CST 2020

		// Date类型转换成日期字符串String
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTimeStr = sdf.format(dateTime);  // 使用format方法将Date类型转换成日期字符串String
		System.out.println(dateTimeStr);
	}

}
