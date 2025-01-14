package com.example.utill;

import java.time.LocalDateTime;

public class ServiceTools {

    // 这相当于日志效果
    public static void doLog(){
        System.out.println("非业务方法，方法的执行时间:" + LocalDateTime.now());
    }

    public static void doTrans(){
        System.out.println("非业务方法，方法执行完毕后，提交事务");
    }
}
