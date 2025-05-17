package K11_流.流Ⅳ_标准输出流;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class 流10_日志工具 {

	public static void main(String[] args) {
		// 测试工具类是否好用
		Logger.log("调用了System类的gc()方法，建议启动垃圾回收");
		Logger.log("调用了UserService的doSome()方法");
		Logger.log("用户尝试进行登录，验证失败");

		System.setOut(System.out);
		System.out.println("我想你了");
	}

}

class Logger {

	public static void log(String msg) {
		try {
			// 指向一个日志文件
			PrintStream out = new PrintStream(
					new FileOutputStream(System.getProperty("user.dir")
							                     + "/Java基础及进阶/K11_流/LogFile", true));
			// 改变输出方向，不再是控制台输出
			System.setOut(out);
			// 日期当前时间
			Date nowTime = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
			String s = sdf.format(nowTime);

			System.out.println(s + " : " + msg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
