package com.example.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 16:13
 * @apiNote TODO
 */
@Getter
@AllArgsConstructor
public class SqlException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 828553759112823740L;

    private SqlEnum sqlEnum;
}
