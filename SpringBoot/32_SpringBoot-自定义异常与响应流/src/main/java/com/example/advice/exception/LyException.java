package com.example.advice.exception;

import com.example.advice.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LyException extends RuntimeException {

	private static final long serialVersionUID = 5660628920800317941L;

	private UserEnum userEnum;
}
