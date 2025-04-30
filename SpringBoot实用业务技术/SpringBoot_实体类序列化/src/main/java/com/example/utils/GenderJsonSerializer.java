package com.example.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderJsonSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if ( o.equals(1) ) {
            jsonGenerator.writeString("男"); // 写出参数
        } else if ( o.equals(0) ) {
            jsonGenerator.writeString("女"); // 写出参数
        } else {
            throw new RuntimeException("无法识别出序列化性别的对应数据");
        }
    }
}
