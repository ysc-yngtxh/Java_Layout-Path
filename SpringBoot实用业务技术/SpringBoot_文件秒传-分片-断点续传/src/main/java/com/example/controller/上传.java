package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-15 21:00
 * @apiNote TODO 上传
 */
@Controller
public class 上传 {

    // 存放普通文件的路径
    public static final String UPLOAD_PATH = System.getProperty("user.dir")
            + "/SpringBoot_文件秒传-分片-断点续传/src/main/resources/";

	@RequestMapping("/upload")
	public ResponseEntity<Map<String, String>> upload(@RequestParam MultipartFile file) throws IOException {
		File dstFile = new File(UPLOAD_PATH, String.format("%s.%s", UUID.randomUUID(), StringUtils.getFilename(file.getOriginalFilename())));
		// 使用 Spring 框架transferTo()方法：将所指向的 'file' 文件上传到对应的 'dstFile' 目录下
		file.transferTo(dstFile);
		return ResponseEntity.ok(Map.of("path", dstFile.getAbsolutePath()));
	}
}
