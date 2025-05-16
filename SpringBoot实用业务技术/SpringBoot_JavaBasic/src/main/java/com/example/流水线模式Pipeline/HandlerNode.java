package com.example.流水线模式Pipeline;

import lombok.Data;

/**
 * @author 游家纨绔
 * @date 2023-1-11 22:00:00
 * @description: 处理器节点(节点包含处理器handler和下个处理器的指向next)
 */
@Data
public class HandlerNode {

	// 处理器
	private Handler<PipelineContext> handler;

	// 指向下一个上下文处理器（类似于链表的指针）
	private HandlerNode next;

	public void exec(PipelineContext context) {
		Boolean ret = handler.handler(context);
		if (ret) {
			if (next != null) {
				next.exec(context);
			}
		}
	}
}
