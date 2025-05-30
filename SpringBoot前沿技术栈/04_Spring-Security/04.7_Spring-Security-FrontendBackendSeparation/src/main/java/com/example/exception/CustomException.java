package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-08 16:30
 * @apiNote TODO 自定义异常
 */
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	@Serial
    private static final long serialVersionUID = -1538611574772578652L;

	private EnumResponse enumResponse;
}
