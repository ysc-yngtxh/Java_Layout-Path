package com.example.vavr实用Api;

import com.example.vo.User;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Predicates.instanceOf;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2022/11/27 0:45
 */
public class OpenVavr {
	public static final Logger log = LoggerFactory.getLogger(OpenVavr.class);

	static {
		// 默认情况下，SLF4J 的日志级别是 INFO。强制设置日志级别为 DEBUG（适用于 Logback）
		((ch.qos.logback.classic.Logger) log).setLevel(ch.qos.logback.classic.Level.DEBUG);
	}

	static final String NAME_ERR = "Invalid Characters In Name: ";
	static final String AGE_ERR = "Age Must Be At Least 0";

	@Test
	public void test1() {
		// TODO 1、元组(引用元素时从1开始，而不是0。),元组中就可以存进不同类型的数据
		Tuple2<String, Integer> vavrTuple = Tuple.of("Java", 8);
		String element1 = vavrTuple._1; // 第一个元素
		int element2 = vavrTuple._2();  // 第二个元素
		log.info(element1);
		log.info(String.valueOf(element2));

		// TODO 2、Try用来包装可能产生异常的代码块，这样就不用显式的通过 try-catch 来处理异常
		Try<Integer> result = Try.of(() -> 1 / 0);
		int errorSentinel = result.getOrElse(-2);
		log.info(String.valueOf(errorSentinel));
		log.info(String.valueOf(result.isFailure()));
		// result.getOrElseThrow(() -> new ArithmeticException()); 会实现真正意义上的抛异常
	}

	@Test
	public void test2() {
		// TODO 3、验证Validation。
		// 通常情况下程序遇到错误未做处理就会终止。然而，Validation会继续处理，并将程序错误累积，最终最为一个整体处理。
		User user = new User(1L, "YouShiCheng", "google@163.com", 25);
		String invalidChars = user.getName().replaceAll("[a-zA-Z]", "");
		Validation<String, String> objects1 = invalidChars.isEmpty() ?
				Validation.valid(user.getName()) : Validation.invalid(NAME_ERR + invalidChars);
		Validation<String, Integer> objects2 = user.getAge() >= 0 ?
				Validation.valid(user.getAge()) : Validation.invalid(AGE_ERR);
		Validation<Seq<String>, User> users = Validation.combine(objects1, objects2).ap(User::new);
		// 这里只会做校验处理，所以当没有遇到错误时并不会返回po类数据，因此对象为空(而且我们业务处理一般只需要错误信息即可)
		log.info(users.isInvalid() ? users.getError().toString() : users.get().toString());

		User user1 = new User(1L, "YouShiCheng😃--97", "google@163.com", -1);
		String invalidChars1 = user1.getName().replaceAll("[a-zA-Z]", "");
		Validation<Seq<String>, User> users1 = Validation.combine(
				invalidChars1.isEmpty() ? Validation.valid(user1.getName()) : Validation.invalid(NAME_ERR + invalidChars1),
				user1.getAge() >= 0 ? Validation.valid(user1.getAge()) : Validation.invalid(AGE_ERR)
		).ap(User::new);
		// 返回所有错误信息
		log.info(String.valueOf(users1.getError().size()));
		log.info(users1.getError().get(0) + " && " + users1.getError().get(1));
		if (users1.isInvalid()) {
			StringBuilder error = new StringBuilder(users1.getError().get(0));
			if (users1.getError().size() >= 2) {
				for (int i = 1; i < users1.getError().size(); i++) {
					error.append(" && ").append(users1.getError().get(i));
				}
			}
			log.info(error.toString());
		}
	}

	@Test
	public void test3() {
		// TODO 4、模式匹配Pattern Matching
		// 在Vavr中我们通过Match方法替换switch块。每个条件检查都通过Case方法调用来替换。 $()来替换条件并完成表达式计算得到结果。
		int input = 4;
		String output = Match(input).of(
				Case($(1), "one")
				, Case($(2), "two")  // 写法效果是一样的的
				, Case($(3), "three")
				, Case($(), "other......")    // 除开1、2、3的其他情况会走这里
		);
		log.info(output);

		int i = 5;
		String s = Match(i).of(
				Case($(n -> List.of(2, 4, 6, 8).contains(n)), "Even Single Digit")
				, Case($(n -> List.of(1, 3, 5, 7, 9).contains(n)), "Odd Single Digit")
				, Case($(), "Out of range")
		);

		log.info(s);
		String s1 = Match(i).of(
				Case($(instanceOf(Integer.class)), "Integer matched")
				, Case($(), "not int")
		);
		log.info(s1);

		Integer i1 = 3;
		String s2 = Match(i1).of(
				Case($(n -> n != null && Set.of(1, 2, 3, null).contains(n)), "Number found")
				, Case($(), "Not found")
		);
		log.info(s2);

		Integer year = 1990;
		String s3 = Match(year).of(
				Case($(y -> Set.of(1990, 1991, 1992).contains(y) || y == 1986), "Age match")
				, Case($(), "No age match")
		);
		log.info(s3);
	}
}
