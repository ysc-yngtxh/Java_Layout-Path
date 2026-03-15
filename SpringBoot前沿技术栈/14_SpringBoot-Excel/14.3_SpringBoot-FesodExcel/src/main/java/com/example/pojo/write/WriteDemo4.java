package com.example.pojo.write;

import lombok.Data;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.apache.fesod.sheet.annotation.write.style.ColumnWidth;
import org.apache.fesod.sheet.annotation.write.style.ContentRowHeight;
import org.apache.fesod.sheet.converters.string.StringImageConverter;
import org.apache.fesod.sheet.metadata.data.WriteCellData;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @author 游家纨绔
 * @dateTime 2026-03-15 08:00
 * @apiNote TODO
 */
@Data
@ContentRowHeight(100)
@ColumnWidth(15)
public class WriteDemo4 {

    private File file;

    private InputStream inputStream;

    /**
     * 如果string类型 必须指定转换器，string默认转换成string
     */
    @ExcelProperty(converter = StringImageConverter.class)
    private String string;

    private byte[] byteArray;

    /**
     * 根据url导出
     */
    private URL url;

    /**
     * 根据文件导出 并设置导出的位置
     */
    private WriteCellData<Void> writeCellDataFile;
}
