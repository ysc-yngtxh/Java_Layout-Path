package com.example.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class GenderDataDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (ObjectUtils.isEmpty(jsonParser)) {
            return null;
        }
        int gender = 0;
        switch (jsonParser.getText()) { // 通过getText获取参数
            case "男" -> gender = 1;
            case "女" -> {
            }
            default -> throw new RuntimeException("传入的性别为非法字符");
        }

        return gender;
    }
}