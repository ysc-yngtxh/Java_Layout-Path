package com.example.listener;

import com.example.dto.User1;
import org.springframework.batch.core.ItemReadListener;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-12 09:40
 * @apiNote TODO 自定义读取异常监听器
 */
@SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class ErrorItemReaderListener implements ItemReadListener<User1> {

	@Override
	public void beforeRead() {
		System.err.println("读取文件前的监听器被执行");
	}

	@Override
	public void afterRead(User1 item) {
		System.err.println("读取文件中的监听器被执行" + item);
	}

	@Override
	public void onReadError(Exception ex) {
		ItemReadListener.super.onReadError(ex);
	}
}
