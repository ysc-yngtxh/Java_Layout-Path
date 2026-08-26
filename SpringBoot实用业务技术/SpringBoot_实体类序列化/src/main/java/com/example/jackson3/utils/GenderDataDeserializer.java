package com.example.jackson3.utils;

import org.springframework.util.ObjectUtils;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

@SuppressWarnings("rawtypes")
public class GenderDataDeserializer extends ValueDeserializer {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws JacksonException {
        if (ObjectUtils.isEmpty(jsonParser)) {
            return null;
        }
        int gender = 0;
        switch (jsonParser.getString()) { // 通过getString获取参数
            case "男" -> gender = 1;
            case "女" -> {
            }
            default -> throw new RuntimeException("传入的性别为非法字符");
        }

        return gender;
    }
}
