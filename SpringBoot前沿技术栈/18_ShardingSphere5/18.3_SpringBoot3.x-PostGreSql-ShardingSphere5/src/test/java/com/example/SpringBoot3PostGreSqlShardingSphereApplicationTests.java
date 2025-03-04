package com.example;

import com.example.entity.EceUser;
import com.example.mapper.EceUserMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot3PostGreSqlShardingSphereApplicationTests {

    @Autowired
    private EceUserMapper eceUserMapper;

    @Test
    void contextLoads() throws ParseException {
        String data = "2024-09-16 10:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseData = format.parse(data);
        eceUserMapper.insert(
                EceUser.builder()
                        .eceId(123456)
                        .userCode("ECE-RPT")
                        .userName("李四")
                        .passWord("123456")
                        .email("12345678@qq.com")
                        .phone("13888888867")
                        .birthday(parseData)
                        .age(22)
                        .sex("女")
                        .address("湖北武汉洪山区")
                        .status("ACTIVE")
                        .build()
        );
    }

    @Test
    void contextLoads2() {
        // 使用 PostgreSql 来进行数据分片，有个注意点：public架构中必须要创建分片的表(可以没数据，但必须要建表)
        System.out.println(eceUserMapper.queryById(76));
    }
}
