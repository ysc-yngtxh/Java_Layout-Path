package com.example.utils;


import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfUtil {
    public static void main(String[] args) throws Exception {
        String pdfFile = "F:\\pdf\\demo.pdf";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        document.open();
        setMetaInfo(document);
        setPageInfo(document);

        setCover(document);
        addText(document);
        addImage(document);
        addTable(document);

        document.close();
        writer.close();
    }

    /**
     * 添加表格
     * 表格有两个基本的元素：Table和Cell，其中Table可以认为是一张画布，Cell是放置于其上的拼图。这就是OpenPDF中表格的基本结构
     *
     * @param document pdf文档对象
     */
    private static void addTable(Document document) {
        document.add(new Paragraph("三、生成表格示例", MyFontUtil.SIMSUN_H2));
        // 列数是必需的，行数不那么重要，即便超过3行也可以添加到表格中
        Table table = new Table(5, 3);
        setTableStyle(table);
        table.addCell(createTitleCell("序号"));
        table.addCell(createTitleCell("姓名"));
        table.addCell(createTitleCell("性别"));
        table.addCell(createTitleCell("籍贯"));
        table.addCell(createTitleCell("备注"));
        table.addCell(createDataCell("1"));
        table.addCell(createDataCell("王冰冰"));
        table.addCell(createDataCell("女"));
        table.addCell(createDataCell("吉林省长春市"));
        table.addCell(createDataCell("央视记者"));
        table.addCell(createDataCell("2"));
        table.addCell(createDataCell("庄晓莹"));
        table.addCell(createDataCell("女"));
        table.addCell(createDataCell("福建省三明市"));
        table.addCell(createDataCell("中央电视广播总台国防军事频道编导、记者"));
        table.addCell(createDataCell("3"));
        table.addCell(createDataCell("张三"));
        table.addCell(createDataCell("男"));
        table.addCell(createDataCell("厚大法考"));
        table.addCell(createDataCell("法外狂徒"));
        table.addCell(createDataCell("4"));
        table.addCell(createDataCell("后面是默认填充→"));
        document.add(table);
        Paragraph tableRemark = new Paragraph();
        tableRemark.setFont(MyFontUtil.SIMSUN_REMARK);
        tableRemark.setAlignment(Element.ALIGN_CENTER);
        tableRemark.add("使用openpdf生成表格示例");
        document.add(tableRemark);
    }

    /**
     * 设置表格的数据单元格样式
     *
     * @param text 单元格内容
     */
    private static Cell createDataCell(String text) {
        Cell cell = new Cell(new Paragraph(text, MyFontUtil.SIMSUN_TEXT));
        // 设置水平对齐方式
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐方式
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置单元格边框颜色，同样用四个bit位分别表示上(0001)、下(0010)、左(0100)、右(1000)四个边
        cell.setBorder(Rectangle.BOX);
        // 设置单元格边框颜色。注：经过实测，setBorderColor有效，但是setBorderColorTop/Bottom/Left/Right是无效的，也就是Cell的边框只能设置一个颜色
        cell.setBorderColor(Color.RED);
        // 设置背景色
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        return cell;
    }

    /**
     * 设置表格的标题栏样式
     *
     * @param text 标题
     */
    private static Cell createTitleCell(String text) {
        Cell cell = new Cell(new Paragraph(text, MyFontUtil.SIMSUN_TEXT));
        // 设置水平对齐方式
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐方式
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置单元格边框颜色，同样用四个bit位分别表示上(0001)、下(0010)、左(0100)、右(1000)四个边
        cell.setBorder(Rectangle.BOX);
        // 设置单元格边框颜色。注：经过实测，setBorderColor有效，但是setBorderColorTop/Bottom/Left/Right是无效的，也就是Cell的边框只能设置一个颜色
        cell.setBorderColorLeft(Color.RED);
        // 设置背景色
        cell.setBackgroundColor(Color.GRAY);
        return cell;
    }

    /**
     * 设置表格样式
     *
     * @param table 表格对象
     */
    private static void setTableStyle(Table table) {
        // 表格在页面水平方向的对齐方式
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置表格边框（仅最外层边框为Table的边框，内部边框属于Cell），同样用四个bit位分别表示上(0001)、下(0010)、左(0100)、右(1000)四个边
        table.setBorder(Rectangle.BOX);
        // 表格边框颜色，只有设置了边框（上一步的setBorder）才有效
        table.setBorderColor(Color.BLACK);
        // 设置背景色（可以把Table想象成一个画布，Cell为放置于画布上的拼图，这里设置的是画布的背景色，与Cell的背景色不同）
        table.setBackgroundColor(Color.ORANGE);
        // 设置列宽
        table.setWidths(new int[]{10, 20, 10, 20, 20});
        // Cell之间的间隔
        table.setSpacing(0.5f);
        // 设置该值可以让文字与底部表格线留出一定空间
        table.setPadding(2.5f);
        // 以下两行y一般同时使用：设置默认Cell，然后使用默认Cell填充空的Cell
        table.setDefaultCell(createDataCell("unknow"));
        table.setAutoFillEmptyCells(true);
        // 表格跨页设置，但是好像没什么效果
        table.setTableFitsPage(false);
    }

    /**
     * 添加图片
     *
     * @param document pdf文档对象
     */
    private static void addImage(Document document) throws IOException {
        document.add(new Paragraph("二、生成图片示例", MyFontUtil.SIMSUN_H2));
        Image image = Image.getInstance("F:\\pdf\\addImage.jpg");
        // 重设图片大小
        image.scaleAbsolute(500, 300);
        document.add(image);
    }

    /**
     * 添加文字
     *
     * @param document pdf文档对象
     */
    private static void addText(Document document) {
        /* 可以使用Chapter、ChapterAutoNumber生成段落，但是数字样式单一，建议自定义，直接用程序的方式生成章节号
        ChapterAutoNumber chapter = new ChapterAutoNumber(new Paragraph("一、生成文本示例", MyFontUtil.SIMSUN_H2));
        // 章节编号格式：带点号
        chapter.setNumberStyle(Section.NUMBERSTYLE_DOTTED);
        // 设置章节深度（一级标题）
        chapter.setNumberDepth(1);
        document.add(chapter);*/
        document.add(new Paragraph("一、生成文本示例", MyFontUtil.SIMSUN_H2));
        document.add(new Paragraph("1、整段文字为同一种字体（宋体）", MyFontUtil.SIMSUN_H3));
        Paragraph p1 = new Paragraph();
        p1.setFont(MyFontUtil.SIMSUN_TEXT);
        p1.add("    生成文本可以使用Chunk、Phrase、Paragraph三种类型，三者之间的关系为：Chunk implements Element，TextElementArray extends Element，Phrase implements TextElementArray,Paragraph extends Phrase。");
        Paragraph p2 = new Paragraph();
        p2.setFont(MyFontUtil.SIMSUN_TEXT);
        p2.add("    文本块Chunk是设置字体格式的最小单位，可以设置字体、字体颜色、背景颜色等，写满一行之后自动排列到下一行，Chunk可被包含于Phrase和Paragraph。");
        Paragraph p3 = new Paragraph();
        p3.setFont(MyFontUtil.SIMSUN_TEXT);
        p3.add("    短句Phrase可以包含多个Chunk，默认行间距是字体大小的1.5倍，写满一行之后自动排列到下一行，但Phrase之间并不会自动在末尾添加换行符。");
        Paragraph p4 = new Paragraph();
        p4.setFont(MyFontUtil.SIMSUN_TEXT);
        p4.add("    段落Paragraph与Phrase类似，但间距控制比Phrase好，不会出现两行文字重叠的情况，写满一行之后同样可以自动排列到下一行，Paragraph在自身内容前后都会添加换行符。一般情况下，个人建议使用Paragraph，省去一些排版的麻烦。");
        Paragraph p5 = new Paragraph();
        p5.setFont(MyFontUtil.SIMSUN_TEXT);
        p5.add("    制表符\\t在不同环境不同情况下的长短不一致，包括各类IDE也基本上推荐使用空格替代制表符，所以我们在PDF文档中也使用空格替代制表符。制表符也可以显示的，但经过实测，只有在仅有英文字符且未设置字体时才可以正常显示。测试中也可以看到不同字体中的空格宽度也不一样，测试结果如下：");
        Paragraph p6 = new Paragraph("    [\t\t]->2 tabs!");
        Paragraph p7 = new Paragraph();
        p7.add("    [\t\t\t\t]->4 tabs!");
        Paragraph p8 = new Paragraph("    [\t\t\t\t]->set Font!", MyFontUtil.SIMSUN_TEXT);
        Paragraph p9 = new Paragraph("    [\t\t\t\t]->contains chinese char！");
        Paragraph p10 = new Paragraph("    下面展示的是插入无序列表：", MyFontUtil.SIMSUN_TEXT);
        List underOrderedlist = new List();
        underOrderedlist.add(new ListItem(new Chunk("小兔几", MyFontUtil.SIMSUN_TEXT)));
        underOrderedlist.add(new ListItem(new Phrase("小凶许", MyFontUtil.SIMSUN_TEXT)));
        underOrderedlist.add(new ListItem(new Paragraph("小脑斧", MyFontUtil.SIMSUN_TEXT)));
        underOrderedlist.add(new ListItem(new Paragraph("小西几", MyFontUtil.SIMSUN_TEXT)));
        p10.add(underOrderedlist);
        Paragraph p11 = new Paragraph("    下面展示的是插入有序列表：", MyFontUtil.SIMSUN_TEXT);
        List orderedlist = new List();
        orderedlist.setNumbered(true);
        orderedlist.add(new ListItem(new Chunk("小兔几", MyFontUtil.SIMSUN_TEXT)));
        orderedlist.add(new ListItem(new Phrase("小凶许", MyFontUtil.SIMSUN_TEXT)));
        orderedlist.add(new ListItem(new Paragraph("小脑斧", MyFontUtil.SIMSUN_TEXT)));
        orderedlist.add(new ListItem(new Paragraph("小西几", MyFontUtil.SIMSUN_TEXT)));
        p11.add(orderedlist);

        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(p5);
        document.add(p6);
        document.add(p7);
        document.add(p8);
        document.add(p9);
        document.add(p10);
        document.add(p11);

        document.add(new Paragraph("2、一段文字包含多种字体格式", MyFontUtil.SIMSUN_H3));
        Paragraph p12 = new Paragraph("    ");
        p12.add(new Chunk("这部分是微软雅黑-黑体，18号字体。", MyFontUtil.MSYHBD_H3));
        p12.add(new Chunk("这部分是宋体，10.5号字体。后面开始测试字体：", MyFontUtil.SIMSUN_TEXT));
        // 测试字体
        String[] styles = {"无格式", "加黑", "变斜", "下划线", "删除线"};
        for (int i = 0; i < styles.length; i++) {
            // 要使用不同字体，必须新建一个字体对象，不允许直接修改公共变量
            Font font = new Font(MyFontUtil.MyBaseFonts.SIMSUN, 10.5f);
            font.setColor(Color.RED);
            // 使用四个bit位设置字体格式，类似Linux的文件权限（chmod 147）设置，bit位与字体的对应关系可以查看Font.getStyleValue()方法
            int style = i == 0 ? 0 : 1 << (i - 1);
            font.setStyle(style);
            Chunk chunk = new Chunk();
            chunk.setFont(font);
            chunk.append(style + "-" + styles[i] + "；");
            p12.add(chunk);
        }
        p12.add(new Chunk("后面开始测试背景色和划线：", MyFontUtil.SIMSUN_TEXT));
        Font font = new Font(MyFontUtil.MyBaseFonts.SIMSUN, 10.5f);
        font.setColor(Color.RED);
        font.setStyle(3);
        Chunk chunk = new Chunk();
        chunk.setFont(font);
        chunk.setBackground(Color.YELLOW);
        chunk.setUnderline(0.4f, 6);
        chunk.append("这一段文字为红色、加黑、变斜、黄底，有一条宽度为0.4、垂直坐标（行底部为y轴0坐标）为6的线段。");
        p12.add(chunk);
        Chunk markerPen = new Chunk();
        markerPen.setFont(MyFontUtil.SIMSUN_TEXT);
        // 第一个参数表示线条颜色，第二个参数是线宽，第四个参数是线的y轴坐标，同setUnderline(float, float)，其它参数不知道怎么计算的，实际使用时慢慢试吧
        markerPen.setUnderline(Color.GREEN, 1, 1, 4, 0, 1);
        markerPen.append("这一段文字用荧光笔（绿色圆角宽线）标记。");
        p12.add(markerPen);
        document.add(p12);
    }

    /**
     * 设置封面
     *
     * @param document pdf文档对象
     */
    private static void setCover(Document document) {
        document.add(new Chunk("编号：0123456789", MyFontUtil.MSYH_H3));
        Paragraph fileName = new Paragraph();
        fileName.setFont(MyFontUtil.MSYHBD_H1);
        fileName.setAlignment(Element.ALIGN_CENTER);
        int lineCnt = 19;
        while (lineCnt-- > 0)
            fileName.add(Chunk.NEWLINE);
        fileName.add("使用OpenPDF生成PDF文档\n\n\n");
        Chunk chunk = new Chunk("2023年3月26日", MyFontUtil.MSYH_H3);
        fileName.add(chunk);
        document.add(fileName);
        lineCnt = 19;
        while (lineCnt-- > 0)
            document.add(Chunk.NEWLINE);
        Paragraph subscript = new Paragraph();
        subscript.setFont(MyFontUtil.MSYH_H3);
        subscript.setAlignment(Element.ALIGN_RIGHT);
        subscript.add("本文档使用openpdf生成");
        document.add(subscript);
        document.newPage();
    }

    /**
     * 设置页面大小、边距、页眉、页脚
     *
     * @param document pdf文档对象
     */
    private static void setPageInfo(Document document) {
        // 页面大小
        document.setPageSize(PageSize.A4);

        // 左、右、上、下边距
        document.setMargins(36, 64, 36, 72);

        // 设置页眉，可以使用Phrase、Paragraph
        Paragraph headerParagraph = new Paragraph();
        headerParagraph.setFont(MyFontUtil.SIMSUN_TEXT);
        headerParagraph.add("PDF输出样例");
        // 第二个参数表示是否显示页码
        HeaderFooter header = new HeaderFooter(headerParagraph, false);
        header.setAlignment(Element.ALIGN_CENTER);
        document.setHeader(header);

        // 设置页脚，页脚设置页码，所以在页码数字前后多了前缀后缀
        Phrase pre = new Phrase();
        pre.setFont(MyFontUtil.SIMSUN_TEXT);
        pre.add("第");
        Phrase suffix = new Phrase();
        suffix.setFont(MyFontUtil.SIMSUN_TEXT);
        suffix.add("页");
        // 两个参数都是Phrase类型，则表示分别设置pageCount的前缀和后缀
        HeaderFooter footer = new HeaderFooter(pre, suffix);
        footer.setAlignment(Element.ALIGN_RIGHT);
        document.setFooter(footer);
        // 重设页码
        document.resetPageCount();
    }

    /**
     * 设置文件属性
     *
     * @param document pdf文档对象
     */
    private static void setMetaInfo(Document document) {
        document.addTitle("这是标题");
        document.addSubject("这是主题");
        document.addAuthor("java_t_t");
        document.addCreationDate();
        document.addCreator("应用程序");
        // 无参方法document.addProducer()的值为OpenPDF的版本，如OpenPDF 1.3.9
        document.addProducer("PDF制作程序");
        // 关键字，只能有一个
        document.addKeywords("keyword");
        // 自定义属性，可以有多个
        document.addHeader("custom_key_1", "custome_value_1");
        document.addHeader("custom_key_2", "custome_value_2");
        // document.addDocListener(new Document());
    }
}
