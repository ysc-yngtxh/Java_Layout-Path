package H8_Java对日期的处理;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-25 16:00:00
 * @apiNote TODO 用于取代Date的Java8时间API
 */
public class Java8_用于替代Date的JDK8时间API {

	public static void main(String[] args) {
		// Instant代表当前时间的时间戳，获取的是UTC的时间
		System.out.println(Instant.now());
		// 国际时间与我们当前时间偏移了8个时区，因此需要加上八个小时
		System.out.println("Instant + 08:00 = " + Instant.now().atOffset(ZoneOffset.ofHours(8)));

		// Date 转 Instant
		System.out.println("Date Reverse Instant：" + new Date().toInstant());
		// Instant 转 Date
		System.out.println("Instant Reverse Date：" + Date.from(Instant.now()));

		System.out.println("=============================================================");

		// LocalDate代表一个格式为yyyy-MM-dd的日期 ，包括 年、月、日
		System.out.println("获取当前日期：" + LocalDate.now());
		// 指定日期
		System.out.println("获取指定日期：" + LocalDate.of(2024, 1, 1));
		System.out.println("获取当前时间是该年的第几天：" + LocalDate.of(2024, 3, 25).getDayOfYear());
		System.out.println("获取当前时间是该月份的第几天：" + LocalDate.of(2024, 3, 25).getDayOfMonth());
		System.out.println("获取当前时间是星期几：" + LocalDate.of(2024, 3, 25).getDayOfWeek());
		// 解析为LocalDate
		System.out.println("解析为LocalDate：" + LocalDate.parse("2024-03-25"));

		System.out.println("=============================================================");

		// LocalDateTime是一个不可变类且线程安全，它的默认格式为 yyyy-MM-ddTHH:mm:ss.SSS
		System.out.println(LocalDateTime.now());
		System.out.println(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
		System.out.println(LocalDateTime.now(ZoneId.of("Europe/Paris")));

		// 时间格式化 SimpleDateFormat 不是线程安全的类。
		// 在多线程环境下调用format()方法，一个线程刚设置好time值，另外的一个线程马上把设置的time值给修改了，导致返回的格式化时间可能是错误的。
		// 而使用 DateTimeFormatter 格式化时间是线程安全的。
		DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
		DateTimeFormatter localDateTimeParse = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// 当前时间格式化为String
		String formatterStr = LocalDateTime.now().format(localDateTimeFormatter);
		System.out.println("当前时间LocalDateTime类型格式化为String类型：" + formatterStr);
		// 解析为LocalDateTime
		LocalDateTime parse = LocalDateTime.parse(formatterStr, localDateTimeFormatter);
		System.out.println("String类型解析为LocalDateTime类型：" + parse);
		// 再解析为指定格式的String
		String format = parse.format(localDateTimeParse);
		System.out.println("指定格式的时间字符串：" + format);

        System.out.println("Instant = " +
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
         );
    }
}
