package com.example.pojo.write;

import cn.idev.excel.metadata.data.WriteCellData;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 17:48
 * @apiNote TODO
 */

@Data
public class WriteDemo5 {

    /**
     * 超链接
     */
    private WriteCellData<String> hyperlink;

    /**
     * 备注
     */
    private WriteCellData<String> commentData;

    /**
     * 公式
     */
    private WriteCellData<String> formulaData;

    /**
     * 指定单元格的样式。当然样式 也可以用注解等方式。
     */
    private WriteCellData<String> writeCellStyle;

    /**
     * 指定一个单元格有多个样式
     */
    private WriteCellData<String> richText;
}
