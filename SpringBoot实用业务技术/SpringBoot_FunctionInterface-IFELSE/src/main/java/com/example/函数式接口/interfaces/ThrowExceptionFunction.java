package com.example.函数式接口.interfaces;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 09:51
 * @apiNote TODO @FunctionalInterface下只能声明一个抽象方法，多一个、少一个都不能编译通过。
 *               覆写Object中 toString/equals 的方法不受此个数限制。
 *               default方法和static方法因为带有实现体，所有也不受此限制。
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

    void throwMessage(String message);
}
