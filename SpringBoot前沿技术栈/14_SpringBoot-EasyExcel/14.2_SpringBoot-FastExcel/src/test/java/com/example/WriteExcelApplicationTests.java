package com.example;

import cn.idev.excel.ExcelWriter;
import cn.idev.excel.FastExcel;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.data.CommentData;
import cn.idev.excel.metadata.data.FormulaData;
import cn.idev.excel.metadata.data.HyperlinkData;
import cn.idev.excel.metadata.data.ImageData;
import cn.idev.excel.metadata.data.RichTextStringData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.util.BooleanUtils;
import cn.idev.excel.util.FileUtils;
import cn.idev.excel.util.ListUtils;
import cn.idev.excel.write.handler.CellWriteHandler;
import cn.idev.excel.write.handler.RowWriteHandler;
import cn.idev.excel.write.handler.SheetWriteHandler;
import cn.idev.excel.write.handler.context.CellWriteHandlerContext;
import cn.idev.excel.write.handler.context.RowWriteHandlerContext;
import cn.idev.excel.write.handler.context.SheetWriteHandlerContext;
import cn.idev.excel.write.metadata.WriteSheet;
import cn.idev.excel.write.metadata.style.WriteCellStyle;
import cn.idev.excel.write.metadata.style.WriteFont;
import com.example.pojo.write.WriteDemo1;
import com.example.pojo.write.WriteDemo2;
import com.example.pojo.write.WriteDemo3;
import com.example.pojo.write.WriteDemo4;
import com.example.pojo.write.WriteDemo5;
import com.example.pojo.write.WriteDemo6;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 00:40
 * @apiNote TODO
 */
@SpringBootTest
public class WriteExcelApplicationTests {

	private final Logger log = LoggerFactory.getLogger(WriteExcelApplicationTests.class);

	private List<List<String>> head() {
		List<List<String>> list = ListUtils.newArrayList();
		List<String> head0 = ListUtils.newArrayList();
		head0.add("字符串" + System.currentTimeMillis());
		List<String> head1 = ListUtils.newArrayList();
		head1.add("数字" + System.currentTimeMillis());
		List<String> head2 = ListUtils.newArrayList();
		head2.add("日期" + System.currentTimeMillis());
		list.add(head0);
		list.add(head1);
		list.add(head2);
		return list;
	}

	private List<WriteDemo1> data() {
		List<WriteDemo1> list = ListUtils.newArrayList();
		for (int i = 0; i < 10; i++) {
			WriteDemo1 data = new WriteDemo1();
			data.setString("字符串" + i);
			data.setDate(new Date());
			data.setDoubleData(0.56);
			list.add(data);
		}
		return list;
	}

	// 简单写入一个 Excel，不创建对象的写(可能会出现表头与数据无法相对应的问题)
	@Test
	public void noModelWrite() {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		FastExcel.write(fileName).head(head()).sheet("模板").doWrite(data());
	}

	// 简单写入一个 Excel，创建对象的写入
	// 注意 在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
	@Test
	void writeExcel1() {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板1 然后文件流会自动关闭
		FastExcel.write(fileName, WriteDemo1.class)
		         .sheet("模板1")
		         .doWrite(data());
	}

	// 重复多次写入(写到单个或者多个Sheet，分担写入数据压力)
	@Test
	public void repeatedWrite1() {
		// 方法1: 如果写到同一个sheet
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		// 这里 需要指定写用哪个class去写
		try (ExcelWriter excelWriter = FastExcel.write(fileName, WriteDemo1.class).build()) {
			// 这里注意 如果同一个sheet只需要创建一次
			WriteSheet writeSheet = FastExcel.writerSheet("模板").build();
			// 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
			for (int i = 0; i < 5; i++) {
				// 分页去数据库查询数据 这里可以去数据库查询每一页的数据
				List<WriteDemo1> data = data();
				excelWriter.write(data, writeSheet);
			}
		}

		// 方法2: 如果写到不同的sheet 同一个对象
		fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		// 这里 指定文件
		try (ExcelWriter excelWriter = FastExcel.write(fileName, WriteDemo1.class).build()) {
			// 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
			for (int i = 0; i < 5; i++) {
				// 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
				WriteSheet writeSheet = FastExcel.writerSheet(i, "模板" + i).build();
				// 分页去数据库查询数据 这里可以去数据库查询每一页的数据
				List<WriteDemo1> data = data();
				excelWriter.write(data, writeSheet);
			}
		}

		// 方法3 如果写到不同的sheet 不同的对象
		fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		// 这里 指定文件
		try (ExcelWriter excelWriter = FastExcel.write(fileName).build()) {
			// 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
			for (int i = 0; i < 5; i++) {
				// 每次都要创建writeSheet; 这里注意必须指定 sheetNo ,而且 sheetName 必须不一样。
				// 这里注意表头 head 可以每次都变，我这里为了方便 所以用的同一个class,实际上可以一直变
				WriteSheet writeSheet = FastExcel.writerSheet(i, "模板" + i).head(WriteDemo1.class).build();
				// 分页去数据库查询数据 这里可以去数据库查询每一页的数据
				List<WriteDemo1> data = data();
				excelWriter.write(data, writeSheet);
			}
		}

	}

