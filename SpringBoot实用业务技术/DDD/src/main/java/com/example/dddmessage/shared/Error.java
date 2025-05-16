package com.example.dddmessage.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Getter
@AllArgsConstructor
public enum Error {

	ENTITY_NOT_FUND("实体未找到"),
	NOT_OPERATION_PERMISSION("没有操作的权限"),
	NOT_ALLOW_RECALL("不允许撤销");

	private final String msg;
}
