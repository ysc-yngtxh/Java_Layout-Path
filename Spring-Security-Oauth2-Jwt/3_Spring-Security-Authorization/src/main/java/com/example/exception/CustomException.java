package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

/**
 * @author youshicheng
 * @dateTime 2023-05-08 16:31
 * @apiNote TODO 自定义异常
 */
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{


    private EnumResponse enumResponse;


}