	// 导出指定的列【excludeColumnFieldNames()排除列 ｜｜ includeColumnFieldNames()包含列】
	@Test
	void writeExcel11() {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		FastExcel.write(fileName, WriteDemo1.class)
		         .excludeColumnFieldNames(Set.of("date"))
		         .sheet("模板2")
		         .doWrite(data());
	}

	// 复杂头写入
	@Test
	void writeExcel2() {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		FastExcel.write(fileName, WriteDemo2.class)
		         .sheet("模板2")
		         .doWrite(data());
	}

	// 日期、数字或者自定义格式转换
	@Test
	void writeExcel3() {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		FastExcel.write(fileName, WriteDemo3.class)
		         .sheet("模板3")
		         .doWrite(data());
	}

	// 图片导出
	@Test
	public void imageWrite4() throws Exception {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";

		// 这里注意下 所有的图片都会放到内存 暂时没有很好的解法，大量图片的情况下建议 2选1:
		// 1. 将图片上传到oss 或者其他存储网站: https://www.aliyun.com/product/oss ，然后直接放链接
		// 2. 使用: https://github.com/coobird/thumbnailator 或者其他工具压缩图片

		String imagePath = System.getProperty("user.dir") + "/src/main/resources/static/下载.jpeg";
		try (InputStream inputStream = FileUtils.openInputStream(new File(imagePath))) {
			List<WriteDemo4> list = ListUtils.newArrayList();
			WriteDemo4 writeDemo = new WriteDemo4();
			list.add(writeDemo);
			// 放入五种类型的图片 实际使用只要选一种即可
			writeDemo.setByteArray(FileUtils.readFileToByteArray(new File(imagePath)));
			writeDemo.setFile(new File(imagePath));
			writeDemo.setString(imagePath);
			writeDemo.setInputStream(inputStream);
			writeDemo.setUrl(new URI("https://www.wohaoyun.com/img/M00/06/1F/wKjg2lvJRi6AUblsAAMb93MwhpY910.jpg").toURL());

			// 这里演示的是额外的单元格，放入两张图片还有文字，个人觉得了解即可，不需要记忆
			// 第一个图片靠左
			// 第二个靠右 而且要额外的占用他后面的单元格
			WriteCellData<Void> writeCellData = new WriteCellData<>();
			writeDemo.setWriteCellDataFile(writeCellData);
			// 这里可以设置为 EMPTY 则代表不需要其他数据了
			writeCellData.setType(CellDataTypeEnum.STRING);
			writeCellData.setStringValue("额外的放一些文字");

			// 可以放入多个图片
			List<ImageData> imageDataList = new ArrayList<>();
			ImageData imageData = new ImageData();
			imageDataList.add(imageData);
			writeCellData.setImageDataList(imageDataList);
			// 放入2进制图片
			imageData.setImage(FileUtils.readFileToByteArray(new File(imagePath)));
			// 图片类型
			imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
			// 上 右 下 左 需要留空
			// 这个类似于 css 的 margin
			// 这里实测 不能设置太大 超过单元格原始大小后 打开会提示修复。暂时未找到很好的解法。
			imageData.setTop(5);
			imageData.setRight(40);
			imageData.setBottom(5);
			imageData.setLeft(5);

			// 放入第二个图片
			imageData = new ImageData();
			imageDataList.add(imageData);
			writeCellData.setImageDataList(imageDataList);
			imageData.setImage(FileUtils.readFileToByteArray(new File(imagePath)));
			imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
			imageData.setTop(5);
			imageData.setRight(5);
			imageData.setBottom(5);
			imageData.setLeft(50);
			// 设置图片的位置 假设 现在目标 是 覆盖 当前单元格 和当前单元格右边的单元格
			// 起点相对于当前单元格为0 当然可以不写
			imageData.setRelativeFirstRowIndex(0);
			imageData.setRelativeFirstColumnIndex(0);
			imageData.setRelativeLastRowIndex(0);
			// 前面3个可以不写  下面这个需要写 也就是 结尾 需要相对当前单元格 往右移动一格
			// 也就是说 这个图片会覆盖当前单元格和 后面的那一格
			imageData.setRelativeLastColumnIndex(1);

			// 写入数据
			FastExcel.write(fileName, WriteDemo4.class).sheet().doWrite(list);
		}
	}

