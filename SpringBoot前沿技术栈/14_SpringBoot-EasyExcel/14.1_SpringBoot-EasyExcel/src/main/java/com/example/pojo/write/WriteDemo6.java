package com.example.pojo.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 18:40
 * @apiNote TODO
 */
@Data
// 头背景设置成红色 IndexedColors.RED.getIndex()
@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 10)
// 头字体设置成20
@HeadFontStyle(fontHeightInPoints = 20)
// 内容的背景设置成绿色 IndexedColors.GREEN.getIndex()
@ContentStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 17)
// 内容字体设置成20
@ContentFontStyle(fontHeightInPoints = 20)
public class WriteDemo6 {

	// 字符串的头背景设置成粉红 IndexedColors.PINK.getIndex()
	@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 14)
	// 字符串的头字体设置成20
	@HeadFontStyle(fontHeightInPoints = 30)
	// 字符串的内容的背景设置成天蓝 IndexedColors.SKY_BLUE.getIndex()
	@ContentStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 40)
	// 字符串的内容字体设置成20
	@ContentFontStyle(fontHeightInPoints = 30)
	@ExcelProperty("字符串标题")
	private String string;

	@ExcelProperty("日期标题")
	private Date date;

	@ExcelProperty("数字标题")
	private Double doubleData;
}
