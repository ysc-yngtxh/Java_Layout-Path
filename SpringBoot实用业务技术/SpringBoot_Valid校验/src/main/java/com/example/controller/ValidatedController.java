package com.example.controller;

import com.example.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import java.util.Collections;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户表(User)表控制层
 *
 * @author 游家纨绔
 * @since 2024-08-03 16:00:00
 */
// @Validated
@RestController
public class ValidatedController {

	// @Validated 是Spring Validation验证框架对参数的验证机制；
	// @Valid 是 jakarta 提供的参数验证机制

	// TODO 校验普通参数
	@RequestMapping("/validated1")
	public List<User> queryByPage1(@RequestParam
	                               @NotNull(message = "名称不能为空")
	                               @Length(message = "名称不能超过个 {max} 字符", max = 10) String name) {
		return Collections.emptyList();
	}


	// TODO 分组校验【Spring的 @Validated 支持分组，而JSR标准的 @Valid 不支持分组】
	//      分组概念：@Validated中定义的分组，表示会在对应的DTO（实体类）中只校验分组的字段。
	//              例如：@Validated({User.Student.class})，表示只校验 User 类中 Student 分组的字段，
	//                   校验字段没有定义到对应的分组将不会进行校验。
	//      注意：没有指定显示分组的被校验字段和校验注解，默认都是 Default 组（即 Default.class）

	// TODO 使用 @Validated 注解，无法实现嵌套校验。因此，User 类中的 UserInfo 属性中的校验规则无法实现
	@RequestMapping("/validated2")
	public List<User> queryByPage2(@RequestBody @Validated({User.Student.class, Default.class}) User user) {
		return Collections.emptyList();
	}


	// TODO 这里使用 @Validated 注解，需配合 @Valid 实现嵌套校验
	@RequestMapping("/validated3")
	public List<User> queryByPage3(@RequestBody @Validated({User.Teacher.class}) User user) {
		return Collections.emptyList();
	}


	// TODO 如果没有全局异常统一配置，则尝试在接口中获取校验信息
	@RequestMapping("/validated4")
	public String queryByPage4(@RequestBody @Validated() User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// bindingResult.getFieldErrors() 字段的错误
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				// fieldError.getField() 绑定失败的字段名
				// fieldError.getDefaultMessage() 默认的错误提示信息
				System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());
			}
			// bindingResult.getAllErrors() 所有错误
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				System.out.println(objectError.getDefaultMessage());
			}
			return "fail";
		}
		return "success";
	}
}
