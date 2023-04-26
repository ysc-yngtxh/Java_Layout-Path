package com.cn.leyou.exception;

import com.cn.leyou.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LyException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

}
