package com.example.pojo.read;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.example.converter.CustomStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-15 08:00
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadDemo2 {

    // 映射 Excel 的头标题
    @ExcelProperty("Id")
    private Integer id;

    // 映射 Excel 的头标题
    @ExcelProperty("用户名")
    private String name;

    // 映射 Excel 的头标题
    @ExcelProperty("价格")
    private double price;

    // 忽略这个字段注解
    @ExcelIgnore
    private Integer age;

    // 强制读取第四个 这里不建议 index 和 value 同时用，要么一个对象只用index，要么一个对象只用value去匹配
    @ExcelProperty(index = 3)
    private Double doubleData;

    // 我自定义转换器，不管Excel传过来什么 。我给他加上 "自定义："
    @ExcelProperty(value = "转换", converter = CustomStringConverter.class)
    private String string;

    // 这里用string 去接日期才能格式化年月日
    @ExcelProperty("日期")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private String date;

    // 我想接收保留小数点后两位的百分比的数字
    @ExcelProperty("百分比")
    @NumberFormat("#.##%")
    private String percent;
}
