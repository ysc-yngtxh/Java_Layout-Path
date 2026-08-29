package com.example.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-05 15:30
 * @apiNote TODO 上传图片
 */
@Controller
public class PictureController {

	private static String upPath = "https://ysc-test0322.oss-cn-shenzhen.aliyuncs.com/";

	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			return "上传文件失败";
		}
		String filename = file.getOriginalFilename();
		String substr = Objects.requireNonNull(filename).substring(filename.indexOf("."));
		String reFileName = UUID.randomUUID().toString().replace("-", "") + substr;

		String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
		String accessKeyId = "LTAI5tMGA97nbLGRdSVBqZxQcym";
		String accessKeySecret = "VPBZ6qcRNlFdJ5PE2eFlOQ87y0XKIO"; // 这里密钥有点问题，需要更改
		OSS ossClient = new OSSClientBuilder().build(
				endpoint            // 地域节点
				, accessKeyId       // 访问密钥 ID
				, accessKeySecret   // 连接秘钥
		);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("image/jpg");
		objectMetadata.setContentDisposition("inline");
		ossClient.putObject(
				"ysc-test0322"           // 列表名
				, reFileName             // 文件名
				, file.getInputStream()  // 文件流
				, objectMetadata         // 设置响应体参数
		);

		ossClient.shutdown();   // 关闭连接
		return upPath + reFileName;
	}
}
