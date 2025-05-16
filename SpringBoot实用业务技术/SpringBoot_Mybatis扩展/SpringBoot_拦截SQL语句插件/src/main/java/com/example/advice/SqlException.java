package com.example.advice;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 16:00:00
 * @apiNote TODO
 */
@Getter
@AllArgsConstructor
public class SqlException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 828553759112823740L;

	private SqlEnum sqlEnum;
}
