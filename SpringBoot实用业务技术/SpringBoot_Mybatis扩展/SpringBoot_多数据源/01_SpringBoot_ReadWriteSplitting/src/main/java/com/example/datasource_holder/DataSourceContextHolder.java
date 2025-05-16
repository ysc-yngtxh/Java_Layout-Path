package com.example.datasource_holder;

import com.example.constant.DataSourceType;

public class DataSourceContextHolder {

	private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

	public static DataSourceType getDataSourceType() {
		return contextHolder.get();
	}

	public static void setDataSourceType(DataSourceType dataSourceType) {
		contextHolder.set(dataSourceType);
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
}
