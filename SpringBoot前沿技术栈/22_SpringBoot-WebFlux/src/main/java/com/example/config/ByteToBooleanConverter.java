package com.example.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@ReadingConverter
@Component
public class ByteToBooleanConverter implements Converter<Byte, Boolean> {
    @Override
    public Boolean convert(Byte source) {
        return source != null && source == 1;
    }
}

@WritingConverter
@Component
class BooleanToByteConverter implements Converter<Boolean, Byte> {
    @Override
    public Byte convert(Boolean source) {
        return source != null && source ? (byte) 1 : (byte) 0;
    }
}
