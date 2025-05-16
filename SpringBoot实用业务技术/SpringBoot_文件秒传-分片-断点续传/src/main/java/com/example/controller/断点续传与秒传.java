package com.example.controller;

import com.example.utils.Encrypt;
import io.micrometer.common.util.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class 断点续传与秒传 {

    private static final String uploadPath = System.getProperty("user.dir")
            + "/SpringBoot_文件秒传-分片-断点续传/src/main/resources/File Library";

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private RedisTemplate redisTemplate;

	// 断点续传页面地址
	@RequestMapping("/resume")
	public ModelAndView resume() {
		return new ModelAndView("resume");
	}


	/**
	 * 秒传逻辑：前端校验文件的 md5值，如果和已上传文件存入 Redis 中的 md5值一致，则表示是同一文件，无需再上传
	 *
	 * @param fileMD5 文件的md5值
	 * @return 文件库存在，则返回 true；否则返回 false
	 */
	@PostMapping("/file2/teleport")
	public ResponseEntity<Boolean> check(@RequestParam("fileMD5") String fileMD5) {
		// 判断Redis 中是否存有该文件的 md5值。存在则表示该文件已经上传，否则未上传
		if (redisTemplate.hasKey(fileMD5)) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}


	/**
	 * 断点续传：前端将文件分片上传到服务器，服务器将文件分片存储到 Redis 中，待所有分片上传完毕后，将所有分片合并成一个文件
	 *
	 * @param chunk      文件块
	 * @param chunkIndex 该文件分片所在整个文件中的索引
	 * @param chunkMD5   该文件分片的校验值
	 * @param fileId     文件唯一标识
	 * @return 文件唯一标识
	 */
	@PostMapping("/file2/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("chunk") MultipartFile chunk,
	                                    @RequestParam("chunkIndex") Integer chunkIndex,
	                                    @RequestParam("chunkMD5") String chunkMD5,
	                                    @RequestParam("fileId") String fileId) throws Exception {
		if (StringUtils.isBlank(fileId) || StringUtils.isEmpty(fileId)) {
			fileId = UUID.randomUUID().toString();
		}
		String key = chunk.getOriginalFilename() + "&" + chunkIndex + "-" + fileId;
		// 获取文件块的字节
		byte[] chunkBytes = chunk.getBytes();
		// 获取文件块的 md5 校验值
		String actualChecksum = Encrypt.calculateHash(chunkBytes);
		// 比较前端上传的文件块校验值和实际计算出的校验值是否一致（避免文件在上传过程中被篡改或者网络波动导致文件丢失）
		if (!chunkMD5.equals(actualChecksum)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chunk checksum does not match");
		}
		// 通过文件唯一标识判断该文件是否上传过
		if (redisTemplate.opsForHash().hasKey(fileId, chunkIndex)) {
			return ResponseEntity.ok(fileId);
		}
		// 将文件块信息存储到 /resources/static 目录下
		File file = new File(uploadPath + "/" + key);
		if (!file.exists()) {
			// 将分片文件内容写入到输出文件中
			chunk.transferTo(file);
			// 将文件块信息存储到 Redis 中（这里简化操作：不存储至DB数据库）
			redisTemplate.opsForHash().put(fileId, chunkIndex, file);
		}
		return ResponseEntity.ok(fileId);
	}


	/**
	 * 合并文件
	 *
	 * @param fileId        文件唯一标识
	 * @param fileName      文件名
	 * @param fileMD5       文件MD5值
	 * @param fileChunkSize 文件分片数量
	 * @return
	 */
	@PostMapping("/file2/merge")
	public ResponseEntity<?> mergeFile(@RequestParam("fileId") String fileId,
	                                   @RequestParam("fileName") String fileName,
	                                   @RequestParam("fileMD5") String fileMD5,
	                                   @RequestParam("fileChunkSize") Integer fileChunkSize) {
		try {
			// 文件合并前，先判断该文件分片是否存在
			if (!redisTemplate.hasKey(fileId)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
			}
			// 获取文件分片信息
			Map<Integer, File> chunkMap = redisTemplate.opsForHash().entries(fileId);

			// 检测是否所有分片都上传了
			boolean allChunksUploaded = true;
			List<Integer> missingChunkIndexes = new ArrayList<>();
			for (int i = 0; i < fileChunkSize; i++) {
				if (!chunkMap.containsKey(i)) {
					allChunksUploaded = false;
					missingChunkIndexes.add(i);
				}
			}
			if (!allChunksUploaded) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(missingChunkIndexes);
			}

			// 合并文件分片
			boolean flag = mergeChunks(chunkMap, new File(uploadPath, fileName));
			Resource resource = resourceLoader.getResource("file:" + uploadPath + fileName);

			// 判断文件是否合并成功
			// 如果合并成功，则删除 Redis 中该文件的分片信息，并存储该文件的MD5值(用于在秒传逻辑中进行判断)
			if (flag) {
				// 文件上传结束，删除在 Redis 中的文件分片信息
				redisTemplate.delete(fileId);
				// 在 Redis 中存储上传文件的 md5 值
				redisTemplate.opsForValue().set(fileMD5, new File(uploadPath, fileName).toURI().getPath());
				return ResponseEntity.ok().body(resource.getURI().toString());
			} else {
				return ResponseEntity.status(555).body("文件合并失败！");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	// 分片文件合并逻辑
	private boolean mergeChunks(Map<Integer, File> chunkMap, File destFile) {
		try (FileChannel outChannel = new FileOutputStream(destFile).getChannel()) {
			// 将分片按照顺序合并
			for (int i = 0; i < chunkMap.size(); i++) {
				try (FileChannel inChannel = new FileInputStream(chunkMap.get(i)).getChannel()) {
					// 使用 FileChanel 进行文件合并
					inChannel.transferTo(0, inChannel.size(), outChannel);
					// 文件合并成功后，删除文件分片
					chunkMap.get(i).delete();
				}
			}
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
