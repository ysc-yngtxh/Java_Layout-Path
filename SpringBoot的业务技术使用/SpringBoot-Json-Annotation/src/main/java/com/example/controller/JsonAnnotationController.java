package com.example.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.entity.Consumer1;
import com.example.entity.Consumer2;
import com.example.entity.Consumer3;
import com.example.entity.Supplier;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-19 17:45
 * @apiNote TODO
 */
@RestController
public class JsonAnnotationController {

    @RequestMapping("/consumer1")
    public Consumer1 test1() {
        Consumer1 consumer1 = Consumer1.builder()
                .id(1)
                .username("")
                .password(" ")
                .alias("小曹啊小曹")
                .sex(null)
                .age(22)
                .phone(null)
                .address("山西太原")
                .deleteFlag(0)
                .date(new Date())
                .price(0.568)
                .optional(Optional.empty())
                .atomicReference(new AtomicReference<>())
                .supplier( Supplier.builder().id(1).build() )
                .build();
        System.out.println(JSON.toJSONString(consumer1));
        return consumer1;
    }

    @RequestMapping("/consumer2")
    public Consumer2 test2() {
        Consumer2 consumer2 = Consumer2.builder()
                .id(2)
                .username("")
                .password(" ")
                .alias("我的宝啊宝")
                .sex(null)
                // .age(22)
                .phone(null)
                .address("山西太原")
                // .deleteFlag(0L)
                .date(new Date())
                // .price(0.568)
                .optional(Optional.empty())
                .atomicReference(new AtomicReference<>())
                .supplier( Supplier.builder().id(1).build() )
                .build();
        System.out.println(JSON.toJSONString(consumer2));
        return consumer2;
    }

    @RequestMapping("/consumer3")
    public Consumer3 test3() {
        Consumer3 consumer3 = Consumer3.builder()
                .id(3)
                .username("")
                .password(" ")
                .alias("敲你脑阔啊敲")
                .sex(null)
                .age(22)
                .phone(null)
                .address("四川成都")
                .deleteFlag(null)
                .date(new Date())
                .price(0.568)
                .optional(Optional.empty())
                .atomicReference(new AtomicReference<>())
                .supplier( Supplier.builder().id(1).build() )
                .build();
        return consumer3;
    }
}
