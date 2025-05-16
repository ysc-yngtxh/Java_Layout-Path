package com.example.vo;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-29 14:40:00
 * @apiNote TODO 返回类
 */
public class ResponseUtils<T> {

	private T obj;
	private Integer code;

	public ResponseUtils(T obj, Integer code) {
		this.obj = obj;
		this.code = code;
	}

	public static <T> ResponseUtils<T> success(T obj) {
		return new ResponseUtils<>(obj, 200);
	}
}
