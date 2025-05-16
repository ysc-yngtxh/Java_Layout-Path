package com.example.函数式接口.utils;

import com.example.函数式接口.interfaces.BranchHandler;
import com.example.函数式接口.interfaces.PresentOrElseHandler;
import com.example.函数式接口.interfaces.ThrowExceptionFunction;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 09:40:00
 * @apiNote TODO
 */
public class FunctionUtil {

	public static BranchHandler isTrueOrFalse(boolean b) {
		return (trueHandler, falseHandler) -> {
			if (b) {
				trueHandler.run();
			} else {
				falseHandler.run();
			}
		};
	}

	public static PresentOrElseHandler<?> isPresent(String obj) {
		return (action, falseHandler) -> {
			if (obj != null) {
				action.accept(obj);
			} else {
				falseHandler.run();
			}
		};
	}

	public static ThrowExceptionFunction isTrue(boolean b) {
		return (message) -> {
			if (b) {
				throw new RuntimeException(message);
			}
		};
	}
}
