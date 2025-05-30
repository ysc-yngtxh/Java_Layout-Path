package com.example.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import lombok.SneakyThrows;

/**
 * 结果集处理器
 */
public class ResultSetHandler {

	// 数据库下划线转Java驼峰命名
	public static String HumpToUnderline(String para) {
		StringBuilder sb = new StringBuilder(para);
		int temp = 0;
		if (!para.contains("_")) {
			for (int i = 0; i < para.length(); i++) {
				if (Character.isUpperCase(para.charAt(i))) {
					sb.insert(i + temp, "_");
					temp += 1;
				}
			}
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * @param resultSet 结果集
	 * @param type      需要转换的目标类型
	 */
	@SneakyThrows
	public <T> T handle(ResultSet resultSet, Class<T> type) {
		// 直接调用Class的newInstance方法产生一个实例
		Object pojo = type.getDeclaredConstructor().newInstance();

		// 遍历结果集
		if (resultSet.next()) {
			// 循环赋值
			for (Field field : pojo.getClass().getDeclaredFields()) {
				setValue(pojo, field, resultSet);
			}
		}

		return type.cast(pojo);
	}

	/**
	 * 通过反射给属性赋值
	 */
	@SneakyThrows
	private void setValue(Object pojo, Field field, ResultSet rs) {
		// 获取 pojo 的 set 方法
		Method setMethod = pojo.getClass().getMethod("set" + firstWordCapital(field.getName()), field.getType());

		// 调用 pojo 的set 方法，使用结果集给属性赋值
		// 赋值先从resultSet取出值 setter
		setMethod.invoke(pojo, getResult(rs, field));
	}

	/**
	 * 根据反射判断类型，从ResultSet中取对应类型参数
	 */
	private Object getResult(ResultSet rs, Field field) throws SQLException {
		// TODO TypeHandler
		Class<?> type = field.getType();
		String dataName = HumpToUnderline(field.getName()); // 驼峰转下划线
		// TODO 类型判断不够全
		if (Integer.class == type) {
			return rs.getInt(dataName);
		} else if (String.class == type) {
			return rs.getString(dataName);
		} else if (Long.class == type) {
			return rs.getLong(dataName);
		} else if (Boolean.class == type) {
			return rs.getBoolean(dataName);
		} else if (Double.class == type) {
			return rs.getDouble(dataName);
		} else if (Date.class == type) {
			return rs.getDate(dataName);
		} else {
			return rs.getString(dataName);
		}
	}

	/**
	 * 单词首字母大写
	 */
	private String firstWordCapital(String word) {
		String first = word.substring(0, 1);
		String tail = word.substring(1);
		return first.toUpperCase() + tail;
	}

}
