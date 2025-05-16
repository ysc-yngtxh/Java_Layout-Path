package com.example.空指针异常Optional;

import com.example.vo.ModelView;
import com.example.vo.Models;
import com.example.vo.User;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2022/11/26 17:20:00
 */
public class OptionalApi {
	public static final Logger log = LoggerFactory.getLogger(OptionalApi.class);

	static {
		// 默认情况下，SLF4J 的日志级别是 INFO。强制设置日志级别为 DEBUG（适用于 Logback）
		((ch.qos.logback.classic.Logger) log).setLevel(ch.qos.logback.classic.Level.DEBUG);
	}

	@Test
	public void test1() {
		User user = new User();
		Models models = null;
		ModelView modelView = null;
		// 为了避免空指针异常NullPointerException，我们要想获取底层的值就必须要就行判空处理，以至于多层的嵌套if/else(不优雅啊！)
		if (user != null) {
			models = user.getModels();
			if (models != null) {
				modelView = models.getModelView();
				if (modelView != null) {
					log.debug(modelView.getAddress());
				} else {
					log.debug("ModelView为空");
				}
			} else {
				log.debug("Models为空");
			}
		} else {
			log.debug("User为空");
		}
	}

	@Test
	public void test2() {
		// TODO 使用optional来处理空指针(简约)
		User user = new User();
		// 通过 Optional.ofNullable() 方法将 user 包装成 Optional 对象。
		// 如果 user 为 null，后续直接跳到 orElseGet()
		// 如果 user 不为 null，继续调用 map(User::getModels)方法，并返回新的 Optional。
		String stringOptional = Optional.ofNullable(user)
		                                .map(User::getModels)
		                                .map(Models::getModelView)
		                                .map(ModelView::getAddress)
		                                .orElseGet(() -> "空值");
		//      .orElse("空值"); 这里要不为空值的话，最后应该返回的地址address的值
		log.debug(stringOptional);

		// TODO orElse()与orElseGet()的区别(orElseGet的性能高，建议使用)
		//      orElse()    立即计算默认值
		//      orElseGet() 仅当值为 null 时计算默认值，比较节约资源提高性能（惰性）
		User user1 = null;
		User one = Optional.ofNullable(user1)
		                   .orElse(new User(1L, "one", "qwer@163.com", 25));
		User two = Optional.ofNullable(user1)
		                   .orElseGet(() -> new User(1L, "two", "qwer@163.com", 25));
		log.info(one.toString());
	}

	@Test
	public void test3() {
		User user3 = new User(null, null, null, 0, null, null, Optional.of(new Models(null, Optional.of(new ModelView("湖北武汉洪山区")))));

		// TODO map() 与 flatMap() 方法的区别
		//      map()：像“翻译器”，直接转换内容，但保留外包装。
		//      flatMap()：像“拆箱器”，先拆开一层包装，再转换。⚠️flatMap 的参数必须返回为 Optional或者 Stream
		Optional<Integer> mapOptional = Optional.of(user3).map(u -> u.getAge());
		Optional<Models> flatOptional = Optional.of(user3).flatMap(u -> u.getModelsOptional());
		Optional<Long> optional = Optional.of(user3).flatMap(u -> Optional.of(u.getId()));

		String stringOptionalFlatMap = Optional.ofNullable(user3)
		                                       .flatMap(User::getModelsOptional)
		                                       .flatMap(Models::getModelViewOptional)
		                                       .map(ModelView::getAddress)
		                                       .orElse("空值");
		log.debug(stringOptionalFlatMap);

		// TODO 需要注意的点:flatMap中的内容不能直接为null，或者Optional.of(null),而要使用Optional.empty()或者Optional.of(new Models())
		String name = Optional.ofNullable(user3).map(User::getName).orElse("name为空值");
		System.out.println(name);
		User user4 = new User(null, null, null, 0, null, null
				, Optional.of(new Models()));
		Models orElse = Optional.ofNullable(user4)
		                        .flatMap(User::getModelsOptional)
		                        .orElse(new Models(new ModelView("大大"), null));
		System.out.println(orElse);
	}

	@Test
	public void test4() {
		// TODO 判断是否有值
		User user4 = null;
		boolean present = Optional.ofNullable(user4).isPresent();
		log.debug(String.valueOf(present));

		// TODO 判断是否有值，如果值存在则执行块。注意：这里是if不是is
		User user5 = new User("ysc", 25);
		Optional.ofNullable(user5).ifPresent(u -> log.debug(u.toString()));

		// TODO 使用Optional与Stream来取代if判空逻辑
		Optional.ofNullable(user4)
		        .map(Collections::singletonList)
		        .orElseGet(() -> Collections.singletonList(new User(null, null, null, 0, null, null, Optional.of(new Models()))))
		        .stream().filter(Objects::nonNull).forEach(System.out::println);

		// TODO 为空返回一个异常
		Optional.ofNullable(user4).orElseThrow(() -> new NullPointerException("从未见过有如此厚颜无耻之人！--曹家千金"));
	}
}
