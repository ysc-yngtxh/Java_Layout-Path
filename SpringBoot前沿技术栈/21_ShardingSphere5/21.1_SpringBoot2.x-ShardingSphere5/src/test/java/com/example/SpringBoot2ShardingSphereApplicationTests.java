package com.example;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot2ShardingSphereApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() throws ParseException {
        String data = "2024-09-16 10:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseData = format.parse(data);
        userMapper.insert(
                User.builder()
                        .username("李四")
                        .birthday(parseData)
                        .sex("女")
                        .address("湖北武汉洪山区")
                        .build()
        );
    }

    @Test
    void contextLoads2() {
        System.out.println(userMapper.queryById(1));
    }

}
