package com.example.function;

import com.example.utils.FunctionUtil;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-17 07:43
 * @apiNote TODO
 */
public class FunctionExample {

    public static void main(String[] args) {
        // FunctionUtil.isTrue(true).throwMessage("this a err1");
        FunctionUtil.isTrue(false).throwMessage("this a err2");

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
    }
}
