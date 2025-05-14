package com.example.convert;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-05-15 01:15:30
 */
public class CustomConvert implements Converter<String, List<FlowRule>> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	// 根据规则类型决定如何转换（可以返回 FlowRule, DegradeRule, SystemRule 等）
	@Override
	public List<FlowRule> convert(String source) {
		try {
			// 这里实现你的自定义转换逻辑
			List<FlowRule> rules = objectMapper.readValue(source, objectMapper.getTypeFactory().constructCollectionType(List.class, FlowRule.class));
			// 添加校验
			for (FlowRule rule : rules) {
				if (rule.getResource() == null) {
					throw new IllegalArgumentException("Resource cannot be null");
				}
			}
			return rules;
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to parse flow rules", e);
		}
	}
}
