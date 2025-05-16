package com.example.Objects工具包;

import com.example.vo.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2022/11/28 23:40:00
 */
public class ObjectsApi {
	public static final Logger log = LoggerFactory.getLogger(ObjectsApi.class);

	static {
		// 默认情况下，SLF4J 的日志级别是 INFO。强制设置日志级别为 DEBUG（适用于 Logback）
		((ch.qos.logback.classic.Logger) log).setLevel(ch.qos.logback.classic.Level.DEBUG);
	}

	public static void main(String[] args) {
		// TODO Objects是jdk自带的关于Object类的工具类
		User user = new User();
		boolean aNull = Objects.isNull(user);
		boolean nonNull = Objects.nonNull(user);
		log.info(Instant.now().toString());
		log.info(LocalDateTime.now().toString());

		// 如果参数相同，则返回0，否则返回c.compare(a，b)。因此，如果两个参数都为null，则返回0。
		int compare = Objects.compare(123, 456, new Comp());
		log.info(String.valueOf(compare));

		// deepEquals()  如果两个参数都相等，则返回true。否则，它返回false。如果两个参数都为null，则返回true。
		// equals()      如果两参数相等返回true，否则返回false。但是这里的equals比较对象是比较对象地址
		String[][] name = {{"G", "a", "o"}, {"H", "u", "a", "n"}, {"j", "i", "e"}};
		String[][] names = {{"G", "a", "o"}, {"H", "u", "a", "n"}, {"j", "i", "e"}};
		boolean deepEquals1 = Objects.deepEquals(name, names);
		boolean equals1 = Objects.equals(name, names);
		log.info(deepEquals1 + "---" + equals1);

		String[] name1 = {"G", "a", "o", "H", "u", "a", "n", "j", "i", "e"};
		String[] name2 = {"G", "a", "o", "H", "u", "a", "n", "j", "i", "e"};
		boolean deepEquals2 = Objects.deepEquals(name1, name2);
		boolean equals2 = Objects.equals(name1, name2);
		log.info(deepEquals2 + "---" + equals2);

		// requireNonNull方法将一个Supplier作为第二个参数,第一个参数为null时抛出空指针异常并获取Supplier函数的值
		Object requireNonNull = Objects.requireNonNull(null, () -> "Name is required. Error generated on " + Instant.now());
		log.info(requireNonNull.toString());
	}

	/**
	 * 自定义的Comparator实现类
	 */
	public static class Comp implements Comparator<Integer> {
		/**
		 * 如果t1的值大于t2，返回111；相等返回100；否则返回222
		 */
		@Override
		public int compare(Integer t1, Integer t2) {
			if (t1 > t2) {
				return 111;
			} else if (t1.equals(t2)) {
				return 100;
			} else {
				return 222;
			}
		}
	}
}
