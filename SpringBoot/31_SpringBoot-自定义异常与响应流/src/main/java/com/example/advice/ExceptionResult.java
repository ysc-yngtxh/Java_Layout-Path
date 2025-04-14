package com.example.advice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

import java.text.SimpleDateFormat;

/**
 * @author 游家纨绔
 */
@Data
public class ExceptionResult {

    private int status;
    private String message;
    private String timestamp;

    public ExceptionResult(UserEnum em) {
        this.status = em.getCord();
        this.message = em.getMsg();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(dtf);
    }
}
