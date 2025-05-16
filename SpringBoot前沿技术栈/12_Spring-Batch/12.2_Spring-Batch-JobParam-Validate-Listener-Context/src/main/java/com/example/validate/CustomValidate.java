package com.example.validate;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-27 15:10
 * @apiNote TODO 定制参数校验器
 */
public class CustomValidate implements JobParametersValidator {

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {

		assert parameters != null;
		String parametersString = parameters.getLocalDateTime("name").toString();

		if (!StringUtils.hasText(parametersString)) {
			throw new JobParametersInvalidException("批处理name参数不能为null或者空");
		} else if (parametersString.equals("游家纨绔")) {
			throw new JobParametersInvalidException("批处理name参数不合法");
		}
	}
}
