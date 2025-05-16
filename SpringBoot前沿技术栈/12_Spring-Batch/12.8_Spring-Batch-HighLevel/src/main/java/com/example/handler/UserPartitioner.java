package com.example.handler;

import lombok.NonNull;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户文件读取分区器
 * 作用：指定从步骤名称 + 配置从步骤需要上下文环境
 */
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class UserPartitioner implements Partitioner {

	@Override
	public @NonNull Map<String, ExecutionContext> partition(int gridSize) {
		Map<String, ExecutionContext> result = new HashMap<>(gridSize);

		int range = 10; // 文件间隔
		int start = 11; // 开始位置
		int end = 10;  // 结束位置
		String text = "user%s-%s.txt";  // 也可以使用 MessageFormat 进行字符串格式化，效率更高

		for (int i = 0; i < gridSize; i++) {
			ExecutionContext value = new ExecutionContext();
			Resource resource = new ClassPathResource(String.format(text, start, end));
			try {
				value.putString("file", resource.getURL().toExternalForm());
			} catch (IOException e) {
				e.printStackTrace();
			}
			start += range;
			end += range;

			result.put("user_partition_" + i, value);
		}
		return result;
	}
}
