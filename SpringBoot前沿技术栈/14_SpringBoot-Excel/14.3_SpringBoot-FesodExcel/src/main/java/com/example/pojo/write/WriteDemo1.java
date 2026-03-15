package com.example.pojo.write;

import lombok.Data;
import org.apache.fesod.sheet.annotation.ExcelIgnore;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.apache.fesod.sheet.annotation.write.style.ColumnWidth;
import org.apache.fesod.sheet.annotation.write.style.ContentLoopMerge;
import org.apache.fesod.sheet.annotation.write.style.ContentRowHeight;
import org.apache.fesod.sheet.annotation.write.style.HeadRowHeight;

import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2026-03-15 08:00
 * @apiNote TODO
 */
@Data
// 内容行高
@ContentRowHeight(100)
// 表头行高
@HeadRowHeight(20)
// 全部列宽
@ColumnWidth(100 / 8)
// 将第6-7行的2-3列合并成一个单元格
// @OnceAbsoluteMerge(firstRowIndex = 5, lastRowIndex = 6, firstColumnIndex = 1, lastColumnIndex = 2)
public class WriteDemo1 {

    // 这一列 每隔2行 合并单元格
    @ContentLoopMerge(eachRow = 2)
    // 单个列宽
    @ColumnWidth(50)
    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;

    @ExcelProperty("数字标题")
    private Double doubleData;

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
