package com.example.函数式接口;

import com.example.函数式接口.utils.FunctionUtil;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 07:43
 * @apiNote TODO
 */
public class FunctionExample {

    public static void main(String[] args) {
        // TODO 这种函数式接口优化IF，实际上并没有消除，只是封装起来显得更优雅
        FunctionUtil.isTrueOrFalse(true).trueOrFalseHandler(
                () -> {
                    System.out.println("this is true");
                },
                () -> {
                    System.out.println("this is false");
                }
        );

        FunctionUtil.isPresent(null).presentOrElseHandler(
                System.out::println,
                () -> {
                    System.out.println("空字符串");
                }
        );

        // FunctionUtil.isTrue(true).throwMessage("this a err1");
        FunctionUtil.isTrue(false).throwMessage("this a err2");
    }
}
