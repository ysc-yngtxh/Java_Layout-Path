package com.example.transactionalTemplate.service;

import com.example.transactionalTemplate.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class PaymentService {

	// 该类实例上的所有方法共享一个TransactionTemplate
	private final TransactionTemplate transactionTemplate;

	// 在构造方法上注入PlatformTransactionManager
	@Autowired
	public PaymentService(PlatformTransactionManager platformTransactionManager) {
		this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
		// 设置事务传播属性
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// 设置事务的隔离级别,设置为读已提交（默认是ISOLATION_DEFAULT:使用的是底层数据库的默认的隔离级别）
		transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		// 设置是否只读，默认是false
		transactionTemplate.setReadOnly(true);
		// 默认使用的是数据库底层的默认的事务的超时时间
		transactionTemplate.setTimeout(30000);
	}

	public Order payOrder(Order order) {
		// TransactionCallback() 有返回参数的事务
		Order result = transactionTemplate.execute(new TransactionCallback<Order>() {
			// 在该方法中的代码在一个事务中
			@Override
			public Order doInTransaction(TransactionStatus transactionStatus) {
				try {
					createOrder(order);
					updateOrder(order);
					return order;
				} catch (Exception e) {
					// 手动回滚
					transactionStatus.setRollbackOnly();
					return null;
				}
			}
		});
		// 使用TransactionCallbackWithoutResult类型的匿名类，无返回参数的事务
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				try {
					updateOrder(order);
					updateOrder(order);
				} catch (Exception e) {
					// 手动回滚
					transactionStatus.setRollbackOnly();
				}
			}
		});
		return result;
	}

	private void createOrder(Order order) {}

	private void updateOrder(Order order) {}

}
