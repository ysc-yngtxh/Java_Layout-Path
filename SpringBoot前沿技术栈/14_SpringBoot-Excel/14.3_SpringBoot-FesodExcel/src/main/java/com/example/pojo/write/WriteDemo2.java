package com.example.pojo.write;

import lombok.Data;
import org.apache.fesod.sheet.annotation.ExcelProperty;

import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2026-03-15 08:00
 * @apiNote TODO
 */
@Data
public class WriteDemo2 {

    @ExcelProperty({"主标题", "字符串标题"})
    private String string;

    @ExcelProperty({"主标题", "日期标题"})
    private Date date;

    @ExcelProperty({"主标题", "数字标题"})
    private Double doubleData;
}
