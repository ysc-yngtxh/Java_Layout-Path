package com.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 事件监听
 */
@Slf4j
@Component
public class AnnotationEventListener {

	@Async
	// 注意：一般建议都需要指定此值，否则默认可以处理所有类型的事件，范围太广了
	@EventListener(classes = AnnotationEvent.class)
	public void listener(AnnotationEvent<String> event) {
		log.info("listener监听到数据1：{}", event.getMessage());
	}

    // @EventListener：
    //     非事务感知的普通事件监听器，事件发布后立即同步执行，不管发布事件的代码是否处于事务中、事务最终是提交成功还是回滚失败。
    //     哪怕发布方事务后续回滚，监听器的逻辑也早已执行完毕，无法回滚；
    // @TransactionalEventListener：
    //     事务感知的事件监听器，事件发布后不会立即响应事件，而是「等待发布方的事务完成指定动作后」才执行，
    //     默认规则是：发布方事务「提交成功」后，监听器才执行。也可通过属性 phase 指定其它规则。
    //     若发布方事务回滚，则监听器永远不会执行，从根源避免「事务失败但监听器已执行」的业务问题；
    //     该监听器执行时，发布方事务内的数据库操作已完全生效，能读取到最新的提交数据，保证数据一致性。但需注意此注解需要spring-tx的依赖。
	// @TransactionalEventListener注解的属性phase取值有：
	//     BEFORE_COMMIT：   指定目标方法在事务commit之前执行
	//     AFTER_COMMIT：    指定目标方法在事务commit之后执行
	//     AFTER_ROLLBACK：  指定目标方法在事务rollback之后执行
	//     AFTER_COMPLETION：指定目标方法在事务完成时执行，这里的完成是指无论事务是成功提交还是事务回滚了
	// 	   * 需要注意的是：AFTER_COMMIT + AFTER_COMPLETION 是可以同时生效的
	// 	   *             AFTER_ROLLBACK + AFTER_COMPLETION 也是可以同时生效的
	// fallbackExecution()：表明若没有事务的时候，对应的event是否需要执行，默认值为false表示，没事务就不执行了。
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT,
			                    value = {AnnotationEvent.class})
	public void messageListener(AnnotationEvent<String> event) {
		System.out.println("messageListener收到事件:" + event);
		System.out.println("开始执行业务操作给用户发送短信。用户userId为：" + event.getMessage());
	}
}
