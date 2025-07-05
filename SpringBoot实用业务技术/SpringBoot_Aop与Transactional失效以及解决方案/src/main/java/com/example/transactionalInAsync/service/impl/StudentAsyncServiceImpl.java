package com.example.transactionalInAsync.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transactional.entity.Student;
import com.example.transactional.mapper.StudentMapper;
import com.example.transactionalInAsync.event.StudentSaveEvent;
import com.example.transactionalInAsync.service.StudentAsyncService;
import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * (Student)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-11-11 17:30:00
 */
@Service("studentAsyncService")
@RequiredArgsConstructor
public class StudentAsyncServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentAsyncService {

	private final StudentMapper studentMapper;

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveAndAsync() {
		// 写操作
		studentMapper.insert(new Student(5, "黄河以北，我曹最美", "456@qq.com", 22));
		// 异步执行写操作
		CompletableFuture.runAsync(() -> {
			studentMapper.insert(Student.builder().name("实现AI的MCP").email("456@qq.com").age(22).build());
		});
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveAndAsyncException() {
		// 写操作
		studentMapper.insert(Student.builder().name("实现AI的MCP").email("456@qq.com").age(22).build());
		// 异步执行写操作
		CompletableFuture.runAsync(() -> {
			studentMapper.insert(new Student(5, "黄河以北，我曹最美", "456@qq.com", 22));
		});
	}



	// 解决事务失效问题的方案1：等待异步操作完成并检查异常
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveAndAsyncScheme1() {
		// 写操作
		studentMapper.insert(Student.builder().name("实现AI的MCP").email("456@qq.com").age(22).build());

		// 异步执行写操作
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			studentMapper.insert(new Student(5, "黄河以北，我曹最美", "456@qq.com", 22));
		});

		try {
			future.get(); // 等待异步操作完成，如有异常会抛出
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("异步操作失败", e);
		}
	}

	// 解决事务失效问题的方案2：使用事务同步管理器（适用于Spring环境）
	private final TransactionTemplate transactionTemplate;
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveAndAsyncScheme2() {
		// 写操作
		studentMapper.insert(Student.builder().name("实现AI的MCP").email("456@qq.com").age(22).build());

		// 异步执行写操作
		CompletableFuture.runAsync(() -> {
			transactionTemplate.execute(status -> {
				try {
					studentMapper.insert(new Student(5, "黄河以北，我曹最美", "456@qq.com", 22));
					return null;
				} catch (Exception e) {
					status.setRollbackOnly();
					throw e;
				}
			});
		}).exceptionally(ex -> {
			throw new RuntimeException("异步操作失败", ex);
		});
		try {
			TimeUnit.SECONDS.sleep(3); // 等待异步操作完成
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private final PlatformTransactionManager transactionManager;

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveAndAsyncScheme3() {
		// 写操作
		studentMapper.insert(Student.builder().name("实现AI的MCP").email("456@qq.com").age(22).build());

		// 注册事务回调，在主事务提交后执行
		TransactionSynchronizationManager.registerSynchronization(
				new TransactionSynchronization() {
					@Override
					public void afterCompletion(int status) {
						if (status == STATUS_COMMITTED) {
							try {
								CompletableFuture.runAsync(() -> {
									studentMapper.insert(new Student(5, "黄河以北，我曹最美", "456@qq.com", 22));
								}).get(); // 主事务提交后执行并等待
							} catch (Exception e) {
								// 异步操作失败，这里可以记录日志或触发补偿机制
								studentMapper.delete(
										new QueryWrapper<Student>().eq("name", "实现AI的MCP")
								);
								throw new RuntimeException("异步操作失败，需要人工干预", e);
							}
						}
					}
				}
		);
	}

	// 解决事务失效问题的方案3：使用事件监听模式（推荐）
	private final ApplicationEventPublisher eventPublisher;
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveAndAsyncScheme4() {
		// 写操作
		studentMapper.insert(Student.builder().name("实现AI的MCP").email("456@qq.com").age(22).build());

		// 发布事件
		eventPublisher.publishEvent(new StudentSaveEvent(
				new Student(5, "黄河以北，我曹最美", "456@qq.com", 22)
		));
	}
}
