package com.example.流水线模式Pipeline;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 游家纨绔
 * @date 2022-11-30 15:00
 * @description: TODO
 */
@Data
@AllArgsConstructor
public final class PipelineContext {

	String name;

	/** 流水线模式--管道模式（Pipeline）
	 *   1、核心思想
	 *      将一个任务处理分解为若干个处理阶段，其中每个处理阶段的输出作为下一个处理阶段的输入，并且各个处理阶段都有相应的工作者线程去执行相应的计算。
	 *   2、评价：
	 *      充分利用CPU，提高其计算效率。
	 *      允许子任务间存在依赖关系的条件下实现并行计算。
	 *      非常便于采用单线程模型实现对子任务的处理。
	 *      有错误处理 PipeContext
	 *   3、适用场景
	 *      适合于处理规模较大的任务，否则可能得不偿失。各个处理阶段所使用的工作者线程或者线程池、输入输出对象的创建和转移都有自身的时间和空间消耗。
	 *   4、优点
	 *      ①、降低耦合度。他将请求的发送者和接收者解耦
	 *      ②、简化了Handler处理器，使得处理器不需要知道链的结构。也就是Handler处理器可以是无状态的。与责任链(流水线)相关的状态，交给了Context去维护
	 *      ③、增强对象指派职责的灵活性，通过改变链内的成员或者调动他们的次序，允许动态的新增或者删除责任
	 *      ④、增加新的请求处理器很方便
	 */
}
