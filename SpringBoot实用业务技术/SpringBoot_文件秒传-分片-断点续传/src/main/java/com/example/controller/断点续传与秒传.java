package com.example.controller;

import io.micrometer.common.util.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class 断点续传与秒传 {

    private static final String FILE_UPLOAD_PREFIX = "file_upload:";

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String uploadPath = System.getProperty("user.dir")
            + "/SpringBoot_文件秒传-分片-断点续传/src/main/resources/static";

    @Autowired
    private ThreadLocal<RedisConnection> redisConnectionThreadLocal;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/resume")
    public String resume() {
        return "resume";
    }


    /**
     * 秒传逻辑：前端校验文件的 md5值，如果和已上传文件存入 Redis 中的 md5值一致，则表示是同一文件，无需再上传
     * @param fileHash 文件的md5值
     * @return 文件库存在，则返回 true；否则返回 false
     */
    @PostMapping("/file2/check")
    public @ResponseBody ResponseEntity<Boolean> check(@RequestParam("fileHash") String fileHash) {
        if (redisTemplate.opsForValue().get(fileHash) != null) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    /**
     * @param chunk 文件块
     * @param chunkIndex 该文件分片所在整个文件中的索引
     * @param chunkCheckSum 该文件分片的校验值
     * @param fileId 文件唯一标识
     */
    @PostMapping("/file2/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("chunk") MultipartFile chunk,
                                        @RequestParam("chunkIndex") Integer chunkIndex,
                                        @RequestParam("chunkCheckSum") String chunkCheckSum,
                                        @RequestParam("fileId") String fileId) throws Exception {
        if (StringUtils.isBlank(fileId) || StringUtils.isEmpty(fileId)) {
            fileId = UUID.randomUUID().toString();
        }
        String key = chunk.getOriginalFilename() + "&" + chunkIndex + "-" + fileId;
        // 获取文件块的字节
        byte[] chunkBytes = chunk.getBytes();
        // 获取文件块的 md5 校验值
        String actualChecksum = calculateHash(chunkBytes);
        // 比较前端上传的文件块校验值和实际计算出的校验值是否一致（避免文件在上传过程中被篡改或者网络波动导致文件丢失）
        if (!chunkCheckSum.equals(actualChecksum)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chunk checksum does not match");
        }
        // 将文件块存储到 Redis 中（这里简化操作：不存储数据库或者 OOS 或者 fastDFT）
        if (!redisTemplate.opsForHash().hasKey(key, String.valueOf(chunkIndex))) {
            redisTemplate.opsForHash().put(key, String.valueOf(chunkIndex), chunkBytes);
        }
        // 将文件块信息存储到 /resources/static 目录下
        File file = new File(uploadPath + "/" + key);
        if (!file.exists()) {
            chunk.transferTo(file);
        }
        return ResponseEntity.ok(fileId);
    }

    public static String calculateHash(byte[] fileChunk) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(fileChunk);
        byte[] hash = md.digest();
        ByteBuffer byteBuffer = ByteBuffer.wrap(hash);
        StringBuilder hexString = new StringBuilder();
        while (byteBuffer.hasRemaining()) {
            hexString.append(String.format("%02x", byteBuffer.get()));
        }
        return hexString.toString();
    }

    @PostMapping("/file2/merge")
    public ResponseEntity<?> mergeFile(@RequestParam("fileId") String fileId,
                                       @RequestParam("fileName") String fileName) {
        String key = FILE_UPLOAD_PREFIX + fileId;
        RedisConnection connection = redisConnectionThreadLocal.get();
        try {
            Map<byte[], byte[]> chunkMap = connection.hGetAll(key.getBytes());
            // Map chunkMap = redisTemplate.opsForHash().entries(key);
            if (chunkMap.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }
            Map<String, byte[]> hashMap = new HashMap<>();
            for (Map.Entry<byte[], byte[]> entry : chunkMap.entrySet()) {
                hashMap.put((new String(entry.getKey())), entry.getValue());
            }
            // 检测是否所有分片都上传了
            boolean allChunksUploaded = true;
            List<Integer> missingChunkIndexes = new ArrayList<>();
            for (int i = 0; i < hashMap.size(); i++) {
                if (!hashMap.containsKey(String.valueOf(i))) {
                    allChunksUploaded = false;
                    missingChunkIndexes.add(i);
                }
            }
            if (!allChunksUploaded) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(missingChunkIndexes);
            }
            File outputFile = new File(uploadPath, fileName);
            boolean flag = mergeChunks(hashMap, outputFile);
            Resource resource = resourceLoader.getResource("file:" + uploadPath + fileName);
            if (flag == true) {
                connection.del(key.getBytes());
                redisTemplate.delete(key);
                return ResponseEntity.ok().body(resource.getURI().toString());
            } else {
                return ResponseEntity.status(555).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private boolean mergeChunks(Map<String, byte[]> chunkMap, File destFile) {
        try (FileOutputStream outputStream = new FileOutputStream(destFile)) {
            // 将分片按照顺序合并
            for (int i = 0; i < chunkMap.size(); i++) {
                byte[] chunkBytes = chunkMap.get(String.valueOf(i));
                outputStream.write(chunkBytes);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}