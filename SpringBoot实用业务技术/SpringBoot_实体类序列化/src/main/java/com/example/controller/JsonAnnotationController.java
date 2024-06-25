package com.example.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.entity.Consumer1;
import com.example.entity.Consumer2;
import com.example.entity.Consumer3;
import com.example.entity.Supplier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-19 17:45
 * @apiNote TODO
 */
@RestController
public class JsonAnnotationController {

    @RequestMapping("/consumer1")
    public Consumer1 test1(@RequestBody(required = false) Consumer1 param) {

        System.out.println("参数转成对象" + param);

        // TODO jdk17 之前定义一个 JSON 字符串：1、双引号需要进行转义；2、为了字符串的可读性需要通过+号连接；

        // 反序列化
        // 通过Java 17中的文本块语法，类似的字符串处理则会方便很多；
        // 通过三个双引号可以定义一个文本块，并且结束的三个双引号不能和开始的在同一行。
        String jsonString = """
                {
                	"consumerId": 1,
                	"username": "",
                	"password": " ",
                	"alias": "小曹啊小曹",
                	"age": 22,
                	"sex": null,
                	"phone": null,
                	"address": "山西太原",
                	"deleteFlag": 0,
                	"date": "2024-03-25 18:34:07.582",
                	"price": 0.568,
                	"optional": null,
                	"atomicReference": null,
                	"supplier": {
                	    "id": "1"
                	},
                	"customer": "2023-08-20"
                }
                """;
        Consumer1 parseObject = JSONObject.parseObject(jsonString, Consumer1.class);
        System.out.println("反序列化: " + parseObject);

        // 序列化
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
                .optional( Optional.empty() )
                .atomicReference( new AtomicReference<>() )
                .supplier( Supplier.builder().id(1).build() )
                .customer( new Date() )
                .build();
        System.out.println("序列化: " + JSON.toJSONString(consumer1));
        return consumer1;
    }

    @RequestMapping("/consumer2")
    public Consumer2 test2() {
        // 序列化
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
                .optional( Optional.empty() )
                .atomicReference( new AtomicReference<>() )
                .supplier( Supplier.builder().id(1).build() )
                .customer( new Date() )
                .build();
        System.out.println("序列化: " + JSON.toJSONString(consumer2));
        return consumer2;
    }

    @RequestMapping("/consumer3")
    public Consumer3 test3(@RequestBody(required = false) Consumer3 param) throws JsonProcessingException {
        // 反序列化
        String jsonString = """
                {
                	"consumerId": 3,
                	"username": "",
                	"password": " ",
                	"optional": null,
                	"atomicReference": null,
                	"supplier": {
                 		"id": 11
                 	},
                	"gender": "男",
                	"title": "标题",
                	"category": "产品",
                	"propertiesJson": {"id": 1, "user": "ysc"}
                }
                """;
        // 这里使用的是Jackson的非序列化方法
        ObjectMapper objectMapper = new ObjectMapper();
        Consumer3 jacksonParse = objectMapper.readValue(jsonString, Consumer3.class);
        System.out.println("Jackson非序列化：" + jacksonParse);
        // 这里使用的是FastJson的非序列化方法会报错。
        // 因为我们的 gender 属性字段上标注的注解是基于Jackson的，所以需要使用Jackson进行反序列化。
        // 使用FastJson并不会走我们自定义的反序列化逻辑
        Consumer3 fastJsonParse = JSONObject.parseObject(jsonString, Consumer3.class);
        System.out.println("FastJson2非序列化：" + fastJsonParse);

        // 序列化
        Consumer3 consumer3 = Consumer3.builder()
                .id(3)
                .username("")
                .password(" ")
                .optional( Optional.empty() )
                .atomicReference( new AtomicReference<>() )
                .supplier( Supplier.builder().id(1).build() )
                .gender(0)
                .properties( Map.of("title", "标题") )
                .propertiesJson( Map.of("id", "1", "user", "ysc") )
                .build();
        System.out.println("序列化: " + objectMapper.writer().writeValueAsString(consumer3));
        return consumer3;
    }
}
