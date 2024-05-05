package com.example.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LyException extends RuntimeException{

    private UserEnum userEnum;
}
