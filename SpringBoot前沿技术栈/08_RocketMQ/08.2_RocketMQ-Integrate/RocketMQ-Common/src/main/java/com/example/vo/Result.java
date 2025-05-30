package com.example.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 通用结果返回
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer code;
	private String message;
	private T data;

	public Result(Integer code, String message) {
		this(code, message, null);
	}

	public Result(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static <T> Result<T> success() {
		return new Result<T>(200, "操作成功");
	}

	public static <T> Result<T> success(String message) {
		return new Result<T>(200, message);
	}

	public static <T> Result<T> success(T date) {
		return new Result<T>(200, "操作成功", date);
	}

	public static <T> Result<T> success(String message, T date) {
		return new Result<T>(200, message, date);
	}

	public static <T> Result<T> failed() {
		return new Result<T>(500, "操作失败");
	}

	public static <T> Result<T> failed(Integer code, String message, T date) {
		return new Result<T>(code, message, date);
	}

	public static <T> Result<T> failed(Integer code, String message) {
		return new Result<T>(code, message);
	}
}
