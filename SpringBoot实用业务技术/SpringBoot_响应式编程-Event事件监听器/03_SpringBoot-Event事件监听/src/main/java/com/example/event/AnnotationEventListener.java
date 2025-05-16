package com.example.event;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 事件监听
 */
@Log4j2
@Component
public class AnnotationEventListener {

	@Async
	// 注意：一般建议都需要指定此值，否则默认可以处理所有类型的事件，范围太广了
	@EventListener(classes = AnnotationEvent.class)
	public void listener(AnnotationEvent<String> event) {
		log.info("listener监听到数据1：{}", event.getMessage());
	}

	// @TransactionalEventListener来定义一个监听器，他与@EventListener不同的就是:
	//     @EventListener标记一个方法作为监听器，他默认是同步执行，如果发布事件的方法处于事务中，
	//     那么事务会在监听器方法执行完毕之后才提交。事件发布之后就由监听器去处理，而不要影响原有的事务。
	//     也就是说希望事务及时提交，我们就可以使用该注解来标识。注意此注解需要spring-tx的依赖。
	// @TransactionalEventListener注解的属性phase取值有：
	//      BEFORE_COMMIT(指定目标方法在事务commit之前执行)、
	//      AFTER_COMMIT(指定目标方法在事务commit之后执行)、
	//      AFTER_ROLLBACK(指定目标方法在事务rollback之后执行)、
	//      AFTER_COMPLETION(指定目标方法在事务完成时执行，这里的完成是指无论事务是成功提交还是事务回滚了)
	// 	    * 需要注意的是：AFTER_COMMIT + AFTER_COMPLETION是可以同时生效的
	// 	    * AFTER_ROLLBACK + AFTER_COMPLETION是可以同时生效的
	// fallbackExecution()：表明若没有事务的时候，对应的event是否需要执行，默认值为false表示，没事务就不执行了。
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT,
			                    value = {AnnotationEvent.class})
	public void messageListener(AnnotationEvent<String> event) {
		System.out.println("messageListener收到事件:" + event);
		System.out.println("开始执行业务操作给用户发送短信。用户userId为：" + event.getMessage());
	}
}
