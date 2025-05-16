package com.example.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ConverterUtils;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-15 22:50
 * @apiNote TODO
 */
public class DemoDataListener<T> implements ReadListener<T> {

	private final Logger log = LoggerFactory.getLogger(DemoDataListener.class);

	// 单次缓存的数据量
	public static final int BATCH_COUNT = 100;
	// 临时存储
	private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

	// 读取第一行表头数据
	@Override
	public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
		Map<Integer, String> stringMap = ConverterUtils.convertToStringMap(headMap, context);
		log.info("解析到一条表头数据:{}", JSON.toJSONString(stringMap));
		// 如果想转成 Map<Integer, String>
		// 方案1： 不要implements ReadListener 而是 extends AnalysisEventListener
		// 方案2： 调用 ConverterUtils.convertToStringMap(headMap, context) 自动会转换
	}

	// 这个方法,每一条数据解析都会来调用
	// 一行一行去读取excel内容并封装到 T 类型中
	// 默认从第二行读取数据，因为第一行一般都是表头
	@Override
	public void invoke(T data, AnalysisContext context) {
		log.info("正在读取sheet:[{}]的数据:{}", context.readSheetHolder().getSheetName(), JSON.toJSONString(data));
		cachedDataList.add(data);
		if (cachedDataList.size() <= BATCH_COUNT) {
			// 存储完成清理 list
			cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
		}
	}

	// 这个方法,所有数据解析完成了 会来调用
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		log.info("sheet:[{}]读取完成!", context.readSheetHolder().getSheetName());
	}

	// 解析额外的表格信息：批注、超链接、图表、合并单元格信息读取等
	// 由于是流式读取，没法在读取到单元格数据的时候直接读取到额外信息，所以只能最后通知哪些单元格有哪些额外信息
	// 在 invoke() 方法之后执行，在 doAfterAllAnalysed() 之前执行
	@Override
	public void extra(CellExtra extra, AnalysisContext context) {
		// log.info("读取到了一条额外信息:{}", JSON.toJSONString(extra));
		switch (extra.getType()) {
			case COMMENT -> log.info("额外信息是批注,在rowIndex:{},columnIndex;{},内容是:{}"
					, extra.getRowIndex()
					, extra.getColumnIndex(),
					                 extra.getText());
			case HYPERLINK -> {
				if ("Sheet1!A1".equals(extra.getText())) {
					log.info("额外信息是超链接,在rowIndex:{},columnIndex;{},内容是:{}"
							, extra.getRowIndex()
							, extra.getColumnIndex()
							, extra.getText());
				} else if ("Sheet1!B3".equals(extra.getText())) {
					log.info(
							"额外信息是超链接,而且覆盖了一个区间,在firstRowIndex:{},firstColumnIndex;{},lastRowIndex:{},lastColumnIndex:{},"
									+ "内容是:{}",
							extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(),
							extra.getLastColumnIndex(), extra.getText());
				} else if ("Sheet2!A1".equals(extra.getText())) {
					log.info(
							"额外信息是超链接,而且覆盖了一个区间,在firstRowIndex:{},firstColumnIndex;{},lastRowIndex:{},lastColumnIndex:{},"
									+ "内容是:{}",
							extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(),
							extra.getLastColumnIndex(), extra.getText());
				} else {
					log.info("Unknown hyperlink!");
				}
			}
			case MERGE -> log.info(
					"额外信息是合并单元格,而且覆盖了一个区间,在firstRowIndex:{},firstColumnIndex;{},lastRowIndex:{},lastColumnIndex:{}",
					extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(),
					extra.getLastColumnIndex());
			default -> {
			}
		}
	}

	// 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
	@Override
	public void onException(Exception exception, AnalysisContext context) {
		log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
		// 如果是某一个单元格的转换异常 能获取到具体行号
		// 如果要获取头的信息 配合invokeHeadMap使用
		if (exception instanceof ExcelDataConvertException excelDataConvertException) {
			log.error("第{}行，第{}列解析异常，异常数据为:{}"
					, excelDataConvertException.getRowIndex()
					, excelDataConvertException.getColumnIndex()
					, excelDataConvertException.getCellData().getStringValue()
			         );
		}
	}
}
