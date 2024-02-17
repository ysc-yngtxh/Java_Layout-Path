package com.example.函数式接口.interfaces;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 09:58
 * @apiNote TODO @FunctionalInterface下只能声明一个抽象方法，多一个、少一个都不能编译通过。
 *               覆写Object中 toString/equals 的方法不受此个数限制。
 *               default方法和static方法因为带有实现体，所有也不受此限制。
 */
@FunctionalInterface
public interface BranchHandler {
    // Runnable也可看作是第五大函数区别于Function、Predicate、Supplier、Consumer：不需要出入参
    void trueOrFalseHandler(Runnable trueHandler, Runnable falseHandler);
}
