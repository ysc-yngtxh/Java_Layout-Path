package com.example.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @date 2022-07-05 12:00
 * @apiNote
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 提示信息，如果有错误时，前端可以获取该字段进行提示
	 */
	private String msg;

	/**
	 * 返回的数据
	 */
	private T data;

	public ResponseResult<T> success(String msg, T data) {
		return new ResponseResult<T>(null, msg, data);
	}

	public ResponseResult<T> failure(Integer code, String msg) {
		return new ResponseResult<T>(400, msg, null);
	}
}
