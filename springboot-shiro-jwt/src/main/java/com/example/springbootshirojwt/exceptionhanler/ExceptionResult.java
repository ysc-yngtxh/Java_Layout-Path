package com.example.springbootshirojwt.exceptionhanler;

import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class ExceptionResult {

    private int status;
    private String message;
    private String timestamp;

    public ExceptionResult(ExceptionEnum em){

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        long l = System.currentTimeMillis();

        this.status = em.getCord();
        this.message = em.getMsg();

        String time = sf.format(l);
        this.timestamp = time;

    }
}
