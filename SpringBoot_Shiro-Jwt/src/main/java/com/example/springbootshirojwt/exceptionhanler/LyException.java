package com.example.springbootshirojwt.exceptionhanler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LyException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

}
