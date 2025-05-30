package com.example.service;

import com.example.domain.Student;
import com.example.handler.CsvResultHandler;
import com.example.mapper.CursorMapper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

@Service
public class CursorServiceImpl implements CursorService {

	@Autowired
	private CursorMapper cursorMapper;

	@Override
	@Transactional
	// 使用Mapper 方法通常在这条语句执行完后数据库连接就关闭了，因此 Cursor 也一并关闭了。
	// 但是根据我们下面代码的逻辑是需要通过Cursor迭代的方式一次次从数据库中获取数据的
	// 所以正常会出现异常：java.lang.IllegalStateException: A Cursor is already closed.
	// 因此为了保证程序能够正常执行，我们不能提前关闭数据库连接，可以通过加事务 @Transactional 注解来保证
	public void streamingQuery1() {
		Cursor<Student> cursor = cursorMapper.streamingQuery1();
		int count = 0;
		for (Student stu : cursor) {
			System.out.println(stu);
			count++;
		}

		System.out.println("数据量：" + count);
	}

	@Override
	@Transactional
	public void streamingQuery2() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Cursor<Student> cursor = cursorMapper.streamingQuery2();

		for (Student stu : cursor) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(getCurrentPath()+"/cym.json", true))) {
				writer.write(stu.toString() + "\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		stopWatch.stop();
		System.out.println(stopWatch.getTotalTimeMillis());
	}


	@Override
	public void selectStudentOnResultHandler() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		// 获取当前模块路径
		String path = getCurrentPath();
		// 创建ResultHandler实例，准备将数据写入CSV文件
		CsvResultHandler csvResultHandler = new CsvResultHandler(path + "/ysc.csv");

		// 调用Mapper方法，开始流式查询
		cursorMapper.selectStudentOnResultHandler(csvResultHandler);

		// 关闭文件写入器
		csvResultHandler.close();

		stopWatch.stop();
		System.out.println(stopWatch.getTotalTimeMillis());
	}

	private String getCurrentPath() {
		String pathTarget = null;
		try {
			pathTarget = this.getClass().getClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		return pathTarget.substring(0, pathTarget.indexOf("/target"));
	}
}
