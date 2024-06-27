package com.example;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringBootPDFApplicationTests {

    @SneakyThrows
    @Test
    void contextLoads() {
        // 针对中文的支持，必须创建支持中文字符的基础字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font docFont = new Font(bfChinese, 10, Font.UNDEFINED, Color.BLACK);

        byte[] byteArray = new byte[0];
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("logo.png")) {
            if (inputStream != null) {
                byteArray = new byte[inputStream.available()];
                inputStream.read(byteArray);
            }
        } catch (IOException e) {
            log.warn("load logo.png failed", e);
        }
        Image image = Image.getInstance(byteArray);
        image.scaleAbsolute(73, 19); // 对图片进行缩放
    }

    @Test
    @SneakyThrows
    public void test() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// 输出到客户段的流
        Document document = new Document(); // 创建document对象
        PdfWriter.getInstance(document, byteArrayOutputStream);// 创建书写器
        document.open(); // 打开文档

        // 创建一个对中文字符集支持的基础字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font titleFont = new Font(bfChinese, 16, Font.BOLD);
        Font docFont = new Font(bfChinese, 10, Font.UNDEFINED, Color.BLACK);
        Font docFont2 = new Font(bfChinese, 10, Font.UNDEFINED, Color.GRAY);
        Font headFont = new Font(bfChinese, 12, Font.BOLD, Color.WHITE);

        PdfPTable topTable = new PdfPTable(3);
        topTable.setWidthPercentage(100f);
        topTable.setSpacingAfter(20f);
        Paragraph paragraph = new Paragraph("寄件服务明细", titleFont);
        PdfPCell cell = new PdfPCell(paragraph); // 建立一个单元格
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT); // 设置内容水平居中显示
        topTable.addCell(cell);

        document.add(topTable);

        document.close();// 关闭文档
    }

}
