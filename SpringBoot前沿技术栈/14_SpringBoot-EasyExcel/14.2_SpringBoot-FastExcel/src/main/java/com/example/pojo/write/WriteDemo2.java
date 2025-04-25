package com.example.pojo.write;

import cn.idev.excel.annotation.ExcelProperty;
import java.util.Date;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 15:46
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
