package com.example.jackson3.utils;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class GenderJsonSerializer extends ValueSerializer<Integer> {

    @Override
    public void serialize(Integer value, JsonGenerator jsonGenerator, SerializationContext ctxt) throws JacksonException {
        if (value.equals(1)) {
            jsonGenerator.writeString("男"); // 写出参数
        } else if (value.equals(0)) {
            jsonGenerator.writeString("女"); // 写出参数
        } else {
            throw new RuntimeException("无法识别出序列化性别的对应数据");
        }
    }
}