	// 超链接、批注、公式、指定单个单元格的样式、单个单元格多种样式
	@Test
	public void writeCellDataWrite5() {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		WriteDemo5 writeCellDemoData = new WriteDemo5();

		// TODO 设置超链接
		WriteCellData<String> hyperlink = new WriteCellData<>("官方网站");
		writeCellDemoData.setHyperlink(hyperlink);
		HyperlinkData hyperlinkData = new HyperlinkData();
		hyperlink.setHyperlinkData(hyperlinkData);
		hyperlinkData.setAddress("https://gitee.com/you-shicheng/java-layout-path");
		hyperlinkData.setHyperlinkType(HyperlinkData.HyperlinkType.URL);

		// TODO 设置批注
		WriteCellData<String> comment = new WriteCellData<>("备注的单元格信息");
		writeCellDemoData.setCommentData(comment);
		CommentData commentData = new CommentData();
		comment.setCommentData(commentData);
		commentData.setAuthor("Jiaju Zhuang");
		commentData.setRichTextStringData(new RichTextStringData("这是一个备注"));
		// 备注的默认大小是按照单元格的大小 这里想调整到4个单元格那么大 所以向后 向下 各额外占用了一个单元格
		commentData.setRelativeLastColumnIndex(1);
		commentData.setRelativeLastRowIndex(1);

		// TODO 设置公式
		WriteCellData<String> formula = new WriteCellData<>();
		writeCellDemoData.setFormulaData(formula);
		FormulaData formulaData = new FormulaData();
		formula.setFormulaData(formulaData);
		// 将 123456789 中的第一个数字替换成 2
		// 单元格内容 123456789, 下标 1, 替换字符数量 1, 替换内容 2
		// 这里只是例子 如果真的涉及到公式 能内存算好尽量内存算好 公式能不用尽量不用
		formulaData.setFormulaValue("REPLACE(123456789, 1, 1, 2)");

		// TODO 设置单个单元格的样式 当然样式 很多的话 也可以用注解等方式。
		WriteCellData<String> writeCellStyle = new WriteCellData<>("单元格样式");
		writeCellStyle.setType(CellDataTypeEnum.STRING);
		writeCellDemoData.setWriteCellStyle(writeCellStyle);
		WriteCellStyle writeCellStyleData = new WriteCellStyle();
		writeCellStyle.setWriteCellStyle(writeCellStyleData);
		// 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.
		writeCellStyleData.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
		// 背景绿色
		writeCellStyleData.setFillForegroundColor(IndexedColors.GREEN.getIndex());

		// TODO 设置单个单元格多种样式
		// 这里需要设置 inMomery=true 不然会导致无法展示单个单元格多种样式，所以慎用
		WriteCellData<String> richTest = new WriteCellData<>();
		richTest.setType(CellDataTypeEnum.RICH_TEXT_STRING);
		writeCellDemoData.setRichText(richTest);
		RichTextStringData richTextStringData = new RichTextStringData();
		richTest.setRichTextStringDataValue(richTextStringData);
		richTextStringData.setTextString("红色绿色默认");
		// 前2个字红色
		WriteFont writeFont = new WriteFont();
		writeFont.setColor(IndexedColors.RED.getIndex());
		richTextStringData.applyFont(0, 2, writeFont);
		// 接下来2个字绿色
		writeFont = new WriteFont();
		writeFont.setColor(IndexedColors.GREEN.getIndex());
		richTextStringData.applyFont(2, 4, writeFont);

		List<WriteDemo5> data = new ArrayList<>();
		data.add(writeCellDemoData);
		FastExcel.write(fileName, WriteDemo5.class).inMemory(true).sheet("模板").doWrite(data);
	}

	// 注解形式自定义单元格样式
	@Test
	public void annotationStyleWrite6() {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		FastExcel.write(fileName, WriteDemo6.class).sheet("模板").doWrite(data());
	}

