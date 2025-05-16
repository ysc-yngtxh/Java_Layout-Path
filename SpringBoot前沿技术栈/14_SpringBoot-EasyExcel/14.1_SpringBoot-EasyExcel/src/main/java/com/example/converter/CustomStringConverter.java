package com.example.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

public class CustomStringConverter implements Converter<String> {

	@Override
	public Class<?> supportJavaTypeKey() {
		return String.class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.STRING;
	}

	/**
	 * 这里读的时候会调用
	 */
	@Override
	public String convertToJavaData(ReadConverterContext<?> context) {
		return "自定义：" + context.getReadCellData().getStringValue();
	}

	/**
	 * 这里是写的时候会调用 不用管
	 */
	@Override
	public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
		return new WriteCellData<>(context.getValue());
	}
}
