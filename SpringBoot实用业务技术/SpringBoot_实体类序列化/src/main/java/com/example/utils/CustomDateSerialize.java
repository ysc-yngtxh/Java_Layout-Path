package com.example.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-20 20:00
 * @apiNote TODO 日期序列化工具, 直接将时间类型的转为yyyy-MM-dd类型的数据
 */
public class CustomDateSerialize extends JsonSerializer<Date> {

    // 定义日期格式
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(simpleDateFormat.format(date));
    }
}