	// 根据模板写入（类似于追加数据）
	@Test
	public void templateWrite1() {
		String templateFileName = System.getProperty("user.dir") + "/工作簿WriteDemo1.xlsx";
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo2.xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		// 这里要注意 withTemplate 的模板文件会全量存储在内存里面，所以尽量不要用于追加文件，如果文件模板文件过大会OOM（内存溢出）
		// 如果要在文件中追加（无法在一个线程里面处理，可以在一个线程的建议参照多次写入的demo） 建议临时存储到数据库 或者 磁盘缓存(ehcache) 然后再一次性写入
		FastExcel.write(fileName).withTemplate(templateFileName).sheet().doWrite(data());
		// 追加的有表头的数据
		// FastExcel.write(fileName, WriteDemo1.class).withTemplate(templateFileName).sheet().doWrite(data());
	}

	// TODO 前面实现的超链接和批注都是在新建的扩展单元格中，这里我们实现在已定义好的表头数据里做超链接、批注等
	// 下拉，超链接等自定义拦截器，这里实现3点。
	// 1. 对第一行第一列的表头超链接到: https://gitee.com/you-shicheng/java-layout-path
	// 2. 对第一列第一行和第二行的内容数据新增下拉框，显示 测试1 测试2
	// 3. 对第一行第二列内容数据创建批注
	@Test
	public void customHandlerWrite() {
		String fileName = System.getProperty("user.dir") + "/工作簿WriteDemo2.xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		FastExcel.write(fileName, WriteDemo3.class)
		         // 这里我是用的是匿名内部类的方式注册写入处理器，将第一行第一列的表头超链接
		         .registerWriteHandler(new CellWriteHandler() {
			         @Override
			         public void afterCellDispose(CellWriteHandlerContext context) {
				         Cell cell = context.getCell();
				         // 这里可以对cell进行任何操作
				         log.info("第{}行，第{}列写入完成。", cell.getRowIndex(), cell.getColumnIndex());
				         if (BooleanUtils.isTrue(context.getHead()) && cell.getColumnIndex() == 0) {
					         CreationHelper createHelper = context.getWriteSheetHolder().getSheet().getWorkbook().getCreationHelper();
					         Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.URL);
					         hyperlink.setAddress("https://gitee.com/you-shicheng/java-layout-path");
					         cell.setHyperlink(hyperlink);
				         }
			         }
		         })
		         // 这里我是用的是匿名内部类的方式注册写入处理器，将第一列第一行和第二行的内容数据新增下拉框
		         .registerWriteHandler(new SheetWriteHandler() {
			         @Override
			         public void afterSheetCreate(SheetWriteHandlerContext context) {
				         log.info("第{}个Sheet写入成功。", context.getWriteSheetHolder().getSheetNo());

				         // 区间设置 第一列第一行和第二行的数据。由于第一行是头，所以第一、二行的数据实际上是第二三行
				         CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(1, 2, 0, 0);
				         DataValidationHelper helper = context.getWriteSheetHolder().getSheet().getDataValidationHelper();
				         DataValidationConstraint constraint = helper.createExplicitListConstraint(new String[]{"测试1", "测试2"});
				         DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);
				         context.getWriteSheetHolder().getSheet().addValidationData(dataValidation);
			         }
		         })
		         // 这里我是用的是匿名内部类的方式注册写入处理器，将第二行第二列 内容数据创建批注
		         .registerWriteHandler(new RowWriteHandler() {
			         @Override
			         public void afterRowDispose(RowWriteHandlerContext context) {
				         if (BooleanUtils.isTrue(context.getHead())) {
					         Sheet sheet = context.getWriteSheetHolder().getSheet();
					         Drawing<?> drawingPatriarch = sheet.createDrawingPatriarch();
					         // 这里定义的是锚点，前四个参数不用管
					         // col1 和 row1 代表左上坐标。col1：从左往右数的边框线  row1：从上往下数得边框线
					         // col2 和 row2 代表右下坐标。同上
					         Comment comment =
							         drawingPatriarch.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) 1, 2, (short) 2, 3));
					         // 输入批注信息
					         comment.setString(new XSSFRichTextString("创建批注!"));
					         // 将批注添加到单元格对象中
					         sheet.getRow(0).getCell(1).setCellComment(comment);
				         }
			         }
		         })
		         .sheet("模板").doWrite(data());
	}
}
