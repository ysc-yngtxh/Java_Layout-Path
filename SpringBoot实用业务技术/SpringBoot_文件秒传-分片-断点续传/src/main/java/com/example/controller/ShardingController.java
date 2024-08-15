package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ShardingController {

    @Autowired
    private ResourceLoader resourceLoader;

    public static final String UPLOAD_PATH = System.getProperty("user.dir")
            + "/SpringBoot_文件秒传-分片-断点续传/src/main/resources/static";

    private Map<String, List<File>> chunksMap = new ConcurrentHashMap<>();

    @RequestMapping("/sharding")
    public String sharding() {
        return "sharding";
    }

    @PostMapping("/file/upload")
    public @ResponseBody void upload(@RequestParam int currentChunk,
                                     @RequestParam int totalChunks,
                                     @RequestParam MultipartFile chunk,
                                     @RequestParam String fileName) throws IOException {
        // 将分片保存到临时文件夹中
        String chunkName = chunk.getOriginalFilename() + "." + currentChunk;
        File chunkFile = new File(UPLOAD_PATH, chunkName);
        chunk.transferTo(chunkFile);
        // 记录分片上传状态
        List<File> chunkList = chunksMap.computeIfAbsent(fileName, k -> new ArrayList<>(totalChunks));
        chunkList.add(chunkFile);
    }

    @PostMapping("/file/merge")
    public @ResponseBody String merge(@RequestParam String fileName) throws IOException {
        // 获取所有分片，并按照分片的顺序将它们合并成一个文件
        List<File> chunkList = chunksMap.get(fileName);
        if (chunkList == null || chunkList.isEmpty()) {
            throw new RuntimeException("分片不存在");
        }
        File outputFile = new File(UPLOAD_PATH, fileName);
        try (FileChannel outChannel = new FileOutputStream(outputFile).getChannel()) {
            for (int i = 0; i < chunkList.size(); i++) {
                try (FileChannel inChannel = new FileInputStream(chunkList.get(i)).getChannel()) {
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                }
                chunkList.get(i).delete();
                // 删除分片
            }
        }
        chunksMap.remove(fileName);
        // 删除记录
        // 获取文件的访问URL
        Resource resource = resourceLoader.getResource("file:" + UPLOAD_PATH + fileName);
        // 由于是本地文件，所以开头是"file"，如果是服务器，请改成自己服务器前缀
        return resource.getURI().toString();
    }
}