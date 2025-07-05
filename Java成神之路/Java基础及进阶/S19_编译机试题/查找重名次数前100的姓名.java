package S19_编译机试题;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author 游家纨绔
 * @Description TODO 全国14亿人口查找重名最多的前100个姓名
 * @Date 2025-06-23 00:50:00
 */
public class 查找重名次数前100的姓名 {

	// 分块处理防止OOM
	private static final int BATCH_SIZE = 10_000_000;

	public static void main(String[] args) throws IOException {
		// 1. 数据分片读取
		Map<String, Long> nameCountMap = new HashMap<>(16_000_000); // 预估姓名种类

		try (BufferedReader reader = new BufferedReader(new FileReader("population.txt"))) {
			String line;
			int count = 0;
			while ((line = reader.readLine()) != null) {
				String name = line; // 解析姓名
				nameCountMap.merge(name, 1L, Long::sum);

				// 分批处理防止内存溢出
				if (++count % BATCH_SIZE == 0) {
					System.out.println("Processed " + count + " records");
				}
			}
		}

		// 2. 使用最小堆找出Top100
		PriorityQueue<Map.Entry<String, Long>> minHeap = new PriorityQueue<>(
				100, Comparator.comparingLong(Map.Entry::getValue));

		for (Map.Entry<String, Long> entry : nameCountMap.entrySet()) {
			if (minHeap.size() < 100) {
				minHeap.offer(entry);
			} else if (entry.getValue() > minHeap.peek().getValue()) {
				minHeap.poll();
				minHeap.offer(entry);
			}
		}

		// 3. 输出结果
		List<Map.Entry<String, Long>> result = new ArrayList<>(minHeap);
		result.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		System.out.println("Top 100 duplicate names:");
		result.forEach(entry ->
				               System.out.println(entry.getKey() + ": " + entry.getValue()));
	}
}
