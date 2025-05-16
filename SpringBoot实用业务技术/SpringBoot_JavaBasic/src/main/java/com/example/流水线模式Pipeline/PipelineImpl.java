package com.example.流水线模式Pipeline;

/**
 * @author 游家纨绔
 * @date 2022-11-30 15:40:00
 * @description: TODO
 */
public class PipelineImpl implements Pipeline {

	private HandlerNode head = new HandlerNode();
	private HandlerNode tail;
	private PipelineContext context;

	public PipelineImpl(PipelineContext context) {
		this.context = context;
	}

	@Override
	public void start() {
		head.getNext().exec(context);
	}

	@Override
	public PipelineContext getContext() {
		return context;
	}

	/**
	 * 添加handler到头部
	 *
	 * @param handlers
	 */
	@Override
	public void addHead(Handler<?>... handlers) {
		// (handler1, handler2...)
		HandlerNode next = head.getNext();
		for (Handler handler : handlers) {
			HandlerNode handlerNode = new HandlerNode();
			handlerNode.setHandler(handler);

			handlerNode.setNext(next);
			next = handlerNode;
		}
		// 这里有点绕，总结就是将处理器节点的next指向插入前的节点。
		head.setNext(next);
	}

	/**
	 * 添加handler到尾部
	 *
	 * @param handlers
	 */
	@Override
	public void addTail(Handler<?>... handlers) {
		HandlerNode node = tail;
		for (Handler handler : handlers) {
			HandlerNode handlerNode = new HandlerNode();
			handlerNode.setHandler(handler);

			handlerNode.setNext(handlerNode);
			node = handlerNode;
		}
		tail = node;
	}
}
