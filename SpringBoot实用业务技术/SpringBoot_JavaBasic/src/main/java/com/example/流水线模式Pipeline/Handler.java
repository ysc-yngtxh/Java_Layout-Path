package com.example.流水线模式Pipeline;

/**
 * @author 游家纨绔
 * @description: 管道中的处理器
 * @date 2022-11-30 15:40:00
 */
public interface Handler<T extends PipelineContext> {

	/**
	 * 处理输入的上下文数据
	 *
	 * @param context 处理时的上下文数据
	 * @return 返回 true 则表示由下一个 PipelineContext 继续处理，返回 false 则表示处理结束
	 */
	Boolean handler(T context);
}
