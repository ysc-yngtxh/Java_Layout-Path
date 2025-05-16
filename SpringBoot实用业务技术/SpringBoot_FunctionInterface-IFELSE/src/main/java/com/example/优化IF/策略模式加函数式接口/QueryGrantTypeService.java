package com.example.优化IF.策略模式加函数式接口;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryGrantTypeService {

	@Autowired
	private GrantTypeService grantTypeService;

	private Map<String, Function<String, String>> grantTypeMap = new HashMap<>();

	/**
	 * 初始化业务分派逻辑,代替了if-else部分
	 * key: 优惠券类型
	 * value: lambda表达式,最终会获得该优惠券的发放方式
	 */
	@PostConstruct
	public void dispatcherInit() {
		grantTypeMap.put("红包", (resourceId) -> grantTypeService.redPaper(resourceId));
		grantTypeMap.put("购物券", (resourceId) -> grantTypeService.shopping(resourceId));
		grantTypeMap.put("qq会员", (resourceId) -> grantTypeService.QQVip(resourceId));
	}

	public String getResult(String resourceType) {
		Function<String, String> result = grantTypeMap.get(resourceType);
		if (result != null) {
			return result.apply(resourceType);
		}
		return "查询不到该优惠券的发放方式";
	}
}
