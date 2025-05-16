package com.example.优化IF;

import com.example.优化IF.策略模式.Context;
import com.example.优化IF.策略模式.RedPaper;
import com.example.优化IF.策略模式.Shopping;
import com.example.优化IF.策略模式加函数式接口.QueryGrantTypeService;
import com.example.优化IF.策略模式加工厂单例模式.VipRechargeFactory;
import com.example.优化IF.策略模式加工厂单例模式.VipRechargeStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 12:10:00
 * @apiNote TODO
 */
@SuppressWarnings("ALL")
@Component
public class Example implements CommandLineRunner {
	private static final Log logger = LogFactory.getLog(Example.class);

	@Autowired
	private QueryGrantTypeService queryGrantTypeService;

	// 使用@Value注解需要注意：获取后缀properties配置文件中文数据会乱码；而yml/yaml配置文件中文数据则不会
	// application.properties文件被设置以ISO-8859-1的编码格式进行加载。而yml/yaml文件默认以UTF-8加载
	@Value("${spring.strategy.ormap}")
	private String resourceType;

	@Value("${spring.strategy.factory.singleton}")
	private String rechargeType;

	@Override
	public void run(String... args) throws Exception {

		// 存在冗余问题：正常if判断。不方便修改，可维护性低。
		switch (resourceType) {
			case "红包":
				logger.info("查询红包的派发方式");
				// 查询红包的处理逻辑
				break;
			case "购物券":
				logger.info("查询购物券的派发方式");
				// 查询购物券的处理逻辑
				break;
			// ......
			default:
				logger.info("查找不到该优惠券类型resourceType以及对应的派发方式");
				break;
		}


		// 方法一：策略模式
		//        可维护性会好不少
		switch (resourceType) {
			case "红包":
				String grantType = new Context(new RedPaper()).ContextInterface();
				logger.info("查询" + resourceType + "的派发方式为：" + grantType);
				break;
			case "购物券":
				String shoppingType = new Context(new Shopping()).ContextInterface();
				logger.info("查询" + resourceType + "的派发方式为：" + shoppingType);
				break;
			// ......
			default:
				logger.info("查找不到该优惠券类型resourceType以及对应的派发方式");
				break;
		}


		// 方法二：策略模式加工厂单例模式
		//        策略模式通过接口、实现类、逻辑分派来完成，把 if语句块的逻辑抽出来写成一个类，更好维护。
		//        工厂单例模式便于获取业务列表，避免多次加载业务数据，即static代码块
		VipRechargeStrategy strategy = VipRechargeFactory.getInstance().getConcurrentStrategy(rechargeType);
		if (strategy == null)
			// 策略不符合，及时中断
			return;
		strategy.recharge(rechargeType);


		// 方法三：策略模式加函数式接口
		//        策略模式通过接口、实现类、逻辑分派来完成，把 if语句块的逻辑抽出来写成一个类，更好维护。
		//        Map+函数式接口通过Map.get(key)来代替 if-else的业务分派，避免策略模式带来的类增多、难以俯视整个业务逻辑的问题。
		String result = queryGrantTypeService.getResult(resourceType);
		logger.info("查询" + resourceType + "的派发方式为：" + result);
	}
}
