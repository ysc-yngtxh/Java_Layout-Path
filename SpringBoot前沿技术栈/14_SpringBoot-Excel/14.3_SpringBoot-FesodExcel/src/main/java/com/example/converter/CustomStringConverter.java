package com.example.converter;

import org.apache.fesod.sheet.converters.Converter;
import org.apache.fesod.sheet.converters.ReadConverterContext;
import org.apache.fesod.sheet.converters.WriteConverterContext;
import org.apache.fesod.sheet.enums.CellDataTypeEnum;
import org.apache.fesod.sheet.metadata.data.WriteCellData;

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
