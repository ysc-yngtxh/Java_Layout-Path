package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.fastjson.JSON;
import com.example.pojo.write.WriteDemo1;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 22:30
 * @apiNote TODO 写入 Excel
 */
@RestController
public class WriteExcelController {

	// 这里的数据可以从数据库中取
	private List<WriteDemo1> data() {
		List<WriteDemo1> list = ListUtils.newArrayList();
		for (int i = 0; i < 10; i++) {
			WriteDemo1 data = new WriteDemo1();
			data.setString("字符串" + i);
			data.setDate(new Date());
			data.setDoubleData(0.56);
			list.add(data);
		}
		return list;
	}

	/**
	 * 文件下载并且失败的时候返回json（默认文件下载失败了会返回一个有部分数据的Excel）
	 */
	@GetMapping("/downloadFailedUsingJson")
	public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
		// 请直接用浏览器或者用postman
		try {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setCharacterEncoding("utf-8");
			// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
			String fileName = URLEncoder.encode("测试", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			// 这里需要设置不关闭流
			EasyExcel.write(response.getOutputStream(), WriteDemo1.class)
			         .autoCloseStream(Boolean.FALSE)
			         .sheet("模板").doWrite(data());

		} catch (Exception e) {
			// 重置response
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			Map<String, String> map = MapUtils.newHashMap();
			map.put("status", "failure");
			map.put("message", "下载文件失败" + e.getMessage());
			response.getWriter().println(JSON.toJSONString(map));
		}
	}
}
