package com.example.pojo.read;

import cn.idev.excel.annotation.ExcelProperty;
import java.util.Date;
import lombok.Data;

@Data
public class ReadDemo3 {

    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;

    @ExcelProperty("数字标题")
    private Double doubleData;

    @ExcelProperty("公式")
    private String formulaValue;
}
