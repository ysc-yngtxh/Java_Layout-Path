package com.example.validator;

import com.example.annotation.IsLeek;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

/**
 * 自定义校验器
 *
 * @author 游家纨绔
 * @since 2024-08-03 16:30:00
 */
public class IsLeekValidator implements ConstraintValidator<IsLeek, String> {

	// 是否强制校验
	private boolean required;

	// 初始化
	@Override
	public void initialize(IsLeek constraintAnnotation) {
		this.required = constraintAnnotation.required();
	}

	// 校验
	@Override
	public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
		if (required) {
			// 名字以"新韭菜"开头的则校验通过
			return !StringUtils.hasText(name) && name.startsWith("新韭菜");
		}
		return false;
	}
}
