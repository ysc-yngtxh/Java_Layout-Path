package com.example.jackson3.utils;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-20 20:00
 * @apiNote TODO 日期序列化工具, 直接将时间类型的转为yyyy-MM-dd类型的数据
 */
public class CustomDateSerialize extends ValueSerializer<Date> {

	// 定义日期格式
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date value, JsonGenerator jsonGenerator, SerializationContext ctxt) throws JacksonException {
        jsonGenerator.writeString(simpleDateFormat.format(value));
    }
}
