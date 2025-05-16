package com.example.service.impl;

import com.example.mapper.EmployeeDao;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * (Employee)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-06-27 23:55:00
 */
@Service("employeeService")
@SuppressWarnings("ResultOfMethodCallIgnored")
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeDao employeeDao;

	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Value("${job.data.path}")
	public String path;

	@Override
	public void dataInit() throws IOException {
		File file = new File(path, "employee.csv");
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		FileOutputStream out = new FileOutputStream(file);
		String txt = "";

		Random ageR = new Random();
		Random boolR = new Random();

		// 给文件中生产50万条数据
		long beginTime = System.currentTimeMillis();
		System.out.println("开始时间：【 " + beginTime + " 】");
		for (int i = 1; i <= 500000; i++) {
			if (i == 500000) {
				txt = i + ",minmin_" + i + "," + ageR.nextInt(100) + "," + (boolR.nextBoolean() ? 1 : 0);
			} else {
				txt = i + ",minmin_" + i + "," + ageR.nextInt(100) + "," + (boolR.nextBoolean() ? 1 : 0) + "\n";
			}

			out.write(txt.getBytes());
			out.flush();
		}
		out.close();
		System.out.println("总共耗时：【 " + (System.currentTimeMillis() - beginTime) + " 】毫秒");
	}

	@Override
	public void truncateAll() {
		employeeDao.truncateAll();
	}

	@Override
	public void truncateTemp() {
		employeeDao.truncateTemp();
	}
}
